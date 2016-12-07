package model;

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
	public double calculateBMI(int weight, double height, String serviceURL) {
		ConverterServiceSoapProxy bmiConverterProxy = new ConverterServiceSoapProxy();
		BmiServiceSoapProxy bmiProxy = new BmiServiceSoapProxy();
		if (serviceURL != null) {
			bmiConverterProxy.setEndpoint(serviceURL);
		}
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
	public String getBMIClassification(int weight, double height, String serviceURL) {
		double bmi = calculateBMI(weight, height, serviceURL);
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
}
