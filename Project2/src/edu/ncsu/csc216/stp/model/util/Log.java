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
	}

	@Override
	public void add(Object element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element");
		}
		// TODO fill in
		
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
		// TODO Auto-generated method stub
		return 0;
	}


}
