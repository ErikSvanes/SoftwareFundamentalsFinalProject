package edu.ncsu.csc216.stp.model.util;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
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
	 * This method adds the Test Case given to the parameter into a sorted spot in
	 * the list
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
		try {
			TestPlan add = (TestPlan) element;
			current = front;
			TestPlan tp = (TestPlan) front.data;
			if (add.compareTo(tp) < 0) {
				front = new ListNode(element, front);
				size++;
				return;
			}
			for (int i = 0; i < size - 1; i++) {
				tp = (TestPlan) current.next.data;
				if (add.compareTo(tp) < 0) {
					ListNode savedReference = current.next;
					current.next = new ListNode(element);
					current.next.next = savedReference;
					size++;
					return;
				}
				current = current.next;
			}
			current.next = new ListNode(element);
			size++;
			return;
		} catch (Exception e) {
			// its not a test plan- continue with code assuming its a test case
		}
		
		// add the element
		TestCase tsAdd;
		TestCase temp = new TestCase("temp", "temp", "temp", "temp");
		// TODO boolean onlyFailed = true;
		try {
			tsAdd = (TestCase) element;
		} catch (Exception e) {
			System.out.println("Element is not a test case");
			return;
		}
		current = front;
		int firstPassingTS = -1;
		int firstFailingTS = -1;
		while (current != null) {
			try {
				temp = (TestCase) current.data;
			} catch (Exception e) {
				System.out.println("Could not cast " + current.data);
				return;
			}
			if (temp.isTestCasePassing()) {
				firstPassingTS++;
			} else {
				firstFailingTS++;
			}
			current = current.next;
		}
		current = front;
		// if the list only is passing test cases or only failing test cases
		if (firstFailingTS == -1 || firstPassingTS == -1) {
			addOnly(element, tsAdd);
			return;
		}
		// if its not only failing or passing test cases
		addElse(element, tsAdd);
	}

	/**
	 * Method that helps sort the list when it is a mix of pass and fail
	 * 
	 * @param element the element to add to the list
	 * @param tsAdd   the element but as a test case
	 */
	private void addElse(E element, TestCase tsAdd) {
		ListNode current = front;
		TestCase temp = (TestCase) current.data;
		int idx = -1;
		// if the test case to add is passing
		if (tsAdd.isTestCasePassing()) {
			current = front;
			// find the index of the first passing test
			temp = (TestCase) front.data;
			for (int i = 0; i < size - 1; i++) {
				temp = (TestCase) current.next.data;
				if (temp.isTestCasePassing()) {
					idx = i;
					break;
				}
				current = current.next;
			}
			// find the spot to add it into
			for (int i = idx; i < size - 1; i++) {
				temp = (TestCase) current.next.data;
				if (tsAdd.getTestCaseId().compareTo(temp.getTestCaseId()) < 0) {
					ListNode savedReference = current.next;
					current.next = new ListNode(element);
					current.next.next = savedReference;
					size++;
					return;
				}
				current = current.next;
			}
			// if its the last element
			current.next = new ListNode(element);
			size++;
		}
		// if the test to add is failing
		else {
			current = front;
			// if it goes in the front
			temp = (TestCase) current.data;
			if (tsAdd.getTestCaseId().compareTo(temp.getTestCaseId()) < 0) {
				front = new ListNode(element, front);
				size++;
				return;
			}
			// if it doesn't go in front
			for (int i = 0; i < size - 1; i++) {
				temp = (TestCase) current.next.data;
				if (tsAdd.getTestCaseId().compareTo(temp.getTestCaseId()) < 0 || temp.isTestCasePassing()) {
					ListNode savedReference = current.next;
					current.next = new ListNode(element);
					current.next.next = savedReference;
					size++;
					return;
				}
				current = current.next;
			}

		}
	}

	/**
	 * Method that sorts elements into a list of either only failing or only passing
	 * test cases
	 * 
	 * @param element the element to add to the list
	 * @param tsAdd   the element casted to a test case
	 */
	private void addOnly(E element, TestCase tsAdd) {
		ListNode current = front;
		TestCase temp = (TestCase) current.data;
		// if adding opposite passing value to list of only one kind
		if (temp.isTestCasePassing() && !tsAdd.isTestCasePassing()) {
			front = new ListNode(element, front);
			size++;
			return;
		} else if (!temp.isTestCasePassing() && tsAdd.isTestCasePassing()) {
			while (current.next != null) {
				current = current.next;
			}
			current.next = new ListNode(element);
			size++;
			return;
		}
		// if size is one
		if (size == 1) {
			temp = (TestCase) current.data;
			// before
			if (tsAdd.getTestCaseId().compareTo(temp.getTestCaseId()) < 0) {
				front = new ListNode(element, front);
				size++;
				return;
			}
			// after
			else {
				current.next = new ListNode(element);
				size++;
				return;
			}
		}
		// if it should be the first element
		temp = (TestCase) front.data;
		if (tsAdd.getTestCaseId().compareTo(temp.getTestCaseId()) < 0) {
			front = new ListNode(element, front);
			size++;
			return;
		}
		// if its not the first element
		current = front;
		for (int i = 0; i < size - 1; i++) {
			temp = (TestCase) current.next.data;
			if (tsAdd.getTestCaseId().compareTo(temp.getTestCaseId()) < 0) {
				ListNode savedReference = current.next;
				current.next = new ListNode(element);
				current.next.next = savedReference;
				size++;
				return;
			}
			current = current.next;
		}
		// if its the last element
		current.next = new ListNode(element);
		size++;
	}

	/**
	 * Method to remove an element at a given index. Returns the removed element.
	 * 
	 * @param idx the index to remove
	 * @return the data of the removed element
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
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
			throw new IndexOutOfBoundsException();
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
