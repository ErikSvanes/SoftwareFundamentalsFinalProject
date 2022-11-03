package edu.ncsu.csc216.stp.model.util;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Class for the SortedList object, which is an array of objects sorted by a
 * certain parameter
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 * @param <E> Generic element which can be stored in this given List
 */
public class SortedList<E> implements ISortedList<E> {
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

	@Override
	public void add(E element) {
		// null check the element
		if (element == null) {
			throw new NullPointerException("Cannot add null element");
		}
		// check for duplicate elements
		ListNode current = front;
		while (current != null) {
			if (element.equals(current.data)) {
				throw new IllegalArgumentException("No duplicate elements.");
			}
			current = current.next;
		}
		// if first node in the list
		if (front == null) {
			front = new ListNode(element);
			size++;
			return;
		}
		// add the element
		TestCase tsAdd;
		TestCase temp;
		boolean onlyFailed = true;
		try {
			tsAdd = (TestCase) element;
		} catch (Exception e) {
			System.out.println("Element is not a test case");
			return;
		}
		current = front;
		int firstPassingTS = -1;
		while (current != null) {
			try {
				temp = (TestCase) current.next.data;
			} catch (Exception e) {
				System.out.println("Could not cast");
				return;
			}
			if (!temp.isTestCasePassing()) {
				firstPassingTS++;
			} else if (temp.isTestCasePassing()) {
				onlyFailed = false;
			} else {
				break;
			}
			current = current.next;
		}
		current = front;
		// if the list only is passing test cases
		if (firstPassingTS == -1) {
			current = new ListNode(element);
			size++;
			return;
		}
		// if the list only is failed test cases
		if (onlyFailed) {
			while (current != null) {
				current = current.next;
			}
			current = new ListNode(element);
			size++;
			return;
		}
		// if its not only failed test cases
		for (int i = 0; i < firstPassingTS + 1; i++) {
			current = current.next;
		}
		ListNode savedReference = current.next;
		current.next = new ListNode(element);
		current.next.next = savedReference;
		size++;
	}

	@Override
	public E remove(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int idx) {
		if (idx < 0 || idx > size) {
			throw new IllegalArgumentException("Index " + idx + " is out of bounds for " + 
					"length " + size);
		}
		if (size == 0) {
			return null;
		}
		ListNode current = front;
		for (int i = 0; i < idx - 1; i++) {
			current = current.next;
		}
		return (E) current.next;
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

		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}

		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
