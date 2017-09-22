package com.kikakeyboard.waveform;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kikakeyboard.waveform.domain.ES.MarkedVoice;
import com.kikakeyboard.waveform.domain.Property;
import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.mapper.VoiceMapper;
import com.kikakeyboard.waveform.service.VoiceService;
import com.kikakeyboard.waveform.service.impl.VoiceServiceImpl;
import com.kikakeyboard.waveform.utils.BeanUtil;
import com.kikakeyboard.waveform.utils.JSONResult;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

/**
 * @Author 毛伟
 * @Date 8/14/17  11:02
 */
public class TestControllerTest extends BaseTest{
    @Resource
    VoiceMapper voiceMapper;
    @Resource
    VoiceServiceImpl voiceService;

    @Test
    @Transactional
    @Rollback(false)
    public void test() {
        Voice voice = new Voice();
        voice.setId("maowei");
        voice.setStatus(1);
        //Property property = new Property();
        System.out.println(voice);
        MarkedVoice markedVoice = (MarkedVoice) JSON.parse(JSON.toJSONString(voice));
        markedVoice.setCreateTime(LocalDate.now());
        System.out.println(markedVoice);
    }

    @Test
    public void password() throws NoSuchAlgorithmException {
        System.out.println(BeanUtil.string2Md5("bQM4nHra"));
    }

    @Test
    public void saveVoiceToUnmarkedVoice() {
        voiceService.saveVoiceToUnmarkedVoice();
        System.out.println("save voice to unmarked voice");
    }

}