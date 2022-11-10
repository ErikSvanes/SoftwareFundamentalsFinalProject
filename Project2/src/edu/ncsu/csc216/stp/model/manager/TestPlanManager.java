package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Concrete class which stores the Test Plans being worked on by the User, as
 * well as the list of Failing Test Cases. This is the class which will be
 * utilized by the GUI the most, as it handles all the fields and Strings seen
 * by the User in the UI panel.
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 */
public class TestPlanManager {
	/** Boolean for whether or not the system has been changed */
	private boolean isChanged;
	/** SortedList of test plans */
	ISortedList<TestPlan> testPlans;
	/** List of failing test cases */
	FailingTestList failList;
	/** AbstractTestPlan for the Current Test Plan the User has selected */
	AbstractTestPlan currentTestPlan;

	/**
	 * Constructor for the TestPlanManager singleton method
	 */
	public TestPlanManager() {
		testPlans = new SortedList<TestPlan>(); // Initialize the testPlan
		failList = new FailingTestList(); // Initialize the list of failing tests
		setCurrentTestPlan(failList.getTestPlanName());
		isChanged = false; // Since this has just been constructed, the project has yet to be changed
		// TODO fill in
	}

	/**
	 * Method for loading a list of TestPlans from a specified file
	 * 
	 * @param testPlanFile The File from which the TestPlans are being loaded
	 */
	public void loadTestPlans(File testPlanFile) {
		// TODO fill in
		isChanged = true;
	}

	/**
	 * Method for saving the current list of TestPlans to a specified file
	 * 
	 * @param testPlanFile The File which the list of TestPlans are being saved to
	 */
	public void saveTestPlans(File testPlanFile) {
		// TODO fill in
		isChanged = false;
	}

	/**
	 * Method for determining if the current state of the system has been changed
	 * 
	 * @return Boolean of whether or not the state of the system has been changed
	 */
	public boolean isChanged() {
		return isChanged;
		// TODO Verify this is correct
	}

	/**
	 * Method for adding a Test Plan to the list of TestPlans
	 * 
	 * @param testPlanName Name of the TestPlan being added
	 */
	public void addTestPlan(String testPlanName) {
		if (testPlanName == FailingTestList.FAILING_TEST_LIST_NAME) { // New Test Plan's name cannot be the same as the
			throw new IllegalArgumentException("Invalid name."); // list of failing tests
		}
		for (int i = 0; i < testPlans.size(); i++) { // Cannot have duplicate TestPlan names
			if (testPlans.get(i).getTestPlanName() == testPlanName) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		TestPlan tp = new TestPlan(testPlanName);
		testPlans.add(tp);
		setCurrentTestPlan(testPlanName);

		// TODO Actually add the TestPlan to the list of TestPlans
		isChanged = true;
	}

	/**
	 * Returns a 1D array of Strings that list the TestPlan names. This is primarily
	 * used in the GUI to show the possible TestPlans a User can select to choose.
	 * 
	 * @return 1D array of Strings of the names of the TestPlans
	 */
	public String[] getTestPlanNames() {
		String[] names = new String[testPlans.size() + 1];
		names[0] = FailingTestList.FAILING_TEST_LIST_NAME;
		for (int i = 0; i < testPlans.size(); i++) {
			names[i + 1] = testPlans.get(i).getTestPlanName();
		}
		return names;
		// TODO fill in
	}

	/**
	 * Updates the list of failing tests
	 */
	private void getFailingTests() {
		FailingTestList fail = new FailingTestList();
		fail.clearTests();
		// TODO I dont know what to do here, fix later
	}

	/**
	 * Method for setting the current TestPlan to whatever TestPlan the User has
	 * selected.
	 * 
	 * @param testPlanName Name of the TestPlan the User has selected
	 */
	public void setCurrentTestPlan(String testPlanName) {
		for (int i = 0; i < testPlans.size(); i++) { // Find a TestPlan with the name given
			if (testPlanName == testPlans.get(i).getTestPlanName()) {
				currentTestPlan = testPlans.get(i);
				return; // End the loop so the current is not set the the list of failing tests
			}
		}
		currentTestPlan = failList; // If the TestPlan cannot be found, set current to list of failing tests
		// TODO Test this is done correctly
	}

	/**
	 * Returns the currently selected TestPlan as an AbstractTestPlan for the User
	 * to view.
	 * 
	 * @return The currently selected TestPlan as an AbstractTestPlan
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		if (currentTestPlan.equals(failList)) {
			getFailingTests(); // TODO This probably isnt right, fix later
		}
		return currentTestPlan;
		// TODO Test this is correct
	}

	/**
	 * Method for editing a TestPlan the User has selected.
	 * 
	 * @param testPlanName Name of the TestPlan the User wants to edit
	 */
	public void editTestPlan(String testPlanName) {
		if (currentTestPlan.getTestPlanName() == FailingTestList.FAILING_TEST_LIST_NAME
				|| currentTestPlan instanceof FailingTestList) {
			throw new IllegalArgumentException("Invalid name."); // Current TestPlan cannot be a list of failing tests
		}
		for (int i = 0; i < testPlans.size(); i++) {
			if (currentTestPlan.getTestPlanName() == testPlans.get(i).getTestPlanName()) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		// TODO fill in
	}

	/**
	 * Method for removing the currently selected TestPlan from the list of
	 * TestPlans
	 */
	public void removeTestPlan() {
		if (currentTestPlan instanceof FailingTestList) { // Cannot remove the list of Failing test Cases
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}
		for (int i = 0; i < testPlans.size(); i++) { // Search for the current TestPlan's ID
			if (testPlans.get(i).getTestPlanName() == currentTestPlan.getTestPlanName()) {
				testPlans.remove(i);
			}
		}
		// TODO Test this is correct
		currentTestPlan = failList;
	}

	/**
	 * Method for adding a TestCase to the currently selected TestPlan
	 * 
	 * @param t The TestCase being added to the currently selected TestPlan
	 */
	public void addTestCase(TestCase t) {
		if (currentTestPlan instanceof FailingTestList) { // Cannot add a test case to the list of failing tests
			return;
		} else if (currentTestPlan instanceof TestPlan) {
			currentTestPlan.addTestCase(t);
			if (!t.isTestCasePassing()) {
				failList.addTestCase(t);
				// TODO Test this works
			}
		}
		// TODO fill in
		isChanged = true;
	}

	/**
	 * Method for adding a TestResult to the currently selected TestPlan given an
	 * index, status of passing, and actual results
	 * 
	 * @param idx          Index of the Test Result
	 * @param passing      Boolean value of whether the TestCase is passing or not
	 * @param actualResult String of the Actual Results
	 */
	public void addTestResult(int idx, boolean passing, String actualResult) {
		// TODO fill in
	}

	/**
	 * Method for clearing the failing test list of all Test Cases across all Test
	 * Plans
	 */
	public void clearTestPlans() {
		testPlans = new SortedList<TestPlan>(); // Empty the list of TestPlans
		failList = new FailingTestList(); // Clear the list of Failing Test Cases
		currentTestPlan = failList; // Set the current TestPlan to the list of failing tests
		isChanged = false; // isChanged will not be false
		// TODO Test this is correct
	}

}
