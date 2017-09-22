package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.BaseTest;
import com.kikakeyboard.waveform.domain.User;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @Author 毛伟
 * @Date 9/15/17  10:55
 */
public class userMapperTest extends BaseTest{
    @Resource
    UserMapper userMapper;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("1234");
        user.setPassword("123");
        user.setRealName("maowei");
        user.setEmail("123@gmail");
        userMapper.save(user);
    }
}
