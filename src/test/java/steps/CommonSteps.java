package steps;

import base.TestContext;
import io.cucumber.java.en.Given;
import utils.AuthUtils;

import static io.restassured.RestAssured.*;

/**
 * 通用步骤定义
 * 包含基础配置、认证等通用功能
 */
public class CommonSteps {
    private final TestContext context;

    public CommonSteps() {
        this.context = TestContext.getInstance();
    }

    public CommonSteps(TestContext context) {
        this.context = context;
    }

    @Given("I set base URL to {string}")
    public void i_set_base_url_to(String baseUrl) {
        baseURI = baseUrl;
        context.setBaseUri(baseUrl);
    }

    @Given("I authenticate using API")
    public void i_authenticate_using_api() {
        String token = AuthUtils.createAuthToken();
        context.setAuthToken(token);
    }

}
