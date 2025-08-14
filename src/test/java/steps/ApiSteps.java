package steps;

import base.TestContext;
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

    public ApiSteps(TestContext context) {
        this.context = context;
    }

    @When("I send a {string} request to {string} with body:")
    public void i_send_request_with_body(String method, String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, Object> requestBody = new HashMap<>(data.get(0));
        endpoint = replacePlaceholders(endpoint);

        Response response;
        switch (method.toUpperCase()) {
            case "POST":
                response = given().contentType("application/json")
                        .body(requestBody)
                        .when().post(endpoint);
                break;
            case "PUT":
                response = given().contentType("application/json")
                        .body(requestBody)
                        .when().put(endpoint);
                break;
            case "PATCH":
                response = given().contentType("application/json")
                        .body(requestBody)
                        .when().patch(endpoint);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
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
        endpoint = replacePlaceholders(endpoint);
        
        Response response = given().contentType("application/json")
                .body(requestBody)
                .when().post(endpoint);
        context.setLastResponse(response);
    }

    @When("I send a PUT request to {string} with body:")
    public void i_send_a_put_request_to_with_body(String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, Object> requestBody = new HashMap<>(data.get(0));
        endpoint = replacePlaceholders(endpoint);
        
        Response response = given().contentType("application/json")
                .body(requestBody)
                .when().put(endpoint);
        context.setLastResponse(response);
    }

    @When("I send a PATCH request to {string} with body:")
    public void i_send_a_patch_request_to_with_body(String endpoint, DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, Object> requestBody = new HashMap<>(data.get(0));
        endpoint = replacePlaceholders(endpoint);
        
        Response response = given().contentType("application/json")
                .body(requestBody)
                .when().patch(endpoint);
        context.setLastResponse(response);
    }

    private String replacePlaceholders(String endpoint) {
        for (Map.Entry<String, Object> entry : context.getSavedData().entrySet()) {
            if (entry.getValue() != null) {
                endpoint = endpoint.replace("{" + entry.getKey() + "}", entry.getValue().toString());
            }
        }
        return endpoint;
    }
}