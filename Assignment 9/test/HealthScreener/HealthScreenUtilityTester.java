package HealthScreener;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Assert.*;


public class HealthScreenUtilityTester {
	private HealthScreenUtility utility = new HealthScreenUtility();
	
	// Cholesterol Tests
	@Test
	public void cholesterolHighTest() {
		String cholesterol = utility.getCholesterolClassification(241);
		assertEquals("HIGH", cholesterol);
	}
	
	@Test
	public void cholesterolBorderlineHighTest_A() {
		String cholesterol = utility.getCholesterolClassification(238);
		assertEquals("BORDERLINE HIGH", cholesterol);
	}
	
	@Test
	public void cholesterolBorderlineHighTest_B() {
		String cholesterol = utility.getCholesterolClassification(201);
		assertEquals("BORDERLINE HIGH", cholesterol);
	}
	
	@Test
	public void cholesterolDesirableTest() {
		String cholesterol = utility.getCholesterolClassification(199);
		assertEquals("DESIRABLE", cholesterol);
	}
	
	// Hypertensive Crisis test
	@Test
	public void bloodPressureHypertensiveCrisisTest() {
		String bloodPressure = utility.getBloodPressureClassification(181, 100);
		assertEquals("HYPERTENSIVE CRISIS", bloodPressure);
	}
	
	// Stage 2 Hypertension tests
	@Test
	public void bloodPressureStage2HypertensionTest_A() {
		String bloodPressure = utility.getBloodPressureClassification(161, 105);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage2HypertensionTest_B() {
		String bloodPressure = utility.getBloodPressureClassification(179, 105);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage2HypertensionTest_C() {
		String bloodPressure = utility.getBloodPressureClassification(170, 101);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage2HypertensionTest_D() {
		String bloodPressure = utility.getBloodPressureClassification(170, 109);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	// Stage 1 Hypertension tests
	@Test
	public void bloodPressureStage1HypertensionTest_A() {
		String bloodPressure = utility.getBloodPressureClassification(141, 95);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage1HypertensionTest_B() {
		String bloodPressure = utility.getBloodPressureClassification(158, 95);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage1HypertensionTest_C() {
		String bloodPressure = utility.getBloodPressureClassification(150, 91);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage1HypertensionTest_D() {
		String bloodPressure = utility.getBloodPressureClassification(150, 98);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	// Prehypertension tests
	@Test
	public void bloodPressurePrehypertensionTest_A() {
		String bloodPressure = utility.getBloodPressureClassification(121, 85);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressurePrehypertensionTest_B() {
		String bloodPressure = utility.getBloodPressureClassification(138, 85);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressurePrehypertensionTest_C() {
		String bloodPressure = utility.getBloodPressureClassification(130, 81);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressurePrehypertensionTest_D() {
		String bloodPressure = utility.getBloodPressureClassification(130, 88);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureNormalTest() {
		String bloodPressure = utility.getBloodPressureClassification(119, 79);
		assertEquals("NORMAL", bloodPressure);
	}
	
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

