package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extentReports;
    
    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            extentReports = createExtentReports();
        }
        return extentReports;
    }
    
    private static ExtentReports createExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("src/test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("API Test Report");
        sparkReporter.config().setReportName("API Automation Results");
        
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Environment", "Test");
        extentReports.setSystemInfo("Tester", "Automation Team");
        
        return extentReports;
    }
}