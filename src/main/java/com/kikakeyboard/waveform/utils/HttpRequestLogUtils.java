package com.kikakeyboard.waveform.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

public class HttpRequestLogUtils {
    private static final String EQUAL_SIGN = "=";
    private static final String PLUS_SIGN = "+";
    private static final String AND = "&";

    public static String getRequestURLAndParams(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString(); // 获取客户端请求的URL
        Map<String, String[]> paramsMap = request.getParameterMap(); // 获取所有的请求参数

		/*
         * 获取所有参数的名值对信息的字符串表示
		 */
        StringBuilder allParams = new StringBuilder();
        if (paramsMap != null && !paramsMap.isEmpty()) {
            Set<Map.Entry<String, String[]>> paramsSet = paramsMap.entrySet();
            for (Map.Entry<String, String[]> param : paramsSet) {
                StringBuilder singleParam = new StringBuilder();
                String paramName = param.getKey(); // 参数的名字
                String[] paramValues = param.getValue(); // 参数的值
                if (paramValues.length == 1) { // 参数只有一个值，绝大多数情况
                    singleParam.append(paramName).append(EQUAL_SIGN)
                            .append(paramValues[0]);
                } else {
                    singleParam.append(paramName).append(EQUAL_SIGN);
                    for (String paramValue : paramValues) {
                        singleParam.append(paramValue);
                        singleParam.append(PLUS_SIGN);
                    }
                    singleParam.deleteCharAt(singleParam.length() - 1);
                }
                allParams.append(singleParam).append(AND);
            }
            allParams.deleteCharAt(allParams.length() - 1);
        }
        String paramsStr = allParams.toString();
        String requestURLAndParams = !paramsStr.isEmpty() ? requestURL + "?" + paramsStr : requestURL;
        return requestURLAndParams;
    }
}
