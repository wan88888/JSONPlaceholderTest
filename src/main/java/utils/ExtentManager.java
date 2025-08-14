package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.TestConstants;

public class ExtentManager {
    private static ExtentReports extentReports;
    
    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            extentReports = createExtentReports();
        }
        return extentReports;
    }
    
    private static ExtentReports createExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(TestConstants.EXTENT_REPORT_PATH);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle(TestConstants.REPORT_TITLE);
        sparkReporter.config().setReportName(TestConstants.REPORT_NAME);
        
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Environment", TestConstants.TEST_ENVIRONMENT);
        extentReports.setSystemInfo("Tester", TestConstants.TESTER_NAME);
        
        return extentReports;
    }
}