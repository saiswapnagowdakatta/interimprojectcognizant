
package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ExtentManager {

    private static ExtentReports extent;
    private static final String REPORT_DIR = "target/extent";
    private static final String REPORT_FILE = "ExtentReport.html";
    private static String reportPath = Paths.get(REPORT_DIR, REPORT_FILE).toString();

    /** Return the singleton ExtentReports instance. */
    public static  ExtentReports getInstance() {
        if (extent == null) {
            try {
                Path outDir = Paths.get(REPORT_DIR);
                Files.createDirectories(outDir);

                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

                spark.config().setDocumentTitle("Facebook Signup Automation");
                spark.config().setReportName("Sign Up Flow");
                spark.config().setTheme(Theme.STANDARD);

                extent = new ExtentReports();
                extent.attachReporter(spark);

//                // System info
//                extent.setSystemInfo("OS", System.getProperty("os.name"));
//                extent.setSystemInfo("Java", System.getProperty("java.version"));
//                extent.setSystemInfo("User", System.getProperty("user.name"));

                // Optional: ensure flush happens even if the JVM exits unexpectedly
//                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//                    try {
//                        if (extent != null) {
//                            extent.flush();
//                        }
//                    } catch (Exception ignored) {}
//                }));

            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize ExtentReports", e);
            }
        }
        return extent;
    }

    /** Return the absolute path of the current report file. */
    public static String getReportPath() {
        return reportPath;
    }
}
