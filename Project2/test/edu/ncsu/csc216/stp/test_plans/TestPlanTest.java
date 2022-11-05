package edu.ncsu.csc216.stp.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

class TestPlanTest {
	/** Valid TestPlan instantiation */
	private static final TestPlan TP = new TestPlan("test");

	@Test
	void testNewTestPlan() {
		TestPlan tp = TP;
		assertEquals(tp.getTestPlanName(), "test");
		assertEquals(tp.getNumberOfFailingTests(), 0);
	}
	
	@Test
	void testInvalidTestPlan() {
		assertThrows(IllegalArgumentException.class,)
	}

}
