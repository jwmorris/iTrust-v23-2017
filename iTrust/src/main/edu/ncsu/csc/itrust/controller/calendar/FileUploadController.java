package edu.ncsu.csc.itrust.controller.calendar;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;

import edu.ncsu.csc.itrust.parser.FitnessParser;
import edu.ncsu.csc.itrust.parser.FitnessParserFactory;
import edu.ncsu.csc.itrust.webutils.SessionUtils;
@ViewScoped
@ManagedBean(name="fileUpload")
public class FileUploadController {
	private UploadedFile file = null;
	private FitnessParserFactory factory;
	private FitnessParser parser;
	private SessionUtils sessionUtils;
	
	public UploadedFile getFile() {
		return this.file;
	}
	
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public void upload() {
		if(file == null) {
			FacesMessage m = new FacesMessage( "No file selected." );
			FacesContext.getCurrentInstance().addMessage(null, m);
			return;
		}
		FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
		this.sessionUtils = SessionUtils.getInstance();
        this.factory = new FitnessParserFactory();
        try {
			parser = factory.getParser( file );
		} catch (IOException e1) {
			FacesMessage m = new FacesMessage( "Error parsing file." );
			FacesContext.getCurrentInstance().addMessage(null, m);
		}
        if(parser == null) {
        	FacesMessage m = new FacesMessage( "Invalid File" );
			FacesContext.getCurrentInstance().addMessage(null, m);
			return;
        }
        try {
			parser.parse( sessionUtils.getCurrentPatientMID() );
		} catch ( IOException e ) {
			e.printStackTrace();
			message = new FacesMessage( "Error", file.getFileName() + " could not be uploaded." );
		}
        file = null;
        FacesContext.getCurrentInstance().addMessage(null, message);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("calendar", new CalendarController());
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().put("fileUpload", new FileUploadController());
	}
	
	

}
