package edu.ncsu.csc216.stp.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;

class TestCaseTest {
	/** Valid TestCase which can be used for testing */
	private static final TestCase VALID_TEST_CASE = new TestCase("id", "type", "description", "expected results");

	@Test
	void testNewTestCase() {
		TestCase tc = VALID_TEST_CASE;
		assertEquals(tc.getTestCaseId(), "id");
		assertEquals(tc.getTestType(), "type");
		assertEquals(tc.getTestDescription(), "description");
		assertEquals(tc.getExpectedResults(), "expected results");
	}
	
	@Test
	void testIsTestCasePassing() {
		TestCase tc = VALID_TEST_CASE;
		tc.addTestResult(false, "erik messed it up :/");
		assertFalse(tc.isTestCasePassing());
		tc.addTestResult(true, "erik fixed it with his amazingness, thankyou erik <3");
		assertTrue(tc.isTestCasePassing());
	}
	
	@Test
	void testGetStatus() {
		TestCase tc = VALID_TEST_CASE;
		tc.addTestResult(false, "erik please stop messing this up ):");
		assertEquals(tc.getStatus(), "FAIL");
		tc.addTestResult(true, "erik did it right this time!!");
		assertEquals(tc.getStatus(), "PASS");
	}
	
	@Test
	void testGetActualResultsLog() {
		TestCase tc = VALID_TEST_CASE;
		tc.addTestResult(false, "erik is stinky");
		tc.addTestResult(true, "erik is no longer stinky");
		String expectedResultsLog = "-FAIL: erik is stinky\n-PASS: erik is no longer stinky";
		assertEquals(tc.getActualResultsLog(), expectedResultsLog);
		String toString = "# id,type\n* description\n* expected results\n" + expectedResultsLog;
		assertEquals(tc.toString(), toString);
	}
	
	@Test
	void testTestPlans() {
		TestCase tc = VALID_TEST_CASE;
		TestPlan testPlan = new TestPlan("test");
		tc.setTestPlan(testPlan);
		assertEquals(tc.getTestPlan(), testPlan);
		TestPlan returnedTestPlan = tc.getTestPlan();
		assertEquals(returnedTestPlan.getTestPlanName(), "test");
	}
	
}
