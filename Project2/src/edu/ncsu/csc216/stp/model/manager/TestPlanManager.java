package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
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
	 * Constructor for the TestPlanManager singleton method. isChanged is set to
	 * false since this is the default list of TestPlans. The current TestPlan is
	 * the list of Failing Tests.
	 */
	public TestPlanManager() {
		testPlans = new SortedList<TestPlan>(); // Initialize the testPlan
		failList = new FailingTestList(); // Initialize the list of failing tests
		currentTestPlan = failList;
		isChanged = false; // Since this has just been constructed, the project has yet to be changed
	}

	/**
	 * Method for loading a list of TestPlans from a specified file. This will set
	 * isChanged to true since the User has loaded a new list of TestPlans
	 * 
	 * @param testPlanFile The File from which the TestPlans are being loaded
	 */
	public void loadTestPlans(File testPlanFile) {
		ISortedList<TestPlan> loaded = TestPlanReader.readTestPlansFile(testPlanFile);
		for(int i = 0; i < loaded.size(); i++) {
			try {
				testPlans.add(loaded.get(i));
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		setCurrentTestPlan(failList.getTestPlanName());
		isChanged = true;
	}

	/**
	 * Method for saving the current list of TestPlans to a specified file. This
	 * will set isChanged to false since the TestPlan has been saved
	 * 
	 * @param testPlanFile The File which the list of TestPlans are being saved to
	 */
	public void saveTestPlans(File testPlanFile) {
		TestPlanWriter.writeTestPlanFile(testPlanFile, testPlans);
		isChanged = false;
	}

	/**
	 * Method for determining if the current state of the system has been changed
	 * 
	 * @return Boolean of whether or not the state of the system has been changed
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * Method for adding a Test Plan to the list of TestPlans
	 * 
	 * @param testPlanName Name of the TestPlan being added
	 * @throws IllegalArgumentException When the name is "Failing Tests" or a
	 *                                  duplicate of another
	 */
	public void addTestPlan(String testPlanName) {
		if (testPlanName.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) { // New Test
																										// Plan's name
																										// cannot be the
																										// same as the
			throw new IllegalArgumentException("Invalid name."); // list of failing tests
		}
		for (int i = 0; i < testPlans.size(); i++) { // Cannot have duplicate TestPlan names
			if (testPlans.get(i).getTestPlanName().toLowerCase().equals(testPlanName.toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		TestPlan tp = new TestPlan(testPlanName);
		testPlans.add(tp);
		setCurrentTestPlan(testPlanName);

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
	}

	/**
	 * Updates the list of failing tests
	 */
	private void getFailingTests() {
		failList = new FailingTestList();
		for (int i = 0; i < testPlans.size(); i++) {
			for (int j = 0; j < testPlans.get(i).getTestCases().size(); j++) {
				if (!testPlans.get(i).getTestCase(j).isTestCasePassing()) {
					failList.addTestCase(testPlans.get(i).getTestCase(j));
				}
			}
		}
	}

	/**
	 * Method for setting the current TestPlan to whatever TestPlan the User has
	 * selected.
	 * 
	 * @param testPlanName Name of the TestPlan the User has selected
	 */
	public void setCurrentTestPlan(String testPlanName) {
		for (int i = 0; i < testPlans.size(); i++) { // Find a TestPlan with the name given
			if (testPlanName == FailingTestList.FAILING_TEST_LIST_NAME) {
				getFailingTests();
				currentTestPlan = failList;
			} else if (testPlanName.equals(testPlans.get(i).getTestPlanName())) {
				currentTestPlan = testPlans.get(i);
				return; // End the loop so the current is not set the the list of failing tests
			}
		}
		currentTestPlan = failList; // If the TestPlan cannot be found, set current to list of failing tests
	}

	/**
	 * Returns the currently selected TestPlan as an AbstractTestPlan for the User
	 * to view.
	 * 
	 * @return The currently selected TestPlan as an AbstractTestPlan
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		if (currentTestPlan.equals(failList)) {
			getFailingTests(); // TODO This probably isn't right, fix later
		}
		return currentTestPlan;
	}

	/**
	 * Method for editing a TestPlan the User has selected.
	 * 
	 * @param testPlanName Name of the TestPlan the User wants to edit
	 * @throws IllegalArgumentException When the current TestPlan is the Failing
	 *                                  Tests or if there the name is a duplicate of
	 *                                  another
	 */
	public void editTestPlan(String testPlanName) {
		if (testPlanName.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())
				|| testPlanName.equals(currentTestPlan.getTestPlanName())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		if (currentTestPlan.getTestPlanName().equals(FailingTestList.FAILING_TEST_LIST_NAME)
				|| currentTestPlan instanceof FailingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited."); // Current TestPlan cannot
																								// be a list of failing
																								// tests
		}
		TestPlan newTestPlan = (TestPlan) currentTestPlan;
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlanName.equals(testPlans.get(i).getTestPlanName()) && testPlans.get(i) != currentTestPlan) {
				throw new IllegalArgumentException("Invalid name.");
			}
			newTestPlan.setTestPlanName(testPlanName);
			currentTestPlan = newTestPlan;
		}
	}

	/**
	 * Method for removing the currently selected TestPlan from the list of
	 * TestPlans
	 * 
	 * @throws IllegalArgumentException When the User attempts to delete the Failing
	 *                                  Tests list
	 */
	public void removeTestPlan() {
		if (currentTestPlan instanceof FailingTestList) { // Cannot remove the list of Failing test Cases
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}
		for (int i = 0; i < testPlans.size(); i++) { // Search for the current TestPlan's ID
			if (testPlans.get(i).getTestPlanName() == currentTestPlan.getTestPlanName()) {
				testPlans.remove(i);
			}
		}
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
			t.setTestPlan((TestPlan) currentTestPlan);
			currentTestPlan.addTestCase(t);
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
		currentTestPlan.addTestResult(idx, passing, actualResult);
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
	}

}
