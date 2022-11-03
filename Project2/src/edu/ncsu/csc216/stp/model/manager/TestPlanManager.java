package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;

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

	/**
	 * Constructor for the TestPlanManager singleton method
	 */
	public TestPlanManager() {
		// TODO fill in
	}

	/**
	 * Method for loading a list of TestPlans from a specified file
	 * 
	 * @param testPlanFile The File from which the TestPlans are being loaded
	 */
	public void loadTestPlans(File testPlanFile) {
		// TODO fill in
	}

	/**
	 * Method for saving the current list of TestPlans to a specified file
	 * 
	 * @param testPlanFile The File which the list of TestPlans are being saved to
	 */
	public void saveTestPlans(File testPlanFile) {
		// TODO fill in
	}

	/**
	 * Method for determining if the current state of the system has been changed
	 * 
	 * @return Boolean of whether or not the state of the system has been changed
	 */
	public boolean isChanged() {
		return false;
		// TODO fill in
	}

	/**
	 * Method for adding a Test Plan to the list of TestPlans
	 * 
	 * @param testPlanName Name of the TestPlan being added
	 */
	public void addTestPlan(String testPlanName) {
		// TODO fill in
	}

	/**
	 * Returns a 1D array of Strings that list the TestPlan names. This is primarily
	 * used in the GUI to show the possible TestPlans a User can select to choose.
	 * 
	 * @return 1D array of Strings of the names of the TestPlans
	 */
	public String[] getTestPlanNames() {
		return null;
		// TODO fill in
	}

	/**
	 * Updates the list of failing tests
	 */
	private void getFailingTests() {
		// TODO fill in
	}

	/**
	 * Method for setting the current TestPlan to whatever TestPlan the User has
	 * selected.
	 * 
	 * @param testPlanName Name of the TestPlan the User has selected
	 */
	public void setCurrentTestPlan(String testPlanName) {
		// TODO fill in
	}

	/**
	 * Returns the currently selected TestPlan as an AbstractTestPlan for the User
	 * to view.
	 * 
	 * @return The currently selected TestPlan as an AbstractTestPlan
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		// TODO fill in
		return null;
	}

	/**
	 * Method for editing a TestPlan the User has selected.
	 * 
	 * @param testPlanName Name of the TestPlan the User wants to edit
	 */
	public void editTestPlan(String testPlanName) {
		// TODO fill in
	}

	/**
	 * Method for removing the currently selected TestPlan from the list of
	 * TestPlans
	 */
	public void removeTestPlan() {
		// TODO fill in
	}

	/**
	 * Method for adding a TestCase to the currently selected TestPlan
	 * 
	 * @param t The TestCase being added to the currently selected TestPlan
	 */
	public void addTestCase(TestCase t) {
		// TODO fill in
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
		// TODO fill in
	}
	
}
