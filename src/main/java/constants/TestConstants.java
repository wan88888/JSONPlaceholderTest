package constants;

/**
 * 测试常量类
 * 集中管理项目中使用的常量值
 */
public final class TestConstants {
    
    // 私有构造函数，防止实例化
    private TestConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    // API相关常量
    public static final String DEFAULT_BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    
    // 报告相关常量
    public static final String EXTENT_REPORT_PATH = "src/test-output/ExtentReport.html";
    public static final String EXTENT_CONFIG_PATH = "src/test/resources/extent-config.xml";
    public static final String CUCUMBER_REPORT_PATH = "target/cucumber-reports.html";
    
    // 报告配置常量
    public static final String REPORT_TITLE = "API Test Report";
    public static final String REPORT_NAME = "API Automation Results";
    public static final String TEST_ENVIRONMENT = "Test";
    public static final String TESTER_NAME = "Automation Team";
    
    // 文件路径常量
    public static final String FEATURES_PATH = "src/test/resources/features";
    public static final String GLUE_PACKAGES = "steps";
    public static final String BASE_PACKAGE = "base";
    
    // HTTP方法常量
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";
    public static final String HTTP_PATCH = "PATCH";
    public static final String HTTP_GET = "GET";
    public static final String HTTP_DELETE = "DELETE";
    
    // 测试运行器配置
    public static final String[] CUCUMBER_PLUGINS = {
        "pretty",
        "html:" + CUCUMBER_REPORT_PATH,
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    };
    
    // 认证相关常量
    public static final String DEFAULT_AUTH_TOKEN = "special-key";
    
    // JSON路径相关常量
    public static final String EMPTY_JSON_PATH = "";
    
    // 占位符相关常量
    public static final String PLACEHOLDER_START = "{";
    public static final String PLACEHOLDER_END = "}";
}