
package pagelocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class Pagelocators {

    private final WebDriver driver;

    // Home page: "Create new account" button (stable attribute)
    private final By btnCreateAccount = By.cssSelector("[data-testid='open-registration-form-button']");

    // Registration modal fields (stable NAME attributes)
    private final By firstName = By.name("firstname");
    private final By lastName  = By.name("lastname");
    private final By mobile    = By.name("reg_email__");
    private final By newPass   = By.name("reg_passwd__");

    // DOB dropdowns (stable NAME attributes)
    private final By dayDD   = By.name("birthday_day");
    private final By monthDD = By.name("birthday_month");
    private final By yearDD  = By.name("birthday_year");

    // Gender radios (NAME='sex'; values: 1 = Female, 2 = Male)
    private final By genderFemale = By.cssSelector("input[name='sex'][value='1']");
    private final By genderMale   = By.cssSelector("input[name='sex'][value='2']");

    // Sign Up button
    private final By btnSignUp = By.name("websubmit");



    public Pagelocators(WebDriver driver) {
        this.driver = driver;
    }

    // ---------------- Actions ----------------

    /** Clicks the "Create new account" button to open the sign-up modal. */
    public void openCreateAccountModal() {
        driver.findElement(btnCreateAccount).click();
    }

    /** Enter First Name. */
    public void enterFirstName(String value) {
        WebElement el = driver.findElement(firstName);
        el.clear();
        el.sendKeys(value);
    }

    /** Enter Surname / Last Name. */
    public void enterSurname(String value) {
        WebElement el = driver.findElement(lastName);
        el.clear();
        el.sendKeys(value);
    }

    /** Enter Mobile number */
    public void enterMobile(String value) {
        WebElement el = driver.findElement(mobile);
        el.clear();
        el.sendKeys(value);
    }



    /** Select Day by visible text (e.g., "12"). */
    public void selectDayByVisibleText(String dayText) {
        new Select(driver.findElement(dayDD)).selectByVisibleText(dayText);
    }

    /** Select Month by index (FB supports 1-based index). */
    public void selectMonthByIndex(int monthIndex) {
        new Select(driver.findElement(monthDD)).selectByIndex(monthIndex);
    }

    /** Select Year by value (e.g., "1997"). */
    public void selectYearByValue(String yearValue) {
        new Select(driver.findElement(yearDD)).selectByValue(yearValue);
    }

    /** Select gender radio (Male/Female). */
    public void selectGender(String genderText) {
        if ("Male".equalsIgnoreCase(genderText)) {
            driver.findElement(genderMale).click();
        } else if ("Female".equalsIgnoreCase(genderText)) {
            driver.findElement(genderFemale).click();
        }
    }

    /** Click the Sign Up button. */
    public void clickSignUp() {
        driver.findElement(btnSignUp).click();
    }



    // Native HTML5 validation message for Mobile field
    public String getFieldValidationMessageForMobile() {
        try {
            WebElement el = driver.findElement(mobile);
            return (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return arguments[0].validationMessage;", el);
        } catch (Exception e) {
            return "";
        }
    }

    //Native HTML5 validation message for Password field
    public String getFieldValidationMessageForPassword() {
        try {
            WebElement el = driver.findElement(newPass);
            return (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return arguments[0].validationMessage;", el);
        } catch (Exception e) {
            return "";
        }
    }
}
