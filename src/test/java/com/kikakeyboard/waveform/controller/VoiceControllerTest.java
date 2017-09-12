package com.kikakeyboard.waveform.controller;

import com.kikakeyboard.waveform.BaseTest;
import com.kikakeyboard.waveform.domain.Property;
import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.domain.VoicePackage;
import com.kikakeyboard.waveform.mapper.VoicePackageMapper;
import com.kikakeyboard.waveform.service.VoiceService;
import com.kikakeyboard.waveform.service.impl.VoiceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @Author 毛伟
 * @Date 9/11/17  18:00
 */
@Slf4j
public class VoiceControllerTest extends BaseTest {
    @Resource
    VoiceServiceImpl voiceService;
    @Resource
    VoicePackageMapper voicePackageMapper;

    @Test
    public void listByMarkerId() throws Exception {
    }

    @Test
    public void newTaskByMarker() throws Exception {
        VoicePackage voicePackage = new VoicePackage();
        voicePackage.setMarkerId(1);
        voicePackage.setCreateTime(LocalDate.now());
        voiceService.newTaskByMarker(voicePackage);
        System.out.println(voicePackage.getId());
    }

    @Test
    public void getByRandom() throws Exception {
        voiceService.getByRandom(1);
    }

    @Test
    public void listByPackageId() throws Exception {
    }

    @Test
    public void getByVoiceId() throws Exception {
    }

    @Test
    public void submitByMarker() throws Exception {
        Voice voice = new Voice();
        voice.setId("adfadf");
        voice.setStatus(1);
        Property property = new Property();
        property.setName("maowei");
        voice.setProperty(property);
        voiceService.submitByMarker(voice);
    }

    @Test
    public void uploadByMarkerIdAndDate() throws Exception {
        VoicePackage voicePackage = new VoicePackage();
        voicePackage.setMarkerId(1);
        voicePackage.setCreateTime(LocalDate.now());
        System.out.println(voicePackageMapper.save(voicePackage));
        System.out.println(voicePackage.getId());
        //voiceService.uploadByMarkerIdAndDate(voicePackage);
        System.out.println("end");
    }

    @Test
    public void newTaskByChecker() throws Exception {
    }

    @Test
    public void listByCheckerId() throws Exception {
    }

    @Test
    public void listByCheckerIdAndDate() throws Exception {
    }

    @Test
    public void updateByCheckerIdAndVoiceId() throws Exception {
    }

    @Test
    public void uploadByCheckerId() throws Exception {
    }

    @Test
    public void getTotalInfoByCheckerId() throws Exception {
    }

    @Test
    public void getMonthInfoByCheckerId() throws Exception {
    }

    @Test
    public void getDayInfoByCheckerId() throws Exception {
    }

    @Test
    public void getTheDayBeforeInfo() throws Exception {
    }

}