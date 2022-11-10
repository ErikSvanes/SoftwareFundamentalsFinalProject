package edu.ncsu.csc216.stp.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;

class FailingTestListTest {
	/** Valid TestCase which can be used for testing */
	private static final TestCase VALID_TEST_CASE1 = new TestCase("test 1", "generic", "desc1", "expected1");
	/** Second valid TestCase which can be used for testing */
	private static final TestCase VALID_TEST_CASE2 = new TestCase("test 2", "generic2", "desc2", "expected2");

	@Test
	void testNewFailingTestList() {
		FailingTestList failList = new FailingTestList();
		assertEquals(failList.getTestPlanName(), FailingTestList.FAILING_TEST_LIST_NAME);
		assertEquals(failList.getTestCasesAsArray().length, 0);
	}

	@Test
	void testAddTestCase() {
		FailingTestList failList = new FailingTestList();
		TestCase tc = VALID_TEST_CASE1;
		tc.addTestResult(false, "idk");
		failList.addTestCase(tc);
		assertEquals(failList.getTestCase(0).getTestCaseId(), "test 1");
		// Create a passing TestCase
		TestCase invalid = VALID_TEST_CASE1;
		invalid.addTestResult(true, "this is working");
		// Attempting to add a passing TestCase should throw an IAE
		assertThrows(IllegalArgumentException.class, () -> failList.addTestCase(invalid));
	}

	@Test
	void testSetTestPlanName() {
		FailingTestList failList = new FailingTestList();
		// You can set the TestPlan's name to the default name
		failList.setTestPlanName(FailingTestList.FAILING_TEST_LIST_NAME);
		// You cannot set the TestPlan's name to anything other than the default name
		assertThrows(IllegalArgumentException.class, () -> failList.setTestPlanName("hi"));
	}

	@Test
	void testGetTestCasesAsArray() {
		FailingTestList failList = new FailingTestList();
		assertEquals(failList.getTestCasesAsArray().length, 0);
		TestPlan plan1 = new TestPlan("plan 1");
		TestPlan plan2 = new TestPlan("plan 2");
		TestCase tc = VALID_TEST_CASE1;
		tc.addTestResult(false, "erik broke it again");
		tc.setTestPlan(plan1);
		TestCase tc2 = VALID_TEST_CASE2;
		tc2.addTestResult(false, "erik STOP BREAKING IT");
		tc2.setTestPlan(plan2);
		failList.addTestCase(tc);
		failList.addTestCase(tc2);
		String[][] output = failList.getTestCasesAsArray();
		assertEquals(output[0][0], "test 1");
		assertEquals(output[0][1], "generic");
		assertEquals(output[0][2], "plan 1");
		assertEquals(output[1][0], "test 2");
		assertEquals(output[1][1], "generic2");
		assertEquals(output[1][2], "plan 2");
	}
	
	@Test
	void testClearTests() {
		FailingTestList failList = new FailingTestList();
		TestCase tc = VALID_TEST_CASE1;
		tc.addTestResult(false, "idk im not creative at this point");
		failList.clearTests();
		assertEquals(failList.getTestCasesAsArray().length, 0);
	}

}
