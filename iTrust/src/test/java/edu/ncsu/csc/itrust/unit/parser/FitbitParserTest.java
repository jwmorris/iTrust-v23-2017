package edu.ncsu.csc.itrust.unit.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.model.UploadedFile;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessMySQL;
import edu.ncsu.csc.itrust.parser.FitbitParser;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

public class FitbitParserTest {

	private UploadedFile file;
	private FitnessMySQL sql;
	private FitbitParser parser;

	@Before
	public void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		//Got help from http://stackoverflow.com/questions/18076454/how-to-test-junit-test-case-for-primefaces-uploadedfile-component
		File f = new File("testing-files/fitness_data/fitbit_export_HW3.csv");
		InputStream is = new FileInputStream(f);
		file = Mockito.mock(UploadedFile.class);
		Mockito.when(file.getInputstream()).thenReturn(is);
		sql = new FitnessMySQL(ConverterDAO.getDataSource());
		parser = new FitbitParser(file, ConverterDAO.getDataSource());
	}

	@Test
	public void testParse() throws IOException, DBException {
		parser.parse("2");
		List<Fitness> data = sql.getFitnessDataForPatient("2");
		assertNotNull(data);
		assertTrue(data.size() == 31);
		
	}
	@Test
	public void testParseFail() {
		try {
			new FitbitParser(file);
		} catch (Exception e) {
			//pass
		}
	}

}
