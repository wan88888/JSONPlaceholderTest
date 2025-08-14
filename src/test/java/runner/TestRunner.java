package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import constants.TestConstants;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = TestConstants.FEATURES_PATH,
    glue = {TestConstants.GLUE_PACKAGES, TestConstants.BASE_PACKAGE},
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    }
)

public class TestRunner {
}