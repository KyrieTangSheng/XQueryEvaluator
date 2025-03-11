package main;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import main.antlr.*;
import javax.xml.parsers.ParserConfigurationException;


public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java Main <input-xml> <input-query> <output-xml>");
            System.exit(1);
        }
        
        String inputXmlPath = args[0];
        System.out.println("Input XML path: " + inputXmlPath);  
        String queryFilePath = args[1];
        System.out.println("Query file path: " + queryFilePath);
        String outputXmlPath = args[2];
        System.out.println("Output XML path: " + outputXmlPath);
        
        try {
            // Read query from file and trim whitespace
            String query = new String(Files.readAllBytes(Paths.get(queryFilePath))).trim();
            
            // Evaluate query
            LinkedList<Node> result = evaluateQuery(query, inputXmlPath);
            
            // Create a new document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            // Use first result node as document root
            if (result != null && !result.isEmpty()) {
                Node importedNode = doc.importNode(result.getFirst(), true);
                doc.appendChild(importedNode);
            }
            
            // Write the result document
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(outputXmlPath));
            transformer.transform(source, streamResult);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static LinkedList<Node> evaluateQuery(String query, String inputXmlPath) {
        boolean isXQuery = isXQuery(query);
        query = query.replaceAll("[“”]", "\""); 
        
        if (!isXQuery && !query.startsWith("doc(")) {
            File xmlFile = new File(inputXmlPath);
            System.out.println("File exists: " + xmlFile.exists());
            System.out.println("Absolute path: " + xmlFile.getAbsolutePath());
            String xmlURL = xmlFile.toURI().toString();
            System.out.println("XML URL: " + xmlURL);
            query = "doc(\"" + xmlURL + "\")" + query;
        }
        
        // For XQuery queries, update any document() call to use absolute URLs.
        if (isXQuery) {
            query = fixDocumentCalls(query, inputXmlPath);
            System.out.println("Modified XQuery: " + query);
            XQueryEvaluator evaluator = new XQueryEvaluator();
            LinkedList<Node> result = evaluateXQuery(query, evaluator);
            
            // Check if the query contains a JOIN operation
            boolean containsJoin = query.contains("join(");
            if (containsJoin) {
                return wrapInResultElement(result);
            }
            return result;
        } else {
            query = modifyXPath(query, inputXmlPath);
            System.out.println("Modified XPath: " + query);
            XPathEvaluator evaluator = new XPathEvaluator();
            return evaluateXPath(query, evaluator);
        }
    }

    private static String modifyXPath(String query, String inputXmlPath) {
        // Convert the input XML path to an absolute file URL
        System.out.println("Query in modifyXPath: " + query);
        int closeQuote = 0;
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i); // Get character at index i
            if (c == ')') {
                closeQuote = i;
                break;
            }
        }

        String result = query.substring(closeQuote + 1);

        String absolutePath = new File(inputXmlPath).toURI().toString();
        return "doc(\"" + absolutePath + "\")" + result;
    }

    
    // Simple heuristic to decide if the query is XQuery.
    private static boolean isXQuery(String query) {
        String trimmed = query.trim().toLowerCase();
        return trimmed.startsWith("for ") ||
               trimmed.startsWith("let ") ||
               trimmed.startsWith("<") ||
               trimmed.contains("$") ||
               trimmed.contains("{") ||
               trimmed.startsWith("\"") ||
               (trimmed.startsWith("(") && !trimmed.startsWith("(doc("));
    }
    
    // Replace document("...") calls with ones that use absolute file URLs.
    private static String fixDocumentCalls(String query, String inputXmlPath) {
        Pattern pattern = Pattern.compile("(document|doc)\\(\"([^\"]+)\"\\)");
        Matcher matcher = pattern.matcher(query);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String filename = inputXmlPath;
            // If the filename doesn't already contain a protocol, convert it.
            if (!filename.contains("://")) {
                System.out.println("Filename: " + filename);
                String absolutePath = new File(filename).toURI().toString();
                System.out.println("Absoloute Path: " + absolutePath);
                matcher.appendReplacement(sb, "document(\"" + absolutePath + "\")");
            } else {
                System.out.println("Filename already contains protocol: " + filename);
                matcher.appendReplacement(sb, matcher.group(0));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    
    private static LinkedList<Node> evaluateXPath(String xpathQuery, XPathEvaluator evaluator) {
        System.out.println("Evaluating XPath query: " + xpathQuery);
        XPathLexer lexer = new XPathLexer(CharStreams.fromString(xpathQuery));
        XPathParser parser = new XPathParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.xpath();
        LinkedList<Node> result = evaluator.visit(tree);
        LinkedList<Node> wrapped = wrapInResultElement(result);
        return wrapped;
    }
    
    private static LinkedList<Node> evaluateXQuery(String xquery, XQueryEvaluator evaluator) {
        System.out.println("Evaluating XQuery query: " + xquery);
        String rewritten = RewriteHelper.rewriteXQuery(xquery);
        System.out.println("Rewritten XQuery: " + rewritten);
        if (rewritten.isEmpty()) {
            XQueryLexer lexer = new XQueryLexer(CharStreams.fromString(xquery));
            XQueryParser parser = new XQueryParser(new CommonTokenStream(lexer));
            ParseTree tree = parser.xquery();
            List<Node> result = evaluator.visit(tree);
            return new LinkedList<>(result);
        } else {
            System.out.println("Rewritten XQuery is not empty: " + rewritten);
            XQueryLexer lexer = new XQueryLexer(CharStreams.fromString(rewritten));
            XQueryParser parser = new XQueryParser(new CommonTokenStream(lexer));
            ParseTree tree = parser.xquery();
            List<Node> result = evaluator.visit(tree);
            return new LinkedList<>(result);
        }
    }

        /**
     * A helper to wrap the final list of Nodes in a <RESULT> root element.
     */
    private static LinkedList<Node> wrapInResultElement(LinkedList<Node> originalNodes) {
        try {
            // 1) Create a fresh document in which to build <RESULT>.
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document newDoc = db.newDocument();
    
            // 2) Create the <RESULT> element and attach it to the new document.
            Node resultElem = newDoc.createElement("RESULT");
            newDoc.appendChild(resultElem);
    
            // 3) Import every node from the original list into the new <RESULT>.
            for (Node node : originalNodes) {
                Node imported = newDoc.importNode(node, true); // 'true' = deep copy
                resultElem.appendChild(imported);
            }
    
            // 4) Return a list whose single element is this new <RESULT> node.
            LinkedList<Node> wrappedList = new LinkedList<>();
            wrappedList.add(resultElem);
            return wrappedList;
    
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

}
