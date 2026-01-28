
package ScreenShot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils; // For copying file from temp to target path

public class ScreenShotsCapture {

    // Simple counter to make each screenshot filename unique
    public static int flag = 1;

    public static void screenShotTestCase(WebDriver driver) {

        // Take screenshot and store it in a temporary file
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {

            FileUtils.copyFile(
                    src,
                    new File("C:\\Users\\2457210\\IdeaProjects\\Selenium\\Selenium-basics\\src\\test\\resources\\Screenshotsfullpage" + flag + ".png")
            );
        } catch (IOException e) {
            // Print the stack trace if file copy fails
            e.printStackTrace();
        }

        // Increment flag to avoid overwriting the previous screenshot
        flag++;
    }
}
