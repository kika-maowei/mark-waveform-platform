package com.kikakeyboard.waveform.controller;

import com.kikakeyboard.waveform.domain.User;
import com.kikakeyboard.waveform.service.impl.UserServiceImpl;
import com.kikakeyboard.waveform.utils.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @Author 毛伟
 *
 * @Date 16/10/24  下午5:58
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    UserServiceImpl userService;

    @RequestMapping(value = "/update")
    @ResponseBody
    public JSONResult update(@RequestParam("user") User user) {
        userService.update(user);
        return null;
    }

    @RequestMapping(value = "/list-all-marker-name")
    @ResponseBody
    public JSONResult listAllMarkerName(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/list-all-checker-name")
    @ResponseBody
    public JSONResult listAllCheckerName(HttpServletRequest request) {
        return null;
    }
}
