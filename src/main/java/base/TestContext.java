package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
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
        this.baseUri = "https://jsonplaceholder.typicode.com"; // Default to JSONPlaceholder
        this.requestSpecification = buildRequestSpecification();
    }

    // Singleton instance getter
    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    // Constructor with custom base URI
    public TestContext(String baseUri) {
        this.baseUri = baseUri;
        this.requestSpecification = buildRequestSpecification();
    }

    // Helper method to build request specification
    private RequestSpecification buildRequestSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(this.baseUri)
                .addHeader("Content-Type", "application/json");
        
        if (authToken != null && !authToken.isEmpty()) {
            builder.addHeader("Authorization", "Bearer " + authToken);
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

    // Method to clear all saved data (useful for test cleanup)
    public void clearSavedData() {
        savedData.clear();
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