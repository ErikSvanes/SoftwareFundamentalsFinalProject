package edu.ncsu.csc216.stp.model.util;

/**
 * Class for Log objects
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 * @param <E> The generic Element that can be added to a log object
 */
public class Log<E> implements ILog<E> {
	/** Static integer which matches the initial capacity of all lists */
	private static final int INIT_CAPACITY = 10;
	/** The array for each log object */
	private E[] log;
	/** The size of the log object */
	private int size;

	/**
	 * Constructor for a new Log object
	 */
	@SuppressWarnings("unchecked")
	public Log() {
		log = (E[]) new Object[INIT_CAPACITY];
		size = 0;
	}

	/**
	 * Overridden add method that ensures that the element to add is not null, adds
	 * more slots to the array if needed, and adds the element to the end of the
	 * list while increasing the size.
	 * 
	 * @param element the element to add to the list
	 * @throws NullPointerException if the element is null
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (size == log.length) {
			growArray();
		}
		if (size == 0) {
			log[0] = (E) element;
		} else {
			log[size] = (E) element;
		}
		size++;
	}

	/**
	 * Overridden get method that ensures that the given size from the parameter is
	 * within the size of the list.
	 * 
	 * @param idx the index of the element to get from the list
	 * @return the element of the list at the specified index from the parameter
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size || size == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return log[idx];
	}

	/**
	 * Overridden method that returns the size of the list
	 * 
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Private helper method that doubles the size of the array when the size catches up.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		int currentLength = log.length;
		E[] newArr = (E[]) (new Object[currentLength * 2]);
		for (int i = 0; i < currentLength; i++) {
			newArr[i] = log[i];
		}
		log = newArr;
	}

}
