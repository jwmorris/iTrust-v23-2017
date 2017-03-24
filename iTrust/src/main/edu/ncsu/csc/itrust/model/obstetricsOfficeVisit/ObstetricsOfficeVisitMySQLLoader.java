/**
 * 
 */
package edu.ncsu.csc.itrust.model.obstetricsOfficeVisit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ncsu.csc.itrust.model.SQLLoader;

/**
 * loads and unpacks sql statements and parameters
 * 
 * @author wyattmaxey
 */
public class ObstetricsOfficeVisitMySQLLoader implements SQLLoader<ObstetricsOfficeVisit> {

	@Override
	public List<ObstetricsOfficeVisit> loadList(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObstetricsOfficeVisit loadSingle(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement loadParameters(Connection conn, PreparedStatement ps, ObstetricsOfficeVisit insertObject,
			boolean newInstance) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
