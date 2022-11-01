package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Concrete class for creating the list of Failing Test Cases, which is a list
 * of all test cases marked as failing across all of the loaded System Test
 * Plans. This class will inherit the AbstractTestPlan class because it has a
 * lot of identical fields with the other inherited class, TestPlan.
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 */
public class FailingTestList extends AbstractTestPlan {
	/** String for the title of the list of failing tests */
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";

	/**
	 * Constructor method for the list of Failing Tests
	 */
	public FailingTestList() {
		// TODO Give Erik a kiss on the cheek <3
	}

	/**
	 * Method for adding a TestCase to the list of Failing Tests
	 * 
	 * @param t TestCase being added
	 */
	public void addTestCase(TestCase t) {
		// TODO fill in
	}

	/**
	 * Method for setting a new name for the TestPlan given a String
	 * 
	 * @param testPlanName New TestPlan name given as a String
	 */
	public void setTestPlanName(String testPlanName) {
		// TODO fill in
	}

	/**
	 * Method for getting the TestCases as a 2D array
	 * 
	 * @return 2D array of Strings of TestCase names and their corresponding
	 *         important fields.
	 */
	public String[][] getTestCasesAsArray() {
		return null;
		// TODO fill in
	}
	
	/**
	 * Method for resetting the list of Failing Tests
	 */
	public void clearTests() {
		// TODO fill in
	}
}
