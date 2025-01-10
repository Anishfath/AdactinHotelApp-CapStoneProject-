package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {
    public static ExtentReports extent;
    public static ExtentTest test;

    // Setup ExtentReports
    public static void setupExtentReports() {
        // Set the report file path
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReport.html");
        sparkReporter.config().setReportName("Automation Test Report");
        sparkReporter.config().setDocumentTitle("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system information
        extent.setSystemInfo("Tester","Anish Fathima");
        extent.setSystemInfo("Environment", "QA");
    }

    // Tear down ExtentReports
    public static void tearDownExtentReports() {
     extent.flush(); 
}
}
