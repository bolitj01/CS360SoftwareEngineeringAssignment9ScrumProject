package HealthScreener;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.beetledev.www.BmiServiceSoapProxy;
import com.beetledev.www.ConverterServiceSoapProxy;

public final class HealthScreenUtility {
	
	public HealthScreenUtility(){
	}
	
	/**
	 * @return Calculated BMI, -1.0 if RemoteException was thrown
	 */
	public double calculateBMI(int weight, double height) {
		ConverterServiceSoapProxy bmiConverterProxy = new ConverterServiceSoapProxy();
		BmiServiceSoapProxy bmiProxy = new BmiServiceSoapProxy();
		double weightInKg = 0;
		double heightInCm = 0;
		try {
			weightInKg = bmiConverterProxy.lbs2Kg(weight);
			heightInCm = bmiConverterProxy.in2Cm(height);
			return bmiProxy.getBmiValue(weightInKg, heightInCm);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("RemoteException thrown in calculateBMI(), returning -1.0.\n\n" + e1.getMessage());
			return -1.0;
		} 
	}
	
	/**
	 * Determines the patient's cholesterol classification based on their cholesterol level
	 * @return The patient's cholesterol classification
	 */
	public String getCholesterolClassification(int cholesterol) {
		if (cholesterol >= 240) {
			return "HIGH";
		} else if ((200 <= cholesterol) && (cholesterol <= 239)) {
			return "BORDERLINE HIGH";
		} else {
			return "DESIRABLE";
		}
	}
	
	/**
	 * Determines the patient's blood pressure classification based on their systolic and diastolic levels
	 * @return The patient's blood pressure classification
	 */
	public String getBloodPressureClassification(int systolic, int diastolic) {
		if ((systolic > 180) || (diastolic > 110)) {
			return "HYPERTENSIVE CRISIS";
		} else if (((160 <= systolic) && (systolic <= 180)) || ((100 <= diastolic) && (diastolic <= 110))) {
			return "STAGE 2 HYPERTENSION";
		} else if (((140 <= systolic) && (systolic <= 159)) || ((90 <= diastolic) && (diastolic <= 99))) {
			return "STAGE 1 HYPERTENSION";
		} else if (((120 <= systolic) && (systolic <= 139)) || ((80 <= diastolic) && (diastolic <= 89))) {
			return "PREHYPERTENSION";
		} else {
			return "NORMAL";
		}
	}
	
	/**
	 * Determines the patient's BMI classification based on their BMI level
	 * @return The patient's BMI classification
	 */
	public String getBMIClassification(int weight, double height) {
		double bmi = calculateBMI(weight, height);
		if (bmi >= 30.0) {
			return "OBESE";
		} else if ((25.0 <= bmi) && (bmi <= 29.9)) {
			return "OVERWEIGHT";
		} else if ((18.5 <= bmi) && (bmi <= 24.9)) {
			return "NORMAL";
		} else {
			return "UNDERWEIGHT";
		}
	}
	
	
	public void tempScreenReport(ArrayList<Patient> patients){
		System.out.println("Health Screening Results\n\n" +
				"Date: " + patients.get(0).getDate() + 
				"\nNumber of Individuals Screened: " + patients.size() + "\n");
		
		int desirableCount = 0;
		int borderlineCount = 0;
		int highCount = 0;
		
		int underweightCount = 0;
		int normalBMICount = 0;
		int overweightCount = 0;
		int obeseCount = 0;
		
		int normalBPCount = 0;
		int prehypertensionCount = 0;
		int stage1HypertensionCount = 0;
		int stage2HypertensionCount = 0;
		int hypertensiveCrisisCount = 0;
		
		for (Patient patient: patients){
			if (patient.getCholesterolClassification().equals("DESIRABLE")){
				desirableCount++;
			} else if (patient.getCholesterolClassification().equals("BORDERLINE")){
				borderlineCount++;
			} else if (patient.getCholesterolClassification().equals("HIGH")){
				highCount++;
			}
			
			if (patient.getBMIClassification().equals("UNDERWEIGHT")){
				underweightCount++;
			} else if (patient.getBMIClassification().equals("NORMAL")){
				normalBMICount++;
			} else if (patient.getBMIClassification().equals("OVERWEIGHT")){
				overweightCount++;
			} else if (patient.getBMIClassification().equals("OBESE")){
				obeseCount++;
			}
			
			if (patient.getBloodPressureClassification().equals("NORMAL")){
				normalBPCount++;
			} else if (patient.getBloodPressureClassification().equals("PREHYPERTENSION")){
				prehypertensionCount++;
			} else if (patient.getBloodPressureClassification().equals("STAGE 1 HYPERTENSION")){
				stage1HypertensionCount++;
			} else if (patient.getBloodPressureClassification().equals("STAGE 2 HYPERTENSION")){
				stage2HypertensionCount++;
			} else if (patient.getBloodPressureClassification().equals("HYPERTENSIVE CRISIS")){
				hypertensiveCrisisCount++;
			}
		}
		
		System.out.println("Total Cholesterol\n" + 
		
				"\nDESIRABLE\t\t" + desirableCount + 
				"\nBORDERLINE\t\t" + borderlineCount +
				"\nHIGH\t\t\t" + highCount +
				
				"\n\nBody Mass Index\n" +
				
				"\nUNDERWEIGHT\t\t" + underweightCount +
				"\nNORMAL\t\t\t" + normalBMICount +
				"\nOVERWEIGHT\t\t" + overweightCount +
				"\nOBESE\t\t\t" + obeseCount +
				
				"\n\nBlood Pressure\n" + 
				
				"\nNORMAL\t\t\t" + normalBPCount + 
				"\nPREHYPERTENSION\t\t" + prehypertensionCount +
				"\nSTAGE 1 HYPERTENSION\t" + stage1HypertensionCount + 
				"\nSTAGE 2 HYPERTENSION\t" + stage2HypertensionCount + 
				"\nHYPERTENSIVE CRISIS\t" + stage2HypertensionCount + "\n\n");
				
	}
}
