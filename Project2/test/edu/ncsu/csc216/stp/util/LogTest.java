package edu.ncsu.csc216.stp.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.util.Log;

class LogTest {

	@Test
	void testNewLog() {
		Log<String> l = new Log<String>();
		assertEquals(l.size(), 0);
	}
	
	@Test
	void testAdd() {
		Log<String> l = new Log<String>();
		l.add("test1");
		assertEquals(l.size(), 1);
		l.add("test2");
		assertEquals(l.size(), 2);
		l.add("test3");
		assertEquals(l.size(), 3);
	}
	
	@Test
	void testGet() {
		Log<String> l = new Log<String>();
		l.add("test1");
		l.add("test2");
		l.add("test3");
		assertEquals(l.get(0), "test1");
		assertEquals(l.get(1), "test2");
		assertEquals(l.get(2), "test3");
		assertThrows(IndexOutOfBoundsException.class, () -> l.get(5));
		assertThrows(IndexOutOfBoundsException.class, () -> l.get(-1));
	}

}
