package edu.ncsu.csc.itrust.controller.fitnessgraphs;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.webutils.SessionUtils;

@ViewScoped
@ManagedBean(name="fitnessGraphController")
public class FitnessGraphController {
	private String pid;
	private String graphType;
	private Date startDate;
	private Date endDate;
	private LineChartModel lcm;
	
	public FitnessGraphController() {
		pid = SessionUtils.getInstance().getCurrentPatientMID();
		graphType = "step";
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		c.add(Calendar.DAY_OF_WEEK, -7);
		Date weekAgo = c.getTime();
		startDate = weekAgo;
		endDate = today;
		lcm = createGraph();
	}
	
	public LineChartModel createGraph() {
		
		
		GraphDisplayModel displayModel = new StepCountModel();
		if(graphType.equals("step")) {
			displayModel = new StepCountModel();
		} else if (graphType.equals("stepDelta")) {
			displayModel = new StepDeltaModel();
		} else if (graphType.equals("stepAvg")) {
			displayModel = new StepAverageModel();
		}
		
		return displayModel.getLineChartModel(pid, startDate, endDate);
		
	}
	
	public String getGraphType() {
		return graphType;
	}
	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public LineChartModel getModel() {
		return lcm;
	}


	
	public void checkDateRange() {
		long dayDiff = endDate.getTime() - startDate.getTime();
		long days = TimeUnit.DAYS.convert(dayDiff, TimeUnit.MILLISECONDS);
		if(days <= 1) {
			FacesMessage m = new FacesMessage( "Invalid date range. There must be at least two days between the start and end date." );
			FacesContext.getCurrentInstance().addMessage(null, m);
		} else {
			LineChartModel model = createGraph();
			lcm = model;
			/*if(model.getSeries().get(0).getData().containsKey("01/01/1900")) {
				FacesMessage m = new FacesMessage( "There is no fitness data for the selected date range." );
				FacesContext.getCurrentInstance().addMessage(null, m);
			} else {
				lcm = model;
			}*/
		}
	}

}
