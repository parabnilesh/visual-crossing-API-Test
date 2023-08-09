package api.utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {

	/*public static Properties readTestData() {
        Properties testData = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/TestData.properties");
            testData.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }*/
	
	static Properties readProperties;
	
	public static Properties readPropertiesFile(String filePath) {
        readProperties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            readProperties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readProperties;
	}
}
