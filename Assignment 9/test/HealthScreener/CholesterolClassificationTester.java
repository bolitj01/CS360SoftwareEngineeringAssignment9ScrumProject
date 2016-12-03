package HealthScreener;

import static org.junit.Assert.*;

import org.junit.Test;

public class CholesterolClassificationTester {

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

}