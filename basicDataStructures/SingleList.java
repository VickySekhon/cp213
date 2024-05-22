package cp213;
import java.util.ArrayList; // import the ArrayList class
import java.util.Currency;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author your name, id, email here
 * @version 2023-09-06
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The value to look for.
     * @return A pointer to the node previous to the node containing key.
     */
    private SingleNode<T> linearSearch(final T key) {

	// your code here
    if (this.length > 0) {
    	SingleNode<T> curr = this.front;
    	while (curr != null && curr.getItem().compareTo(key) != 0) {
    		curr = curr.getNext();
    	}
    	if (curr != null) {
    		return curr;
    	}
    }
	return null;
    }

    /**
     * Appends data to the end of this SingleList.
     *
     * @param data The value to append.
     */
    public void append(final T data) {

	// your code here
	SingleNode<T> node = new SingleNode<T>(data, null);
    if (this.length == 0) {
    	this.front = node; 
    } else {
    	this.rear.setNext(node);
    }
    this.rear = node;
    this.length++;
	return;
    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each value formerly present in this SingleList. The first occurrence of
     * each value is preserved.
     */
    public void clean() {

	// your code here
    ArrayList<T> arr = new ArrayList<T>(); // Create an ArrayList object
    if (this.length > 1) {
    	SingleNode<T> curr = this.front; 
    	while (curr != null) {
    		if (arr.contains(curr.getItem())) {
    			this.remove(curr.getItem());
    		} else {
    			arr.add(curr.getItem());
    		}
    		curr = curr.getNext();
    	}
    }

	return;
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

	// your code here
    while (left.length > 0 || right.length > 0) {
    	if (left.length > 0) {
    		this.moveFrontToRear(left);
    	}
    	if (right.length > 0) {
    		this.moveFrontToRear(right);
    	}
    }

	return;
    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key value to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {

	// your code here
    SingleNode<T> node = this.linearSearch(key);
    if (node != null) {
    	return true;
    }
	return false;
    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The value to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {

	// your code here
    int count = 0;
    if (this.length == 0) {
    	count = 0;
    } else {
    	SingleNode<T> curr = this.front;
    	while (curr != null) {
    		if (curr.getItem().compareTo(key) == 0) {
    			count++;
    		}
    		curr = curr.getNext();
    	}
    }
	return count;
    }

    /**
     * Finds and returns the value in list that matches key.
     *
     * @param key The value to search for.
     * @return The value that matches key, null otherwise.
     */
    public T find(final T key) {

	// your code here
    if (this.length == 1) {
    	T val = this.front.getItem();
    	if (val.compareTo(key) == 0) {
    		return val;
    	}
    } else if (this.length > 1) {
    	SingleNode<T> curr = this.front; 
    	while (curr != null && curr.getItem().compareTo(key) != 0) {
    		curr = curr.getNext();
    	}
    	if (curr != null) {
    		T val = curr.getItem();
    		return val;
    	}
    }
	return null;
    }

    /**
     * Get the nth item in this SingleList.
     *
     * @param n The index of the item to return.
     * @return The nth item in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {

	// your code here
    if (this.length > 0) {
    	int count = 0;
    	SingleNode<T> curr = this.front; 
    	while (count != n) {
    		curr = curr.getNext();
    		count++;
    	}
    	T val = curr.getItem();
    	return val;
    }
	return null;
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same values in the same order as
     *         source, false otherwise.
     */
    public boolean identical(final SingleList<T> source) {

	// your code here
    if (this.length == 0 && source.length == 0) {
    	return true;
    } else if (this.length == 1 && source.length == 1) {
    	T thisCurr = this.front.getItem();
    	T sourceCurr = source.front.getItem();
    	if (thisCurr.compareTo(sourceCurr) == 0) {
    		return true;
    	}
    } else if (this.length > 1 && source.length > 1) {
    	SingleNode<T> thisCurr = this.front;
    	SingleNode<T> sourceCurr = source.front;
    	while (thisCurr != null && thisCurr.getItem().compareTo(sourceCurr.getItem()) == 0) {
    		thisCurr = thisCurr.getNext();
    		sourceCurr = sourceCurr.getNext();
    	}
    	if (thisCurr == null) {
    		return true;
    	}
    }	
	return false;
    }

    /**
     * Finds the first location of a value by key in this SingleList.
     *
     * @param key The value to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {

	// your code here
    int count = 0;
    if (this.length != 0) {
    	SingleNode<T> curr = this.front; 
    	while (curr != null && curr.getItem().compareTo(key) != 0) {
    		curr = curr.getNext();
    		count++;
    	}
    	if (curr != null) {
    		return count;
    	}
    }
	return -1;
    }

    /**
     * Inserts value into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i    The index to insert the new data at.
     * @param data The new value to insert into this SingleList.
     */
    public void insert(int i, final T data) {

	// your code here
    if (this.length == 0) {
    	SingleNode<T> node = new SingleNode<T>(data, null);
    	this.front = node; 
    	this.rear = node; 
    } else {
    	if (i < 0) {
    		this.prepend(data);
    	} else if (i > this.length) {
    		this.append(data);
    	} else {
    		int count = 0;
    		SingleNode<T> prev = null; 
    		SingleNode <T> curr = this.front; 
    		while (count != i) {
    			prev = curr; 
    			curr = curr.getNext();
    			count++;
    		}
    		SingleNode<T> node = new SingleNode<T>(data, curr);
    		prev.setNext(node);
    	}
    }
    this.length++;
	return;
    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then values from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {

	// your code here
    if (right.length > 0) {
    	SingleNode<T> rightNode = right.front; 
    	while (rightNode != null) {
    		if (left.contains(rightNode.getItem())) {
    			this.append(rightNode.getItem());
    		}
    		rightNode = rightNode.getNext();
    	}
    	return;
    }
    }

    /**
     * Finds the maximum value in this SingleList.
     *
     * @return The maximum value.
     */
    public T max() {

	// your code here
    if (this.length == 1) {
    	T maxVal = this.front.getItem(); 
    	return maxVal; 
    } else if (this.length > 1) {
    	T maxVal = this.front.getItem();
    	SingleNode<T> curr = this.front.getNext();
    	while (curr != null) {
    		if (curr.getItem().compareTo(maxVal) > 0) {
    			maxVal = curr.getItem();
    		}
    		curr = curr.getNext();
    	}
    	return maxVal;
    }
	return null;
    }

    /**
     * Finds the minimum value in this SingleList.
     *
     * @return The minimum value.
     */
    public T min() {

	// your code here
    if (this.length == 1) {
    	T minVal = this.front.getItem(); 
    	return minVal; 
    } else if (this.length > 1) {
    	T minVal = this.front.getItem();
    	SingleNode<T> curr = this.front.getNext();
    	while (curr != null) {
    		if (curr.getItem().compareTo(minVal) < 0) {
    			minVal = curr.getItem();
    		}
    		curr = curr.getNext();
    	}
    	return minVal;
    }
	return null;
    }

    /**
     * Inserts value into the front of this SingleList.
     *
     * @param data The value to insert into the front of this SingleList.
     */
    public void prepend(final T data) {

	// your code here
    if (this.length == 0 ) {
    	SingleNode<T> node = new SingleNode<T>(data, null);
    	this.front = node; 
    	this.rear = node;
    } else {
    	SingleNode<T> node = new SingleNode<T>(data, this.front);
    	this.front = node;
    }
    this.length++;

	return;
    }

    /**
     * Finds, removes, and returns the value in this SingleList that matches key.
     *
     * @param key The value to search for.
     * @return The value matching key, null otherwise.
     */
    public T remove(final T key) {

	// your code here
    if (this.length != 0) {
    	if (this.front.getItem().compareTo(key) == 0) {
    		T val = this.front.getItem(); 
    		this.length--;
    		if (this.length == 0) {
    			this.front = null;
    			this.rear = null;
    		} else if (this.length == 1) {
    			this.front = this.rear;
    		} else {
    			this.front = this.front.getNext();    			
    		}
    		return val;
    	} else if (this.rear.getItem().compareTo(key) == 0) {
    		SingleNode<T> curr = this.front; 
    		while (curr.getNext().getItem().compareTo(this.rear.getItem()) != 0) {
    			curr = curr.getNext();
    		}
    		T val = this.rear.getItem();
    		this.length--;
    		this.rear = curr;
    		this.rear.setNext(null);
    		return val;
    	} else {
    		SingleNode<T> prev = null; 
    		SingleNode<T> curr = this.front; 
    		while (curr != null && curr.getItem().compareTo(key) != 0) {
    			prev = curr;
    			curr = curr.getNext();
    		}
    		if (curr != null) {
    			T val = curr.getItem();
    			this.length--;
    			prev.setNext(curr.getNext());
    			return val; 
    		}
    	}
    }
	return null;
    }

    /**
     * Removes the value at the front of this SingleList.
     *
     * @return The value at the front of this SingleList.
     */
    public T removeFront() {

	// your code here
    if (this.length > 0) {
    	T val = this.front.getItem();
    	if (this.length == 1) {
    		this.front = null;
    		this.rear = null; 
    	} else if (this.length > 1) {
    		this.front = this.front.getNext();
    	}
    	this.length--;    	
    	return val;
    }
	return null;
    }

    /**
     * Finds and removes all values in this SingleList that match key.
     *
     * @param key The value to search for.
     */
    public void removeMany(final T key) {

	// your code here
    if (this.length == 1) {
    	T singleVal = this.front.getItem();
    	if (singleVal.compareTo(key) == 0) {
    		this.remove(singleVal);
    	}
    } else if (this.length > 1) {
    	SingleNode<T> curr = this.front; 
    	while (curr != null) {
    		if (curr.getItem().compareTo(key) == 0) {
    			this.remove(curr.getItem());
    		}
    		curr = curr.getNext();
    	}
    }
	return;
    }

    /**
     * Reverses the order of the values in this SingleList.
     */
    public void reverse() {

	// your code here
    SingleNode<T> prev = null;
    SingleNode<T> curr = this.front; 
    SingleNode<T> temp = this.front.getNext();
    this.rear = curr;
    while (curr != null) {
    	temp = curr.getNext(); 
    	curr.setNext(prev);
    	prev = curr; 
    	curr = temp;
    }
    this.front = prev;

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * item than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {

	// your code here
    int count = this.length;
    int half = (this.length) / 2;
    for (int i = 0; i <= half; i++) {
    	left.moveFrontToRear(this);
    }
    for (int j = half; j < count - 1; j++) {
    	right.moveFrontToRear(this);
    }
	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

	// your code here
    while (this.length > 0) {
    	left.moveFrontToRear(this);
    	if (this.length > 0) {
    		right.moveFrontToRear(this);
    	}
    }
	return;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies value
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then values from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {

	// your code here
    if (left.length > 0) {
    	SingleNode<T> leftNode = left.front;
    	while (leftNode != null) {
    		this.append(leftNode.getItem());
    		leftNode = leftNode.getNext();
    	}
    } 
    if (right.length > 0) {
    	SingleNode<T> rightNode = right.front; 
    	while (rightNode != null) {
    		this.append(rightNode.getItem());
    		rightNode = rightNode.getNext();
    	}
    }
	return;
    }
}
