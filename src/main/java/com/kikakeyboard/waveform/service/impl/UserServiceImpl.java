package com.kikakeyboard.waveform.service.impl;

import com.kikakeyboard.waveform.domain.User;
import com.kikakeyboard.waveform.mapper.UserMapper;
import com.kikakeyboard.waveform.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;


    @Override
    public boolean update(User user) {
        return userMapper.update(user);
    }
}
