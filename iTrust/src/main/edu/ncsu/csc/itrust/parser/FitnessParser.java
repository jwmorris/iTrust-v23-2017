package edu.ncsu.csc.itrust.parser;

import java.io.IOException;

public interface FitnessParser {
	/**
	 * parse the file
	 * @param pid
	 * 		patient id for fitness data
	 * @throws IOException 
	 */
	public void parse( String pid ) throws IOException;
}
