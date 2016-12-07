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
public class CholesterolClassificationTester {

	private HealthScreenUtility utility;
	private int cholesterol;
	private String classification;
	
	@Before
	public void initialize(){
		utility = new HealthScreenUtility();
	}
	
	public CholesterolClassificationTester(int cholesterol, String classification){
		this.cholesterol = cholesterol;
		this.classification = classification;
	}
	
	@Parameterized.Parameters
	public static Collection cholesterols(){
		return Arrays.asList(new Object[][]{
			{241, "HIGH"},
			{238, "BORDERLINE HIGH"},
			{201, "BORDERLINE HIGH"},
			{199, "DESIRABLE"}
		});
	}
	@Test
	public void cholesterolBorderlineHighTestB() {
		assertEquals(classification, utility.getCholesterolClassification(cholesterol));
	}

}
