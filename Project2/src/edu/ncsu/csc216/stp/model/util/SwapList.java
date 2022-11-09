package edu.ncsu.csc216.stp.model.util;

/**
 * Class for the SwapList object, which is used to swap elements in a List
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 * @param <E> Generic elements which can be stored in this type of List
 */
public class SwapList<E> implements ISwapList<E> {
	/** Initial size of the array */
	private static final int INIT_SIZE = 10;
	/** The array */
	@SuppressWarnings("unchecked")
	private E[] list = (E[]) (new Object[INIT_SIZE]);
	/** The size of the array */
	private int size = 0;

	/**
	 * Method that adds an element to the back of the list
	 * 
	 * @param element the element to add to the back of the list
	 * @throws NullPointerException if the element parameter is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(Object element) {
		if (element == null)
			throw new NullPointerException("Cannot add null element.");
		if (size == list.length)
			growArray();
		list[size] = (E) element;
		size++;
	}

	/**
	 * Method that removes the element at the specified valid index
	 * 
	 * @param idx the index of the element to remove
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the list
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException("Invalid index.");
		E removedElement = list[idx];
		for (int i = idx; i < size; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return removedElement;
	}

	/**
	 * Method that swaps the element at the given index with the element before it
	 * in the list (index - 1).
	 * 
	 * @param idx the index of the element to swap
	 */
	@Override
	public void moveUp(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException("Invalid index.");
		swapElements(idx, idx - 1);
	}

	/**
	 * Method that swaps the element at the given index with the element after it in
	 * the list (index - +).
	 * 
	 * @param idx the index of the element to swap
	 */
	@Override
	public void moveDown(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException("Invalid index.");
		swapElements(idx, idx + 1);
	}

	/**
	 * Method that moves the element at the specified valid index to the front of
	 * the list.
	 * 
	 * @param idx the index of the specified element
	 */
	@Override
	public void moveToFront(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException("Invalid index.");
		E movedElement = list[idx];
		for (int i = idx; i > 0; i--) {
			list[i] = list[i - 1];
		}
		list[0] = movedElement;
	}

	/**
	 * Method that moves the element at the specified valid index to the back of the
	 * list.
	 * 
	 * @param idx the index of the specified element
	 */
	@Override
	public void moveToBack(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException("Invalid index.");
		E movedElement = list[idx];
		for (int i = idx; i < size; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = movedElement;
	}

	/**
	 * Method that returns the element at the specified valid index.
	 * 
	 * @param idx the specified valid index
	 * @return the element of the list at the specified index
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException("Invalid index.");
		return list[idx];
	}

	/**
	 * Method that returns the size of the list
	 * 
	 * @return the size of the array
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Method that doubles the length of the list variable, and is called when
	 * something is added when the size is equal to the length.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		int currentLength = list.length;
		E[] newArr = (E[]) (new Object[currentLength * 2]);
		for (int i = 0; i < currentLength; i++) {
			newArr[i] = list[i];
		}
		list = newArr;
	}

	/**
	 * Swaps two elements in the list given their indexes.
	 * 
	 * @param firstIdx  the index of the first element to swap
	 * @param secondIdx the index of the second element to swap
	 */
	private void swapElements(int firstIdx, int secondIdx) {
		E firstElement = list[firstIdx];
		list[firstIdx] = list[secondIdx];
		list[secondIdx] = firstElement;
	}

}
