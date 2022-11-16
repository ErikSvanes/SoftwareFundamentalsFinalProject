package edu.ncsu.csc216.stp.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.SortedList;

class TestPlanReaderTest {
	@Test
	void testTP0() {
		File file = new File("test-files/provided/test-plans0.txt");
		SortedList<TestPlan> list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
		
		assertEquals(list.size(), 2);
		assertEquals(list.get(0).getTestPlanName(), "PackScheduler");
		assertEquals(list.get(1).getTestPlanName(), "WolfScheduler");
		TestPlan tp1 = list.get(0);
		TestPlan tp2 = list.get(1);
		assertEquals(tp1.getTestCases().size(), 2);
		assertEquals(tp1.getTestCases().get(0).getTestCaseId(), "test0");
		assertEquals(tp1.getTestCases().get(1).getTestCaseId(), "test1");
		assertEquals(tp2.getTestCases().size(), 3);
		assertEquals(tp2.getTestCases().get(0).getTestCaseId(), "test1");
		assertEquals(tp2.getTestCases().get(1).getTestCaseId(), "test2");
		assertEquals(tp2.getTestCases().get(2).getTestCaseId(), "test3");
		assertEquals(tp2.getTestCase(2).getTestDescription(), "description\non multiple lines");
		
		//Tests for PackScheduler's Test0
		assertEquals(tp1.getTestCase(0).getTestCaseId(), "test0");
		assertEquals(tp1.getTestCase(0).getTestType(), "Invalid");
		assertEquals(tp1.getTestCase(0).getTestDescription(), "description");
		assertEquals(tp1.getTestCase(0).getExpectedResults(), "expected results\nwith multiple lines");
		assertEquals(tp1.getTestCase(0).getActualResultsLog(), "- PASS: actual results\n- FAIL: actual results");
		
		//Tests for PackScheduler's Test1
		assertEquals(tp1.getTestCase(1).getTestCaseId(), "test1");
		assertEquals(tp1.getTestCase(1).getTestType(), "Equivalence Class");
		assertEquals(tp1.getTestCase(1).getTestDescription(), "description");
		assertEquals(tp1.getTestCase(1).getExpectedResults(), "expected results");
		assertEquals(tp1.getTestCase(1).getActualResultsLog(), "- PASS: actual results");
		
		//Tests for WolfScheduler's Test0
		assertEquals(tp2.getTestCase(0).getExpectedResults(), "expected results\nwith multiple lines");
	}
	
	@Test
	void testTP4() {
		File file = new File("test-files/provided/test-plans4.txt");
		SortedList<TestPlan> list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
		
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getTestCases().size(), 0);
	}
	
	@Test
	void testTP5() {
		File file = new File("test-files/provided/test-plans5.txt");
		SortedList<TestPlan> list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
		
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getTestCases().size(), 0);
	}
	
	@Test
	void testTP6() {
		File file = new File("test-files/provided/test-plans6.txt");
		SortedList<TestPlan> list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
		
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getTestCases().size(), 0);
	}
	
	@Test
	void testTP8() {
		File file = new File("test-files/provided/test-plans8.txt");
		SortedList<TestPlan> list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
		
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getTestCases().size(), 0);
	}
	
	@Test
	void testTP7() {
		File file = new File("test-files/provided/test-plans7.txt");
		SortedList<TestPlan> list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
		
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getTestCases().size(), 0);
	}

}
