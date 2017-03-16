package edu.ncsu.csc.itrust.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessData;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessMySQL;
import edu.ncsu.csc.itrust.model.fitnessData.FitnessValidator;



public class MicrosoftParser implements FitnessParser {

	
	private UploadedFile file;
	
	private FitnessData fitnessData;
	
	private FitnessValidator validator;
	
	private final SimpleDateFormat df4 = new SimpleDateFormat("MM/dd/yyyy");
	
	private final SimpleDateFormat df2 = new SimpleDateFormat("MM/dd/yy");
	
	//Testing constructor
	public MicrosoftParser(UploadedFile file, DataSource ds) throws DBException {
		this.fitnessData = new FitnessMySQL(ds);
		this.file = file;
		this.validator = new FitnessValidator();
	}
	public MicrosoftParser(UploadedFile file)  {
		try {
			this.fitnessData = new FitnessMySQL();
		} catch ( DBException e ) {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error",  "Parser error" );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}
		if ( file == null ) {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error",  "Could not open file" );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		} else {
			this.file = file;
		}
		this.validator = new FitnessValidator();
	}
	@Override
	public void parse(String pid) throws IOException {
		InputStream stream = file.getInputstream();
		StringWriter sw = new StringWriter();
		IOUtils.copy(stream, sw);
		StringReader sr = new StringReader(sw.toString());
		BufferedReader br = new BufferedReader(sr);
		try {
			br.readLine();
		} catch (IOException e) {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error",  "Error reading file" );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}
		
		int numberLinesSkipped = 0;
		int numberLinesAdded = 0;
		
		String line = null;
		while((line = br.readLine()) != null) {
			LineStatus status = parseLine(line, pid);
			if(status == LineStatus.ADDED) {
				numberLinesAdded++;
			} else if(status == LineStatus.SKIPPED) {
				numberLinesSkipped++;
			}
		}
		br.close();
		try {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO, "File Results",  "Successfully added or updated "
					+ numberLinesAdded + " entries. Skipped " + numberLinesSkipped + " entries.");
			FacesContext.getCurrentInstance().addMessage( null, msg );
		} catch (NullPointerException e) {
			//testing check
		}
		
	}
	
	private LineStatus parseLine(String line, String pid) throws IOException {
		Fitness f = new Fitness();
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		try {
			f.setPid( pid );
		} catch ( SQLException e ) {
			throw new IOException( "Invalid PID" );
		}
		try {
			//Read and set date
			Date date = df2.parse(scan.next());
			f.setDate(df4.format(date));
			
			//Read and set steps
			int steps = 0;
			String stepsString = scan.next();
			if(!stepsString.isEmpty() && stepsString.charAt(0) == '"') {
				steps = Integer.parseInt((stepsString + scan.next()).replaceAll("\"", ""));
			} else {
				steps = (stepsString.isEmpty()) ? 0 : Integer.parseInt(stepsString);
			}
			f.setSteps(steps);
			
			//Read and set calories
			int calories = 0;
			String calsString= scan.next();
			if(!calsString.isEmpty() && calsString.charAt(0) == '"') {
				calories = Integer.parseInt((calsString + scan.next()).replaceAll("\"", ""));
			} else {
				calories = (calsString.isEmpty()) ? 0 : Integer.parseInt(calsString);
			}
			f.setCalories(calories);
			
			//Read and set hrLow
			int hrLow = 0;
			String hrLowString = scan.next();
			if(!hrLowString.isEmpty() && hrLowString.charAt(0) == '"') {
				hrLow = Integer.parseInt((hrLowString + scan.next()).replaceAll("\"", ""));
			} else {
				hrLow = (hrLowString.isEmpty()) ? 0 : Integer.parseInt(hrLowString);
			}
			f.setHRLow(hrLow);

			//Read and set hrHigh
			int hrHigh = 0;
			String hrHighString = scan.next();
			if(!hrHighString.isEmpty() && hrHighString.charAt(0) == '"') {
				hrHigh = Integer.parseInt((hrHighString + scan.next()).replaceAll("\"", ""));
			} else {
				hrHigh = (hrHighString.isEmpty()) ? 0 : Integer.parseInt(hrHighString);
			}
			f.setHRHigh(hrHigh);
			
			//Read and set hrAvg
			int hrAvg = 0;
			String hrAvgString = scan.next();
			if(!hrAvgString.isEmpty() && hrAvgString.charAt(0) == '"') {
				hrAvg = Integer.parseInt((hrAvgString + scan.next()).replaceAll("\"", ""));
			} else {
				hrAvg = (hrAvgString.isEmpty()) ? 0 : Integer.parseInt(hrAvgString);
			}
			f.setHRAvg(hrAvg);
			
			//Read and set distance
			double distance = 0;
			String distanceString = scan.next();
			if(!distanceString.isEmpty() && distanceString.charAt(0) == '"') {
				distance = Double.parseDouble((distanceString + scan.next()).replaceAll("\"", ""));
			} else {
				distance = (distanceString.isEmpty()) ? 0 : Double.parseDouble(distanceString);
			}
			f.setDistance(distance);
			
			//Read and set activeHours
			int activeHours = 0;
			String activeHoursString = scan.next();
			if(!activeHoursString.isEmpty() && activeHoursString.charAt(0) == '"') {
				activeHours = Integer.parseInt((activeHoursString + scan.next()).replaceAll("\"", ""));
			} else {
				activeHours = (activeHoursString.isEmpty()) ? 0 : Integer.parseInt(activeHoursString);

			}
			f.setActiveHours(activeHours);
			
			//Read and set floors
			int floors = 0;
			String floorsString = scan.next();
			if(!floorsString.isEmpty() && floorsString.charAt(0) == '"') {
				floors = Integer.parseInt((floorsString + scan.next()).replaceAll("\"", ""));
			} else {
				floors = (floorsString.isEmpty()) ? 0 : Integer.parseInt(floorsString);
			}
			f.setFloors(floors);
			
			//Read and set uvExposure
			int uvExposure = 0;
			String uvExposureString = scan.next();
			if(!uvExposureString.isEmpty() && uvExposureString.charAt(0) == '"') {
				uvExposure = Integer.parseInt((uvExposureString + scan.next()).replaceAll("\"", ""));
			} else {
				uvExposure = (uvExposureString.isEmpty()) ? 0 : Integer.parseInt(uvExposureString);
			}
			f.setUVExposure(uvExposure);
			
			
			f.setActiveCals(0);
			f.setMinsFA(0);
			f.setMinsLA(0);
			f.setMinsSed(0);
			f.setMinsVA(0);
		
			try {
				validator.validate(f);
				fitnessData.add(f);
				return LineStatus.ADDED;
			} catch (FormValidationException e) {
				return LineStatus.SKIPPED;
			} catch (DBException e) {
				return LineStatus.SKIPPED;
			} 
		} catch (ParseException e) {
			e.printStackTrace();
			return LineStatus.SKIPPED;
		} catch (SQLException e) {
			e.printStackTrace();
			return LineStatus.SKIPPED;
		} catch (NumberFormatException e) {
			return LineStatus.SKIPPED;
		}
	}
	
	public enum LineStatus {ADDED, SKIPPED, INVALID_FILE};

}
