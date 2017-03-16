package edu.ncsu.csc.itrust.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import edu.ncsu.csc.itrust.controller.fitness.FitnessController;
import edu.ncsu.csc.itrust.exception.DBException;

/**
 * provides a template for other parser classes
 * based on their filetypes
 * 
 * @author wyattmaxey
 */
public class FitnessParserFactory {
	/** File extension for a fibit data file */
	private static final String FITBIT_FILE_EXTENSION = "csv";
	
	/**
	 * return a parser for the client
	 * 
	 * @param fn
	 * 		filename, used to decide which parser is appropriate
	 * @return
	 * 		a parser
	 * @throws IOException 
	 */
	public FitnessParser getParser(UploadedFile file ) throws IOException {
		InputStream stream = file.getInputstream();
		StringWriter sw = new StringWriter();
		IOUtils.copy(stream, sw);
		StringReader sr = new StringReader(sw.toString());
		BufferedReader br = new BufferedReader(sr);
		
		String firstLine = br.readLine();
		if(firstLine == null) {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error",  "Invalid File" );
			FacesContext.getCurrentInstance().addMessage( null, msg );
			return null;
		} 
		FitnessParser ret = null;
		if(checkFitbitFile(firstLine)) {
			ret =  new FitbitParser(file);
		} else if (checkMicrosoftFile(firstLine)) {
			ret =  new MicrosoftParser(file);
		} 
		
		br.close();
		return ret;
	}
	
	
	/**
	 * This contrsuctor is for testing the functionality of the parser factory
	 * @param file
	 * @param ds
	 * @return
	 * @throws IOException
	 * @throws DBException 
	 */
	public FitnessParser getParser(UploadedFile file, DataSource ds) throws IOException, DBException {
		InputStream stream = file.getInputstream();
		StringWriter sw = new StringWriter();
		IOUtils.copy(stream, sw);
		StringReader sr = new StringReader(sw.toString());
		BufferedReader br = new BufferedReader(sr);
		
		String firstLine = br.readLine();
		if(firstLine == null) {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error",  "Invalid File" );
			FacesContext.getCurrentInstance().addMessage( null, msg );
			return null;
		} 
		FitnessParser ret = null;
		if(checkFitbitFile(firstLine)) {
			ret =  new FitbitParser(file, ds);
		} else if (checkMicrosoftFile(firstLine)) {
			ret =  new MicrosoftParser(file, ds);
		} 
		
		br.close();
		return ret;
	}
	
	private boolean checkFitbitFile(String firstLine) {
		String [] parts = firstLine.split(",");
		return parts[0].equals("Activities");
		
	}
	
	private boolean checkMicrosoftFile(String firstLine) {
		String [] parts = firstLine.split(",");
		return parts[0].equals("Date")
				&& parts[1].equals("Steps")
				&& parts[2].equals("Calories")
				&& parts[3].equals("HR_Lowest")
				&& parts[4].equals("HR_Highest")
				&& parts[5].equals("HR_Average")
				&& parts[6].equals("Total_Miles_Moved")
				&& parts[7].equals("Active_Hours")
				&& parts[8].equals("Floors_Climbed")
				&& parts[9].equals("UV_Exposure_Minutes");
	}
}
