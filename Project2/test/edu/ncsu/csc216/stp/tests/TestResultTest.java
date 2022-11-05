package edu.ncsu.csc216.stp.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestResult;

class TestResultTest {
	/** Valid Passing Test Result */
	private static final TestResult PASSING_TEST_RESULT = new TestResult(true, "erik made it work");
	/** Valid Failing Test Result */
	private static final TestResult FAILING_TEST_RESULT = new TestResult(false, "erik broked it");

	@Test
	void testNewTestResult() {
		TestResult tr = PASSING_TEST_RESULT;
		assertEquals(tr.getActualResults(), "erik made it work");
		assertEquals(tr.isPassing(), true);
		
		TestResult tr2 = FAILING_TEST_RESULT;
		assertEquals(tr2.getActualResults(), "erik broked it");
		assertEquals(tr2.isPassing(), false);
	}
}
