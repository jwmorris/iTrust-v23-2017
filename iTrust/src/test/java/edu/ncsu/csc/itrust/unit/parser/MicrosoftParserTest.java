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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessMySQL;
import edu.ncsu.csc.itrust.parser.MicrosoftParser;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

public class MicrosoftParserTest {
	private UploadedFile file;
	private UploadedFile file2;
	private UploadedFile file3;
	private FitnessMySQL sql;
	private MicrosoftParser parser;
	private MicrosoftParser parser2;
	private MicrosoftParser parser3;

	@Before
	public void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		//Got help from http://stackoverflow.com/questions/18076454/how-to-test-junit-test-case-for-primefaces-uploadedfile-component
		File f = new File("../iTrust/testing-files/fitness_data/MS_Band_Data.csv");
		File f2 = new File("../iTrust/testing-files/fitness_data/microsoft_comma.csv");
		File f3 = new File("../iTrust/testing-files/fitness_data/diabolicalMicrosoft.csv");
		InputStream is = new FileInputStream(f);
		InputStream is2 = new FileInputStream(f2);
		InputStream is3 = new FileInputStream(f3);
		file = Mockito.mock(UploadedFile.class);
		file2 = Mockito.mock(UploadedFile.class);
		file3 = Mockito.mock(UploadedFile.class);
		Mockito.when(file.getInputstream()).thenReturn(is);
		Mockito.when(file2.getInputstream()).thenReturn(is2);
		Mockito.when(file3.getInputstream()).thenReturn(is3);
		sql = new FitnessMySQL(ConverterDAO.getDataSource());
		parser = new MicrosoftParser(file, ConverterDAO.getDataSource());
		parser2 = new MicrosoftParser(file2, ConverterDAO.getDataSource());
		parser3 = new MicrosoftParser(file3, ConverterDAO.getDataSource());
	}

	@Test
	public void testParse() throws IOException, DBException {
		parser.parse("2");
		List<Fitness> data = sql.getFitnessDataForPatient("2");
		assertNotNull(data);
		assertTrue(data.size() == 7);
		
	}
	
	@Test
	public void testParseFail() {
		try {
			new MicrosoftParser(file);
		} catch (Exception e) {
			//pass
		}
	}
	
	@Test
	public void testCommaParse() throws IOException, DBException {
		parser2.parse("2");
		List<Fitness> data = sql.getFitnessDataForPatient("2");
		assertNotNull(data);
		assertEquals(6, data.size());
	}

	@Test
	public void testDiabolicalFile() throws DBException, IOException {
		parser3.parse("2");
		List<Fitness> data = sql.getFitnessDataForPatient("2");
		assertNotNull(data);
		assertEquals(2, data.size());
	}
}
