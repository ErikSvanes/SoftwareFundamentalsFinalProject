package edu.ncsu.csc216.stp.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;

class TestPlanTest {
	/** Valid TestPlan instantiation */
	private static final TestPlan TP = new TestPlan("test");
	/** Valid TestCase to be used for testing */
	private static final TestCase VALID_TEST_CASE1 = new TestCase("id1", "type1", "desc1", "results1");
	/** Valid TestCase to be used for testing */
	private static final TestCase VALID_TEST_CASE2 = new TestCase("id2", "type2", "desc2", "results2");
	/** Valid TestCase to be used for testing */
	private static final TestCase VALID_TEST_CASE3 = new TestCase("id3", "type3", "desc3", "results3");

	@Test
	void testNewTestPlan() {
		TestPlan tp = TP;
		assertEquals(tp.getTestPlanName(), "test");
		assertEquals(tp.getNumberOfFailingTests(), 0);
	}
	
	@Test
	void testInvalidTestPlan() {
		assertThrows(IllegalArgumentException.class, () -> new TestPlan("Failing Tests"));
	}
	
	@Test
	void testGetTestCasesAsArray() {
		TestPlan tp = TP;
		TestCase tc2 = VALID_TEST_CASE2;
		TestCase tc3 = VALID_TEST_CASE3;
		tc2.addTestResult(true, "it works");
		tc3.addTestResult(false, "it dont work");
		tp.addTestCase(VALID_TEST_CASE1);
		tp.addTestCase(tc2);
		tp.addTestCase(tc3);
		String[][] out = tp.getTestCasesAsArray();
		assertEquals(out[0][0], "id1");
		assertEquals(out[0][1], "type1");
		assertEquals(out[0][2], "FAIL");
		assertEquals(out[1][0], "id2");
		assertEquals(out[1][1], "type2");
		assertEquals(out[1][2], "PASS");
		assertEquals(out[2][0], "id3");
		assertEquals(out[2][1], "type3");
		assertEquals(out[2][2], "FAIL");
	}
	
	@Test
	void testCompareTo() {
		TestPlan tp = new TestPlan("abcdef");
		TestPlan tp2 = new TestPlan("bcd");
		TestPlan tp3 = new TestPlan("abc");
		assertEquals(tp.compareTo(tp2), -1);
		assertEquals(tp2.compareTo(tp), 1);
		assertEquals(tp3.compareTo(tp), -1);
	}
	
	@Test
	void testNewInvalidTestPlan() {
		assertThrows(IllegalArgumentException.class, () -> new TestPlan(null));
		assertThrows(IllegalArgumentException.class, () -> new TestPlan(""));
	}
	
	@Test
	void testGetTestCases() {
		TestPlan tp = new TestPlan("test");
		tp.addTestCase(VALID_TEST_CASE1);
		tp.addTestCase(VALID_TEST_CASE2);
		tp.addTestCase(VALID_TEST_CASE3);
		assertEquals(tp.getTestCases().get(0).getTestCaseId(), "id1");
		assertEquals(tp.getTestCases().get(1).getTestCaseId(), "id2");
		assertEquals(tp.getTestCases().get(2).getTestCaseId(), "id3");
	}
	
	@Test
	void testRemoveTestCase() {
		TestPlan tp = new TestPlan("test");
		tp.addTestCase(VALID_TEST_CASE1);
		tp.addTestCase(VALID_TEST_CASE2);
		assertEquals(tp.removeTestCase(0).getTestCaseId(), "id1");
		assertEquals(tp.getTestCasesAsArray().length, 1);
	}
	
	@Test
	void testGetTestCase() {
		TestPlan tp = new TestPlan("test");
		tp.addTestCase(VALID_TEST_CASE1);
		assertEquals(tp.getTestCase(0).getTestCaseId(), "id1");
	}
	
	@Test
	void testGetFailingTests() {
		TestPlan tp = new TestPlan("test");
		TestCase tc1 = VALID_TEST_CASE1;
		tc1.addTestResult(false, "erik pls stop ):");
		TestCase tc2 = VALID_TEST_CASE2;
		tc2.addTestResult(true, "erik i love you <3");
		TestCase tc3 = VALID_TEST_CASE3;
		tc3.addTestResult(false, "boof");
		tp.addTestCase(tc1);
		tp.addTestCase(tc2);
		tp.addTestCase(tc3);
		assertEquals(tp.getNumberOfFailingTests(), 2);
		tp.removeTestCase(0);
		assertEquals(tp.getNumberOfFailingTests(), 1);
	}
	
	@Test
	void testEquals() {
		TestPlan tp = new TestPlan("test");
		TestPlan tp2 = new TestPlan("Test");
		TestPlan tp3 = new TestPlan("Testtttt");
		assertTrue(tp.equals(tp2));
		assertFalse(tp.equals(tp3));
	}
	
	@Test
	void testHashCode() {
		TestPlan tp = new TestPlan("test");
		assertEquals(tp.hashCode(), 3556529);
	}

}
