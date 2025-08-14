package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import constants.TestConstants;
import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private Response lastResponse;
    private Map<String, Object> savedData = new HashMap<>();
    private String authToken;
    private RequestSpecification requestSpecification;
    private String baseUri;
    private static TestContext instance;

    // Private constructor for singleton pattern
    private TestContext() {
        this.baseUri = TestConstants.DEFAULT_BASE_URL;
        this.requestSpecification = buildRequestSpecification();
    }

    // Singleton instance getter
    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    // Helper method to build request specification
    private RequestSpecification buildRequestSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(this.baseUri)
                .addHeader(TestConstants.CONTENT_TYPE_HEADER, TestConstants.CONTENT_TYPE_JSON);
        
        if (authToken != null && !authToken.isEmpty()) {
            builder.addHeader(TestConstants.AUTHORIZATION_HEADER, TestConstants.BEARER_PREFIX + authToken);
        }
        
        return builder.build();
    }

    public void setLastResponse(Response response) {
        this.lastResponse = response;
    }

    public Response getLastResponse() {
        return lastResponse;
    }

    public void saveAttribute(String key, Object value) {
        savedData.put(key, value);
    }

    public Object getAttribute(String key) {
        return savedData.get(key);
    }

    public void setAuthToken(String token) {
        this.authToken = token;
        this.requestSpecification = buildRequestSpecification();
    }

    // Method to set base URI and rebuild request specification
    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
        this.requestSpecification = buildRequestSpecification();
    }

    public String getBaseUri() {
        return baseUri;
    }

    // Method to reset context (useful between test scenarios)
    public void reset() {
        lastResponse = null;
        savedData.clear();
        authToken = null;
        this.requestSpecification = buildRequestSpecification();
    }

    // Method to check if attribute exists
    public boolean hasAttribute(String key) {
        return savedData.containsKey(key);
    }

    // Method to remove specific attribute
    public void removeAttribute(String key) {
        savedData.remove(key);
    }

    // Method to clear all saved data (useful for test cleanup)
    public void clearSavedData() {
        savedData.clear();
    }

    public String getAuthToken() {
        return authToken;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public Map<String, Object> getSavedData() {
        return savedData;
    }
}