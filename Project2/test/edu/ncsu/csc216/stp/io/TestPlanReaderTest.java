package edu.ncsu.csc216.stp.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;

class TestPlanReaderTest {

	@Test
	void testProjectReader() {
		File file = new File("test-files/provided/test-plans0.txt");
		TestPlanReader.readTestPlansFile(file);
	}

}
