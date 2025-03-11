package main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

public class TupleSet {
    private List<Node> tuples;
    private Document ownerDocument;

    public TupleSet(Document ownerDocument) {
        this.tuples = new ArrayList<>();
        this.ownerDocument = ownerDocument;
    }

    public void addTuple(Node tuple) {
        tuples.add(tuple);
    }

    public List<Node> getTuples() {
        return tuples;
    }

    public Document getOwnerDocument() {
        return ownerDocument;
    }

    /**
     * Performs a hash join between this tuple set and another tuple set.
     * 
     * @param other The other tuple set to join with
     * @param thisAttributes List of attribute names from this tuple set to join on
     * @param otherAttributes List of attribute names from the other tuple set to join on
     * @return A new tuple set containing the joined tuples
     */
    public TupleSet hashJoin(TupleSet other, List<String> thisAttributes, List<String> otherAttributes) {
        // Ensure attribute lists have the same length
        if (thisAttributes.size() != otherAttributes.size()) {
            throw new IllegalArgumentException("Join attribute lists must have the same length");
        }

        // Create a new tuple set for the result
        TupleSet result = new TupleSet(ownerDocument);

        // Determine which tuple set is smaller (to build hash table on)
        TupleSet smaller = (this.tuples.size() <= other.tuples.size()) ? this : other;
        TupleSet larger = (this.tuples.size() <= other.tuples.size()) ? other : this;
        List<String> smallerAttributes = (this.tuples.size() <= other.tuples.size()) ? thisAttributes : otherAttributes;
        List<String> largerAttributes = (this.tuples.size() <= other.tuples.size()) ? otherAttributes : thisAttributes;

        // Build hash table on the smaller tuple set
        Map<String, List<Node>> hashTable = new HashMap<>();
        for (Node tuple : smaller.tuples) {
            String key = buildHashKey(tuple, smallerAttributes);
            if (!hashTable.containsKey(key)) {
                hashTable.put(key, new ArrayList<>());
            }
            hashTable.get(key).add(tuple);
        }

        // Probe hash table with the larger tuple set
        for (Node largeTuple : larger.tuples) {
            String key = buildHashKey(largeTuple, largerAttributes);
            if (hashTable.containsKey(key)) {
                List<Node> matchingTuples = hashTable.get(key);
                for (Node smallTuple : matchingTuples) {
                    // Merge the two tuples
                    Node joinedTuple = mergeTuples(
                        smaller == this ? smallTuple : largeTuple,
                        smaller == this ? largeTuple : smallTuple
                    );
                    result.addTuple(joinedTuple);
                }
            }
        }

        return result;
    }

    /**
     * Builds a hash key for a tuple based on the specified attributes.
     */
    private String buildHashKey(Node tuple, List<String> attributes) {
        StringBuilder key = new StringBuilder();
        for (String attr : attributes) {
            NodeList children = tuple.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE && 
                    child.getNodeName().equals(attr)) {
                    // Get the text content of this element
                    key.append(getTextContent(child)).append("|");
                    break;
                }
            }
        }
        return key.toString();
    }

    /**
     * Gets the text content of a node, handling nested elements.
     */
    private String getTextContent(Node node) {
        if (node.getNodeType() == Node.TEXT_NODE) {
            return node.getNodeValue();
        }
        
        StringBuilder result = new StringBuilder();
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                result.append(child.getNodeValue());
            } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                result.append(getTextContent(child));
            }
        }
        return result.toString();
    }

    /**
     * Merges two tuples into a single tuple.
     */
    private Node mergeTuples(Node tuple1, Node tuple2) {
        Element mergedTuple = ownerDocument.createElement("tuple");
        
        // Copy all children from tuple1
        NodeList children1 = tuple1.getChildNodes();
        for (int i = 0; i < children1.getLength(); i++) {
            Node child = children1.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Node imported = ownerDocument.importNode(child, true);
                mergedTuple.appendChild(imported);
            }
        }
        
        // Copy all children from tuple2
        NodeList children2 = tuple2.getChildNodes();
        for (int i = 0; i < children2.getLength(); i++) {
            Node child = children2.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                // Check if this element already exists in the merged tuple
                String nodeName = child.getNodeName();
                boolean exists = false;
                NodeList mergedChildren = mergedTuple.getChildNodes();
                for (int j = 0; j < mergedChildren.getLength(); j++) {
                    if (mergedChildren.item(j).getNodeName().equals(nodeName)) {
                        exists = true;
                        break;
                    }
                }
                
                if (!exists) {
                    Node imported = ownerDocument.importNode(child, true);
                    mergedTuple.appendChild(imported);
                }
            }
        }
        
        return mergedTuple;
    }
}