package model;

import java.io.Serializable;

/**
 * The Patient class describes the traits of a health patient screening
 * 
 * @author Caleb Gordon
 */
public class Patient implements Serializable{
	private static final long serialVersionUID = 1L;
	
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
	public Patient(String name, String date, int age, String height, int weight, double bmi, int cholesterol, int systolic, 
			int diastolic, String cholesterolClassification, String bmiClassification, String bloodPressureClassification) {
		this.name = name; 
		this.dateOfScreening = date;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.cholesterol = cholesterol;
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.bmi = bmi;
		this.cholesterolClassification = cholesterolClassification;
		this.bmiClassification = bmiClassification;
		this.bloodPressureClassification = bloodPressureClassification;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	public String getHeight(){
		return height;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public int getCholesterol(){
		return cholesterol;
	}
	
	public int getSystolicBloodPressure(){
		return systolic;
	}
	
	public int getDiastolicBloodPressure(){
		return diastolic;
	}
	
	public double getBMI(){
		return bmi;
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
}