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

	/**
	 * This method adds the Test Case given to the parameter into a sorted spot in the list
	 * 
	 * @param element the Test Case to add to the list
	 */
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
				throw new IllegalArgumentException("Cannot add duplicate element.");
			}
			current = current.next;
		}
		// if first node in the list
		if (size == 0) {
			front = new ListNode(element);
			size++;
			return;
		}
		// add the element
		TestCase tsAdd;
		TestCase temp = new TestCase("temp", "temp", "temp", "temp");
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
				temp = (TestCase) current.data;
			} catch (Exception e) {
				System.out.println("Could not cast " + current.data);
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
			System.out.println(tsAdd.getTestCaseId());
			return;
		}
		// if the list only is failed test cases
		if (onlyFailed) {
			while (current != null) {
				current = current.next;
			}
			current = new ListNode(element);
			size++;
			System.out.println(tsAdd.getTestCaseId());
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
		System.out.println(tsAdd.getTestCaseId());
	}

	/**
	 * Method to remove an element at a given index. Returns the removed element. 
	 * @param idx the index to remove
	 * @return the data of the removed element
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx > size) {
			throw new IllegalArgumentException();
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

	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
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
		if (idx == 0 && size == 0) {
			return null;
		}
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		System.out.println(((TestCase) current.data).getTestCaseId());
		return (E) current.data;
		
//		if (idx == 0 && size == 0) {
//			return null;
//		}
//		if (idx < 0 || idx >= size) {
//			System.out.println("idx: " + idx + " size: " + size);
//			throw new IndexOutOfBoundsException();
//		}
//		ListNode current = front;
//		for (int i = 0; i < idx - 1; i++) {
//			current = current.next;
//		}
//		return (E) current.data;
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
