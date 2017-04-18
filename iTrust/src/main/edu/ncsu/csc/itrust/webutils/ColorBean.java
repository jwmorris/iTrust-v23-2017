/**
 * 
 */
package edu.ncsu.csc.itrust.webutils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

/**
 * @author wyattmaxey
 */
@ManagedBean(name="colorBean")
@ViewScoped
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
		if ( this.primaryText.charAt( 0 ) != '#' || primaryText.charAt( 0 ) != '#' )
			this.primaryText = "#" + primaryText;
		else
			this.primaryText = primaryText;
		
		RequestContext.getCurrentInstance().addCallbackParam( "mtc", this.primaryText );
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
		if ( this.primaryBackground.charAt( 0 ) != '#' || primaryBackground.charAt( 0 ) != '#' )
			this.primaryBackground = "#" + primaryBackground;
		else
			this.primaryBackground = primaryBackground;
		
		RequestContext.getCurrentInstance().addCallbackParam( "mbc", this.primaryText );
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
		else if ( leftMenuBackground.charAt( 0 ) != '#' )
			this.leftMenuBackground = "#" + leftMenuBackground;
		else
			this.leftMenuBackground = leftMenuBackground;
		
		RequestContext.getCurrentInstance().addCallbackParam( "lmb", this.leftMenuBackground );
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
		if ( this.navigationBarText.charAt( 0 ) != '#' )
			this.navigationBarText = "#" + navigationBarText;
		else if ( navigationBarText.charAt( 0 ) != '#' )
			this.navigationBarText = "#" + navigationBarText;
		else
			this.navigationBarText = navigationBarText;
		
		RequestContext.getCurrentInstance().addCallbackParam( "nbt", this.navigationBarText );
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
		if ( this.navigationBarBackground.charAt( 0 ) != '#' )
			this.navigationBarBackground = "#" + navigationBarBackground;
		else if ( navigationBarBackground.charAt( 0 ) != '#' )
			this.navigationBarBackground = "#" + navigationBarBackground;
		else
			this.navigationBarBackground = navigationBarBackground;
		
		RequestContext.getCurrentInstance().addCallbackParam( "nbc", this.navigationBarBackground );
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
		if ( this.welcomeText.charAt( 0 ) != '#' )
			this.welcomeText = "#" + welcomeText;
		else if ( welcomeText.charAt( 0 ) != '#' )
			this.welcomeText = "#" + welcomeText;
		else
			this.welcomeText = welcomeText;
		
		RequestContext.getCurrentInstance().addCallbackParam( "wtc", this.welcomeText );
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
		if ( this.footerText.charAt( 0 ) != '#' )
			this.footerText = "#" + footerText;
		else if ( footerText.charAt( 0 ) != '#' )
			this.footerText = "#" + footerText;
		else
			this.footerText = footerText;
		
		RequestContext.getCurrentInstance().addCallbackParam( "ftc", this.footerText );
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
		if ( this.footerBackground.charAt( 0 ) != '#' )
			this.footerBackground = "#" + footerBackground;
		else if ( footerBackground.charAt( 0 ) != '#' )
			this.footerBackground = "#" + footerBackground;
		else
			this.footerBackground = footerBackground;
		
		RequestContext.getCurrentInstance().addCallbackParam( "fbc", this.footerBackground );
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
		if ( this.tableHeadingText.charAt( 0 ) != '#' )
			this.tableHeadingText = "#" + tableHeadingText;
		else if ( tableHeadingText.charAt( 0 ) != '#' )
			this.tableHeadingText = "#" + tableHeadingText;
		else
			this.tableHeadingText = tableHeadingText;
		
		RequestContext.getCurrentInstance().addCallbackParam( "tht", this.tableHeadingText );
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
		if ( this.tableHeadingBackground.charAt( 0 ) != '#' )
			this.tableHeadingBackground = "#" + tableHeadingBackground;
		else if ( tableHeadingBackground.charAt( 0 ) != '#' )
			this.tableHeadingBackground = "#" + tableHeadingBackground;
		else
			this.tableHeadingBackground = tableHeadingBackground;
		
		RequestContext.getCurrentInstance().addCallbackParam( "thb", this.tableHeadingBackground );
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
		if ( this.tableRowBackground1.charAt( 0 ) != '#' )
			this.tableRowBackground1 = "#" + tableRowBackground1;
		else if ( tableRowBackground1.charAt( 0 ) != '#' )
			this.tableRowBackground1 = "#" + tableRowBackground1;
		else
			this.tableRowBackground1 = tableRowBackground1;
		
		RequestContext.getCurrentInstance().addCallbackParam( "tb1", this.tableRowBackground1 );
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
		if ( this.tableRowBackground2.charAt( 0 ) != '#' )
			this.tableRowBackground2 = "#" + tableRowBackground2;
		else if ( tableRowBackground2.charAt( 0 ) != '#' )
			this.tableRowBackground2 = "#" + tableRowBackground2;
		else
			this.tableRowBackground2 = tableRowBackground2;
		
		RequestContext.getCurrentInstance().addCallbackParam( "tb2", this.tableRowBackground2 );
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
		if ( this.selectedPatient.charAt( 0 ) != '#' )
			this.selectedPatient = "#" + selectedPatient;
		else if ( selectedPatient.charAt( 0 ) != '#' )
			this.selectedPatient = "#" + selectedPatient;
		else
			this.selectedPatient = selectedPatient;
		
		RequestContext.getCurrentInstance().addCallbackParam( "spb", this.selectedPatient );
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
		if ( this.errorText.charAt( 0 ) != '#' )
			this.errorText = "#" + errorText;
		else if ( errorText.charAt( 0 ) != '#' )
			this.errorText = "#" + errorText;
		else
			this.errorText = errorText;
		
		RequestContext.getCurrentInstance().addCallbackParam( "etc", this.errorText );
	}
	
	public void submit() {
		
	}
}
