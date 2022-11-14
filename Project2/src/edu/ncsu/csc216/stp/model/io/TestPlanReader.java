package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

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
		Scanner scan;
		SortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file");
		}
		String file = new String();
		while (scan.hasNextLine()) {
			file = file + scan.nextLine() + "\n";
		}
		Scanner testPlanReader = new Scanner(file);
		testPlanReader.useDelimiter("\\r?\\n?[!]");
		while (testPlanReader.hasNext()) {
			TestPlan tp = processTestPlan(testPlanReader.next());
			testPlans.add(tp);
		}

		scan.close();
		testPlanReader.close();
		if(testPlans.size() == 0) {
			throw new IllegalArgumentException("Unable to load file");
		}
		return testPlans;
	}

	/**
	 * Method for processing individual TestPlans as Strings
	 * 
	 * @param testPlan the String which makes up a TestPlan
	 * @return The String read in as a TestPlan object
	 */
	private static TestPlan processTestPlan(String testPlan) {
		Scanner tpRead = new Scanner(testPlan);
		String title = tpRead.next();
		TestPlan tp = new TestPlan(title);
		tpRead.useDelimiter("\\r?\\n?[#]");
		while (tpRead.hasNext()) {
			TestCase tc = processTest(tp, tpRead.next());
			if (tc != null) {
				tp.addTestCase(tc);
			}
		}
		tpRead.close();
		return tp;
	}

	/**
	 * Method for processing Test Cases that make up TestPlans as Strings
	 * 
	 * @param plan     The TestPlan which is read in
	 * @param testCase The String which is read in to be processed
	 * @return The String read in as a TestCase object
	 */
	private static TestCase processTest(AbstractTestPlan plan, String testCase) {
		Scanner tcRead = new Scanner(testCase);
		String firstLine = tcRead.nextLine();
		firstLine = firstLine.substring(1);
		Scanner firstLineRead = new Scanner(firstLine);
		firstLineRead.useDelimiter(",");
		
		String testCaseId = firstLineRead.next();
		String testType = firstLineRead.next();
		
		tcRead.useDelimiter("\\r?\\n?[*]");
		String testDescription = tcRead.next();
		String expectedResults = tcRead.next();
		//expectedResults = expectedResults.replace("\n", " ");
		TestCase tc = new TestCase(testCaseId, testType, testDescription, expectedResults);
		Scanner extras = new Scanner(expectedResults);
		extras.useDelimiter("\\r?\\n?[-]");
		while(extras.hasNext()) {
			boolean isPassing;
			String result = extras.next().substring(1);
			if (result.contains("FAIL:")) {
				isPassing = false;
				result = result.substring(6);
				tc.addTestResult(isPassing, result);
			} else if (result.contains("PASS:")) {
				isPassing = true;
				result = result.substring(6);
				tc.addTestResult(isPassing, result);
			}
		}
		
		firstLineRead.close();
		tcRead.close();
		extras.close();
		return tc;
	}

}
