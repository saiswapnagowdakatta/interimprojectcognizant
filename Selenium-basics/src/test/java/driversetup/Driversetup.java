//this is driversetup class
package driversetup;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Driversetup {

    // WebDriver reference shared across methods
    static WebDriver driver;

    // Base URL to be opened by the browser
    static String baseUrl = "https://www.facebook.com/";

    // To store which browser is selected
    static String browserName;

    // Method to create and return a WebDriver based on the browser name
    public static WebDriver getWebDriver(String browser) {

        browserName = browser;

        // Launch Chrome browser if user selects "chrome"
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        // Launch Edge browser if user selects "edge"
        else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        // Maximize the browser window
        driver.manage().window().maximize();

        // Set implicit wait time (10 seconds)
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open the base URL
        driver.get(baseUrl);


        return driver; // Return driver object
    }

    // Method to close the browser completely
    public static void driverClose() {
        driver.quit();
    }
}
