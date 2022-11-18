package edu.ncsu.csc216.stp.model.util;

/**
 * Class for the SortedList object, which is an array of objects sorted by a
 * certain parameter
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 * @param <E> Generic element which can be stored in this given List
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	/** The first list node in the list */
	private ListNode front;
	/** Size of the list as an integer */
	private int size;

	/**
	 * The constructor for the sorted list class. This sets the front variable to
	 * null and the size to zero.
	 */
	public SortedList() {
		front = null;
		size = 0;
	}

	/**
	 * This method adds the Test Case given to the parameter into a sorted spot in
	 * the list
	 * 
	 * @param element the Test Case to add to the list
	 * @throws NullPointerException     if the parameter is null
	 * @throws IllegalArgumentException if the element to add is a duplicate of an
	 *                                  element in the list
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		ListNode current = front;
		for (int i = 0; i < size - 1; i++) {
			if (element.compareTo(current.data) == 0) {
				throw new IllegalArgumentException("Cannot add duplicate element.");
			}
			current = current.next;
		}
		if (front == null) {
			front = new ListNode(element);
			front.next = null;
			size++;
			return;
		}

		current = front;
		if (current.data.compareTo(element) > 0) {
			front = new ListNode(element, front);
			size++;
		} else {
			while (current.data.compareTo(element) < 0 && current.next != null) {
				if (current.next.data.compareTo(element) < 0) {
					current = current.next;
				} else {
					break;
				}
			}
			current.next = new ListNode(element, current.next);
			size++;
		}
	}

	/**
	 * Method to remove an element at a given index. Returns the removed element.
	 * 
	 * @param idx the index to remove
	 * @return the data of the removed element
	 * @throws IndexOutOfBoundsException if the index is outside the scope of the
	 *                                   list
	 * @throws NullPointerException      if the list is empty
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		if (front == null) {
			throw new NullPointerException();
		}
		if (idx == 0) {
			E returnValue = front.data;
			front = front.next;
			size--;
			return returnValue;
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			ListNode returnValue = current.next;
			current.next = current.next.next;
			size--;
			return (E) returnValue.data;
		}
	}

	/**
	 * Overridden contains method that returns true or false whether the list
	 * includes the element given by the parameter.
	 * 
	 * @param element the element to check in the list
	 * @return true if the element is in the list and false if not
	 */
	@Override
	public boolean contains(E element) {
		ListNode current = front;
		for (int i = 0; i < size; i++) {
			if (element.equals(current.data)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * This method gets the element at the specified index
	 * 
	 * @param idx the index of the element
	 * @return the element at the index
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size || size == 0 || idx == 0 && size == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return (E) current.data;
	}

	@Override
	public int size() {
		return size;
	}

	private class ListNode {
		/** The data variable for each node */
		private E data;
		/** The next list node for each node */
		private ListNode next;

		/**
		 * ListNode constructor if there is only data, and not a next list node
		 * 
		 * @param data the data field for the list node
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}

		/**
		 * ListNode constructor that sets the data field and the next field
		 * 
		 * @param data the data for the list node
		 * @param next the next list node for this list node
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}