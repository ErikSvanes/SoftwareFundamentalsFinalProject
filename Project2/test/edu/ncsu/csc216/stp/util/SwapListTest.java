package edu.ncsu.csc216.stp.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.util.SwapList;

class SwapListTest {

	@Test
	void testNewSwapList() {
		SwapList<Integer> list = new SwapList<Integer>();
		assertEquals(0, list.size());
	}

	@Test
	void testAdd() {
		SwapList<Integer> list = new SwapList<Integer>();
		Exception e = assertThrows(NullPointerException.class,
				() -> list.add(null));
		assertEquals("Cannot add null element.", e.getMessage());
		list.add(1);
		assertEquals(1, list.size());
		assertEquals(1, list.get(0));
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		assertEquals(11, list.size());
	}
	
	@Test
	void testRemove() {
		SwapList<Integer> list = new SwapList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		assertEquals(4, list.size());
		list.remove(3);
		assertEquals(3, list.size());
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		list.remove(1);
		assertEquals(2, list.size());
		assertEquals(1, list.get(0));
		assertEquals(3, list.get(1));
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals(3, list.get(0));
	}
	
	@Test
	void testMoveUpDown() {
		SwapList<Integer> list = new SwapList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(4, list.get(3));
		list.moveUp(2);
		assertEquals(1, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(4, list.get(3));
		list.moveDown(1);
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(4, list.get(3));
	}
	
	@Test
	void testMoveFrontBack() {
		SwapList<Integer> list = new SwapList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(4, list.get(3));
		list.moveToFront(3);
		assertEquals(4, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(3, list.get(3));
		list.moveToBack(1);
		assertEquals(4, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(1, list.get(3));
	}
}
