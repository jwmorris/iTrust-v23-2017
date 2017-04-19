/**
 * 
 */
package edu.ncsu.csc.itrust.webutils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.primefaces.context.RequestContext;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;

/**
 * @author wyattmaxey
 */
@SessionScoped
@ManagedBean( name="color_controller" )
public class ColorController {
	private ColorData sql;
	
	private ColorBean bean;
	
	private SessionUtils utils;
	
	public ColorController() throws DBException {
		sql = new ColorMySQL();
		bean = new ColorBean();
		utils = SessionUtils.getInstance();
		bean.setPid( utils.getSessionLoggedInMIDLong() );
	}
	
	public ColorController( DataSource ds ) {
		sql = new ColorMySQL( ds );
		bean = new ColorBean();
		utils = SessionUtils.getInstance();
		bean.setPid( utils.getSessionLoggedInMIDLong() );
	}

	/**
	 * @return the primaryText
	 */
	public String getPrimaryText() {
		return bean.getPrimaryText();
	}

	/**
	 * @param primaryText the primaryText to set
	 */
	public void setPrimaryText( String primaryText ) {
		this.bean.setPrimaryText( primaryText );
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "mtc", this.bean.getPrimaryText() );
	}

	/**
	 * @return the primaryBackground
	 */
	public String getPrimaryBackground() {
		return bean.getPrimaryBackground();
	}

	/**
	 * @param primaryBackground the primaryBackground to set
	 */
	public void setPrimaryBackground( String primaryBackground ) {
		bean.setPrimaryBackground( primaryBackground );
	}

	/**
	 * @return the leftMenuBackground
	 */
	public String getLeftMenuBackground() {
		return bean.getLeftMenuBackground();
	}

	/**
	 * @param leftMenuBackground the leftMenuBackground to set
	 */
	public void setLeftMenuBackground( String leftMenuBackground ) {
		bean.setLeftMenuBackground( leftMenuBackground );
	}

	/**
	 * @return the navigationBarText
	 */
	public String getNavigationBarText() {
		return bean.getNavigationBarText();
	}

	/**
	 * @param navigationBarText the navigationBarText to set
	 */
	public void setNavigationBarText( String navigationBarText ) {
		bean.setNavigationBarText( navigationBarText );
	}

	/**
	 * @return the navigationBarBackground
	 */
	public String getNavigationBarBackground() {
		return bean.getNavigationBarBackground();
	}

	/**
	 * @param navigationBarBackground the navigationBarBackground to set
	 */
	public void setNavigationBarBackground( String navigationBarBackground ) {
		bean.setNavigationBarBackground( navigationBarBackground );
	}

	/**
	 * @return the welcomeText
	 */
	public String getWelcomeText() {
		return bean.getWelcomeText();
	}

	/**
	 * @param welcomeText the welcomeText to set
	 */
	public void setWelcomeText( String welcomeText ) {
		bean.setWelcomeText( welcomeText );
	}

	/**
	 * @return the footerText
	 */
	public String getFooterText() {
		return bean.getFooterText();
	}

	/**
	 * @param footerText the footerText to set
	 */
	public void setFooterText( String footerText ) {
		bean.setFooterText( footerText );
	}

	/**
	 * @return the footerBackground
	 */
	public String getFooterBackground() {
		return bean.getFooterBackground();
	}

	/**
	 * @param footerBackground the footerBackground to set
	 */
	public void setFooterBackground( String footerBackground ) {
		bean.setFooterBackground( footerBackground );
	}

	/**
	 * @return the tableHeadingText
	 */
	public String getTableHeadingText() {
		return bean.getTableHeadingText();
	}

	/**
	 * @param tableHeadingText the tableHeadingText to set
	 */
	public void setTableHeadingText( String tableHeadingText ) {
		bean.setTableHeadingText( tableHeadingText );
	}

	/**
	 * @return the tableHeadingBackground
	 */
	public String getTableHeadingBackground() {
		return bean.getTableHeadingBackground();
	}

	/**
	 * @param tableHeadingBackground the tableHeadingBackground to set
	 */
	public void setTableHeadingBackground( String tableHeadingBackground ) {
		bean.setTableHeadingBackground( tableHeadingBackground );
	}

	/**
	 * @return the tableRowBackground1
	 */
	public String getTableRowBackground1() {
		return bean.getTableRowBackground1();
	}

	/**
	 * @param tableRowBackground1 the tableRowBackground1 to set
	 */
	public void setTableRowBackground1( String tableRowBackground1 ) {
		bean.setTableRowBackground1( tableRowBackground1 );
	}

	/**
	 * @return the tableRowBackground2
	 */
	public String getTableRowBackground2() {
		return bean.getTableRowBackground2();
	}

	/**
	 * @param tableRowBackground2 the tableRowBackground2 to set
	 */
	public void setTableRowBackground2(String tableRowBackground2) {
		bean.setTableRowBackground2( tableRowBackground2 );
	}

	/**
	 * @return the selectedPatient
	 */
	public String getSelectedPatient() {
		return bean.getSelectedPatient();
	}

	/**
	 * @param selectedPatient the selectedPatient to set
	 */
	public void setSelectedPatient( String selectedPatient ) {
		bean.setSelectedPatient( selectedPatient );
	}

	/**
	 * @return the errorText
	 */
	public String getErrorText() {
		return bean.getErrorText();
	}

	/**
	 * @param errorText the errorText to set
	 */
	public void setErrorText(String errorText) {
		bean.setErrorText( errorText );
	}
	
	public void addDefaultColor() {
		try {
			sql.add( bean );
		} catch (DBException | FormValidationException e) {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Couldn't load default color scheme" ) );
		}
	}
	
	public void addCustomColor() {
		try {
			sql.update( bean );
		} catch (DBException | FormValidationException e) {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Couldn't load custom color scheme" ) );
		}
	}
	
	public void getColor() {
		System.out.println( "Called" );
		try {
			bean = sql.getColorBean( utils.getSessionLoggedInMIDLong() );
		} catch (DBException e) {
			bean = null;
		}
		if ( bean == null )
			bean = new ColorBean();
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null ) {
			ctx.addCallbackParam( "mbc", this.bean.getPrimaryBackground() );
			ctx.addCallbackParam( "lmb", this.bean.getLeftMenuBackground() );
			ctx.addCallbackParam( "mtx", this.bean.getPrimaryText() );
		}
	}
}
