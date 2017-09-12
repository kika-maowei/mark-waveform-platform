package com.kikakeyboard.waveform;


import com.kikakeyboard.waveform.mapper.VoiceMapper;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author 毛伟
 * @Date 8/14/17  11:02
 */
public class TestControllerTest extends BaseTest{
    @Resource
    VoiceMapper voiceMapper;

    @Test
    @Transactional
    @Rollback(false)
    public void test() {
        voiceMapper.insertt(1);
    }

}