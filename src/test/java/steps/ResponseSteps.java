package steps;

import base.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 响应验证相关的步骤定义
 * 包含所有响应断言和验证的步骤
 */
public class ResponseSteps {
    private final TestContext context;

    public ResponseSteps() {
        this.context = TestContext.getInstance();
    }

    public ResponseSteps(TestContext context) {
        this.context = context;
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        assertThat(context.getLastResponse().getStatusCode()).isEqualTo(expectedStatusCode);
    }

    @Then("the response should be a JSON array")
    public void the_response_should_be_a_json_array() {
        JsonPath jsonPath = context.getLastResponse().jsonPath();
        assertThat(jsonPath.getList("")).isNotNull();
    }

    @Then("the response array should have {int} items")
    public void the_response_array_should_have_items(int expectedCount) {
        JsonPath jsonPath = context.getLastResponse().jsonPath();
        List<Object> items = jsonPath.getList("");
        assertThat(items.size()).isEqualTo(expectedCount);
    }

    @Then("the response should contain:")
    public void the_response_should_contain(DataTable dataTable) {
        Map<String, String> expectedData = dataTable.asMap(String.class, String.class);
        JsonPath jsonPath = context.getLastResponse().jsonPath();
        
        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            Object actualValue = jsonPath.get(entry.getKey());
            String expectedValue = entry.getValue();
            
            if (actualValue != null) {
                // Convert both to strings for comparison
                String actualString = actualValue.toString();
                assertThat(actualString).isEqualTo(expectedValue);
            } else {
                throw new AssertionError("Field '" + entry.getKey() + "' not found in response");
            }
        }
    }

    @Then("all items in response should have {string} equal to {string}")
    public void all_items_in_response_should_have_field_equal_to(String fieldName, String expectedValue) {
        JsonPath jsonPath = context.getLastResponse().jsonPath();
        List<Object> items = jsonPath.getList("");
        
        for (int i = 0; i < items.size(); i++) {
            String actualValue = jsonPath.getString("[" + i + "]." + fieldName);
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

    @Then("Save attribute {string} as {string}")
    public void save_attribute_as(String jsonPath, String key) {
        Object value = context.getLastResponse().jsonPath().get(jsonPath);
        context.saveAttribute(key, value);
    }
}