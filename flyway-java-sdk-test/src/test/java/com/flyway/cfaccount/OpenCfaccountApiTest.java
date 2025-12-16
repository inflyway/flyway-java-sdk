package com.flyway.cfaccount;

import com.flyway.cfaccount.model.CurrencyBalance;
import com.flyway.cfaccount.model.MultiBalance;
import com.flyway.cfaccount.model.QueryBalanceRequest;
import com.flyway.common.FlywayConfig;
import com.flyway.common.TokenApi;
import com.flyway.common.model.CommonResponse;
import com.flyway.exception.FlywayApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * OpenCfaccountApi.queryBalance() 方法集成测试
 * 直接调用真实服务，不使用Mock
 * 
 * 基于 CfaccountApiExample 中的真实配置参数
 * 
 * @author Test Team
 */
@DisplayName("OpenCfaccountApi queryBalance 方法测试")
class OpenCfaccountApiTest {

    private FlywayConfig flywayConfig;
    private OpenCfaccountApi cfaccountApi;
    private TokenApi tokenApi;
    
    // 成功场景参数 - 来自 CfaccountApiExample
    private static final String VALID_OPEN_ID = "";
    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String AES_KEY = "";
    private static final String RSA_PRIVATE_KEY = "";
    private static final String SERVER_URL = "https://open-test.inflyway.com";

    @BeforeEach
    void setUp() {
        System.out.println("=== 初始化测试环境 ===");
        
        // 使用 CfaccountApiExample 中完全相同的配置
        flywayConfig = new FlywayConfig();
        flywayConfig.setServerUrl(SERVER_URL);
        flywayConfig.setClientId(CLIENT_ID);
        flywayConfig.setClientSecret(CLIENT_SECRET);
        flywayConfig.setAesKey(AES_KEY);
        flywayConfig.setRsaPrivateKey(RSA_PRIVATE_KEY);
        flywayConfig.setDebug(true);
        
        // 初始化API实例
        cfaccountApi = new OpenCfaccountApi(flywayConfig);
        tokenApi = new TokenApi(flywayConfig);
        
        System.out.println("服务地址: " + SERVER_URL);
        System.out.println("客户端ID: " + CLIENT_ID);
        System.out.println("测试OpenId: " + VALID_OPEN_ID);
        System.out.println("=========================");
    }

    // ==================== 成功场景测试 ====================

    @Test
    @DisplayName("成功场景：使用有效参数查询余额")
    void testQueryBalance_Success_ValidParams() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：使用有效参数查询余额");
        
        // 1. 获取访问令牌
        System.out.println("步骤1: 获取访问令牌...");
        String token = tokenApi.getToken();
        assertNotNull(token, "获取的Token不应为空");
        System.out.println("✓ Token获取成功: " + maskToken(token));
        
        // 2. 构建查询请求
        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(VALID_OPEN_ID);
        request.setToken(token);
        
        // 3. 执行查询
        System.out.println("步骤2: 执行余额查询...");
        long startTime = System.currentTimeMillis();
        CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);
        long endTime = System.currentTimeMillis();
        
        // 4. 验证响应
        System.out.println("步骤3: 验证响应结果...");
        assertNotNull(response, "响应不能为空");
        assertNotNull(response.getCode(), "响应代码不能为空");
        
        System.out.println("✓ 响应代码: " + response.getCode());
        System.out.println("✓ 响应消息: " + response.getMessage());
        System.out.println("✓ 请求耗时: " + (endTime - startTime) + "ms");
        
        // 5. 验证余额数据
        if (response.getData() != null && !response.getData().isEmpty()) {
            System.out.println("✓ 余额数据详情:");
            for (CurrencyBalance balance : response.getData()) {
                assertNotNull(balance.getCurrency(), "币种不能为空");
                System.out.println("  币种: " + balance.getCurrency());
                
                MultiBalance multiBalance = balance.getMultiBalance();
                if (multiBalance != null) {
                    System.out.println("    余额: " + multiBalance.getBalance());
                    System.out.println("    锁定保证金: " + multiBalance.getLockingMargin());
                    System.out.println("    待出账: " + multiBalance.getPendingOutbound());
                    System.out.println("    待入账: " + multiBalance.getPendingInbound());
                    System.out.println("    分销: " + multiBalance.getDistribution());
                    System.out.println("    非法款: " + multiBalance.getIllegalFunds());
                }
            }
        } else {
            System.out.println("✓ 账户余额为空（正常情况）");
        }
        
        System.out.println(">>> 测试完成：有效参数查询成功\n");
    }

    @Test
    @DisplayName("成功场景：连续查询验证一致性")
    void testQueryBalance_Success_ConsistencyCheck() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：连续查询验证一致性");
        
        String token = tokenApi.getToken();
        
        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(VALID_OPEN_ID);
        request.setToken(token);
        
        // 连续查询3次
        CommonResponse<List<CurrencyBalance>> response1 = cfaccountApi.queryBalance(request);
        Thread.yield(); // 让出CPU
        CommonResponse<List<CurrencyBalance>> response2 = cfaccountApi.queryBalance(request);
        Thread.yield();
        CommonResponse<List<CurrencyBalance>> response3 = cfaccountApi.queryBalance(request);
        
        // 验证响应代码一致性
        assertEquals(response1.getCode(), response2.getCode(), "连续查询的响应代码应一致");
        assertEquals(response2.getCode(), response3.getCode(), "连续查询的响应代码应一致");
        
        System.out.println("✓ 三次查询响应代码均为: " + response1.getCode());
        System.out.println("✓ 连续查询一致性验证通过");
        
        System.out.println(">>> 测试完成：一致性验证通过\n");
    }

    @Test
    @DisplayName("成功场景：性能基准测试")
    void testQueryBalance_Success_PerformanceBenchmark() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：性能基准测试");
        
        String token = tokenApi.getToken();
        
        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(VALID_OPEN_ID);
        request.setToken(token);
        
        int testRounds = 5;
        long totalTime = 0;
        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;
        
        for (int i = 0; i < testRounds; i++) {
            long startTime = System.currentTimeMillis();
            CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;
            
            totalTime += responseTime;
            minTime = Math.min(minTime, responseTime);
            maxTime = Math.max(maxTime, responseTime);
            
            assertNotNull(response, "第" + (i + 1) + "次查询响应不能为空");
            System.out.println("第" + (i + 1) + "次查询: " + responseTime + "ms");
            
            // 避免过于频繁的请求
            if (i < testRounds - 1) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        
        double avgTime = (double) totalTime / testRounds;
        System.out.println("✓ 性能统计:");
        System.out.println("  平均响应时间: " + String.format("%.2f", avgTime) + "ms");
        System.out.println("  最快响应时间: " + minTime + "ms");
        System.out.println("  最慢响应时间: " + maxTime + "ms");
        System.out.println("  总耗时: " + totalTime + "ms");
        
        // 性能断言
        assertTrue(avgTime < 10000, "平均响应时间应小于10秒");
        assertTrue(maxTime < 15000, "最大响应时间应小于15秒");
        
        System.out.println(">>> 测试完成：性能基准测试通过\n");
    }

    // ==================== 失败场景测试 ====================

    @Test
    @DisplayName("失败场景：使用无效Token")
    void testQueryBalance_Failure_InvalidToken() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：使用无效Token");
        
        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(VALID_OPEN_ID);
        request.setToken("invalid-fake-token-12345"); // 无效Token
        
        // 无效Token可能抛出异常或返回错误响应，需要同时处理两种情况
        try {
            CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);
            // 如果没有抛出异常，验证返回错误响应
            System.out.println("✓ 收到错误响应:");
            System.out.println("  响应代码: " + response.getCode());
            System.out.println("  响应消息: " + response.getMessage());

            assertNotNull(response, "响应不应为空");
            assertTrue(response.getCode() >= 400, "无效Token应返回错误代码 (>=400)");

        } catch (FlywayApiException exception) {
            // 如果抛出异常，验证异常信息
            System.out.println("✓ 成功捕获异常:");
            System.out.println("  异常类型: " + exception.getClass().getSimpleName());
            System.out.println("  错误代码: " + exception.getErrorCode());
            System.out.println("  错误消息: " + exception.getErrorMessage());
            System.out.println("  HTTP状态: " + exception.getHttpStatus());
        }
        
        System.out.println(">>> 测试完成：无效Token处理正确\n");
    }

    @Test
    @DisplayName("失败场景：使用空Token")
    void testQueryBalance_Failure_EmptyToken() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：使用空Token");

        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(VALID_OPEN_ID);
        request.setToken(""); // 空Token

        // 空Token抛出异常
        try {
            CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);
        }catch (FlywayApiException exception) {

            System.out.println("✓ 收到错误响应:");
            System.out.println("  响应代码: " + exception.getErrorCode());
            System.out.println("  响应消息: " + exception.getMessage());
            assertTrue(exception.getErrorCode() >= 400, "空Token应返回错误代码 (>=400)");
            System.out.println(">>> 测试完成：空Token错误处理正确\n");
        }


    }

    @Test
    @DisplayName("失败场景：使用null Token")
    void testQueryBalance_Failure_NullToken() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：使用null Token");

        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(VALID_OPEN_ID);
        request.setToken(null); // null Token

        try {
            // null Token抛出异常
            CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);
        }catch (FlywayApiException exception) {


            System.out.println("✓ 收到错误响应:");
            System.out.println("  响应代码: " + exception.getErrorCode());
            System.out.println("  响应消息: " + exception.getMessage());


            // 验证返回错误代码
            assertTrue(exception.getErrorCode() >= 400,"null Token应返回错误代码 (>=400)");
            System.out.println(">>> 测试完成：null Token错误处理正确\n");
        }
    }

    @Test
    @DisplayName("失败场景：使用无效OpenId")
    void testQueryBalance_Failure_InvalidOpenId() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：使用无效OpenId");

        String token = tokenApi.getToken();

        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId("invalid-open-id-12345"); // 无效OpenId
        request.setToken(token);

        // 无效OpenId不会抛出异常，而是返回错误响应
        CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);
        System.out.println("✓ 收到错误响应:");
        System.out.println("  响应代码: " + response.getCode());
        System.out.println("  响应消息: " + response.getMessage());

        // 验证返回错误代码
        assertNotNull(response, "响应不应为空");
        assertTrue(response.getCode() >= 400, "无效OpenId应返回错误代码 (>=400)");
        System.out.println(">>> 测试完成：无效OpenId错误处理正确\n");
    }

    @Test
    @DisplayName("失败场景：使用空OpenId")
    void testQueryBalance_Failure_EmptyOpenId() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：使用空OpenId");

        String token = tokenApi.getToken();

        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(""); // 空OpenId
        request.setToken(token);

        // 空OpenId不会抛出异常，而是返回错误响应
        CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);

        System.out.println("✓ 收到错误响应:");
        System.out.println("  响应代码: " + response.getCode());
        System.out.println("  响应消息: " + response.getMessage());

        // 验证返回错误代码
        assertNotNull(response, "响应不应为空");
        assertTrue(response.getCode() >= 400, "空OpenId应返回错误代码 (>=400)");
        System.out.println(">>> 测试完成：空OpenId错误处理正确\n");
    }

    @Test
    @DisplayName("失败场景：使用null OpenId")
    void testQueryBalance_Failure_NullOpenId() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：使用null OpenId");

        String token = tokenApi.getToken();

        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(null); // null OpenId
        request.setToken(token);

        // null OpenId不会抛出异常，而是返回错误响应
        CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);
        System.out.println("✓ 收到错误响应:");
        System.out.println("  响应代码: " + response.getCode());
        System.out.println("  响应消息: " + response.getMessage());

        // 验证返回错误代码
        assertNotNull(response, "响应不应为空");
        assertTrue(response.getCode() >= 400, "null OpenId应返回错误代码 (>=400)");
        System.out.println(">>> 测试完成：null OpenId错误处理正确\n");
    }

    @Test
    @DisplayName("失败场景：使用null请求对象")
    void testQueryBalance_Failure_NullRequest() {
        System.out.println("\n>>> 测试开始：使用null请求对象");

        // null请求对象可能抛出异常或返回错误响应
        try {
            CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(null);
            // 如果没有抛出异常，验证返回错误响应
            System.out.println("✓ 收到错误响应:");
            System.out.println("  响应代码: " + response.getCode());
            System.out.println("  响应消息: " + response.getMessage());

            assertNotNull(response, "响应不应为空");
            assertTrue(response.getCode() >= 400, "null请求应返回错误代码 (>=400)");

        } catch (Exception exception) {
            // 如果抛出异常，验证异常信息
            System.out.println("✓ 成功捕获异常:");
            System.out.println("  异常类型: " + exception.getClass().getSimpleName());
            System.out.println("  错误消息: " + exception.getMessage());

            // null请求可能抛出NullPointerException或FlywayApiException
            assertTrue(exception instanceof FlywayApiException || exception instanceof NullPointerException,
                    "应抛出FlywayApiException或NullPointerException");
        }

        System.out.println(">>> 测试完成：null请求对象处理正确\n");
    }
    @Test
    @DisplayName("失败场景：网络连接异常测试")
    void testQueryBalance_Failure_NetworkException() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：网络连接异常测试");

        // 创建一个错误的服务器地址配置来模拟网络异常
        FlywayConfig wrongConfig = new FlywayConfig();
        wrongConfig.setServerUrl("https://non-existent-server-12345.com");
        wrongConfig.setClientId(CLIENT_ID);
        wrongConfig.setClientSecret(CLIENT_SECRET);
        wrongConfig.setAesKey(AES_KEY);
        wrongConfig.setRsaPrivateKey(RSA_PRIVATE_KEY);
        wrongConfig.setDebug(false);

        OpenCfaccountApi wrongApi = new OpenCfaccountApi(wrongConfig);
        TokenApi wrongTokenApi = new TokenApi(wrongConfig);
        try {
            // 尝试获取Token，这应该会抛出网络异常
            String token = wrongTokenApi.getToken();
            fail("使用错误服务器地址应该抛出网络异常");
        } catch (FlywayApiException exception) {
            System.out.println("✓ 成功捕获网络异常:");
            System.out.println("  异常类型: " + exception.getClass().getSimpleName());
            System.out.println("  错误代码: " + exception.getErrorCode());
            System.out.println("  错误消息: " + exception.getErrorMessage());

            // 验证这是真正的网络异常
            assertNotNull(exception.getErrorMessage());
            assertTrue(exception.getErrorMessage().contains("HTTP form request failed"),
                      "应该是网络连接相关的异常");
        }

        System.out.println(">>> 测试完成：网络连接异常处理正确\n");
    }

    // ==================== 边界条件测试 ====================

    @Test
    @DisplayName("边界条件：极长OpenId")
    void testQueryBalance_Boundary_VeryLongOpenId() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：极长OpenId");

        String token = tokenApi.getToken();

        // 生成500个字符的长OpenId
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            sb.append("a");
        }
        String longOpenId = sb.toString();
        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(longOpenId);
        request.setToken(token);

        // 这种情况可能成功（返回空结果）或失败（参数错误）
        try {
            CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);
            System.out.println("✓ 极长OpenId被服务器接受");
            System.out.println("  响应代码: " + response.getCode());
        } catch (FlywayApiException e) {
            System.out.println("✓ 极长OpenId被服务器拒绝");
            System.out.println("  错误代码: " + e.getErrorCode());
            System.out.println("  错误消息: " + e.getErrorMessage());
        }

        System.out.println(">>> 测试完成：极长OpenId处理验证\n");
    }

    @Test
    @DisplayName("边界条件：特殊字符OpenId")
    void testQueryBalance_Boundary_SpecialCharOpenId() throws FlywayApiException {
        System.out.println("\n>>> 测试开始：特殊字符OpenId");

        String token = tokenApi.getToken();

        String specialOpenId = "test@#$%^&*()_+-=[]{}|;':\",./<>?";

        QueryBalanceRequest request = new QueryBalanceRequest();
        request.setOpenId(specialOpenId);
        request.setToken(token);

        // 这种情况可能成功或失败，取决于服务器验证规则
        try {
            CommonResponse<List<CurrencyBalance>> response = cfaccountApi.queryBalance(request);
            System.out.println("✓ 特殊字符OpenId被服务器接受");
            System.out.println("  响应代码: " + response.getCode());
        } catch (FlywayApiException e) {
            System.out.println("✓ 特殊字符OpenId被服务器拒绝");
            System.out.println("  错误代码: " + e.getErrorCode());
            System.out.println("  错误消息: " + e.getErrorMessage());
        }

        System.out.println(">>> 测试完成：特殊字符OpenId处理验证\n");
    }

    // ==================== 辅助方法 ====================

    /**
     * 遮掩Token显示，保护敏感信息
     */
    private String maskToken(String token) {
        if (token == null || token.length() < 10) {
            return "***";
        }
        return token.substring(0, 6) + "..." + token.substring(token.length() - 4);
    }
}