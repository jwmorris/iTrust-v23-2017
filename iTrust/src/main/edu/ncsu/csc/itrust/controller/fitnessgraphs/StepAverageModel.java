package edu.ncsu.csc.itrust.controller.fitnessgraphs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import edu.ncsu.csc.itrust.controller.fitness.FitnessController;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.fitnessData.Fitness;

public class StepAverageModel implements GraphDisplayModel {
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public LineChartModel getLineChartModel(String pid, Date startDate, Date endDate) {
		LineChartModel model = new LineChartModel();
		FitnessController fitnessController = null;
		try {
			fitnessController = new FitnessController();
		} catch (DBException e) {
		}

		List<Fitness> fitnessInfo = null;
		try {
			fitnessInfo = fitnessController.getFitnessDateRange(sdf.format(startDate), sdf.format(endDate), pid);
		} catch (DBException e) {
		}
		
		LineChartSeries series = new LineChartSeries();
		HashMap<String, ArrayList<Integer>> weekMap = new HashMap<>();
		if(fitnessInfo != null) {
			for(Fitness f : fitnessInfo) {
				String startWeek = getStartOfWeek(f.getDate());
				if(weekMap.containsKey(startWeek)) {
					weekMap.get(startWeek).add(f.getSteps());
				} else {
					ArrayList<Integer> weekList = new ArrayList<>();
					weekList.add(f.getSteps());
					weekMap.put(startWeek, weekList);
				}
			}

			for(Entry<String, ArrayList<Integer>> e : weekMap.entrySet()) {
				int average = getAverageForWeek(e.getValue());
				series.set(e.getKey(), average);
			}
			
			
		} else {
			series.set("01/01/1900", 0);
		}
		
		model.addSeries(series);
		model.setTitle("Weekly Average Step Count");
		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setLabel("Steps");
		
		
		DateAxis axis = new DateAxis("Weeks");
		axis.setTickAngle(-50);
		axis.setMin((fitnessInfo == null) ? sdf.format(startDate) : getStartOfWeek(fitnessInfo.get(0).getDate()));
		axis.setMax((fitnessInfo == null) ? sdf.format(startDate) : getStartOfWeek(fitnessInfo.get(fitnessInfo.size() - 1).getDate()));
		axis.setTickInterval("7 days");
		
		axis.setTickFormat("Week of %m/%d/%Y");
		
		
		model.getAxes().put(AxisType.X, axis);
		
		return model;
	}
	
	private String getStartOfWeek(String date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() - cal.get(Calendar.DAY_OF_WEEK));
		return sdf.format(cal.getTime());
	}
	
	private int getAverageForWeek(ArrayList<Integer> list) {
		int len = list.size();
		int sum = 0;
		for(Integer i : list) {
			sum += i;
		}
		return sum / len;
	}

}
