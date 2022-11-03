package edu.ncsu.csc216.stp.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SortedList;

class SortedListTest {
	
	/** Valid TestCase to be used */
	private static final TestCase VALID_TESTCASE1 = new TestCase("test1", "type1", "test description 1", "valid1");
	/** Valid TestCase to be used */
	private static final TestCase VALID_TESTCASE2 = new TestCase("test2", "type2", "test description 2", "valid2");
	/** Valid TestCase to be used */
	private static final TestCase VALID_TESTCASE3 = new TestCase("test3", "type3", "test description 3", "valid3");
	/** Valid TestCase to be used */
	private static final TestCase VALID_TESTCASE4 = new TestCase("test4", "type4", "test description 4", "valid4");

	@Test
	void testNewSortedList() {
		SortedList<TestCase> list = new SortedList<TestCase>();
		assertEquals(list.get(0), null);
		assertEquals(list.size(), 0);
	}
	
	@Test
	void testAddElement() {
		SortedList<TestCase> list = new SortedList<TestCase>();
		list.add(VALID_TESTCASE2);
		list.add(VALID_TESTCASE4);
		list.add(VALID_TESTCASE3);
		list.add(VALID_TESTCASE1);
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getTestCaseId());
		}
		assertEquals(list.get(0).getTestCaseId(), "test1");
		assertEquals(list.get(1).getTestCaseId(), "test2");
		assertEquals(list.get(2).getTestCaseId(), "test3");
		assertEquals(list.get(3).getTestCaseId(), "test4");
	}

}
