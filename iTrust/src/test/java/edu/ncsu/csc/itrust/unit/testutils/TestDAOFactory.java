package edu.ncsu.csc.itrust.unit.testutils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import edu.ncsu.csc.itrust.model.old.dao.DAOFactory;
import edu.ncsu.csc.itrust.model.old.dao.IConnectionDriver;

/**
 * This class pulls the JDBC driver information from Tomcat's context.xml file
 * in WebRoot/META-INF/context.xml. This is done only for convenience - so that
 * you only have to pull your JDBC info from one place (context.xml)<br />
 * <br />
 * The tangled mess you see here is SAX, the XML-parser and XPath, an XML
 * querying language. Note that this class is only ever constructed once since
 * DAOFactory only constructs up to 2 instances of itself.<br />
 * <br />
 * Also, you'll notice that we're using a "BasicDataSource" to obtain
 * connections instead of the usual DriverManager. That's because we're using
 * Tomcat's built-in database pooling mechanism. It's purely for performance in
 * this case.
 */
public class TestDAOFactory extends DAOFactory implements IConnectionDriver {

	private static DAOFactory testInstance;

	public static DAOFactory getTestInstance() {
		if (testInstance == null)
			testInstance = new TestDAOFactory();
		return testInstance;
	}

	private BasicDataSource dataSource;

	private TestDAOFactory() {
		try {
			Document document = parseXML(new BufferedReader(new FileReader("WebRoot/META-INF/context.xml")));
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(getAttribute(document, "@driverClassName"));
			dataSource.setUsername(getAttribute(document, "@username"));
			dataSource.setPassword(getAttribute(document, "@password"));
			dataSource.setUrl(getAttribute(document, "@url"));
			dataSource.setMaxTotal( 3 ); // only allow three connections open at
			dataSource.setMaxIdle( 2 );
										// a time
			dataSource.setMaxWaitMillis( 250 ); // wait 250ms until throwing an
			
			dataSource.setTimeBetweenEvictionRunsMillis( 250 );
			dataSource.setMinEvictableIdleTimeMillis( 10000 );
										// exception
			dataSource.setRemoveAbandonedOnBorrow( true );
			dataSource.setRemoveAbandonedOnMaintenance( true );

			dataSource.setPoolPreparedStatements( false );
			//dataSource.setMaxOpenPreparedStatements( 5 );

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getAttribute(Document document, String attribute) throws XPathExpressionException {
		return (String) XPathFactory.newInstance().newXPath().compile("/Context/Resource/" + attribute)
				.evaluate(document.getDocumentElement(), XPathConstants.STRING);
	}

	private Document parseXML(BufferedReader reader) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new InputSource(reader));
	}

	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
}
