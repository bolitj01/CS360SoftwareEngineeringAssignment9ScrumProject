package HealthScreener;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.beetledev.www.BmiServiceSoapProxy;

/**
 * The Patient class describes the traits of a health patient screening
 * 
 * @author Caleb Gordon
 */
public class Patient {
	private String name;
	private Date dateOfScreening;
	private int age;
	private int height;
	private int weight;
	private int cholesterol;
	private int systolic;
	private int diastolic;
	
	/**
	 * Constructor which defines a new patient with the given input parameters
	 */
	public Patient(String _name, Date _date, int _age, int _height, int _weight, int _cholesterol, int _systolic, int _diastolic) {
		name = _name; 
		dateOfScreening = _date;
		age = _age;
		height = _height;
		weight = _weight;
		cholesterol = _cholesterol;
		systolic = _systolic;
		diastolic = _diastolic;
	}
	
	/**
	 * @return Calculated BMI, 0.0 if RemoteException was thrown
	 */
	public double calculateBMI() {
		BmiServiceSoapProxy bmiProxy = new BmiServiceSoapProxy();
		double weightInKg = (double)weight * 0.453592;
		double heightInCm = (double)height * 2.54;
		try {
			return bmiProxy.getBmiValue(weightInKg, heightInCm);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0.0;
		}
	}
	
	/**
	 * Determines the patient's cholesterol classification based on their cholesterol level
	 * @return The patient's cholesterol classification
	 */
	public String getCholesterolClassification() {
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
	public String getBloodPressureClassification() {
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
	public String getBMIClassification() {
		double bmi = calculateBMI();
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
	
	/**
	 * Displays a report of the patient's screening in the console
	 */
	public void displayPatientReport() {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormat.format(dateOfScreening);
		int feet = height/12;
		int remaining_inches = height % 12;
		double bmi = calculateBMI();
		DecimalFormat decim = new DecimalFormat("#.#");
		
		System.out.println("Health Screening for " + name + "\n");
		System.out.println("Date: " + date);
		System.out.println("Age: " + age + "\t\tHeight: " + feet + "\' " + remaining_inches + "\"\t\tWeight: " + weight + "\n");
		System.out.println("Total Cholesterol: " + cholesterol + " " + getCholesterolClassification());
		System.out.println("Body Mass Index: " + decim.format(bmi) + " " + getBMIClassification());
		System.out.println("Blood Pressure: " + systolic + "/" + diastolic + " " + getBloodPressureClassification());
	}
}
