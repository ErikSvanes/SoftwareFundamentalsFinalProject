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
			throw new IllegalArgumentException("Unable to load file.");
		}
		String file = new String();
		while (scan.hasNextLine()) {
			file = file + scan.nextLine() + "\n";
		}
		Scanner testPlanReader = new Scanner(file);
		Scanner firstLine = new Scanner(file);
		testPlanReader.useDelimiter("\\r?\\n?[!]");
		if (!firstLine.next().contains("!")) {
			firstLine.close();
			scan.close();
			testPlanReader.close();
			throw new IllegalArgumentException("Unable to load file.");
		}
		while (testPlanReader.hasNext()) {
			TestPlan tp = processTestPlan(testPlanReader.next());
			if (tp != null) {
				testPlans.add(tp);
			}
		}

		firstLine.close();
		scan.close();
		testPlanReader.close();
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
			} else if (tc == null) {
				tpRead.close();
				return tp;
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
		String testCaseId = new String();
		String testType = new String();
		Scanner tcRead = new Scanner(testCase);
		String firstLine = tcRead.nextLine().substring(1);
		Scanner firstTwo = new Scanner(firstLine);
		firstTwo.useDelimiter(",");
		if (!firstTwo.hasNext()) {
			firstTwo.close();
			tcRead.close();
			return null;
		} else if (firstTwo.hasNext()) {
			testCaseId = firstTwo.next();
		}

		if (!firstTwo.hasNext()) {
			firstTwo.close();
			tcRead.close();
			return null;
		} else if (firstTwo.hasNext()) {
			testType = firstTwo.next();
		}

		tcRead.useDelimiter("[*]");
		String testDescription = tcRead.next().substring(1).trim();
		if (!tcRead.hasNext()) {
			firstTwo.close();
			tcRead.close();
			return null;
		}
		String expInit = tcRead.next().substring(1);

		Scanner expResRead = new Scanner(expInit);
		Scanner extras = new Scanner(expInit);
		String expectedResults = new String();
		while (expResRead.hasNextLine()) {
			String temp = expResRead.nextLine();
			if (temp.isBlank() || temp.isEmpty()) {
				expResRead.close();
				extras.close();
				tcRead.close();
				firstTwo.close();
				return null;
			}
			if (temp.charAt(0) != '-') {
				if (expectedResults.isEmpty()) {
					expectedResults += temp;
				} else {
					expectedResults += "\n" + temp;
				}
			} else if (temp.charAt(0) == '-') {
				break;
			}
		}
		expectedResults.trim();
		firstTwo.close();
		expResRead.close();
		if (expectedResults.isBlank() || expectedResults.isEmpty() || testDescription.isEmpty()
				|| testDescription.isBlank()) {
			tcRead.close();
			extras.close();
			return null;
		}
		TestCase tc = new TestCase(testCaseId, testType, testDescription, expectedResults);
		extras.useDelimiter("\\r?\\n?[-]");
		extras.next();
		while (extras.hasNext()) {
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
			} else if (!result.contains("PASS:") && !result.contains("FAIL:")) {
				tcRead.close();
				extras.close();
				return null;
			}
		}
		extras.close();
		firstTwo.close();
		tcRead.close();
		tc.setTestPlan((TestPlan) plan);
		return tc;
	}

}
