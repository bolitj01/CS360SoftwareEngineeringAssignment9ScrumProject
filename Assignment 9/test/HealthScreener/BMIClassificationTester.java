package HealthScreener;

import static org.junit.Assert.*;

import org.junit.Test;

public class BMIClassificationTester {

	private HealthScreenUtility utility = new HealthScreenUtility();
	
	// BMI tests
	@Test
	public void bmiObeseTest() {
		String bmiClassification = utility.getBMIClassification(150, 58);
		assertEquals("OBESE", bmiClassification);
	}
	
	@Test
	public void bmiOverweightTest_A() {
		String bmiClassification = utility.getBMIClassification(140, 62);
		assertEquals("OVERWEIGHT", bmiClassification);
	}
	
	@Test
	public void bmiOverweightTest_B() {
		String bmiClassification = utility.getBMIClassification(220, 73);
		assertEquals("OVERWEIGHT", bmiClassification);
	}
	
	@Test
	public void bmiNormalTest_A() {
		String bmiClassification = utility.getBMIClassification(130, 70);
		assertEquals("NORMAL", bmiClassification);
	}
	
	@Test
	public void bmiNormalTest_B() {
		String bmiClassification = utility.getBMIClassification(140, 64);
		assertEquals("NORMAL", bmiClassification);
	}
	
	@Test
	public void bmiUnderweightTest() {
		String bmiClassification = utility.getBMIClassification(95, 62);
		assertEquals("UNDERWEIGHT", bmiClassification);
	}

}
