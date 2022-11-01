package edu.ncsu.csc216.stp.model.util;

/**
 * Class for the SortedList object, which is an array of objects sorted by a certain parameter
 * @author William Hazlehurst, Erik Svanes
 *
 * @param <E> Generic element which can be stored in this given List
 */
public class SortedList<E> implements ISortedList {

	@Override
	public void add(Comparable element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element");
		}
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i) == element) {
				throw new IllegalArgumentException("Cannot add duplicate element");
			}
		}
		// TODO fill in
		
	}

	@Override
	public Comparable remove(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Comparable element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Comparable get(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
