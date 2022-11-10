package edu.ncsu.csc216.stp.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.tests.TestCase;

class FailingTestListTest {
	/** Valid TestCase which can be used for testing */
	private static final TestCase VALID_TEST_CASE1 = new TestCase("test 1", "generic", "desc1", "expected1");

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
	}

}
