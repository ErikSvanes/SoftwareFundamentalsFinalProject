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
		VALID_TESTCASE1.addTestResult(true, "bb");
		VALID_TESTCASE2.addTestResult(false, "it messed up :(");
		VALID_TESTCASE3.addTestResult(true, "zz");
		VALID_TESTCASE4.addTestResult(false, "aaa");
		list.add(VALID_TESTCASE2);
		list.add(VALID_TESTCASE4);
		list.add(VALID_TESTCASE3);
		list.add(VALID_TESTCASE1);
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getTestCaseId());
		}
		assertEquals(list.get(0).getTestCaseId(), "test2");
		assertEquals(list.get(1).getTestCaseId(), "test4");
		assertEquals(list.get(2).getTestCaseId(), "test1");
		assertEquals(list.get(3).getTestCaseId(), "test3");
	}
	
	@Test
	void testAddOnly() {
		SortedList<TestCase> list = new SortedList<TestCase>();
		VALID_TESTCASE1.addTestResult(true, "bb");
		VALID_TESTCASE2.addTestResult(false, "it messed up :(");
		VALID_TESTCASE3.addTestResult(true, "zz");
		VALID_TESTCASE4.addTestResult(false, "aaa");
		list.add(VALID_TESTCASE1);
		list.add(VALID_TESTCASE2);
		assertEquals("test2", list.get(0).getTestCaseId());
		assertEquals("test1", list.get(1).getTestCaseId());
		// reset
		list = new SortedList<TestCase>();
		VALID_TESTCASE2.addTestResult(true, "passed!");
		VALID_TESTCASE4.addTestResult(true, "passed!");
		list.add(VALID_TESTCASE2);
		list.add(VALID_TESTCASE4);
		list.add(VALID_TESTCASE3);
		list.add(VALID_TESTCASE1);
		assertEquals("test1", list.get(0).getTestCaseId());
		assertEquals("test2", list.get(1).getTestCaseId());
		assertEquals("test3", list.get(2).getTestCaseId());
		assertEquals("test4", list.get(3).getTestCaseId());
	}
	
	@Test
	void testAddElse() {
		SortedList<TestCase> list = new SortedList<TestCase>();
		VALID_TESTCASE1.addTestResult(true, "bb");
		VALID_TESTCASE2.addTestResult(false, "it messed up :(");
		VALID_TESTCASE3.addTestResult(true, "zz");
		VALID_TESTCASE4.addTestResult(false, "aaa");
		list.add(VALID_TESTCASE2);
		list.add(VALID_TESTCASE1);
		list.add(VALID_TESTCASE3);
		assertEquals("test2", list.get(0).getTestCaseId());
		assertEquals("test1", list.get(1).getTestCaseId());
		assertEquals("test3", list.get(2).getTestCaseId());
		// reset
		list = new SortedList<TestCase>();
		VALID_TESTCASE1.addTestResult(false, "passed!");
		list.add(VALID_TESTCASE2);
		list.add(VALID_TESTCASE3);
		list.add(VALID_TESTCASE1);
		list.add(VALID_TESTCASE4);
		assertEquals("test1", list.get(0).getTestCaseId());
		assertEquals("test2", list.get(1).getTestCaseId());
		assertEquals("test4", list.get(2).getTestCaseId());
		assertEquals("test3", list.get(3).getTestCaseId());
	}

	@Test
	void testRemove() {
		SortedList<TestCase> list = new SortedList<TestCase>();
		VALID_TESTCASE1.addTestResult(true, "bb");
		VALID_TESTCASE2.addTestResult(false, "it messed up :(");
		VALID_TESTCASE3.addTestResult(true, "zz");
		VALID_TESTCASE4.addTestResult(false, "aaa");
		list.add(VALID_TESTCASE2);
		list.add(VALID_TESTCASE1);
		list.add(VALID_TESTCASE3);
		list.add(VALID_TESTCASE4);
		assertEquals(4, list.size());
		list.remove(2);
		assertEquals(3, list.size());
		list.remove(0);
		assertEquals(2, list.size());
	}
}
