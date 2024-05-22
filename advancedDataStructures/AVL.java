package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Vicky Sekhon
 * @author David Brown
 * @version 2023-09-06
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance item of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {

	// your code here
    
    // balance factor = height of (left child - right child) 
    if (node != null) {
    	int leftChild = (node.getLeft() != null) ? node.getLeft().getHeight() : 0;
    	int rightChild = (node.getRight() != null) ? node.getRight().getHeight() : 0;
    	return leftChild - rightChild;
    } else {
    	return 0;    	
    }
    }

    /**
     * Rebalances the current node if its children are not balanced.
     *
     * @param node the node to rebalance
     * @return replacement for the rebalanced node
     */
    private TreeNode<T> rebalance(TreeNode<T> node) {

	// your code here
    int balanceFactor = this.balance(node);
    if (balanceFactor > 1) {
    	return this.rotateRight(node);
    } else if (balanceFactor < -1) {
    	return this.rotateLeft(node);
    } 
	return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {

	// your code here
	TreeNode<T> rightNode = node.getRight();
	TreeNode<T> centerNode = rightNode.getLeft();
	
	rightNode.setLeft(node);
	node.setRight(centerNode);
	node.updateHeight();
	rightNode.updateHeight();
	return rightNode;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	// your code here
	TreeNode<T> leftNode = node.getLeft();
	TreeNode<T> centerNode = leftNode.getRight();
	
	leftNode.setRight(node);
	node.setLeft(centerNode);
	node.updateHeight();
	leftNode.updateHeight();
	return leftNode;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL.
     *
     * @param node The current node (TreeNode).
     * @param data Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

	// your code here
    if (node == null) {
    	TreeNode<T> newNode = new TreeNode<T>(data);
    	this.size++;
    	newNode.getdata().incrementCount();	
    	return newNode;
    } else {
    	int comparison = data.compareTo(node.getdata());
    	if (comparison > 0) {
    		node.setRight(insertAux(node.getRight(), data));
    	} else if (comparison < 0) {
    		node.setLeft(insertAux(node.getLeft(), data));
    	} else {
    		node.getdata().incrementCount();
    		return node;
    	}
    }
    node.updateHeight();
    return rebalance(node);
    }
    
    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	// your code here
	if (node == null) {
    	return true;
    } else if ((minNode != null && this.balance(minNode) == 0) && minNode.getdata().compareTo(node.getdata()) > 0) {
		return false;
	} else if ((maxNode != null && this.balance(maxNode) == 0) && node.getRight().getdata().compareTo(node.getdata()) < 0) {
		return false;
	}
    return this.isValidAux(node.getLeft(), minNode, node) && this.isValidAux(node.getRight(), node, maxNode);
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

}
