package edu.ncsu.csc216.stp.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.SortedList;

class TestPlanWriterTest {

	@Test
	void test() {
		File file = new File("test-files/provided/test-plans0.txt");
		
		SortedList<TestPlan> list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
		
		file = new File("test-files/provided/test-plans0-expected.txt");
		
		TestPlanWriter.writeTestPlanFile(file, list);
		
//		list = (SortedList<TestPlan>) TestPlanReader.readTestPlansFile(file);
//		
//		assertEquals(list.size(), 2);
//		assertEquals(list.get(0).getTestPlanName(), "PackScheduler");
//		assertEquals(list.get(1).getTestPlanName(), "WolfScheduler");
//		TestPlan tp1 = list.get(0);
//		TestPlan tp2 = list.get(1);
//		assertEquals(tp1.getTestCases().size(), 2);
//		assertEquals(tp1.getTestCases().get(0).getTestCaseId(), "test0");
//		assertEquals(tp1.getTestCases().get(1).getTestCaseId(), "test1");
//		assertEquals(tp2.getTestCases().size(), 3);
//		assertEquals(tp2.getTestCases().get(0).getTestCaseId(), "test1");
//		assertEquals(tp2.getTestCases().get(1).getTestCaseId(), "test2");
//		assertEquals(tp2.getTestCases().get(2).getTestCaseId(), "test3");
	}

}
