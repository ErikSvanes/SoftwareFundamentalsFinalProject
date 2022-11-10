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
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
	
	/**
	 * Constructor for a TestPlan object
	 * 
	 * @param testPlanName name of the Test Plan
	 */
	public TestPlan(String testPlanName) {
		super(testPlanName);
		if(testPlanName == FailingTestList.FAILING_TEST_LIST_NAME) {
			throw new IllegalArgumentException("Invalid name.");
		}
		// TODO Test this works
	}

	/**
	 * Method for returning the list of TestCases as a 2D array with their
	 * corresponding important fields.
	 * 
	 * @return 2D String of the TestCase names and their important fields
	 */
	public String[][] getTestCasesAsArray() {
		String[][] out = new String[testCases.size()][3];
		for(int i = 0; i < testCases.size(); i++) {
			out[i][0] = testCases.get(i).getTestCaseId();
			out[i][1] = testCases.get(i).getTestType();
			out[i][2] = testCases.get(i).getStatus();
		}
		// TODO Test this works
		return out;
	}

	/**
	 * Method for adding a TestCase object to a TestPlan.
	 * 
	 * @param t TestCase object being added to the TestPlan
	 */
	@Override
	public void addTestCase(TestCase t) {
		testCases.add(t);
		// TODO fill in
	}

	/**
	 * Method for comparing two TestPlans together
	 * 
	 * @param tp the TestPlan to be compared to the current instance of TestPlan
	 * @return an integer to express which TestPlan comes before the other one when
	 *         being sorted
	 */
	public int compareTo(TestPlan tp) {
		String a = this.getTestPlanName().toLowerCase();
		String b = tp.getTestPlanName().toLowerCase();
		int length = a.length();
	    if (a.length() != b.length()) {
	        if (a.length() < b.length()) {
	            length = a.length();
	        }
	        else {
	            length = b.length();
	        }
	    }
	    for(int i = 0; i < length; i++) {
	        int aVal = (int) a.charAt(i);
	        int bVal = (int) b.charAt(i);
	        if ( aVal - bVal != 0) {
	            return (aVal - bVal);
	        }
	    }
	    if (a.length() == b.length()) {
	        return 0;
	    }
	    else if (length == a.length()) {
	        return -1;
	    }
	    return 1;
	}

}
