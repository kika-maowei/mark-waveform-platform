package com.kikakeyboard.waveform.springMvc;

import com.kikakeyboard.waveform.utils.HttpRequestLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal StartTime");
    private static final int EXECUTION_TIME_THRESHOLD = 100;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("Accepted a request.From {},URL:{}", request.getRemoteAddr(), HttpRequestLogUtils
                .getRequestURLAndParams(request));
        startTimeThreadLocal.set(System.currentTimeMillis());
        //HttpSession session = request.getSession();
        //if (session.getAttribute("User") != null){
        //    return true;
        //}
        //request.getRequestDispatcher("/operator/login").forward(request,response);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o,
                           ModelAndView mv) throws Exception {
        long executionTime = System.currentTimeMillis() - startTimeThreadLocal.get();
        if (executionTime > EXECUTION_TIME_THRESHOLD) {
            log.info("Request execution time too long,{}ms!From {},URL:{}", executionTime, request.getRemoteAddr(),
                    HttpRequestLogUtils.getRequestURLAndParams(request));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
