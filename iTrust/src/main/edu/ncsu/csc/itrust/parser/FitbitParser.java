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

/**
 * parses a data file from a FitBit
 * 
 * @author wyattmaxey
 */
public class FitbitParser implements FitnessParser {
	/** file to parse */
	private UploadedFile f;
	
	/** adding data */
	private FitnessData fitnessData;
	
	/** formats date Strings */
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	/*
	public FitbitParser( FitnessController fc ) {
		this.fc = fc;
	}
	*/
	
	public FitbitParser( UploadedFile f ) {
		try {
			this.fitnessData = new FitnessMySQL();
		} catch ( DBException e ) {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error",  "Parser error" );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}
		if ( f == null ) {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error",  "Could not open file" );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		} else {
			this.f = f;
		}
	}
	
	public FitbitParser( UploadedFile f , DataSource ds) {
		try {
			this.fitnessData = new FitnessMySQL(ds);
		} catch ( DBException e ) {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error",  "Parser error" );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		}
		if ( f == null ) {
			FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error",  "Could not open file" );
			FacesContext.getCurrentInstance().addMessage( null, msg );
		} else {
			this.f = f;
		}
	}

	@Override
	public void parse( String pid ) throws IOException {
		InputStream stream = f.getInputstream();
		StringWriter sw = new StringWriter();
		IOUtils.copy(stream, sw);
		StringReader sr = new StringReader(sw.toString());
		BufferedReader br = new BufferedReader(sr);
		
		
		br.readLine();
		br.readLine();
		String line = null;
		while ( (line = br.readLine()) != null ) {
			processLine(line, pid);
		}
		br.close();
	}

	private void processLine(String line, String pid) throws IOException {
		System.out.println( "Line: " + line );
		Scanner l = new Scanner( line );
		l.useDelimiter( "," );
		Fitness f = new Fitness();
			try {
				Date d;
				try {
					String temp = l.next();
					String tempYear = temp.substring( temp.length() - 2, temp.length() );
					String dString = temp.substring( 0, temp.length() - 2 )  + tempYear;
					d = DATE_FORMAT.parse( temp );
				} catch ( ParseException e ) {
					l.close();
					throw new IOException();
				}
			
				try {
					f.setPid( pid );
				} catch ( SQLException e ) {
					l.close();
					throw new IOException( "Invalid PID" );
				}
			
				String date = DATE_FORMAT.format( d );
				f.setDate( date );

			
				String calString = l.next();
				int cals;
				if ( calString.charAt( 0 ) == '"' ) {
					calString += l.next();
					cals = Integer.parseInt( calString.substring( 1,  calString.length() - 1 ) );
				} else {
					cals = Integer.parseInt( calString );
				}
				f.setCalories( cals );
				String stepString = l.next();
				int steps;
				if ( stepString.charAt( 0 ) == '"' ) {
					stepString += l.next();
					steps = Integer.parseInt( stepString.substring( 1,  stepString.length() - 1 ) );
				} else {
					steps = Integer.parseInt( stepString );
				}
				f.setSteps( steps );

			
				f.setDistance( l.nextDouble() );
			
				int floors = l.nextInt();
				f.setFloors( floors );

				String sedString = l.next();
				int sed;
				if ( sedString.charAt( 0 ) == '"' ) {
					sedString += l.next();
					sed = Integer.parseInt( sedString.substring( 1,  sedString.length() - 1 ) );
				} else {
					sed = Integer.parseInt( sedString );
				}
				f.setMinsSed( sed );
				f.setMinsLA( l.nextInt() );
				f.setMinsFA( l.nextInt() );
				f.setMinsVA( l.nextInt() );
			
				calString = null;
				calString = l.next();
				if ( calString.charAt( 0 ) == '"' ) {
					calString += l.next();
					cals = Integer.parseInt( calString.substring( 1,  calString.length() - 1 ) );
				} else {
					cals = Integer.parseInt( calString );
				}
				f.setActiveCals( cals );
			} catch ( Exception e ) {
				throw new IOException( "Error reading data from file" );
			}
		
		l.close();
		try {
			fitnessData.add( f );
		} catch ( DBException | FormValidationException e ) {
			e.printStackTrace();
			throw new IOException( "Error reading data from file" );
		}

	}
}
