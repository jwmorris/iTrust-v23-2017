package edu.ncsu.csc.itrust.controller.fitnessgraphs;

import java.util.Date;

import org.primefaces.model.chart.LineChartModel;

public interface GraphDisplayModel {

	
	public LineChartModel getLineChartModel(String pid, Date startDate, Date endDate);
}
