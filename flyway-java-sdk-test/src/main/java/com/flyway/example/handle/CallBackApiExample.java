package com.flyway.example.handle;

import com.flyway.common.FlywayConfig;
import com.flyway.handle.OpenCallBackHandler;
import com.flyway.util.OpenAesUtil;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.ReadListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * OpenCallBackHandler 使用示例
 * 演示如何使用 OpenCallBackHandler 处理回调请求
 */
public class CallBackApiExample {

    public static void main(String[] args) {
        try {
            // 初始化配置
            FlywayConfig config = new FlywayConfig();
            config.setAesKey(""); // 16位AES密钥
            config.setFlywayRsaPublicKey("");

            // 创建 OpenCallBackHandler 实例
            OpenCallBackHandler callBackHandler = new OpenCallBackHandler(config);

            // 设置请求头参数（手动填入）
            String timestamp = ""; // 手动设置时间戳
            String requestId = "";   // 手动设置请求ID
            String signature = "";  //手动设置签名
            String originalData = ""; //手动设置请求报文

            // 创建模拟的 HttpServletRequest
            HttpServletRequest mockRequest = createMockRequest(config, timestamp, requestId, signature, originalData);

            // 调用 handleCallbackRequest 方法处理回调
            String result = callBackHandler.handleCallbackRequest(mockRequest);

            // 输出结果
            System.out.println("回调处理结果: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建模拟的 HttpServletRequest
     *
     * @param config       配置对象
     * @param timestamp
     * @param requestId
     * @param signature
     * @param originalData
     * @return 模拟的 HttpServletRequest
     */
    private static HttpServletRequest createMockRequest(FlywayConfig config, String timestamp, String requestId, String signature, String originalData) {
        String body;
        try {
            body = OpenAesUtil.encryptAndBase64Encode(originalData, config.getAesKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 创建一个简单的 HttpServletRequest 实现
        String finalBody = body;
        return new HttpServletRequest() {
            private final Map<String, String> headers = new HashMap<>();

            {
                headers.put("Tuotuo-Timestamp", timestamp);
                headers.put("Request-ID", requestId);
                headers.put("Tuotuo-Signature", signature);
            }

            @Override
            public String getHeader(String name) {
                return headers.get(name);
            }

            @Override
            public BufferedReader getReader() throws IOException {
                return new BufferedReader(new StringReader(finalBody));
            }

            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new ServletInputStream() {
                    private InputStream inputStream = new ByteArrayInputStream(finalBody.getBytes());

                    @Override
                    public boolean isFinished() {
                        try {
                            return inputStream.available() == 0;
                        } catch (IOException e) {
                            return true;
                        }
                    }

                    @Override
                    public boolean isReady() {
                        return true;
                    }

                    @Override
                    public void setReadListener(ReadListener readListener) {
                        // Not implemented for this example
                    }

                    @Override
                    public int read() throws IOException {
                        return inputStream.read();
                    }
                };
            }

            // 其他方法返回默认值或null
            @Override
            public String getMethod() {
                return "POST";
            }

            @Override
            public String getContentType() {
                return "application/json";
            }

            // 为满足接口要求，其他方法可以返回null或默认值
            @Override
            public String getAuthType() { return null; }

            @Override
            public Enumeration<String> getHeaders(String name) { return Collections.emptyEnumeration(); }

            @Override
            public Enumeration<String> getHeaderNames() { return Collections.emptyEnumeration(); }

            @Override
            public javax.servlet.http.Cookie[] getCookies() { return new javax.servlet.http.Cookie[0]; }

            @Override
            public long getDateHeader(String name) { return 0; }

            @Override
            public int getIntHeader(String name) { return 0; }

            @Override
            public String getPathInfo() { return null; }

            @Override
            public String getPathTranslated() { return null; }

            @Override
            public String getContextPath() { return null; }

            @Override
            public String getQueryString() { return null; }

            @Override
            public String getRemoteUser() { return null; }

            @Override
            public boolean isUserInRole(String role) { return false; }

            @Override
            public java.security.Principal getUserPrincipal() { return null; }

            @Override
            public String getRequestedSessionId() { return null; }

            @Override
            public String getRequestURI() { return null; }

            @Override
            public StringBuffer getRequestURL() { return null; }

            @Override
            public String getServletPath() { return null; }

            @Override
            public javax.servlet.http.HttpSession getSession(boolean create) { return null; }

            @Override
            public javax.servlet.http.HttpSession getSession() { return null; }

            @Override
            public String changeSessionId() { return null; }

            @Override
            public boolean isRequestedSessionIdValid() { return false; }

            @Override
            public boolean isRequestedSessionIdFromCookie() { return false; }

            @Override
            public boolean isRequestedSessionIdFromURL() { return false; }

            @Override
            public boolean isRequestedSessionIdFromUrl() { return false; }

            @Override
            public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
                return false;
            }

            @Override
            public void login(String s, String s1) throws ServletException {

            }

            @Override
            public void logout() throws ServletException {

            }

            @Override
            public Collection<Part> getParts() throws IOException, ServletException {
                return Collections.emptyList();
            }

            @Override
            public Part getPart(String s) throws IOException, ServletException {
                return null;
            }

            @Override
            public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
                return null;
            }

            @Override
            public Object getAttribute(String name) { return null; }

            @Override
            public Enumeration<String> getAttributeNames() { return Collections.emptyEnumeration(); }

            @Override
            public String getCharacterEncoding() { return null; }

            @Override
            public void setCharacterEncoding(String env) throws UnsupportedEncodingException { }

            @Override
            public int getContentLength() { return 0; }

            @Override
            public long getContentLengthLong() { return 0; }

            @Override
            public String getParameter(String name) { return null; }

            @Override
            public Enumeration<String> getParameterNames() { return Collections.emptyEnumeration(); }

            @Override
            public String[] getParameterValues(String name) { return new String[0]; }

            @Override
            public Map<String, String[]> getParameterMap() { return Collections.emptyMap(); }

            @Override
            public String getProtocol() { return null; }

            @Override
            public String getScheme() { return null; }

            @Override
            public String getServerName() { return null; }

            @Override
            public int getServerPort() { return 0; }

            @Override
            public String getRemoteAddr() { return null; }

            @Override
            public String getRemoteHost() { return null; }

            @Override
            public void setAttribute(String name, Object o) { }

            @Override
            public void removeAttribute(String name) { }

            @Override
            public Locale getLocale() { return null; }

            @Override
            public Enumeration<Locale> getLocales() { return Collections.emptyEnumeration(); }

            @Override
            public boolean isSecure() { return false; }

            @Override
            public javax.servlet.RequestDispatcher getRequestDispatcher(String path) { return null; }

            @Override
            public String getRealPath(String path) { return null; }

            @Override
            public int getRemotePort() { return 0; }

            @Override
            public String getLocalName() { return null; }

            @Override
            public String getLocalAddr() { return null; }

            @Override
            public int getLocalPort() { return 0; }

            @Override
            public ServletContext getServletContext() {
                return null;
            }

            @Override
            public AsyncContext startAsync() throws IllegalStateException {
                return null;
            }

            @Override
            public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
                return null;
            }

            @Override
            public boolean isAsyncStarted() {
                return false;
            }

            @Override
            public boolean isAsyncSupported() {
                return false;
            }

            @Override
            public AsyncContext getAsyncContext() {
                return null;
            }

            @Override
            public DispatcherType getDispatcherType() {
                return null;
            }
        };
    }
}