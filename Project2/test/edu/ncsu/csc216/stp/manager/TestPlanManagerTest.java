package edu.ncsu.csc216.stp.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.manager.TestPlanManager;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;

class TestPlanManagerTest {

	@Test
	void testNewSTPManager() {
		TestPlanManager tp = new TestPlanManager();
		assertEquals(tp.getTestPlanNames().length, 1); // Should be 1 since failing test list was initialized
		assertEquals(tp.getTestPlanNames()[0], FailingTestList.FAILING_TEST_LIST_NAME);
		assertEquals(tp.getCurrentTestPlan().getTestPlanName(), FailingTestList.FAILING_TEST_LIST_NAME);
	}

	@Test
	void testAddTestPlan() {
		TestPlanManager tp = new TestPlanManager();
		tp.addTestPlan("test plan 1");
		assertEquals(tp.getTestPlanNames().length, 2);
		assertEquals(tp.getTestPlanNames()[0], FailingTestList.FAILING_TEST_LIST_NAME);
		assertEquals(tp.getTestPlanNames()[1], "test plan 1");
		assertEquals(tp.getCurrentTestPlan().getTestPlanName(), "test plan 1");
		assertThrows(IllegalArgumentException.class, () -> tp.addTestPlan(FailingTestList.FAILING_TEST_LIST_NAME));
		assertThrows(IllegalArgumentException.class, () -> tp.addTestPlan("test plan 1"));
	}

	@Test
	void testRemoveTestPlan() {
		TestPlanManager tp = new TestPlanManager();
		tp.addTestPlan("test plan 1");
		tp.removeTestPlan();
		assertEquals(tp.getTestPlanNames().length, 1);
		assertEquals(tp.getCurrentTestPlan().getTestPlanName(), FailingTestList.FAILING_TEST_LIST_NAME);
	}

	@Test
	void testSetCurrentTestPlan() {
		TestPlanManager tp = new TestPlanManager();
		tp.addTestPlan("test plan 1");
		tp.addTestPlan("test plan 2");
		// The last added TestPlan should be the current TestPlan
		assertEquals(tp.getCurrentTestPlan().getTestPlanName(), "test plan 2");
		tp.setCurrentTestPlan(FailingTestList.FAILING_TEST_LIST_NAME);
		assertEquals(tp.getCurrentTestPlan().getTestPlanName(), FailingTestList.FAILING_TEST_LIST_NAME);
		tp.setCurrentTestPlan("test plan 1");
		assertEquals(tp.getCurrentTestPlan().getTestPlanName(), "test plan 1");
		// If there is no test plan with the given name, the failing test list will
		// be the current TestPlan
		tp.setCurrentTestPlan("something dumb");
		assertEquals(tp.getCurrentTestPlan().getTestPlanName(), FailingTestList.FAILING_TEST_LIST_NAME);
	}

}
