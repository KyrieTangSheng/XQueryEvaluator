package main;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import main.antlr.XQueryBaseVisitor;
import main.antlr.XQueryParser;
import main.antlr.XPathLexer;
import main.antlr.XPathParser;

public class XQueryEvaluator extends XQueryBaseVisitor<List<Node>> {

    // The document used to create new nodes (e.g. for tag constructors)
    private Document outputDoc;
    // A stack-based environment for variable bindings
    private Deque<Map<String, List<Node>>> env;
    // Delegate evaluator for absolute/relative XPath expressions
    private XPathEvaluator xpath;

    public XQueryEvaluator() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.outputDoc = builder.newDocument();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create output document", e);
        }
        this.env = new ArrayDeque<>();
        // Push an initial (empty) scope
        env.push(new HashMap<>());

        // Create a delegate XPath evaluator
        this.xpath = new XPathEvaluator();
    }

    // ---------------------------------------------------------
    //  XQuery Expression Visitors
    // ---------------------------------------------------------

    /**
     * xquery: var  # XQueryVariable
     */
    @Override
    public List<Node> visitXQueryVariable(XQueryParser.XQueryVariableContext ctx) {
        String varName = ctx.var().getText();  // e.g. "$x"
        // Look up in env stack (from top to bottom)
        for (Map<String, List<Node>> scope : env) {
            if (scope.containsKey(varName)) {
                List<Node> nodes = scope.get(varName);
                return nodes;
            }
        }
        throw new RuntimeException("Undefined variable: " + varName);
    }

    /**
     * xquery: stringConstant  # XQueryConstant
     */
    @Override
    public List<Node> visitXQueryConstant(XQueryParser.XQueryConstantContext ctx) {
        // stringConstant -> StringConstant from XPath grammar
        String quoted = ctx.stringConstant().getText();  // e.g. "\"Hello\""
        // Strip surrounding quotes
        String unquoted = quoted.substring(1, quoted.length() - 1);
        // Create text node in our output document
        Text textNode = outputDoc.createTextNode(unquoted);
        return Collections.singletonList(textNode);
    }

    /**
     * xquery: absolutePath  # XQueryAbsolutePath
     *
     * Here, the "absolutePath" sub-tree actually belongs to the XPath grammar,
     * specifically:  XPathParser.AbsolutePathContext
     */
    @Override
    public List<Node> visitXQueryAbsolutePath(XQueryParser.XQueryAbsolutePathContext ctx) {
        // 1) Extract the text from the sub-tree, e.g. "doc(\"file.xml\")/PLAY"
        String pathText = ctx.getText();

        // 2) Parse that text using your XPath grammar
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(pathText));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        XPathParser.XpathContext xpathTree = parser.xpath();

        // 3) Evaluate it with your XPathEvaluator
        return xpath.visit(xpathTree);
    }


    /**
     * xquery: '(' xquery ')'  # XQueryParentheses
     */
    @Override
    public List<Node> visitXQueryParentheses(XQueryParser.XQueryParenthesesContext ctx) {
        List<Node> inside = visit(ctx.xquery());
        return (inside != null) ? inside : new ArrayList<>();
    }

    /**
     * xquery: xquery ',' xquery  # XQueryConcat
     */
    @Override
    public List<Node> visitXQueryConcat(XQueryParser.XQueryConcatContext ctx) {
        List<Node> left = visit(ctx.xquery(0));
        List<Node> right = visit(ctx.xquery(1));
        
        List<Node> result = new ArrayList<>();
        if (left != null) result.addAll(left);
        if (right != null) result.addAll(right);
        return result;
    }

    /**
     * xquery: xquery '/' relativePath  # XQueryPath
     */
    @Override
    public List<Node> visitXQueryPath(XQueryParser.XQueryPathContext ctx) {
        // Evaluate the XQuery expression on the left to get context nodes
        List<Node> contextNodes = visit(ctx.xquery());
        if (contextNodes == null) contextNodes = new ArrayList<>();

        String relPathText = ctx.relativePath().getText();
        
        // Special handling for text() function
        if (relPathText.equals("text()")) {
            List<Node> result = new ArrayList<>();
            for (Node node : contextNodes) {
                NodeList children = node.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        result.add(child);
                    }
                }
            }
            return result;
        }

        // Let the XPath evaluator handle the relative path
        xpath.setCurrentContext(contextNodes);
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(relPathText));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        XPathParser.XpathContext rpTree = parser.xpath();

        return xpath.visit(rpTree);
    }

    /**
     * xquery: xquery '//' relativePath  # XQueryDoubleSlash
     */
    @Override
    public List<Node> visitXQueryDoubleSlash(XQueryParser.XQueryDoubleSlashContext ctx) {
        // Evaluate the left side XQuery
        List<Node> leftNodes = visit(ctx.xquery());
        if (leftNodes == null) leftNodes = new ArrayList<>();

        // Set context for XPath evaluation
        xpath.setCurrentContext(leftNodes);

        // Get all descendants first
        List<Node> descendants = new ArrayList<>();
        for (Node node : leftNodes) {
            descendants.add(node); // Include self
            getDescendants(node, descendants);
        }
        xpath.setCurrentContext(descendants);

        // Now evaluate the relative path on the descendants
        String relPathText = ctx.relativePath().getText();
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(relPathText));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        XPathParser.XpathContext rpTree = parser.xpath();

        return xpath.visit(rpTree);
    }

    /**
     * xquery: '<' tagName '>' '{' xquery '}' '</' tagName '>'  # XQueryTag
     */
    @Override
    public List<Node> visitXQueryTag(XQueryParser.XQueryTagContext ctx) {
        String tagName = ctx.tagName(0).getText();
        
        Element newElement = outputDoc.createElement(tagName);
        List<Node> contentNodes = visit(ctx.xquery());
        
        if (contentNodes != null) {
            for (Node contentNode : contentNodes) {
                Node importedNode = contentNode;
                if (contentNode.getOwnerDocument() != outputDoc) {
                    importedNode = outputDoc.importNode(contentNode, true);
                }
                // Preserve element structure
                if (contentNode.getNodeType() == Node.ELEMENT_NODE) {
                    newElement.appendChild(importedNode);
                } else {
                    newElement.appendChild(importedNode);
                }
            }
        }
        
        return Collections.singletonList(newElement);
    }

    /**
     * xquery: '<' tagName '>' xquery '</' tagName '>'  # XQueryDirectTag
     */
    @Override
    public List<Node> visitXQueryDirectTag(XQueryParser.XQueryDirectTagContext ctx) {
        String tagName = ctx.tagName(0).getText();
        
        Element newElement = outputDoc.createElement(tagName);
        List<Node> contentNodes = visit(ctx.xquery());
        
        if (contentNodes != null) {
            for (Node contentNode : contentNodes) {
                Node importedNode = contentNode;
                if (contentNode.getOwnerDocument() != outputDoc) {
                    importedNode = outputDoc.importNode(contentNode, true);
                }
                newElement.appendChild(importedNode);
            }
        }
        
        return Collections.singletonList(newElement);
    }

   /**
     * xquery: forClause letClause? whereClause? returnClause  # XQueryFLWR
     */
    @Override
    public List<Node> visitXQueryFLWR(XQueryParser.XQueryFLWRContext ctx) {

        System.out.println("\nDebug FLWR Expression:");
    
        // 1) Attempt to build a rewritten query string based on the classification approach
        //    that you described in your code snippet.
        String rewrittenQuery = rewriteFLWR(ctx);
    
        List<Node> finalResults;
        if (rewrittenQuery.isEmpty()) {
            // If no rewrite was needed or possible, just do the normal FLWR evaluation
            finalResults = evaluatePlainFLWR(ctx);
        } else {
            // We got a join-based XQuery string. We re-parse and evaluate it:
            System.out.println("Rewritten XQuery:\n" + rewrittenQuery);
    
            // 1) Parse the new query
            //XQueryParser.XqueryContext newTree = parseXQueryString(rewrittenQuery);
    
            // 2) Evaluate the new parse tree by visiting it
            //finalResults = visit(newTree);
            finalResults = new ArrayList<>();
        }
    
        return finalResults;
    }

    private String rewriteFLWR(XQueryParser.XQueryFLWRContext ctx) {
        System.out.println("Rewriting FLWR expression...");
        
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
        System.out.println("Classification: " + classification);

        // 2. Parse the Where clause condition


        String whereClause = ctx.whereClause().cond().getText();
        String[] condParts = whereClause.split("and");
        List<List<String>> conditions = parseCondParts(condParts);
        System.out.println("Conditions: " + conditions);

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
            System.out.println("Rewrite 1 Group FLWR");
            // No join needed; just build a single subquery or do a normal FLWR
            return "";  // or some indication "no rewrite"
        } else if (classification.size() == 2) {
            // The code you already have for 2-group join
            System.out.println("Rewrite 2 Group FLWR");
            String result = rewriteTwoGroupFLWR(ctx, classification, conditions, isLocal);
            System.out.println("Result: " +result);
            return "";
        } else {
            System.out.println("Rewrite Multi Group FLWR");
            String result = rewriteMultiGroupFLWR(ctx, classification, conditions, isLocal);
            System.out.println("Result: " +result);
            return "";
        }
    }


    private String rewriteTwoGroupFLWR(XQueryParser.XQueryFLWRContext ctx, List<HashSet<String>> classification, List<List<String>> conditions, boolean[] isLocal) {
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

            // 7) Done
            System.out.println("Final Query: " + sb.toString());

            return sb.toString();
    }
    private String rewriteMultiGroupFLWR(XQueryParser.XQueryFLWRContext ctx,
                                     List<HashSet<String>> classification,
                                     List<List<String>> conditions,
                                     boolean[] isLocal) 
    {
        System.out.println("Rewrite Multi Group FLWR");

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

        System.out.println("Final Multi-Group Query:\n" + finalQuery);

        return finalQuery.toString();
    }


    private String modifyReturnClause(String returnClause) {
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


    private String buildSubquery(Set<String> groupVars,
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
        for (int i = 0; i < nFor; i++) {
            String varName = ctx.forClause().var(i).getText();  // e.g. "$s"
            if (groupVars.contains(varName)) {
                if (first) {
                    sb.append("  for ").append(varName).append(" in ")
                    .append(ctx.forClause().xquery(i).getText());
                    first = false;
                } else {
                    sb.append(",\n      ").append(varName).append(" in ")
                    .append(ctx.forClause().xquery(i).getText());
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
            sb.append("  where ");
            sb.append(String.join(" and ", localConds));
            sb.append("\n");
        }
    

        // 3) return <tuple> ...
        sb.append("  return <tuple>");

        // For each variable in this group, produce <varName>{ $varName }</varName>
        List<String> orderedVars = new ArrayList<>(groupVars);
        // Collections.reverse(orderedVars);  // Reverse the order
        
        int count = 0;
        for (String var : orderedVars) {
            if (count++ > 0) sb.append(",");
            String bare = var.substring(1); // remove leading '$'
            sb.append(" <").append(bare).append(">{")
            .append(var).append("}</").append(bare).append(">");
        }
        sb.append("</tuple>");

        return sb.toString();
    }


    

    private List<List<String>> parseCondParts(String[] condParts) {
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


    private List<Node> evaluatePlainFLWR(XQueryParser.XQueryFLWRContext ctx) {

        List<Map<String, List<Node>>> forBindings = evaluateForClause(ctx.forClause());
        List<Node> finalResults = new ArrayList<>();

        // Save the current (outer) environment
        Map<String, List<Node>> savedEnv = new HashMap<>(env.peek());

        try {
            // For each binding from the for-clause
            for (Map<String, List<Node>> binding : forBindings) {
                // Create a new scope on top of saved environment
                Map<String, List<Node>> newScope = new HashMap<>(savedEnv);
                newScope.putAll(binding);
                env.push(newScope);

                try {
                    // If there's a let-clause, evaluate it
                    if (ctx.letClause() != null) {
                        Map<String, List<Node>> letBindings = evaluateLetClause(ctx.letClause());
                        env.peek().putAll(letBindings);
                    }

                    // If there's a where-clause, check condition
                    boolean pass = true;
                    if (ctx.whereClause() != null) {
                        pass = evaluateCondition(ctx.whereClause().cond());
                    }

                    // If condition passes, evaluate the return-clause
                    if (pass) {
                        List<Node> ret = visit(ctx.returnClause().xquery());
                        if (ret != null) {
                            finalResults.addAll(ret);
                        }
                    }
                } finally {
                    // Pop this iteration's scope
                    env.pop();
                }
            }
        } finally {
            // Restore the original env scope
            env.peek().clear();
            env.peek().putAll(savedEnv);
        }

        return finalResults;
    }


    /**
     * xquery: letClause xquery  # XQueryLet
     */
    @Override
    public List<Node> visitXQueryLet(XQueryParser.XQueryLetContext ctx) {
        // Evaluate let-bindings
        Map<String, List<Node>> letBindings = evaluateLetClause(ctx.letClause());
        // Create a new scope that includes those bindings
        Map<String, List<Node>> newScope = new HashMap<>(env.peek());
        newScope.putAll(letBindings);

        env.push(newScope);
        try {
            // Then evaluate the following xquery
            List<Node> result = visit(ctx.xquery());
            return (result != null) ? result : new ArrayList<>();
        } finally {
            env.pop();
        }
    }


    // xquery: 'join' '(' xquery ',' xquery ',' '[' Name ']' ',' '[' Name ']' ')' # XQueryJoin
    @Override
    public List<Node> visitXQueryJoin(XQueryParser.XQueryJoinContext ctx) {
        // Evaluate the two subqueries
        List<Node> leftNodes = visit(ctx.xquery(0));
        List<Node> rightNodes = visit(ctx.xquery(1));
        
        // Extract join attributes
        String leftAttr = ctx.Name(0).getText();
        String rightAttr = ctx.Name(1).getText();
        
        // Create tuple sets
        TupleSet leftSet = new TupleSet(outputDoc);
        for (Node node : leftNodes) {
            leftSet.addTuple(node);
        }
        
        TupleSet rightSet = new TupleSet(outputDoc);
        for (Node node : rightNodes) {
            rightSet.addTuple(node);
        }
        
        // Perform hash join
        TupleSet result = leftSet.hashJoin(rightSet, 
                                          Collections.singletonList(leftAttr), 
                                          Collections.singletonList(rightAttr));
        
        return result.getTuples();
    }
        

    // ---------------------------------------------------------
    //   Helper Methods for FLWR
    // ---------------------------------------------------------

    /**
     * Evaluate a forClause, returning a list of variable-binding maps.
     * Each map is { $varName -> single node } for one iteration.
     */
    private List<Map<String, List<Node>>> evaluateForClause(XQueryParser.ForClauseContext ctx) {
        List<Map<String, List<Node>>> currentBindings = new ArrayList<>();
        currentBindings.add(new HashMap<>());

        int nPairs = ctx.var().size();
        
        for (int i = 0; i < nPairs; i++) {
            String varName = ctx.var(i).getText();
            
            List<Map<String, List<Node>>> newBindings = new ArrayList<>();
            for (Map<String, List<Node>> binding : currentBindings) {
                Map<String, List<Node>> savedScope = new HashMap<>(env.peek());
                Map<String, List<Node>> mergedScope = new HashMap<>(savedScope);
                mergedScope.putAll(binding);
                env.push(mergedScope);

                List<Node> nodes = visit(ctx.xquery(i));
                
                env.pop();

                for (Node n : nodes) {
                    Map<String, List<Node>> extended = new HashMap<>(binding);
                    extended.put(varName, Collections.singletonList(n));
                    newBindings.add(extended);
                }
            }
            currentBindings = newBindings;
        }
        
        return currentBindings;
    }

    /**
     * Evaluate a letClause -> a map of { varName -> list of nodes }.
     */
    private Map<String, List<Node>> evaluateLetClause(XQueryParser.LetClauseContext ctx) {
        Map<String, List<Node>> bindings = new HashMap<>();
        int count = ctx.var().size();
        for (int i = 0; i < count; i++) {
            String varName = ctx.var(i).getText();
            List<Node> val = visit(ctx.xquery(i));
            if (val == null) val = new ArrayList<>();
            bindings.put(varName, val);
        }
        return bindings;
    }

    // ---------------------------------------------------------
    //   Helpers for whereClause conditions
    // ---------------------------------------------------------

    /**
     * Evaluate any cond expression into a boolean.
     */
    private boolean evaluateCondition(XQueryParser.CondContext ctx) {
        if (ctx instanceof XQueryParser.XQueryValueEqualContext) {
            // xquery ('=' | 'eq') xquery
            XQueryParser.XQueryValueEqualContext c = (XQueryParser.XQueryValueEqualContext) ctx;
            List<Node> left = visit(c.xquery(0));
            List<Node> right = visit(c.xquery(1));
            if (left == null) left = new ArrayList<>();
            if (right == null) right = new ArrayList<>();
            return nodesValueEqual(left, right);

        } else if (ctx instanceof XQueryParser.XQueryIdentityEqualContext) {
            // xquery ('==' | 'is') xquery
            XQueryParser.XQueryIdentityEqualContext c = (XQueryParser.XQueryIdentityEqualContext) ctx;
            List<Node> left = visit(c.xquery(0));
            List<Node> right = visit(c.xquery(1));
            if (left == null) left = new ArrayList<>();
            if (right == null) right = new ArrayList<>();
            return nodesIdentityEqual(left, right);

        } else if (ctx instanceof XQueryParser.XQueryEmptyContext) {
            // empty(xquery)
            XQueryParser.XQueryEmptyContext c = (XQueryParser.XQueryEmptyContext) ctx;
            List<Node> check = visit(c.xquery());
            return (check == null || check.isEmpty());

        } else if (ctx instanceof XQueryParser.XQuerySomeContext) {
            // some $v in XQ, ... satisfies cond
            XQueryParser.XQuerySomeContext c = (XQueryParser.XQuerySomeContext) ctx;
            List<Map<String, List<Node>>> bindings = new ArrayList<>();
            bindings.add(new HashMap<>());

            // Build up all possible bindings from 'some' variables
            int nVars = c.var().size();
            for (int i = 0; i < nVars; i++) {
                String vName = c.var(i).getText();
                List<Node> possibleVals = visit(c.xquery(i));
                if (possibleVals == null) possibleVals = new ArrayList<>();

                List<Map<String, List<Node>>> newBindings = new ArrayList<>();
                for (Map<String, List<Node>> b : bindings) {
                    for (Node val : possibleVals) {
                        Map<String, List<Node>> extended = new HashMap<>(b);
                        extended.put(vName, Collections.singletonList(val));
                        newBindings.add(extended);
                    }
                }
                bindings = newBindings;
            }
            // Condition is satisfied if at least one binding yields true
            for (Map<String, List<Node>> b : bindings) {
                // Temporarily push a new scope with these bindings
                Map<String, List<Node>> saved = new HashMap<>(env.peek());
                Map<String, List<Node>> newScope = new HashMap<>(saved);
                newScope.putAll(b);
                env.push(newScope);
                try {
                    boolean sat = evaluateCondition(c.cond());
                    if (sat) {
                        return true;
                    }
                } finally {
                    env.pop();
                }
            }
            return false;

        } else if (ctx instanceof XQueryParser.XQueryCondParenthesesContext) {
            // (cond)
            return evaluateCondition(((XQueryParser.XQueryCondParenthesesContext) ctx).cond());

        } else if (ctx instanceof XQueryParser.XQueryCondAndContext) {
            // cond AND cond
            XQueryParser.XQueryCondAndContext c = (XQueryParser.XQueryCondAndContext) ctx;
            return evaluateCondition(c.cond(0)) && evaluateCondition(c.cond(1));

        } else if (ctx instanceof XQueryParser.XQueryCondOrContext) {
            // cond OR cond
            XQueryParser.XQueryCondOrContext c = (XQueryParser.XQueryCondOrContext) ctx;
            return evaluateCondition(c.cond(0)) || evaluateCondition(c.cond(1));

        } else if (ctx instanceof XQueryParser.XQueryCondNotContext) {
            // not cond
            XQueryParser.XQueryCondNotContext c = (XQueryParser.XQueryCondNotContext) ctx;
            return !evaluateCondition(c.cond());
        }

        // Default fallback
        return false;
    }

    /**
     * Value equality: check if each corresponding node's textContent matches.
     * (Very simplified notion of 'value equal')
     */
    private boolean nodesValueEqual(List<Node> left, List<Node> right) {
        // A common approach is: "some l in left, some r in right, l.textContent == r.textContent"
        // or you might want to compare them pairwise.  Adapt as needed.
        for (Node ln : left) {
            for (Node rn : right) {
                if (ln.getTextContent().equals(rn.getTextContent())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Identity equality: check if there is a pair of nodes that are the same DOM node.
     */
    private boolean nodesIdentityEqual(List<Node> left, List<Node> right) {
        for (Node ln : left) {
            for (Node rn : right) {
                // isSameNode() is a DOM method
                if (ln.isSameNode(rn)) {
                    return true;
                }
            }
        }
        return false;
    }

    // ---------------------------------------------------------
    //    Utility to get descendants
    // ---------------------------------------------------------
    private void getDescendants(Node node, List<Node> result) {
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                result.add(child);
                getDescendants(child, result);
            }
        }
    }


    // ---------------------------------------------------------
    //    Default visitor aggregator overrides
    // ---------------------------------------------------------
    @Override
    protected List<Node> defaultResult() {
        return new ArrayList<>();
    }

    @Override
    protected List<Node> aggregateResult(List<Node> aggregate, List<Node> nextResult) {
        if (aggregate == null) {
            aggregate = new ArrayList<>();
        }
        if (nextResult != null) {
            aggregate.addAll(nextResult);
        }
        return aggregate;
    }
}
