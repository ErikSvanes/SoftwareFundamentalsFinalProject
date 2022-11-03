package edu.ncsu.csc216.stp.model.io;

import java.io.File;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Concrete class which will handle reading in files that are inputed by the
 * User, which will then construct System Test Plans and the Test Cases which
 * belong to them.
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 */
public class TestPlanReader {

	/**
	 * Constructor for TestPlanReader
	 */
	public TestPlanReader() {
		// TODO fill in
	}

	/**
	 * Method for reading in Test Plans from a given file path
	 * 
	 * @return The parsed System Test Plans as Sorted Lists of TestPlans
	 * @param f File being read
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File f) {
		try {
			// TODO fill in
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file");
		}
		return null;
	}

	/**
	 * Method for processing individual TestPlans as Strings
	 * 
	 * @param testPlan the String which makes up a TestPlan
	 * @return The String read in as a TestPlan object
	 */
	private static TestPlan processTestPlan(String testPlan) {
		return null;
	}

	/**
	 * Method for processing Test Cases that make up TestPlans as Strings
	 * 
	 * @param plan     The TestPlan which is read in
	 * @param testCase The String which is read in to be processed
	 * @return The String read in as a TestCase object
	 */
	private static TestCase processTest(AbstractTestPlan plan, String testCase) {
		return null;
	}

}
