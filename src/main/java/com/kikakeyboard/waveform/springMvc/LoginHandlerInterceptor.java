package com.kikakeyboard.waveform.springMvc;

import com.kikakeyboard.waveform.domain.User;
import com.kikakeyboard.waveform.utils.HttpRequestLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");
    private static final int EXECUTION_TIME_THRESHOLD = 100;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("Accepted a request.From {},URL:{}", request.getRemoteAddr(), HttpRequestLogUtils
                .getRequestURLAndParams(request));
        startTimeThreadLocal.set(System.currentTimeMillis());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String path = request.getContextPath();
        String basePath = scheme + "://" + serverName + ":" + port + path + "/";
        String dataPath = scheme + "://" + serverName + ":" + port + path + "-data/";
        request.setAttribute("basePath", basePath);
        request.setAttribute("dataPath", dataPath);
        if(user != null){
            return true;
        }
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        long executionTime = System.currentTimeMillis() - startTimeThreadLocal.get();
        if (executionTime > EXECUTION_TIME_THRESHOLD) {
            log.info("Request execution time too long,{}ms!From {},URL:{}", executionTime, request.getRemoteAddr(),
                    HttpRequestLogUtils.getRequestURLAndParams(request));
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public static String getURL(HttpServletRequest httpServletRequest) {
        StringBuilder url = new StringBuilder(httpServletRequest.getRequestURL());
        if (StringUtils.isNotEmpty(httpServletRequest.getQueryString())) {
            url.append("?").append(httpServletRequest.getQueryString());
        }
        return url.toString();
    }
}
