package edu.ncsu.csc.itrust.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator
public class ImageFileValidator implements Validator {

	@Override
	public void validate(FacesContext ctx, UIComponent comp, Object o) throws ValidatorException {
		Part part = (Part) o;
		if(!part.getContentType().equals("image/jpeg") 
				&& !part.getContentType().equals("application/pdf")
				&& !part.getContentType().equals("image/png")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Type", "Invalid file type"));
			
		}

	}

}
