package base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Hooks {
    private final TestContext context;
    private static ExtentReports extentReports = ExtentManager.getExtentReports();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public Hooks() {
        this.context = TestContext.getInstance();
    }

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        // Reset context state before each scenario
        context.reset();
        
        // Create a new test for each scenario with proper name
        ExtentTest extentTest = extentReports.createTest(scenario.getName());
        test.set(extentTest);
        
        test.get().info("Starting scenario: " + scenario.getName());
        test.get().info("Tags: " + scenario.getSourceTagNames());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            test.get().log(Status.FAIL, "Scenario failed: " + scenario.getName());
        } else {
            test.get().log(Status.PASS, "Scenario passed: " + scenario.getName());
        }
        
        test.get().info("Scenario execution completed.");
        
        // Clean up ThreadLocal to prevent memory leaks
        test.remove();
        
        // Flush reports
        extentReports.flush();
    }
    
    // Utility method to get current test instance for use in step definitions
    public static ExtentTest getCurrentTest() {
        return test.get();
    }
}