package HealthScreener;

import static org.junit.Assert.*;

import org.junit.Test;

public class BMIClassificationTester {

	private HealthScreenUtility utility = new HealthScreenUtility();
	
	// BMI tests
	@Test
	public void bmiObeseTest() {
		String bmiClassification = utility.getBMIClassification(150, 58, null);
		assertEquals("OBESE", bmiClassification);
	}
	
	@Test
	public void bmiOverweightTest_A() {
		String bmiClassification = utility.getBMIClassification(140, 62, null);
		assertEquals("OVERWEIGHT", bmiClassification);
	}
	
	@Test
	public void bmiOverweightTest_B() {
		String bmiClassification = utility.getBMIClassification(220, 73, null);
		assertEquals("OVERWEIGHT", bmiClassification);
	}
	
	@Test
	public void bmiNormalTest_A() {
		String bmiClassification = utility.getBMIClassification(130, 70, null);
		assertEquals("NORMAL", bmiClassification);
	}
	
	@Test
	public void bmiNormalTest_B() {
		String bmiClassification = utility.getBMIClassification(140, 64, null);
		assertEquals("NORMAL", bmiClassification);
	}
	
	@Test
	public void bmiUnderweightTest() {
		String bmiClassification = utility.getBMIClassification(95, 62, null);
		assertEquals("UNDERWEIGHT", bmiClassification);
	}
	
	@Test
	public void bmiRemoteExceptionTest() {
		String bmiClassification = utility.getBMIClassification(95, 62, "http://fakewebsite.com");
		assertEquals("UNDERWEIGHT", bmiClassification);
	}

}
