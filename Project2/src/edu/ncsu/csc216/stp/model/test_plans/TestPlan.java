package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Concrete class which handles the creation of System Test Plans, as well as
 * storing their data. This class extends the AbstractTestPlan class because it
 * has a lot of identical methods for storing data as compared to the other
 * class which inherits the AbstractTestPlan, the FailingTestList.
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 */
public class TestPlan extends AbstractTestPlan implements Comparable {

	/**
	 * Constructor for a TestPlan object
	 */
	public TestPlan() {
		// TODO fill in
	}

	/**
	 * Method for returning the list of TestCases as a 2D array with their
	 * corresponding important fields.
	 * 
	 * @return 2D String of the TestCase names and their important fields
	 */
	public String[][] getTestCasesAsArray() {
		// TODO fill in
		return null;
	}

	/**
	 * Method for adding a TestCase object to a TestPlan.
	 * 
	 * @param t TestCase object being added to the TestPlan
	 */
	public void addTestCase(TestCase t) {
		// TODO fill in
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
