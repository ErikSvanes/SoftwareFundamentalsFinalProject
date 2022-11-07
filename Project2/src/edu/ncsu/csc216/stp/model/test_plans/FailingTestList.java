package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.SwapList;

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
		super(FAILING_TEST_LIST_NAME);
		// TODO Give Erik a kiss on the cheek <3
		// TODO Test this works
	}

	/**
	 * Method for adding a TestCase to the list of Failing Tests
	 * 
	 * @param t TestCase being added
	 */
	@Override
	public void addTestCase(TestCase t) {
		if (t.isTestCasePassing()) {
			throw new IllegalArgumentException("Cannot add passing test case.");
		} else {
			testCases.add(t);
		}
		// TODO Test this works
	}

	/**
	 * Method for setting a new name for the TestPlan given a String
	 * 
	 * @param testPlanName New TestPlan name given as a String
	 */
	@Override
	public void setTestPlanName(String testPlanName) {
		if (testPlanName != FAILING_TEST_LIST_NAME) {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited");
		} else {
			// TODO Assign the testPlan name to the given value
		}
		// TODO fill in
	}

	/**
	 * Method for getting the TestCases as a 2D array
	 * 
	 * @return 2D array of Strings of TestCase names and their corresponding
	 *         important fields.
	 */
	public String[][] getTestCasesAsArray() {
		if(testCases == null) {
			return new String[0][0];
		} else {
			String[][] out = new String[testCases.size()][3];
			for(int i = 0; i < testCases.size(); i++) {
				out[i][0] = testCases.get(i).getTestCaseId();
				out[i][1] = testCases.get(i).getTestType();
				out[i][2] = testCases.get(i).getTestPlan().getTestPlanName();
			}
			return out;
		}
		// TODO Test this works
	}

	/**
	 * Method for resetting the list of Failing Tests
	 */
	public void clearTests() {
		testCases = new SwapList<TestCase>();
		// TODO Test this works
	}
}
