package edu.ncsu.csc.itrust.controller.obstetrics;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;

import edu.ncsu.csc.itrust.model.obstetricsOfficeVisit.ObstetricsOfficeVisitData;

@ManagedBean
@ApplicationScoped
public class Images {

	@EJB
	private ObstetricsOfficeVisitData ovd;
	
	public StreamedContent getImage() throws IOException {
       /* FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Image image = service.find(Long.valueOf(id));
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getBytes()));
        }*/
		return null;
    }
}
