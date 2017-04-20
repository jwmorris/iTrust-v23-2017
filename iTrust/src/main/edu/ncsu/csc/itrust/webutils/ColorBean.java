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
public class ColorBean {
	private long pid;
	
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
		pid = 0;
		primaryText = "#333";
		primaryBackground = "#fff";
		leftMenuBackground = "rgba(247, 247, 247, 0.59)";
		navigationBarText = "#999";
		navigationBarBackground = "#222";
		welcomeText = "#FFFFFF";
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
	 * @return the pid
	 */
	public long getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid( long pid ) {
		this.pid = pid;
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
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "mtc", this.primaryText );
			
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
		if ( primaryBackground.charAt( 0 ) != '#' )
			this.primaryBackground = "#" + primaryBackground;
		else
			this.primaryBackground = primaryBackground;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "mbc", this.primaryBackground );
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
		if ( leftMenuBackground.charAt( 0 ) != '#' )
			this.leftMenuBackground = "#" + leftMenuBackground;
		else
			this.leftMenuBackground = leftMenuBackground;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "lmb", this.leftMenuBackground );
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
		if ( navigationBarText.charAt( 0 ) != '#' )
			this.navigationBarText = "#" + navigationBarText;
		else
			this.navigationBarText = navigationBarText;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "nbt", this.navigationBarText );
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
		if ( navigationBarBackground.charAt( 0 ) != '#' )
			this.navigationBarBackground = "#" + navigationBarBackground;
		else
			this.navigationBarBackground = navigationBarBackground;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "nbc", this.navigationBarBackground );
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
		if ( welcomeText.charAt( 0 ) != '#' )
			this.welcomeText = "#" + welcomeText;
		else
			this.welcomeText = welcomeText;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "wtc", this.welcomeText );
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
		if ( footerText.charAt( 0 ) != '#' )
			this.footerText = "#" + footerText;
		else
			this.footerText = footerText;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "ftc", this.footerText );
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
		if ( footerBackground.charAt( 0 ) != '#' )
			this.footerBackground = "#" + footerBackground;
		else
			this.footerBackground = footerBackground;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "fbc", this.footerBackground );
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
		if ( tableHeadingText.charAt( 0 ) != '#' )
			this.tableHeadingText = "#" + tableHeadingText;
		else
			this.tableHeadingText = tableHeadingText;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "tht", this.tableHeadingText );
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
		if ( tableHeadingBackground.charAt( 0 ) != '#' )
			this.tableHeadingBackground = "#" + tableHeadingBackground;
		else
			this.tableHeadingBackground = tableHeadingBackground;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "thb", this.tableHeadingBackground );
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
		if ( tableRowBackground1.charAt( 0 ) != '#' )
			this.tableRowBackground1 = "#" + tableRowBackground1;
		else
			this.tableRowBackground1 = tableRowBackground1;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "tb1", this.tableRowBackground1 );
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
		if ( tableRowBackground2.charAt( 0 ) != '#' )
			this.tableRowBackground2 = "#" + tableRowBackground2;
		else
			this.tableRowBackground2 = tableRowBackground2;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "tb2", this.tableRowBackground2 );
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
		if ( selectedPatient.charAt( 0 ) != '#' )
			this.selectedPatient = "#" + selectedPatient;
		else
			this.selectedPatient = selectedPatient;
		
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "spb", this.selectedPatient );
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
		if ( errorText.charAt( 0 ) != '#' )
			this.errorText = "#" + errorText;
		else
			this.errorText = errorText;
		RequestContext ctx = RequestContext.getCurrentInstance();
		if ( ctx != null )
			ctx.addCallbackParam( "etc", this.errorText );
		
	}
}
