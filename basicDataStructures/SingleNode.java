package cp213;

/**
 * DO NOT CHANGE THE CONTENTS OF THIS CLASS.
 *
 * The individual node of a linked structure that stores <code>T</code> objects.
 * This is a singly linked node. The node link can be updated, but not the node
 * item, in order to avoid copying or moving values between nodes. Data
 * structures must be updated by moving nodes, not by copying or moving item.
 *
 * @author David Brown
 * @version 2023-09-06
 */
public final class SingleNode<T> {
	
    /**
     * Link to the next Node.
     */
    private SingleNode<T> next = null;	
    /**
     * The generic item stored in the node.
     */
    private T item = null;

    /**
     * Creates a new node with item and link to next node. Not copy safe as it
     * accepts a reference to the item rather than a copy of the item.
     *
     * @param item the item to store in the node.
     * @param next the next node to link to.
     */
    public SingleNode(final T item, final SingleNode<T> next) {
	this.item = item;
	this.next = next;
    }

    /**
     * Returns the next node in the linked structure.
     *
     * @return The node that follows this node.
     */
    public SingleNode<T> getNext() {
	return this.next;
    }

    /**
     * Returns the node item. Not copy safe as it returns a reference to the item,
     * not a copy of the item.
     *
     * @return The item portion of the node.
     */
    public T getItem() {
	return this.item;
    }

    /**
     * Links this node to the next node.
     *
     * @param next The new node to link to.
     */
    public void setNext(final SingleNode<T> next) {
	this.next = next;
    }
}
