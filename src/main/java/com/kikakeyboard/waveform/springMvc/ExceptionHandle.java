package com.kikakeyboard.waveform.springMvc;

import com.kikakeyboard.waveform.utils.HttpRequestLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 毛伟
 * @Email wei.mao@xinmei365.com
 * @Date 16/12/26  上午10:30
 */
@Slf4j
public class ExceptionHandle implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error("Throws an exception when processing request : " + HttpRequestLogUtils.getRequestURLAndParams
                (request), ex);
        return new ModelAndView();
    }
}
