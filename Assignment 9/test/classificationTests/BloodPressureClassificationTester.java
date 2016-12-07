package classificationTests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import model.HealthScreenUtility;

@RunWith(Parameterized.class)
public class BloodPressureClassificationTester {
	
	private HealthScreenUtility utility;
	private int systolic;
	private int diastolic;
	private String classification;

	@Before
	public void initialize(){
		utility = new HealthScreenUtility();
	}
	
	public BloodPressureClassificationTester(int systolic, int diastolic, String classification){
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.classification = classification;
	}
	
	@Parameterized.Parameters
	public static Collection bloodPressures() {
		return Arrays.asList(new Object[][] {
			{181, 100, "HYPERTENSIVE CRISIS"},
			{181, 111, "HYPERTENSIVE CRISIS"},
			{179, 111, "HYPERTENSIVE CRISIS"},
			{161, 105, "STAGE 2 HYPERTENSION"},
			{179, 105, "STAGE 2 HYPERTENSION"},
			{171, 101, "STAGE 2 HYPERTENSION"},
			{170, 109, "STAGE 2 HYPERTENSION"},
			{170, 90, "STAGE 2 HYPERTENSION"},
			{150, 105, "STAGE 2 HYPERTENSION"},
			{141, 95, "STAGE 1 HYPERTENSION"},
			{158, 95, "STAGE 1 HYPERTENSION"},
			{150, 91, "STAGE 1 HYPERTENSION"},
			{150, 98, "STAGE 1 HYPERTENSION"},
			{150, 85, "STAGE 1 HYPERTENSION"},
			{125, 95, "STAGE 1 HYPERTENSION"},
			{121, 85, "PREHYPERTENSION"},
			{138, 85, "PREHYPERTENSION"},
			{130, 81, "PREHYPERTENSION"},
			{130, 88, "PREHYPERTENSION"},
			{125, 75, "PREHYPERTENSION"},
			{115, 85, "PREHYPERTENSION"},
			{119, 79, "NORMAL"}
		});
	}
	
	@Test
	public void bloodPressureClassificationTest() {
		assertEquals(classification, utility.getBloodPressureClassification(systolic, diastolic));
	}

}
