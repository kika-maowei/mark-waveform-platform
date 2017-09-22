package com.kikakeyboard.waveform.controller;

import com.kikakeyboard.waveform.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author 毛伟
 *
 * @Date 16/10/24  下午8:47
 */
public abstract class BaseController {

    public int getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        return 2;
        //return user.getId();
    }

    public String getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        return user.getUsername();
    }

}
