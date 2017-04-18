/**
 * 
 */
package edu.ncsu.csc.itrust.webutils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 * @author wyattmaxey
 */
@ManagedBean(name="colorBean")
public class ColorBean {
	private String primaryText;
	
	private String primaryBackground;
	
	private String leftMenuBackground;
	
	private String navigationBarText;
	
	private String navigationBarBackground;
	
	private String welcomeText;
	
	private String footerText;
	
	private String footerBackground;
	
	private String tableHeadingText;
	
	private String tableHeadingBackground;
	
	private String tableRowBackground1;
	
	private String tableRowBackground2;
	
	private String selectedPatient;
	
	private String errorText;
	
	public ColorBean() {
		primaryText = "#ffffff";
		primaryBackground = "#333";
		leftMenuBackground = "rgba(247, 247, 247, 0.59)";
		navigationBarText = "#999";
		navigationBarBackground = "#222";
		welcomeText = "rgb(255, 255, 255)";
		footerText = "#E04646";
		footerBackground = "rgba(247, 247, 247, 0.59)";
		tableHeadingText = "white";
		tableHeadingBackground = "rgb(75, 75, 75)";
		tableRowBackground1 = "#f1f1f1";
		tableRowBackground2 = "#E7E7E7";
		selectedPatient = "#D6D6D6";
		errorText = "#cc0000";
	}

	/**
	 * @return the primaryText
	 */
	public String getPrimaryText() {
		return primaryText;
	}

	/**
	 * @param primaryText the primaryText to set
	 */
	public void setPrimaryText( String primaryText ) {
		this.primaryText = "#" + primaryText;
		System.out.println( this.primaryText );
	}

	/**
	 * @return the primaryBackground
	 */
	public String getPrimaryBackground() {
		return primaryBackground;
	}

	/**
	 * @param primaryBackground the primaryBackground to set
	 */
	public void setPrimaryBackground( String primaryBackground ) {
		this.primaryBackground = "#" + primaryBackground;
	}

	/**
	 * @return the leftMenuBackground
	 */
	public String getLeftMenuBackground() {
		return leftMenuBackground;
	}

	/**
	 * @param leftMenuBackground the leftMenuBackground to set
	 */
	public void setLeftMenuBackground( String leftMenuBackground ) {
		if ( this.leftMenuBackground.charAt( 0 ) != '#' )
			this.leftMenuBackground = "#" + leftMenuBackground;
		else
			this.leftMenuBackground = leftMenuBackground;
	}

	/**
	 * @return the navigationBarText
	 */
	public String getNavigationBarText() {
		return navigationBarText;
	}

	/**
	 * @param navigationBarText the navigationBarText to set
	 */
	public void setNavigationBarText( String navigationBarText ) {
		this.navigationBarText = "#" + navigationBarText;
	}

	/**
	 * @return the navigationBarBackground
	 */
	public String getNavigationBarBackground() {
		return navigationBarBackground;
	}

	/**
	 * @param navigationBarBackground the navigationBarBackground to set
	 */
	public void setNavigationBarBackground( String navigationBarBackground ) {
		this.navigationBarBackground = "#" + navigationBarBackground;
	}

	/**
	 * @return the welcomeText
	 */
	public String getWelcomeText() {
		return welcomeText;
	}

	/**
	 * @param welcomeText the welcomeText to set
	 */
	public void setWelcomeText( String welcomeText ) {
		this.welcomeText = "#" + welcomeText;
	}

	/**
	 * @return the footerText
	 */
	public String getFooterText() {
		return footerText;
	}

	/**
	 * @param footerText the footerText to set
	 */
	public void setFooterText( String footerText ) {
		this.footerText = "#" + footerText;
	}

	/**
	 * @return the footerBackground
	 */
	public String getFooterBackground() {
		return footerBackground;
	}

	/**
	 * @param footerBackground the footerBackground to set
	 */
	public void setFooterBackground( String footerBackground ) {
		this.footerBackground = "#" + footerBackground;
	}

	/**
	 * @return the tableHeadingText
	 */
	public String getTableHeadingText() {
		return tableHeadingText;
	}

	/**
	 * @param tableHeadingText the tableHeadingText to set
	 */
	public void setTableHeadingText( String tableHeadingText ) {
		this.tableHeadingText = "#" + tableHeadingText;
	}

	/**
	 * @return the tableHeadingBackground
	 */
	public String getTableHeadingBackground() {
		return tableHeadingBackground;
	}

	/**
	 * @param tableHeadingBackground the tableHeadingBackground to set
	 */
	public void setTableHeadingBackground( String tableHeadingBackground ) {
		this.tableHeadingBackground = "#" + tableHeadingBackground;
	}

	/**
	 * @return the tableRowBackground1
	 */
	public String getTableRowBackground1() {
		return tableRowBackground1;
	}

	/**
	 * @param tableRowBackground1 the tableRowBackground1 to set
	 */
	public void setTableRowBackground1( String tableRowBackground1 ) {
		this.tableRowBackground1 = "#" + tableRowBackground1;
	}

	/**
	 * @return the tableRowBackground2
	 */
	public String getTableRowBackground2() {
		return tableRowBackground2;
	}

	/**
	 * @param tableRowBackground2 the tableRowBackground2 to set
	 */
	public void setTableRowBackground2( String tableRowBackground2 ) {
		this.tableRowBackground2 = "#" + tableRowBackground2;
	}

	/**
	 * @return the selectedPatient
	 */
	public String getSelectedPatient() {
		return selectedPatient;
	}

	/**
	 * @param selectedPatient the selectedPatient to set
	 */
	public void setSelectedPatient( String selectedPatient ) {
		this.selectedPatient = "#" + selectedPatient;
	}

	/**
	 * @return the errorText
	 */
	public String getErrorText() {
		return errorText;
	}

	/**
	 * @param errorText the errorText to set
	 */
	public void setErrorText( String errorText ) {
		this.errorText = "#" + errorText;
	}
	
	public void submit() {
		
	}
}
