package edu.ncsu.csc.itrust.selenium;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebWindow;


/**
 * Custom implementation of an HtmlUnitDriver that does not report js and css errors.
 */
public class Driver extends HtmlUnitDriver {
	/**
	 * Construct a new driver and disable script error reporting.
	 */
	public Driver() {
		super();
		this.getWebClient().getCache().setMaxSize(25);
		this.getWebClient().getOptions().setThrowExceptionOnScriptError(false);
		this.getWebClient().setCssErrorHandler(new SilentCssErrorHandler());
	}
	
	public boolean checkHeader(String url, String header, String expected) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		return this.getWebClient().getPage(url).getWebResponse().getResponseHeaderValue(header).equals(expected);
	}
	
}
