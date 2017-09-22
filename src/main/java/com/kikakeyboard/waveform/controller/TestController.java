package com.kikakeyboard.waveform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 毛伟
 * @Date 9/19/17  14:10
 */
@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String monitor() {
        log.info("Monitor ok");
        return "ok";
    }
}
