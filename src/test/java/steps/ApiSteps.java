package steps;

import base.TestContext;
import constants.TestConstants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * API相关的步骤定义
 * 包含所有HTTP请求相关的步骤
 */
public class ApiSteps {
    private final TestContext context;

    public ApiSteps() {
        this.context = TestContext.getInstance();
    }

    @When("I send a {string} request to {string} with body:")
    public void i_send_request_with_body(String method, String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, Object> requestBody = new HashMap<>(data.get(0));
        Response response = sendRequestWithBody(method, endpoint, requestBody);
        context.setLastResponse(response);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        endpoint = replacePlaceholders(endpoint);
        Response response = given().when().get(endpoint);
        context.setLastResponse(response);
    }

    @When("I send a GET request to {string} with query:")
    public void i_send_a_get_request_with_query(String endpoint, DataTable dataTable) {
        endpoint = replacePlaceholders(endpoint);
        Map<String, String> queryParams = dataTable.asMap(String.class, String.class);
        Response response = given().queryParams(queryParams).when().get(endpoint);
        context.setLastResponse(response);
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        endpoint = replacePlaceholders(endpoint);
        Response response = given().when().delete(endpoint);
        context.setLastResponse(response);
    }

    @When("I send a POST request to {string} with body:")
    public void i_send_a_post_request_to_with_body(String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, Object> requestBody = new HashMap<>(data.get(0));
        Response response = sendRequestWithBody("POST", endpoint, requestBody);
        context.setLastResponse(response);
    }

    @When("I send a PUT request to {string} with body:")
    public void i_send_a_put_request_to_with_body(String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, Object> requestBody = new HashMap<>(data.get(0));
        Response response = sendRequestWithBody("PUT", endpoint, requestBody);
        context.setLastResponse(response);
    }

    @When("I send a PATCH request to {string} with body:")
    public void i_send_a_patch_request_to_with_body(String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, Object> requestBody = new HashMap<>(data.get(0));
        Response response = sendRequestWithBody("PATCH", endpoint, requestBody);
        context.setLastResponse(response);
    }

    /**
     * 发送带有请求体的HTTP请求的通用方法
     */
    private Response sendRequestWithBody(String method, String endpoint, Map<String, Object> requestBody) {
        endpoint = replacePlaceholders(endpoint);
        
        switch (method.toUpperCase()) {
            case TestConstants.HTTP_POST:
                return given().contentType(TestConstants.CONTENT_TYPE_JSON)
                        .body(requestBody)
                        .when().post(endpoint);
            case TestConstants.HTTP_PUT:
                return given().contentType(TestConstants.CONTENT_TYPE_JSON)
                        .body(requestBody)
                        .when().put(endpoint);
            case TestConstants.HTTP_PATCH:
                return given().contentType(TestConstants.CONTENT_TYPE_JSON)
                        .body(requestBody)
                        .when().patch(endpoint);
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    /**
     * 替换端点中的占位符
     */
    private String replacePlaceholders(String endpoint) {
        for (Map.Entry<String, Object> entry : context.getSavedData().entrySet()) {
            if (entry.getValue() != null) {
                endpoint = endpoint.replace(TestConstants.PLACEHOLDER_START + entry.getKey() + TestConstants.PLACEHOLDER_END, entry.getValue().toString());
            }
        }
        return endpoint;
    }
}