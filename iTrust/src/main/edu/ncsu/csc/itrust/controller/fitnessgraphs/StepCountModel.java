package edu.ncsu.csc.itrust.controller.fitnessgraphs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import edu.ncsu.csc.itrust.controller.fitness.FitnessController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;

public class StepCountModel implements GraphDisplayModel {

	private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	@Override
	public LineChartModel getLineChartModel(String pid, Date startDate, Date endDate) {
		LineChartModel model = new LineChartModel();
		FitnessController fitnessController = null;
		try {
			fitnessController = new FitnessController();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Fitness> fitnessInfo = null;
		try {
			fitnessInfo = fitnessController.getFitnessDateRange(sdf.format(startDate), sdf.format(endDate), pid);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LineChartSeries series = new LineChartSeries();
		if(fitnessInfo != null) {
			for(Fitness f : fitnessInfo) {
				series.set(f.getDate(), f.getSteps());
			}
		} else {
			
			series.set("01/01/1900", 0);
		}
		
		model.addSeries(series);
		model.setTitle("Step Counts");
		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setLabel("Steps");
		yAxis.setMin(0);
		
		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setMin(sdf.format(startDate));
		axis.setMax(sdf.format(endDate));
		axis.setTickFormat("%m/%d/%Y");
		
		model.getAxes().put(AxisType.X, axis);
		return model;
	}

}
