package edu.ncsu.csc216.stp.model.util;

/**
 *  Class for Log objects
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
		log = (E[]) new Log[INIT_CAPACITY];
		size = 0;
	}

	@Override
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element");
		}
		if (size == log.length) {
			growArray();
		}
		if(size == 0) {
			log[0] = (E) element;
		} else {
		log[size] = (E) element;
		}
		
		// TODO fill in
		size++;
	}

	@Override
	public E get(int idx) {
		if(idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		return log[idx];
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	private void growArray() {
		int currentLength = log.length;
		E[] newArr = (E[])(new Object[currentLength * 2]);
		for(int i = 0; i < currentLength; i++) {
			newArr[i] = log[i];
		}
		log = newArr;
	}

}
