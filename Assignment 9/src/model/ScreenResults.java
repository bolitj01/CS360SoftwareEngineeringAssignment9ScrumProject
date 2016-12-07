package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ScreenResults implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date dateOfScreening;
	private ArrayList<Patient> patients;
	
	/**
	 * Constructor for ScreenResults
	 * @param currentDate - Date object holding todays date
	 * @param patientsIn - Array of patients that were screened today
	 */
	public ScreenResults(Date currentDate, ArrayList<Patient> patientsIn) {
		this.dateOfScreening = currentDate;
		this.patients = patientsIn;
	}
	
	
	/**
	 * calculates how many patients have desirable cholesterol
	 * @return - amount of patients that have desirable cholesterol
	 */
	public int countDesirableCholesterol() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getCholesterolClassification().toUpperCase().equals("DESIRABLE")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have border line cholesterol
	 * @return - amount of patients with borderline high cholesterol
	 */
	public int countBorderLineCholesterol() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getCholesterolClassification().toUpperCase().equals("BORDERLINE HIGH")) {
				count++;
			}
		}
		return count;
	}
	
	/***
	 * Calculates how many patients have high colesterol
	 * @return - amount of patients with high cholesterol
	 */
	public int countHighCholesterol() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getCholesterolClassification().toUpperCase().equals("HIGH")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have normal BMI value
	 * @return - amount of patients with normal BMI values
	 */
	public int countNormalBMI() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getBMIClassification().toUpperCase().equals("NORMAL")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have overweight BMI values
	 * @return - amount of patients with overweight BMI values
	 */
	public int countOverweightBMI() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getBMIClassification().toUpperCase().equals("OVERWEIGHT")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have UNDERWEIGHT BMI values
	 * @return - amount of patients with UNDERWEIGHT BMI values
	 */
	public int countUnderweightBMI() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getBMIClassification().toUpperCase().equals("UNDERWEIGHT")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have UNDERWEIGHT BMI values
	 * @return - amount of patients with UNDERWEIGHT BMI values
	 */
	public int countObeseBMI() {
		int count = 0;
		for(Patient patient : patients ) {
			if(patient.getBMIClassification().toUpperCase().equals("OBESE")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have NORMAL Blood Pressure values
	 * @return - amount of patients with NORMAL Blood Pressure values
	 */
	public int countNormalBloodPressure() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getBloodPressureClassification().toUpperCase().equals("NORMAL")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have STAGE 1 HYPERTENSION Blood Pressure values
	 * @return - amount of patients with STAGE 1 HYPERTENSION Blood Pressure values
	 */
	public int countStage1HypertensionBloodPressure() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getBloodPressureClassification().toUpperCase().equals("STAGE 1 HYPERTENSION")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have STAGE 2 HYPERTENSION Blood Pressure values
	 * @return - amount of patients with STAGE 2 HYPERTENSION Blood Pressure values
	 */
	public int countStage2HypertensionBloodPressure() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getBloodPressureClassification().toUpperCase().equals("STAGE 2 HYPERTENSION")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have PREHYPERTENSION Blood Pressure values
	 * @return - amount of patients with PREHYPERTENSION Blood Pressure values
	 */
	public int countPrehypertensionBloodPressure() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getBloodPressureClassification().toUpperCase().equals("PREHYPERTENSION")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates how many patients have HYPERTENSIVE CRISIS Blood Pressure values
	 * @return - amount of patients with HYPERTENSIVE CRISIS Blood Pressure values
	 */
	public int countHypertensionCrisisBloodPressure() {
		int count = 0;
		for(Patient patient : patients) {
			if(patient.getBloodPressureClassification().toUpperCase().equals("HYPERTENSIVE CRISIS")) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Display a report of all of the patient screenings for the entire day to the console
	 */
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormat.format(dateOfScreening);
		String message = "Health Screening Results\n \n" + 
						 "Date: " + date + "\n" + 
						 "Number of Individuals Screened: " + patients.size() + "\n \n" +
						 "Total Cholesterol\n \n" +
						 "DESIRABLE " + this.countDesirableCholesterol() + "\n" +
						 "BORDERLINE " + this.countBorderLineCholesterol() + "\n" +
						 "HIGH " + this.countHighCholesterol() + "\n \n" +
						 "Body Mass Index\n \n" +
						 "UNDERWEIGHT " + this.countUnderweightBMI() + "\n" + 
						 "NORMAL " + this.countNormalBMI() + "\n" + 
						 "OVERWEIGHT " + this.countOverweightBMI() + "\n" + 
						 "OBESE " + this.countObeseBMI() + "\n \n" + 
						 "Blood Pressure \n \n" + 
						 "NORMAL " + this.countNormalBloodPressure() + "\n" + 
						 "PREHYPERTENSION " + this.countPrehypertensionBloodPressure() + "\n" +
						 "STAGE 1 HYPERTENSION " + this.countStage1HypertensionBloodPressure() + "\n" + 
						 "STAGE 2 HYPERTENSION " + this.countStage2HypertensionBloodPressure() + "\n" +
						 "HYPERTENSION CRISIS " + this.countHypertensionCrisisBloodPressure() + "\n";
		return message;
	}
	
	/**
	 * Print a report of the health screenings and generate website pie charts using ChartBuilder
	 */
	public void generateReport(){
		int[] cholesterolData = new int[3];
		cholesterolData[0] = this.countDesirableCholesterol();
		cholesterolData[1] = this.countBorderLineCholesterol();
		cholesterolData[2] = this.countHighCholesterol();
		
		int[] bmiData = new int[4];
		bmiData[0] = this.countUnderweightBMI();
		bmiData[1] = this.countNormalBMI();
		bmiData[2] = this.countOverweightBMI();
		bmiData[3] = this.countObeseBMI();
		
		int[] bloodPressureData = new int[5];
		bloodPressureData[0] = this.countNormalBloodPressure();
		bloodPressureData[1] = this.countPrehypertensionBloodPressure();
		bloodPressureData[2] = this.countStage1HypertensionBloodPressure();
		bloodPressureData[3] = this.countStage2HypertensionBloodPressure();
		bloodPressureData[4] = this.countHypertensionCrisisBloodPressure();
		
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormat.format(dateOfScreening);
		String report = "Health Screening Results\n \n" + 
						 "Date: " + date + "\n" + 
						 "Number of Individuals Screened: " + patients.size() + "\n \n" +
						 "Total Cholesterol\n \n" +
						 "DESIRABLE " + cholesterolData[0] + "\n" +
						 "BORDERLINE " + cholesterolData[1] + "\n" +
						 "HIGH " + cholesterolData[2] + "\n \n" +
						 "Body Mass Index\n \n" +
						 "UNDERWEIGHT " + bmiData[0] + "\n" + 
						 "NORMAL " + bmiData[1] + "\n" + 
						 "OVERWEIGHT " + bmiData[2] + "\n" + 
						 "OBESE " + bmiData[3] + "\n \n" + 
						 "Blood Pressure \n \n" + 
						 "NORMAL " + bloodPressureData[0] + "\n" + 
						 "PREHYPERTENSION " + bloodPressureData[1] + "\n" +
						 "STAGE 1 HYPERTENSION " + bloodPressureData[2] + "\n" +
						 "STAGE 2 HYPERTENSION " + bloodPressureData[3] + "\n" +
						 "HYPERTENSION CRISIS " + bloodPressureData[4] + "\n";
		
		System.out.println(report);
		
		new ChartBuilder(cholesterolData, bmiData, bloodPressureData).createPage();
	}
}
