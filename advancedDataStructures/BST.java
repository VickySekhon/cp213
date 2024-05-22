package cp213;

import java.util.ArrayList;

/**
 * Implements a Binary Search Tree.
 *
 * @author Vicky Sekhon 
 * @author David Brown
 * @version 2023-09-06
 * 
 */
public class BST<T extends Comparable<T>> {

    // Attributes.
    /**
     * Count of comparisons performed by tree.
     */
    protected int comparisons = 0;
    /**
     * Root node of the tree.
     */
    protected TreeNode<T> root = null;
    /**
     * Number of nodes in the tree.
     */
    protected int size = 0;
    
    /**
     * Determines whether two trees are identical.
     *
     * @param target The tree to compare this BST against.
     * @return true if this and target contain nodes that match in position, item,
     *         count, and height, false otherwise.
     */
    public boolean equals(final BST<T> target) {
	boolean isEqual = false;

	if (this.size == target.size) {
	    isEqual = this.equalsAux(this.root, target.root);
	}
	return isEqual;
    }

    /**
     * Auxiliary method for {@code equals}. Determines whether two subtrees are
     * identical in items and height.
     *
     * @param source Node of this BST.
     * @param target Node of that BST.
     * @return true if source and target are identical in items and height.
     */
    protected boolean equalsAux(final TreeNode<T> source, final TreeNode<T> target) {

	// your code here
    // reached leaf node (end of the tree) and they are equal
    if (source == null & target == null) {
    	return true;
    }
    // they aren't null so we compare elements 
    if (source != null & target != null) {
    	if (source.getdata().compareTo(target.getdata()) != 0) {
    		return false;
    	} 
		boolean left = this.equalsAux(source.getLeft(), target.getLeft());
		boolean right = this.equalsAux(source.getRight(), target.getRight());
		return left && right;
   
    }
    return false;
    }

    /**
     * Auxiliary method for insert. Inserts data into this BST.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be inserted into the tree.
     * @return The inserted node.
     */
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

	if (node == null) {
	    // Base case - add a new node containing the data.
	    node = new TreeNode<T>(data);
	    node.getdata().incrementCount();
	    this.size++;
	} else {
	    // Compare the node data against the insert data.
	    final int result = node.getdata().compareTo(data);

	    if (result > 0) {
		// General case - check the left subtree.
		node.setLeft(this.insertAux(node.getLeft(), data));
	    } else if (result < 0) {
		// General case - check the right subtree.
		node.setRight(this.insertAux(node.getRight(), data));
	    } else {
		// Base case - data is already in the tree, increment its count
		node.getdata().incrementCount();
	    }
	}
	node.updateHeight();
	return node;
    }
    
    /**
     * Determines if this tree is a valid BST; i.e. a node's left child data is
     * smaller than its data, and its right child data is greater than its data, and
     * a node's height is equal to the maximum of the heights of its two children
     * (empty child nodes have a height of 0), plus 1.
     *
     * @return true if this tree is a valid BST, false otherwise.
     */
    public boolean isValid() {
	return this.isValidAux(this.root, null, null);
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree.
     *
     * @param node    The root of the subtree to test for validity.
     * @param minNode The node of the minimum value in the current subtree.
     * @param maxNode The node of the maximum value in the current subtree.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	// your code here 
    if (node == null) {
    	return true;
    } else if (minNode != null && minNode.getdata().compareTo(node.getdata()) > 0) {
		return false;
	} else if (maxNode != null && node.getRight().getdata().compareTo(node.getdata()) < 0) {
		return false;
	}
    return this.isValidAux(node.getLeft(), minNode, node) && this.isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Returns the height of a given TreeNode.
     *
     * @param node The TreeNode to determine the height of.
     * @return The height attribute of node, 0 if node is null.
     */
    protected int nodeHeight(final TreeNode<T> node) {
	int height = 0;

	if (node != null) {
	    height = node.getHeight();
	}
	return height;
    }

    /**
     * Determines if this BST contains key.
     *
     * @param key The key to search for.
     * @return true if this contains key, false otherwise.
     */
    public boolean contains(final CountedItem<T> key) {

	// your code here
    boolean contain = false;
    if (this.root == null) {
    	return contain;
    } else {
    	contain = this.contains_aux(this.root, key);
    }
	return contain;
    }

    private boolean contains_aux(TreeNode<T> root, CountedItem<T> key) {
	boolean contain = false;
	
	if (root != null) {
		int comparison = root.getdata().compareTo(key);
		if (comparison == 0) {
			contain = true;
		} else if (comparison < 0){
			contain = this.contains_aux(root.getLeft(), key);    			
		} else {
			contain = this.contains_aux(root.getRight(), key);
			}
		}    		
	return contain;
    }

    /**
     * Get number of comparisons executed by the retrieve method.
     *
     * @return comparisons
     */
    public int getComparisons() {
	return this.comparisons;
    }

    /**
     * Returns the height of the root node of this tree.
     *
     * @return height of root node, 0 if the root node is null.
     */
    public int getHeight() {
	int height = 0;

	if (this.root != null) {
	    height = this.root.getHeight();
	}
	return height;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in this tree.
     */
    public int getSize() {
	return this.size;
    }

    /**
     * Returns a list of the data in the current tree. The list contents are in
     * order from smallest to largest.
     *
     * Not thread safe as it assumes contents of the tree are not changed by an
     * external thread during the loop.
     *
     * @return The contents of this tree as a list of data.
     */
    public ArrayList<CountedItem<T>> inOrder() {
	return this.root.inOrder();
    }

    /**
     * Inserts data into this tree.
     *
     * @param data Data to store.
     */
    public void insert(final CountedItem<T> data) {
	this.root = this.insertAux(this.root, data);
	return;
    }

    /**
     * Determines if this tree is empty.
     *
     * @return true if this tree is empty, false otherwise.
     */
    public boolean isEmpty() {

	// your code 
	return this.root == null;
    }


    /**
     * Returns a list of the data in the current tree. The list contents are in node
     * level order starting from the root node. Helps determine the structure of the
     * tree.
     *
     * Not thread safe as it assumes contents of the tree are not changed by an
     * external thread during the loop.
     *
     * @return this tree data as a list of data.
     */
    public ArrayList<CountedItem<T>> levelOrder() {
	return this.root.levelOrder();
    }

    /**
     * Returns a list of the data in the current tree. The list contents are in node
     * preorder.
     *
     * Not thread safe as it assumes contents of the tree are not changed by an
     * external thread during the loop.
     *
     * @return The contents of this tree as a list of data.
     */
    public ArrayList<CountedItem<T>> preOrder() {
	return this.root.preOrder();
    }

    /**
     * Resets the comparison count to 0.
     */
    public void resetComparisons() {
	this.comparisons = 0;
	return;
    }

    /**
     * Retrieves a copy of data matching key (key should have item count of 0).
     * Returning a complete CountedItem gives access to the data and its count.
     *
     * @param key The key to look for.
     * @return data The complete CountedItem that matches key, null otherwise.
     */
    public CountedItem<T> retrieve(final CountedItem<T> key) {

	// your code here
    TreeNode<T> node = this.root;
    while (node != null) {
    	int comparison = node.getdata().compareTo(key);
    	
		if (comparison == 0) {
			return node.getdata();
		} else if (comparison < 0) {
			node = node.getRight();
		} else {
			node = node.getLeft();
		} 	
    }
	return null;
    }
}
