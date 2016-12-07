package HealthScreener;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.beetledev.www.BmiServiceSoapProxy;
import com.beetledev.www.ConverterServiceSoapProxy;

/**
 * The Patient class describes the traits of a health patient screening
 * 
 * @author Caleb Gordon
 */
public class Patient implements Serializable{
	private String name;
	private String dateOfScreening;
	private int age;
	private String height;
	private int weight;
	private int cholesterol;
	private int systolic;
	private int diastolic;
	private double bmi;
	private String cholesterolClassification;
	private String bmiClassification;
	private String bloodPressureClassification;
	
	/**
	 * Constructor which defines a new patient with the given input parameters
	 */
	public Patient(String _name, String _date, int _age, String _height, int _weight, double _bmi, int _cholesterol, int _systolic, 
			int _diastolic, String _cholesterolClassification, String _bmiClassification, String _bloodPressureClassification) {
		name = _name; 
		dateOfScreening = _date;
		age = _age;
		height = _height;
		weight = _weight;
		cholesterol = _cholesterol;
		systolic = _systolic;
		diastolic = _diastolic;
		bmi = _bmi;
		cholesterolClassification = _cholesterolClassification;
		bmiClassification = _bmiClassification;
		bloodPressureClassification = _bloodPressureClassification;
	}
	
	public String getDate(){
		return dateOfScreening;
	}
	
	public String getCholesterolClassification(){
		return cholesterolClassification;
	}
	
	public String getBMIClassification(){
		return bmiClassification;
	}
	
	public String getBloodPressureClassification(){
		return bloodPressureClassification;
	}
	
	
//	/**
//	 * Displays a report of the patient's screening in the console
//	 */
//	public void displayPatientReport() {
//		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
//		String date = dateFormat.format(dateOfScreening);
//		int feet = height/12;
//		int remaining_inches = height % 12;
//		DecimalFormat decim = new DecimalFormat("#.#");
//		
//		System.out.println("Health Screening for " + name + "\n");
//		System.out.println("Date: " + date);
//		System.out.println("Age: " + age + "\t\tHeight: " + feet + "\' " + remaining_inches + "\"\t\tWeight: " + weight + "\n");
//		System.out.println("Total Cholesterol: " + cholesterol + " " + getCholesterolClassification());
//		System.out.println("Body Mass Index: " + decim.format(bmi) + " " + getBMIClassification());
//		System.out.println("Blood Pressure: " + systolic + "/" + diastolic + " " + getBloodPressureClassification());
//	}
	
	
}
