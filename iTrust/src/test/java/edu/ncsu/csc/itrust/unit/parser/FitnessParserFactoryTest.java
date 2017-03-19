package edu.ncsu.csc.itrust.unit.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.ConverterDAO;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessMySQL;
import edu.ncsu.csc.itrust.parser.FitbitParser;
import edu.ncsu.csc.itrust.parser.FitnessParser;
import edu.ncsu.csc.itrust.parser.FitnessParserFactory;
import edu.ncsu.csc.itrust.parser.MicrosoftParser;
import edu.ncsu.csc.itrust.unit.datagenerators.TestDataGenerator;

public class FitnessParserFactoryTest {

	private FitnessParserFactory factory;
	private UploadedFile microsoftFile;
	private UploadedFile fitbitFile;
	@Before
	public void setUp() throws Exception {
		factory = new FitnessParserFactory();
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		File f = new File("testing-files/fitness_data/MS_Band_Data.csv");
		File f2 = new File("testing-files/fitness_data/fitbit_export_HW3.csv");
		InputStream is = new FileInputStream(f);
		InputStream is2 = new FileInputStream(f2);
		microsoftFile = Mockito.mock(UploadedFile.class);
		fitbitFile = Mockito.mock(UploadedFile.class);
		Mockito.when(microsoftFile.getInputstream()).thenReturn(is);
		Mockito.when(fitbitFile.getInputstream()).thenReturn(is2);
	}

	@Test
	public void testGetParser() throws IOException, DBException {
		FitnessParser p = factory.getParser(microsoftFile, ConverterDAO.getDataSource());
		assertTrue(p instanceof MicrosoftParser);
		
		p = factory.getParser(fitbitFile, ConverterDAO.getDataSource());
		assertTrue(p instanceof FitbitParser);
		
	}
	
	@Test
	public void testGetParserFail() {
		try {
			factory.getParser(microsoftFile);
		} catch (Exception e) {
			//pass
		}
		try {
			factory.getParser(fitbitFile);
		} catch (Exception e) {
			//pass
		}
	}
	

}
