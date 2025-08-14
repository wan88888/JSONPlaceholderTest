# JSONPlaceholder API 测试项目

这是一个基于 Cucumber BDD 框架的 REST API 自动化测试项目，用于测试 [JSONPlaceholder](https://jsonplaceholder.typicode.com/) API。

## 项目特性

- 🥒 **Cucumber BDD**: 使用 Gherkin 语法编写可读性强的测试场景
- 🔧 **RestAssured**: 强大的 REST API 测试库
- 📊 **ExtentReports**: 生成详细的测试报告
- 🏗️ **模块化设计**: 步骤定义按功能模块组织
- 🔄 **状态管理**: 支持测试场景间的数据共享
- ✅ **Maven 构建**: 标准的 Maven 项目结构
- 🎯 **常量管理**: 集中化的配置常量管理
- 🚀 **代码优化**: 消除硬编码，提升可维护性

## 环境要求

- **Java**: JDK 8 或更高版本
- **Maven**: 3.6.0 或更高版本
- **网络**: 能够访问 https://jsonplaceholder.typicode.com

## 快速开始

### 1. 克隆项目
```bash
git clone <repository-url>
cd JSONPlaceholderTest
```

### 2. 安装依赖
```bash
mvn clean install
```

### 3. 运行测试
```bash
# 运行所有测试
mvn test

# 运行特定标签的测试
mvn test -Dcucumber.filter.tags="@api"
```

### 4. 查看测试报告
测试完成后，可以在以下位置查看报告：
- **ExtentReports**: `test-output/ExtentReport.html`
- **Cucumber Reports**: `target/cucumber-reports.html`
- **Surefire Reports**: `target/surefire-reports/`

## 项目结构

```
JSONPlaceholderTest/
├── src/
│   ├── main/java/
│   │   ├── base/
│   │   │   ├── TestContext.java      # 测试上下文管理
│   │   │   └── Hooks.java            # 测试生命周期钩子
│   │   ├── constants/
│   │   │   └── TestConstants.java    # 项目常量管理
│   │   └── utils/
│   │       ├── AuthUtils.java        # 认证工具类
│   │       └── ExtentManager.java    # 报告管理工具
│   └── test/
│       ├── java/
│       │   ├── runner/
│       │   │   └── TestRunner.java   # 测试运行器
│       │   └── steps/
│       │       ├── CommonSteps.java  # 通用步骤定义
│       │       ├── ApiSteps.java     # API请求步骤
│       │       └── ResponseSteps.java # 响应验证步骤
│       └── resources/
│           ├── features/
│           │   ├── sample.feature    # 基础API测试
│           │   └── advanced.feature  # 高级功能测试
│           ├── extent-config.xml     # ExtentReports配置
│           └── extent.properties     # ExtentReports属性
├── pom.xml                          # Maven配置文件
├── .gitignore                       # Git忽略文件
├── README.md                        # 项目说明文档
├── STEP_DEFINITIONS_GUIDE.md        # 步骤定义组织指南
├── CODE_OPTIMIZATION_SUMMARY.md     # 代码优化总结
└── ADDITIONAL_OPTIMIZATION_SUMMARY.md # 进一步优化总结
```

## 核心组件说明

### 测试上下文 (TestContext)
- 单例模式设计，确保测试状态在步骤间共享
- 支持动态配置 API 基础 URL
- 提供请求/响应管理和数据存储功能
- 优化的构造函数设计，消除冗余代码

### 常量管理 (TestConstants)
- 集中管理所有项目常量（API URL、报告配置、HTTP方法等）
- 消除硬编码值，提升代码可维护性
- 类型安全的常量定义，确保配置一致性

### 步骤定义模块化
- **CommonSteps**: 基础配置、认证等通用功能
- **ApiSteps**: HTTP 请求相关操作，包含优化的请求处理方法
- **ResponseSteps**: 响应验证和断言，包含优化的JsonPath处理

### 测试报告
- **ExtentReports**: 提供丰富的 HTML 测试报告
- **Cucumber Reports**: 标准的 Cucumber 测试报告
- 支持测试结果截图和日志记录
- 集中化的报告配置管理

## 测试场景

### 基础 API 测试 (sample.feature)
- 获取所有文章
- 获取特定文章
- 获取所有用户
- 获取特定用户
- 创建新文章
- 删除文章

### 高级功能测试 (advanced.feature)
- 测试步骤间状态共享
- 数据保存和占位符替换
- 复杂的测试场景编排

## 扩展指南

### 添加新的测试场景
1. 在 `src/test/resources/features/` 目录下创建新的 `.feature` 文件
2. 使用 Gherkin 语法编写测试场景
3. 在相应的步骤定义文件中实现步骤逻辑

### 添加新的步骤定义
- **API 相关**: 添加到 `ApiSteps.java`
- **验证相关**: 添加到 `ResponseSteps.java`
- **通用配置**: 添加到 `CommonSteps.java`
- **业务特定**: 创建新的步骤定义文件

详细的步骤定义组织指南请参考 [STEP_DEFINITIONS_GUIDE.md](STEP_DEFINITIONS_GUIDE.md)

## 配置说明

### Maven 依赖
主要依赖包括：
- `cucumber-java`: Cucumber Java 支持
- `cucumber-junit`: Cucumber JUnit 集成
- `rest-assured`: REST API 测试库
- `extentreports`: 测试报告生成
- `truth`: Google Truth 断言库（已优化scope为test）

### 常量配置
所有配置常量集中在 `TestConstants.java` 中管理：
- **API配置**: 基础URL、Content-Type、认证头等
- **报告配置**: 文件路径、标题、环境信息等
- **HTTP方法**: 标准化的HTTP方法常量
- **文件路径**: 统一的路径配置管理

### 测试配置
- **并行执行**: 支持多线程测试执行
- **标签过滤**: 支持基于标签的测试筛选
- **环境配置**: 支持不同环境的配置切换
- **集中化管理**: 所有配置通过常量类统一管理

## 最佳实践

1. **编写清晰的场景**: 使用业务语言描述测试场景
2. **保持步骤简洁**: 每个步骤只做一件事
3. **合理使用标签**: 为测试场景添加有意义的标签
4. **数据驱动测试**: 使用 Scenario Outline 进行参数化测试
5. **定期重构**: 保持代码的整洁和可维护性
6. **常量管理**: 避免硬编码，使用 TestConstants 类管理配置
7. **单例模式**: 合理使用单例模式管理共享状态
8. **代码复用**: 提取公共方法，减少重复代码
9. **依赖管理**: 正确设置Maven依赖的scope

## 代码优化历史

项目经过多轮优化，详细信息请参考：
- [CODE_OPTIMIZATION_SUMMARY.md](CODE_OPTIMIZATION_SUMMARY.md) - 初始代码优化总结
- [ADDITIONAL_OPTIMIZATION_SUMMARY.md](ADDITIONAL_OPTIMIZATION_SUMMARY.md) - 进一步优化总结

### 主要优化成果
- ✅ 消除了60+行冗余代码
- ✅ 移除了18个硬编码值
- ✅ 引入了集中化常量管理
- ✅ 优化了构造函数设计
- ✅ 提取了公共方法，减少重复
- ✅ 改进了依赖管理配置

## 故障排除

### 常见问题

**Q: 测试运行失败，提示网络连接错误**
A: 检查网络连接，确保能够访问 https://jsonplaceholder.typicode.com

**Q: 找不到步骤定义**
A: 确保步骤定义文件在正确的包路径下，并且方法上有正确的注解

**Q: 测试报告没有生成**
A: 检查 `extent-config.xml` 和 `extent.properties` 配置文件

**Q: 常量类找不到**
A: 确保 `TestConstants.java` 在正确的 `constants` 包下，并且已正确导入

### 调试技巧
- 使用 `@Before` 和 `@After` 钩子进行调试
- 在步骤定义中添加日志输出
- 使用 IDE 的调试功能逐步执行
- 检查常量配置是否正确

## 贡献指南

1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 许可证

本项目采用 MIT 许可证 - 详情请参阅 [LICENSE](LICENSE) 文件

## 联系方式

如有问题或建议，请通过以下方式联系：
- 创建 Issue
- 发送邮件到 [your-email@example.com]

---

**Happy Testing! 🚀**