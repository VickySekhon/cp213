package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author Vicky Sekhon
 * @author David Brown
 * @version 2023-09-06
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node item is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The item to search for. Count is updated to count in matching
     *             node item if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedItem<T> key) {

	// your code here
    if (node == null) {
    	return node; 
    } 
    int comparison = node.getdata().compareTo(key);
    this.comparisons++;
    if (comparison > 0) {
    	node = retrieveAux(node.getLeft(), key);
    } else if ( comparison < 0) {
    	node = retrieveAux(node.getRight(), key);
    } else {
    	node.getdata().incrementCount();
    }
    if (node != null) {
    	node.updateHeight();
    }
    	
	return rotateNode(node);
    }
    
    private TreeNode<T> rotateNode(TreeNode<T> node){
	if (node == null) {
		return node;
	} 
	if (node.getLeft() != null) {
		if (node.getLeft().getdata().getCount() > node.getdata().getCount()) {
			node.updateHeight();
			return rotateLeft(node);
		}
	}
	if (node.getRight() != null) {
		if (node.getRight().getdata().getCount() > node.getdata().getCount()) {
			node.updateHeight();
			return rotateRight(node);
		}
	}
	node.updateHeight();
	return node;
    }

    /**
     * Performs a counterclockwise rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

	// your code here
    
    TreeNode<T> left = parent.getLeft();
    TreeNode<T> center = parent.getRight();
    
    // parent is the subtree, its right child has to become the new root 
    left.setRight(parent);
    parent.setLeft(center);
    parent.updateHeight();
    left.updateHeight();
    

	return left;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

	// your code here
    TreeNode<T> center = parent.getLeft();
    TreeNode<T> right = parent.getRight();
    
    // parent is the subtree, its right child has to become the new root 
    right.setLeft(parent);
    parent.setRight(center);
    parent.updateHeight();
    right.updateHeight();
    

	return right;
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedItem<T> data) {

	// your code here
    if (node == null) {
    	TreeNode<T> newNode = new TreeNode<T>(data);
    	this.size++;
    	return newNode;
    } else {
    	int comparison = data.compareTo(node.getdata());
    	if (comparison > 0) {
    		node.setRight(insertAux(node.getRight(), data));
    	} else if (comparison < 0) {
    		node.setLeft(insertAux(node.getLeft(), data));
    	}
    }
    node.updateHeight();
    return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	// your code here
    if (node == null) {
    	return true;
    } else if (minNode != null) {
    	if (minNode.getdata().compareTo(node.getdata()) > 0) {
    		return false;
    	} else if (minNode.getdata().getCount() < node.getdata().getCount()) {
    		return false;
    	}
	} else if (maxNode != null) {
		if (maxNode.getRight().getdata().compareTo(node.getdata()) < 0) {
			return false;
		} else if (maxNode.getdata().getCount() < node.getdata().getCount()) {
			return false;
		}
	}
    return isValidAux(node.getLeft(), minNode, node) && isValidAux(node.getRight(), node, maxNode);
    
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, item, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedItem<T> retrieve(CountedItem<T> key) {

	// your code here
    CountedItem<T> node = null; 
    TreeNode<T> nNode = retrieveAux(this.root, key);
    if (nNode != null) {
    	node = nNode.getdata();
    }
    return node;
    }

}
