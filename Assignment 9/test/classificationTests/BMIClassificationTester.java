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
public class BMIClassificationTester {

	private HealthScreenUtility utility;
	private int weight;
	private int height;
	private String websiteString;
	private String classification;
	
	@Before
	public void intialize(){
		utility = new HealthScreenUtility();
	}
	
	public BMIClassificationTester(int weight, int height, String websiteString, String classification){
		this.weight = weight;
		this.height = height;
		this.websiteString = websiteString;
		this.classification = classification;
	}
	
	@Parameterized.Parameters
	public static Collection bmiData(){
		return Arrays.asList(new Object[][] {
			{150, 58, null, "OBESE"},
			{140, 62, null, "OVERWEIGHT"},
			{220, 73, null, "OVERWEIGHT"},
			{130, 70, null, "NORMAL"},
			{140, 64, null, "NORMAL"},
			{95, 62, null, "UNDERWEIGHT"},
			{95, 62, "http://fakewebsite.com", "UNDERWEIGHT"}
		});
	}
	
	@Test
	public void bmiTest() {
		assertEquals(classification, utility.getBMIClassification(weight, height, websiteString));
	}

}
