package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Concrete class which handles writing System Test Plans and the Test Cases
 * which belong to them to a text file, allowing for the User to export their
 * data to a drive and be read in/ modified later using the TestPlanReader.
 * 
 * @author William Hazlehurst, Erik Svanes
 *
 */
public class TestPlanWriter {

	/**
	 * Constructor for the TestPlanWriter class
	 */
	public TestPlanWriter() {
		// Left blank on purpose
	}

	/**
	 * Method that handles all of the file output that the program needs. It takes
	 * in a file to write to, and the list of test plans to write in that file.
	 * 
	 * @param f         the file that the method will write to
	 * @param testPlans the ISortedList of test plans to write in the file
	 * @throws IllegalArgumentException if there is any errors or exceptions while
	 *                                  writing to the file
	 */
	public static void writeTestPlanFile(File f, ISortedList<TestPlan> testPlans) {
		PrintStream fileWriter;
		try {
			fileWriter = new PrintStream(f);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file");
		}
		
		TestPlan tp = null;
		for (int i = 0; i < testPlans.size(); i++) {
			tp = testPlans.get(i);
			fileWriter.println("! " + tp.getTestPlanName());
			for (int j = 0; j < tp.getTestCases().size(); j++) {
				fileWriter.print(tp.getTestCases().get(i).toString());
			}
		}
		fileWriter.close();
	}

}
