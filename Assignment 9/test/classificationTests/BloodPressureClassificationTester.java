package classificationTests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.HealthScreenUtility;

import model.HealthScreenUtility;

public class BloodPressureClassificationTester {
	
	private HealthScreenUtility utility = new HealthScreenUtility();

	// Hypertensive Crisis test
	@Test
	public void bloodPressureHypertensiveCrisisTestA() {
		String bloodPressure = utility.getBloodPressureClassification(181, 100);
		assertEquals("HYPERTENSIVE CRISIS", bloodPressure);
	}
	
	@Test
	public void bloodPressureHypertensiveCrisisTestB() {
		String bloodPressure = utility.getBloodPressureClassification(181, 111);
		assertEquals("HYPERTENSIVE CRISIS", bloodPressure);
	}
	
	@Test
	public void bloodPressureHypertensiveCrisisTestC() {
		String bloodPressure = utility.getBloodPressureClassification(179, 111);
		assertEquals("HYPERTENSIVE CRISIS", bloodPressure);
	}
	
	// Stage 2 Hypertension tests
	@Test
	public void bloodPressureStage2HypertensionTestA() {
		String bloodPressure = utility.getBloodPressureClassification(161, 105);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage2HypertensionTestB() {
		String bloodPressure = utility.getBloodPressureClassification(179, 105);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage2HypertensionTestC() {
		String bloodPressure = utility.getBloodPressureClassification(170, 101);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage2HypertensionTestD() {
		String bloodPressure = utility.getBloodPressureClassification(170, 109);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage2HypertensionTestE() {
		String bloodPressure = utility.getBloodPressureClassification(170, 90);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage2HypertensionTestF() {
		String bloodPressure = utility.getBloodPressureClassification(150, 105);
		assertEquals("STAGE 2 HYPERTENSION", bloodPressure);
	}
	
	// Stage 1 Hypertension tests
	@Test
	public void bloodPressureStage1HypertensionTestA() {
		String bloodPressure = utility.getBloodPressureClassification(141, 95);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage1HypertensionTestB() {
		String bloodPressure = utility.getBloodPressureClassification(158, 95);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage1HypertensionTestC() {
		String bloodPressure = utility.getBloodPressureClassification(150, 91);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage1HypertensionTestD() {
		String bloodPressure = utility.getBloodPressureClassification(150, 98);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage1HypertensionTestE() {
		String bloodPressure = utility.getBloodPressureClassification(150, 85);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureStage1HypertensionTestF() {
		String bloodPressure = utility.getBloodPressureClassification(125, 95);
		assertEquals("STAGE 1 HYPERTENSION", bloodPressure);
	}
	
	// Prehypertension tests
	@Test
	public void bloodPressurePrehypertensionTestA() {
		String bloodPressure = utility.getBloodPressureClassification(121, 85);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressurePrehypertensionTestB() {
		String bloodPressure = utility.getBloodPressureClassification(138, 85);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressurePrehypertensionTestC() {
		String bloodPressure = utility.getBloodPressureClassification(130, 81);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressurePrehypertensionTestD() {
		String bloodPressure = utility.getBloodPressureClassification(130, 88);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressurePrehypertensionTestE() {
		String bloodPressure = utility.getBloodPressureClassification(125, 75);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressurePrehypertensionTestF() {
		String bloodPressure = utility.getBloodPressureClassification(115, 85);
		assertEquals("PREHYPERTENSION", bloodPressure);
	}
	
	@Test
	public void bloodPressureNormalTest() {
		String bloodPressure = utility.getBloodPressureClassification(119, 79);
		assertEquals("NORMAL", bloodPressure);
	}

}
