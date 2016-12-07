/**
 * Usage:
 * 
 * Total Cholesterol:	{Desirable, Borderline High, High};
 * Body Mass Index:		{Underweight, Normal, Overweight, Obese};
 * Blood Pressure:		{Normal, Prehypertension, Stage 1 Hypertension, Stage 2 Hypertension, Hypertensive Crisis};
 * 
 * int[] tcData = {3, 3, 3};
 * int[] bmiData = {1, 7, 1, 2};
 * int[] bpData = {3, 4, 4, 2, 1};
 * 
 * new ChartBuilder(tcData, bmiData, bpData).createPage();
 * 
 */

package model;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.toolforge.googlechartwrapper.Color;
import de.toolforge.googlechartwrapper.Dimension;
import de.toolforge.googlechartwrapper.PieChart;
import de.toolforge.googlechartwrapper.color.ChartColor;
import de.toolforge.googlechartwrapper.data.PieChartSlice;
import de.toolforge.googlechartwrapper.label.ChartTitle;

public class ChartBuilder {
	
	final int CHART_WIDTH = 600;
	final int CHART_HEIGHT = 400;
	
	private int[] totalCholesterolData;
	private int[] bodyMassIndexData;
	private int[] bloodPressureData;
	
	/**
	 * Usage:
	 * 
	 * Total Cholesterol:	{Desirable, Borderline High, High};
	 * Body Mass Index:		{Underweight, Normal, Overweight, Obese};
	 * Blood Pressure:		{Normal, Prehypertension, Stage 1 Hypertension, Stage 2 Hypertension, Hypertensive Crisis};
	 * 
	 * int[] tcData = {3, 3, 3};
	 * int[] bmiData = {1, 7, 1, 2};
	 * int[] bpData = {3, 4, 4, 2, 1};
	 * 
	 * new ChartBuilder(tcData, bmiData, bpData).createPage();
	 * 
	 */
	public ChartBuilder(){
		totalCholesterolData = new int[3];
		bodyMassIndexData = new int[4];
		bloodPressureData = new int[5];
	}
	
	/**
	 * Usage:
	 * 
	 * Total Cholesterol:	{Desirable, Borderline High, High};
	 * Body Mass Index:		{Underweight, Normal, Overweight, Obese};
	 * Blood Pressure:		{Normal, Prehypertension, Stage 1 Hypertension, Stage 2 Hypertension, Hypertensive Crisis};
	 * 
	 * int[] tcData = {3, 3, 3};
	 * int[] bmiData = {1, 7, 1, 2};
	 * int[] bpData = {3, 4, 4, 2, 1};
	 * 
	 * new ChartBuilder(tcData, bmiData, bpData).createPage();
	 * 
	 * 
	 * @param tcData Total Cholesterol data array
	 * @param bmiData Body Mass Index data array
	 * @param bpData Blood Pressure data array
	 */
	public ChartBuilder(int[] tcData, int[] bmiData, int[] bpData){
		
		totalCholesterolData = new int[3];
		bodyMassIndexData = new int[4];
		bloodPressureData = new int[5];
		
		for (int i = 0; i < totalCholesterolData.length; i++){
			totalCholesterolData[i] = tcData[i];
		}
		
		for (int i = 0; i < bodyMassIndexData.length; i++){
			bodyMassIndexData[i] = bmiData[i];
		}
		
		for (int i = 0; i < bloodPressureData.length; i++){
			bloodPressureData[i] = bpData[i];
		}
	}
	
	public void createPage(){
		String html = "<title>Health Profile Summary</title><h1>Health Screening Results</h1>";
		int[][] data = {totalCholesterolData, bodyMassIndexData, bloodPressureData};
		File htmlFile = new File("chart.html");
		FileOutputStream fop;
		
		for (int[] d : data){
			html += "<img src='" + constructChart(d) + "' /><br />";
		}
		
		try {
			
			fop = new FileOutputStream(htmlFile);
			fop.write(html.getBytes());
			fop.flush();
			fop.close();
			
			Desktop.getDesktop().browse(htmlFile.toURI());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Generalized method for creating a pie chart with the Google Chart API
	 * @param title Chart title
	 * @param color Chart color theme
	 * @param data Array of integers for pie slices
	 * @param labels Parallel array of labels for the data array
	 * @return URL to the image of the pie chart
	 */
	public String buildChart(String title, Color color, int[] data, String[] labels){
		
		if (data.length != labels.length){ //Basic error checking
			return "Data and label arrays must be of the same length";
		}
		
		PieChart chart = new PieChart(new Dimension(CHART_WIDTH, CHART_HEIGHT));
		
		chart.setChartTitle(new ChartTitle(title));
		chart.addChartColor(new ChartColor(color));
		
		//Convert data and label arrays into labeled slices of the pie chart
		for (int i = 0; i < data.length; i++){
			chart.addPieChartSlice(new PieChartSlice.PieChartSliceBuilder(data[i]).label(labels[i]).build());
		}
		
		return chart.getUrl();
	}
	
	/**
	 * 
	 * Constructs a pie chart of inferred category based on the data parameter. The order for the array is as follows:
	 * 
	 * Total Cholesterol:	{Desirable, Borderline High, High};
	 * Body Mass Index:		{Underweight, Normal, Overweight, Obese};
	 * Blood Pressure:		{Normal, Prehypertension, Stage 1 Hypertension, Stage 2 Hypertension, Hypertensive Crisis};
	 * 
	 * @param data Specifically ordered array of values
	 * @return URL to the image of the pie chart
	 */
	public String constructChart(int[] data){
		//Generalized method to create charts
		
		switch(data.length){
			case 3:
				String[] labelsTC = {"Desirable", "Borderline High", "High"};
				return buildChart("Total Cholesterol", Color.MAGENTA, data, labelsTC);
			case 4:
				String[] labelsBMI = {"Underweight", "Normal", "Overweight", "Obese"};
				return buildChart("Body Mass Index", Color.BLUE, data, labelsBMI);
			case 5:	
				String[] labelsBP = {"Normal", "Prehypertension", "Stage 1 Hypertension", "Stage 2 Hypertension", "Hypertensive Crisis"};
				return buildChart("Blood Pressure", Color.RED, data, labelsBP);
			default:
				return "invalid input";
		}
	}
	
	@Deprecated
	public String constructTotalCholesterolChart(int[] data){
		return constructChart(data);
	}
	
	@Deprecated
	public String constructBMIChart(int[] data){
		return constructChart(data);
	}
	
	@Deprecated
	public String constructBloodPressureChart(int[] data){
		return constructChart(data);
	}

	
}
