package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.BaseTest;
import com.kikakeyboard.waveform.domain.Property;
import com.kikakeyboard.waveform.domain.Voice;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Author 毛伟
 * @Date 9/21/17  11:36
 */
public class VoiceMapperTest extends BaseTest {
    @Resource
    VoiceMapper voiceMapper;

    @Test
    public void listByPackageId() throws Exception {
    }

    @Test
    public void getByVoiceId() throws Exception {
        Voice voice = voiceMapper.getByVoiceId("efe29bf0-51dc-46a7-a663-70192c0189dd");
        System.out.println(voice);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void updateStatusToUnchecked() throws Exception {
    }

    @Test
    public void updateStatusAndCheckerId() throws Exception {
    }

    @Test
    public void save() throws Exception {
        Voice voice1 = new Voice();
        Property property1 = new Property();
        property1.setValidity(1);
        voice1.setId(UUID.randomUUID().toString());
        voice1.setProperty(property1);
        voice1.setPackageId(1);

        //Voice voice2 = new Voice();
        //Property property2 = new Property();
        //property1.setValidity(1);
        //voice2.setId(UUID.randomUUID().toString());
        //voice2.setProperty(property2);
        //voice2.setPackageId(1);

        voiceMapper.save(voice1);
        System.out.println(voice1);
        //voiceMapper.save(voice2);
    }

}