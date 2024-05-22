package cp213;

/**
 * Stores a value and an occurrence count for that value. The value must be
 * Comparable so that it can be compared and sorted against other values of the
 * same type.
 *
 * @author David Brown
 * @version 2023-03-13
 */
public class CountedValue<T extends Comparable<T>> implements Comparable<CountedValue<T>> {

    // Attributes.
    private int count = 0; // value count
    private T value = null; // value

    /**
     * Copy constructor.
     *
     * @param source Another CountedValue object.
     */
    public CountedValue(final CountedValue<T> source) {
	this.value = source.value;
	this.count = source.count;
    }

    /**
     * Constructor for key version of object. (value count defaults to 0)
     *
     * @param value The value to be counted.
     */
    public CountedValue(final T value) {
	this.value = value;
    }

    /**
     * Constructor.
     *
     * @param value The value to be counted.
     * @param count The value count.
     */
    public CountedValue(final T value, final int count) {
	this.value = value;
	this.count = count;
    }

    /**
     * Comparison method for value values. Compares only the stored values, counts
     * are ignored. Returns:
     * <ul>
     * <li>0 if this equals target</li>
     * <li>&lt; 0 if this precedes target</li>
     * <li>&gt; 0 if this follows target</li>
     * </ul>
     *
     * @param target CountedValue object to compare against.
     * @return Comparison result.
     */
    @Override
    public int compareTo(CountedValue<T> target) {
	return this.value.compareTo(target.value);
    }

    /**
     * Decrements the value count.
     */
    public void decrementCount() {
	this.count--;
    }

    /**
     * Returns this value count.
     *
     * @return this value count.
     */
    public int getCount() {
	return this.count;
    }

    /**
     * Returns this value.
     *
     * @return this value.
     */
    public T getValue() {
	return this.value;
    }

    /**
     * Increments the value count.
     */
    public void incrementCount() {
	this.count++;
    }

    /**
     * Sets the value count.
     *
     * @param count the new value count.
     */
    public void setCount(final int count) {
	this.count = count;
	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return String.format("{%s: %d}", this.value.toString(), this.count);
    }

}
