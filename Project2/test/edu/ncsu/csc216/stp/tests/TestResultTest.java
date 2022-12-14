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
		assertTrue(tr.isPassing());
		
		TestResult tr2 = FAILING_TEST_RESULT;
		assertEquals(tr2.getActualResults(), "erik broked it");
		assertFalse(tr2.isPassing());
	}
	
	@Test
	void testResultToString() {
		TestResult tr1 = PASSING_TEST_RESULT;
		assertEquals("PASS: erik made it work", tr1.toString());
		TestResult tr2 = FAILING_TEST_RESULT;
		assertEquals("FAIL: erik broked it", tr2.toString());
	}
}
