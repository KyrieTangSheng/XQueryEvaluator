package main;

import main.antlr.XQueryLexer;
import main.antlr.XQueryParser;
import main.antlr.XQueryParser.XQueryFLWRContext;
import main.antlr.XQueryParser.XqueryContext;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.*;

// If your existing code for rewriting is in a separate file, just adapt as needed:
public class RewriteHelper {

    /**
     * Attempts to rewrite an original XQuery string using your classification + join approach.
     * Returns either the rewritten query or "" if no rewrite is needed/applicable.
     */
    public static String rewriteXQuery(String originalQuery) {
        try {
            // 1) Parse the input query with ANTLR
            XQueryLexer lexer = new XQueryLexer(CharStreams.fromString(originalQuery));
            XQueryParser parser = new XQueryParser(new CommonTokenStream(lexer));

            // This should parse the entire XQuery, e.g. starting at xquery rule
            XQueryParser.XqueryContext tree = parser.xquery();

            // Check if this is a FLWR expression
            if (tree instanceof XQueryParser.XQueryFLWRContext) {
                XQueryParser.XQueryFLWRContext flwrContext = (XQueryParser.XQueryFLWRContext) tree;
                return rewriteFLWR(flwrContext);
            }

            // If not a FLWR expression, return empty string (no rewrite)
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    private static String rewriteFLWR(XQueryParser.XQueryFLWRContext ctx) {
        
        // 1. Classify the parent-child relationships

        int numForClauses = ctx.forClause().var().size();
        List<HashSet<String>> classification = new ArrayList<>();

        for (int i = 0; i < numForClauses; i++) {
            String key = ctx.forClause().var(i).getText();
            String parent = ctx.forClause().xquery(i).getText().split("/")[0];
            boolean found = false;
            for(HashSet<String> curSet : classification) {
                if(curSet.contains(parent)) {
                    curSet.add(key);
                    found = true;
                    break;
                }
            }
            if(!found) {
                HashSet<String> newSet = new HashSet<>();
                newSet.add(key);
                classification.add(newSet);
            }
        }

        // 2. Parse the Where clause condition


        String whereClause = ctx.whereClause().cond().getText();
        String[] condParts = whereClause.split("and");
        List<List<String>> conditions = parseCondParts(condParts);

        // Create parallel array to track if each condition is local
        boolean[] isLocal = new boolean[conditions.size()];
        
        // Check each condition
        for (int i = 0; i < conditions.size(); i++) {
            List<String> condition = conditions.get(i);
            String left = condition.get(0);
            String right = condition.get(1);
            
            // Add $ prefix for checking against classification
            String leftVar = "$" + left;
            String rightVar = "$" + right;
            
            // Find which sets contain left and right
            int leftSetIndex = -1;
            int rightSetIndex = -1;
            
            for (int j = 0; j < classification.size(); j++) {
                if (classification.get(j).contains(leftVar)) {
                    leftSetIndex = j;
                }
                if (classification.get(j).contains(rightVar)) {
                    rightSetIndex = j;
                }
            }
            
            
            // If both variables are in classification but in different sets -> non-local
            if (leftSetIndex != -1 && rightSetIndex != -1 && leftSetIndex != rightSetIndex) {
                isLocal[i] = false;
            } else {
                isLocal[i] = true;
            }
        }

        if (classification.size() == 1) {
            // No join needed; just build a single subquery or do a normal FLWR
            return "";  // or some indication "no rewrite"
        } else if (classification.size() == 2) {
            // The code you already have for 2-group join
            String result = rewriteTwoGroupFLWR(ctx, classification, conditions, isLocal);
            return result;
        } else {
            String result = rewriteMultiGroupFLWR(ctx, classification, conditions, isLocal);
            return result;
        }
    }


    private static String rewriteTwoGroupFLWR(XQueryParser.XQueryFLWRContext ctx,
                                          List<HashSet<String>> classification,
                                          List<List<String>> conditions,
                                          boolean[] isLocal) {
               // 3. Construct the Subquery
            // We'll handle only 2 groups for this example:
            Set<String> group0 = classification.get(0);
            Set<String> group1 = classification.get(1);
    

            // 2) Build subquery for group0
            String subq0 = buildSubquery(group0, ctx, conditions, isLocal);
    
            // 3) Build subquery for group1
            String subq1 = buildSubquery(group1, ctx, conditions, isLocal);
    
    
            // 4) Build join key arrays
            //    We want cross-group conditions: if left var is in group0 and right var is in group1, or vice versa.
            List<String> leftKeys = new ArrayList<>();
            List<String> rightKeys = new ArrayList<>();
            for (List<String> cond : conditions) {
                // cond: [left, right]
                String left = cond.get(0);
                String right = cond.get(1);
    
                boolean leftInG0 = group0.contains("$" + left) || group0.contains("$" + left.trim()); 
                boolean rightInG0 = group0.contains("$" + right) || group0.contains("$" + right.trim());
                boolean leftInG1 = group1.contains("$" + left) || group1.contains("$" + left.trim());
                boolean rightInG1 = group1.contains("$" + right) || group1.contains("$" + right.trim());
    
                // If exactly one side is in group0 and the other in group1, it's cross-group
                if ((leftInG0 && rightInG1) || (rightInG0 && leftInG1)) {
                    // e.g. sp eq s
                    // We want something like [s], [sp]
                    if (leftInG0) {
                        // left var is group0, right var is group1
                        leftKeys.add(left);   // e.g. "s"
                        rightKeys.add(right); // e.g. "sp"
                    } else {
                        leftKeys.add(right);
                        rightKeys.add(left);
                    }
                }
            }
    
            // 5) Assemble the final join(...) expression
            StringBuilder sb = new StringBuilder();
            sb.append("for $tuple in join(\n");
            sb.append("(\n").append(subq0).append("\n),\n");
            sb.append("(\n").append(subq1).append("\n),\n");
    
            // join keys
            sb.append("  [");
            for (int i = 0; i < leftKeys.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(leftKeys.get(i));
            }
            sb.append("], [");
            for (int i = 0; i < rightKeys.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(rightKeys.get(i));
            }
            sb.append("]\n)\n");

            // 6) return*
            String returnClause = ctx.returnClause().getText();
            returnClause = modifyReturnClause(returnClause);

            sb.append(returnClause);
            return sb.toString();
    }
    private static String rewriteMultiGroupFLWR(XQueryParser.XQueryFLWRContext ctx,
                                           List<HashSet<String>> classification,
                                           List<List<String>> conditions,
                                           boolean[] isLocal) 
    {

        // We'll chain the groups from 0..(n-1)
        int n = classification.size();
        if (n < 2) {
            // if there's only 1 group or none, no multi-group logic needed
            return "";
        }

        // 1) Build subquery for the first group:
        String currentJoin = buildSubquery(classification.get(0), ctx, conditions, isLocal);

        // We'll maintain a set of all variables from the groups that have been merged so far
        // because we need to detect cross-group conditions that refer to these "so-far" variables
        Set<String> mergedSoFar = new HashSet<>(classification.get(0));

        // 2) For each subsequent group, chain-join:
        for (int i = 1; i < n; i++) {
            // Build subquery for group i
            Set<String> groupVars = classification.get(i);
            String nextSubquery = buildSubquery(groupVars, ctx, conditions, isLocal);

            // We'll find cross-group keys referencing (mergedSoFar) vs. groupVars
            List<String> leftKeys = new ArrayList<>();
            List<String> rightKeys = new ArrayList<>();

            // For each condition, if it's cross-group (i.e. one side in mergedSoFar, other in groupVars),
            // that becomes our join key
            for (int cIndex = 0; cIndex < conditions.size(); cIndex++) {
                // Must be a non-local condition or it might have two distinct sets
                if (!isLocal[cIndex]) {
                    String left = conditions.get(cIndex).get(0);   // e.g. "tb"
                    String right = conditions.get(cIndex).get(1);  // e.g. "ta"
                    String leftVar = "$" + left;
                    String rightVar = "$" + right;

                    boolean leftInSoFar = mergedSoFar.contains(leftVar);
                    boolean rightInSoFar = mergedSoFar.contains(rightVar);
                    boolean leftInGroup = groupVars.contains(leftVar);
                    boolean rightInGroup = groupVars.contains(rightVar);

                    // We only want the case where exactly one side is in mergedSoFar and the other side is in groupVars
                    if ((leftInSoFar && rightInGroup) || (rightInSoFar && leftInGroup)) {
                        if (leftInSoFar) {
                            // left belongs to so-far, right belongs to new group
                            leftKeys.add(left);
                            rightKeys.add(right);
                        } else {
                            // right belongs to so-far, left belongs to new group
                            leftKeys.add(right);
                            rightKeys.add(left);
                        }
                    }
                }
            }

            // Now form a new join(...) that merges currentJoin with nextSubquery:
            StringBuilder sb = new StringBuilder();
            sb.append("join(\n");
            sb.append("  (\n").append(currentJoin).append("\n  ),\n");
            sb.append("  (\n").append(nextSubquery).append("\n  ),\n");
            // Print the arrays
            sb.append("  [");
            for (int k = 0; k < leftKeys.size(); k++) {
                if (k > 0) sb.append(",");
                sb.append(leftKeys.get(k));
            }
            sb.append("], [");
            for (int k = 0; k < rightKeys.size(); k++) {
                if (k > 0) sb.append(",");
                sb.append(rightKeys.get(k));
            }
            sb.append("]\n)");
            currentJoin = sb.toString();

            // Merge this group's variables into "mergedSoFar"
            mergedSoFar.addAll(groupVars);
        }

        // 3) Finally, wrap the chain-of-joins in: for $tuple in ... return ...
        StringBuilder finalQuery = new StringBuilder();
        finalQuery.append("for $tuple in ").append(currentJoin).append("\n");

        // Rewrite the original return clause to reference $tuple
        String returnClause = ctx.returnClause().getText();
        returnClause = modifyReturnClause(returnClause);
        finalQuery.append(returnClause);


        return finalQuery.toString();
    }


    private static String modifyReturnClause(String returnClause) {
        // Remove 'return' prefix if present
        returnClause = returnClause.replaceFirst("^return", "").trim();
        
        // Replace variable references with tuple path accesses
        returnClause = returnClause.replaceAll(
            "\\$([a-zA-Z0-9]+)", 
            "\\$tuple/$1/*"
        );
        
        // Handle nested path expressions
        returnClause = returnClause.replaceAll(
            "\\$tuple/([a-zA-Z0-9]+)/\\*/([a-zA-Z0-9]+)", 
            "\\$tuple/$1/*/$2"
        );
        
        return "return " + returnClause;
    }


    private static String buildSubquery(Set<String> groupVars,
                               XQueryParser.XQueryFLWRContext ctx,
                               List<List<String>> conditions,
                               boolean[] isLocal) {
    // We'll gather lines like "for $s in doc(...)//SPEAKER, $stxt in $s/text() ..."
    // then a local "where ..." if there's a variable-literal condition
    // finally "return <tuple> ..."

    StringBuilder sb = new StringBuilder();

    // 1) for ... in ...
    boolean first = true;
    int nFor = ctx.forClause().var().size();
    
    // Create a list to maintain the order of variables as they appear in the original query
    List<String> orderedVarsInQuery = new ArrayList<>();
    
    for (int i = 0; i < nFor; i++) {
        String varName = ctx.forClause().var(i).getText();  // e.g. "$s"
        if (groupVars.contains(varName)) {
            // Add to our ordered list
            orderedVarsInQuery.add(varName);
            
            String xqueryText = ctx.forClause().xquery(i).getText();
            
            if (first) {
                sb.append("for ").append(varName).append(" in ")
                  .append(xqueryText);
                first = false;
            } else {
                sb.append(",\n    ").append(varName).append(" in ")
                  .append(xqueryText);
            }
        }
    }
    sb.append("\n");

    // 2) Local conditions?
    List<String> localConds = new ArrayList<>();
    // Check all conditions for local ones that belong to this group
    for (int i = 0; i < conditions.size(); i++) {
        if (isLocal[i]) {
            String left = conditions.get(i).get(0);
            String right = conditions.get(i).get(1);
            String leftVar = "$" + left;
            
            // Only add if the left variable is in this group
            if (groupVars.contains(leftVar)) {
                if (right.matches("^[A-Za-z0-9]+$")) {
                    // Right is a literal
                    localConds.add(leftVar + " eq \"" + right + "\"");
                } else {
                    // Right is a variable
                    String rightVar = "$" + right;
                    if (groupVars.contains(rightVar)) {
                        localConds.add(leftVar + " eq " + rightVar);
                    }
                }
            }
        }
    }

    // Add where clause if we have local conditions
    if (!localConds.isEmpty()) {
        sb.append("where ");
        sb.append(String.join(" and ", localConds));
        sb.append("\n");
    }

    // 3) return <tuple> ...
    sb.append("return <tuple>");

    // Use the ordered list we created earlier to maintain the order of variables
    int count = 0;
    for (String var : orderedVarsInQuery) {
        if (count++ > 0) sb.append(", ");
        String bare = var.substring(1); // remove leading '$'
        sb.append("<").append(bare).append(">{")
          .append(var).append("}</").append(bare).append(">");
    }
    sb.append("</tuple>");

    return sb.toString();
}


    

    private static List<List<String>> parseCondParts(String[] condParts) {
        List<List<String>> result = new ArrayList<>();
        
        for (String part : condParts) {
            // Clean up the part
            part = part.trim();
            
            // Handle both "eq" and "=" conditions
            String[] sides;
            if (part.contains("eq")) {
                sides = part.split("eq");
            } else if (part.contains("=")) {
                sides = part.split("=");
            } else {
                continue; // Skip if no comparison operator found
            }
            
            if (sides.length == 2) {
                String left = sides[0].trim();
                String right = sides[1].trim();
                
                // Remove $ from variables if present
                left = left.replace("$", "");
                right = right.replace("$", "");
                
                // Remove quotes if present
                right = right.replaceAll("\"", "");
                
                // Add as a pair
                result.add(Arrays.asList(left, right));
            }
        }

        return result;
    }

}
