package api.tests;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import api.utility.TestDataReader;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import java.util.Properties;

public class VisualCrossingAPITest {

	private Properties testData, config;

	@BeforeMethod
	@BeforeClass
	public void setUp() {
		testData = TestDataReader.readPropertiesFile("src/test/resources/testData.properties");
		config = TestDataReader.readPropertiesFile("src/test/resources/config.properties");
		baseURI = config.getProperty("baseURI");
	}

	@Test
	public void verifyAPIData() {
		Response response = 
				given()
					.param("unitGroup", "metric")
					.param("key", testData.getProperty("apiKey"))
					.param("contentType", "json").
				when()
					.get(testData.getProperty("city")).
				then()
					.extract()
					.response();
		
		// Verifying status code using TestNG assertion
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Unexpected status code");

        // Verifying address using TestNG assertion
        String address = response.path("address");
        Assert.assertEquals(address, testData.getProperty("city"), "Incorrect address");

        // Verifying timezone using TestNG assertion
        String timezone = response.path("timezone");
        Assert.assertEquals(timezone, testData.getProperty("timezone"), "Incorrect timezone");
        
        //Logging response
        response.then().log().headers();      

		// Printing response to check the data on the console. (Might need to increase the console buffer size to see the full response)
		response.prettyPrint();
	}
}
