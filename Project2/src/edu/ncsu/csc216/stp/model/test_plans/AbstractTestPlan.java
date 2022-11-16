package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * Abstract class which is used to create the two types of test plans, normal
 * System Test Plans and the list of failing tests cases. This method will be
 * using inheritance to simplify data storing and accessing, since the two types
 * of Test Plans share a marginal amount of data and construction. This class is
 * abstract because it will never actually be instantiated, but rather, used to
 * shorten the amount of work to create the two types of Test Plans.
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 */
public abstract class AbstractTestPlan {
	/** Private String of the TestPlan's name */
	private String testPlanName;
	/** SwapList of all testCases for a TestPlan */
	SwapList<TestCase> testCases;

	/**
	 * Constructor for an AbstractTestPlan
	 * 
	 * @param testPlanName String the Test Plan's name is being set to
	 * @throws IllegalArgumentException If the parameterized name is null or empty.
	 */
	public AbstractTestPlan(String testPlanName) {
		if (testPlanName == null || testPlanName.isEmpty()) {
			throw new IllegalArgumentException("Invalid name.");
		}
		setTestPlanName(testPlanName);
		testCases = new SwapList<TestCase>();
	}

	/**
	 * Method for setting the TestPlan's name as a given String
	 * 
	 * @param testPlanName String of the TestPlan's new name
	 */
	public void setTestPlanName(String testPlanName) {
		this.testPlanName = testPlanName;
	}

	/**
	 * Method for returning the TestPlan's name
	 * 
	 * @return String of the TestPlan's name
	 */
	public String getTestPlanName() {
		return testPlanName;
	}

	/**
	 * Method for returning the TestCases of a TestPlan as an ISwapList of TestCases
	 * 
	 * @return ISwapList of TestCases
	 */
	public ISwapList<TestCase> getTestCases() {
		return testCases;
	}

	/**
	 * Method for adding a TestCase object to the TestPlan
	 * 
	 * @param t TestCase object being added to the TestPlan
	 */
	public void addTestCase(TestCase t) {
		t.setTestPlan((TestPlan) this);
		testCases.add(t);
	}

	/**
	 * Method for removing a specified TestCase from the list of TestCases
	 * 
	 * @param idx index of TestCase being removed
	 * @return the TestCase which was removed
	 */
	public TestCase removeTestCase(int idx) {
		TestCase removed = testCases.get(idx);
		testCases.remove(idx);
		return removed;
	}

	/**
	 * Method for getting a TestCase given its index as an integer
	 * 
	 * @param idx Index of the TestCase as an integer
	 * @return The TestCase of the given index
	 */
	public TestCase getTestCase(int idx) {
		return testCases.get(idx);
	}

	/**
	 * Method for getting the number of failing tests in the list of TestPlans
	 * 
	 * @return The number of failing tests in the list of TestPlans as an integer
	 */
	public int getNumberOfFailingTests() {
		int failCount = 0;
		for (int i = 0; i < testCases.size(); i++) {
			if (!this.getTestCase(i).isTestCasePassing()) {
				failCount++;
			}
		}
		return failCount;
	}

	/**
	 * Method for adding a TestResult to a TestCase given an index, a boolean
	 * whether it is passing or not, and the actual Results as a String.
	 * 
	 * @param idx           Index of the TestCase which results are being added to
	 * @param passing       Boolean of whether or not the TestCase is passing or not
	 * @param actualResults String of the actual results
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		testCases.get(idx).addTestResult(passing, actualResults);
		// TODO Need to fix, SwapList doesnt have an index option for add
	}

	/**
	 * Method for getting the TestCases as a 2D array of Strings. This is primarily
	 * used in displaying the information to the User via the GUI.
	 * 
	 * @return A 2D array of Strings to the User of the TestCases and their
	 *         corresponding important fields
	 */
	public abstract String[][] getTestCasesAsArray();

	/**
     * Overridden hashCode method for testPlanName.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((testPlanName == null) ? 0 : testPlanName.hashCode());
        return result;
    }

	@Override
	public boolean equals(Object obj) {
		AbstractTestPlan other = (AbstractTestPlan) obj;
		return testPlanName.equalsIgnoreCase(other.testPlanName);
	}

}
