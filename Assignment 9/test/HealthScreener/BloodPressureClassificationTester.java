package HealthScreener;

import static org.junit.Assert.*;

import org.junit.Test;

public class BloodPressureClassificationTester {
	
	private HealthScreenUtility utility = new HealthScreenUtility();

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

}
