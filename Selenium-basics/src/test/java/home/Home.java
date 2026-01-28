
package home;
import ScreenShot.ScreenShotsCapture;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import driversetup.Driversetup;
import excel.Excelvals;
import pagelocators.Pagelocators;

import reporting.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Home {
    public static void main(String[] args) throws IOException {

        // Initialize Extent report
        ExtentReports extent = ExtentManager.getInstance();
        ExtentTest test = extent.createTest("Facebook SignUp", "Automation Report");

        // Launch browser (Chrome or Edge)
        WebDriver driver = Driversetup.getWebDriver("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        test.info("Browser launched and navigated to Facebook");

        // Page Object
        Pagelocators page = new Pagelocators(driver);

        // Open "Create new account" modal
        page.openCreateAccountModal();
        test.info("Opened Create Account modal");

        // -------- Read data from Excel (replace passwordSource as needed) --------
        String firstName   = Excelvals.getFirstName();
        String surname     = Excelvals.getSurname();
        String mobile      = Excelvals.getMobileNumber();
        String gender      = Excelvals.getGender();
        String dayText     = Excelvals.getDayVisibleText();
        int monthIndex     = Integer.parseInt(Excelvals.getMonthIndex());
        String yearValue   = Excelvals.getYearValue();

        // -------- Fill form fields --------
        page.enterFirstName(firstName);
        page.enterSurname(surname);
        page.enterMobile(mobile);
//        page.enterPassword(password); // now using enterPassword()


        // -------- Select DOB --------
        page.selectDayByVisibleText(dayText);
        page.selectMonthByIndex(monthIndex);
        page.selectYearByValue(yearValue);

        // -------- Select Gender --------
        page.selectGender(gender);

        test.info("Read data from Excel");
        // -------- Pre-submit validations from the DOM --------
        String mobileValidation = page.getFieldValidationMessageForMobile();
        String passValidation   = page.getFieldValidationMessageForPassword();

        if (mobileValidation.isBlank()) {
            test.info("Mobile field validation message: " + mobileValidation);
        } else {
            test.info("No native validation message on Mobile field");
        }

        if (passValidation.isBlank()) {
            test.info("Password field validation message: " + passValidation);
        } else {
            test.info("No native validation message on Password field");
        }

        // -------- Screenshot before submit --------
        ScreenShotsCapture.screenShotTestCase(driver);
        test.info("Screenshot taken before submit");

        // -------- Click Sign Up --------
        page.clickSignUp();
        test.info("Clicked Sign Up");

        // -------- Screenshot after submit --------
        ScreenShotsCapture.screenShotTestCase(driver);
        test.info("Screenshot taken after submit");



        // -------- Close browser --------
        Driversetup.driverClose();
        test.pass("Test completed and browser closed");

        // Flush Extent report
        extent.flush();
        System.out.println("Extent report generated at: " + reporting.ExtentManager.getReportPath());
    }
}
