package com.kikakeyboard.waveform.controller;

import com.kikakeyboard.waveform.BaseTest;
import com.kikakeyboard.waveform.domain.Property;
import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.domain.VoicePackage;
import com.kikakeyboard.waveform.mapper.VoiceMapper;
import com.kikakeyboard.waveform.mapper.VoicePackageMapper;
import com.kikakeyboard.waveform.service.VoiceService;
import com.kikakeyboard.waveform.service.impl.VoiceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.kikakeyboard.waveform.constant.Constants.VOICE_VALID;
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
    @Resource
    VoiceMapper voiceMapper;

    @Test
    public void listByMarkerId() throws Exception {
    }

    @Test
    public void newTaskByMarker() throws Exception {
        VoicePackage voicePackage = new VoicePackage();
        voicePackage.setMarkerId(1);
        voicePackage.setMarkTime(LocalDate.now());
        voicePackage.setPackageId(Long.parseLong(voicePackage.getMarkTime().toString().replaceAll("-","") +voicePackage.getMarkerId()));
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
        voice.setProperty(property);
        voiceService.submitByMarker(voice);
    }

    @Test
    public void uploadByMarkerIdAndDate() throws Exception {
        VoicePackage voicePackage = new VoicePackage();
        voicePackage.setMarkerId(1);
        System.out.println(voicePackageMapper.save(voicePackage));
        System.out.println(voicePackage.getId());
        //voiceService.uploadByMarkerIdAndDate(voicePackage);
        System.out.println("end");
    }

    @Test
    public void newTaskByChecker() throws Exception {
        VoicePackage voicePackage = voiceService.newTaskByChecker(1);
        System.out.println(voicePackage);
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

    @Test
    public void test() {

        //List<VoicePackage> voicePackageList1 = voicePackageMapper.listByCheckerId(2);
        //System.out.println(voicePackageList1);
        //List<VoicePackage> voicePackageList = voiceService.getTotalInfoByCheckerId(2);
        //System.out.println(voicePackageList);
        //System.out.println(voicePackageList.get(0).getPassCount());
        //System.out.println(voicePackageList.stream().mapToInt(e-> e.getPassCount()).sum());


        List<Voice> voiceList = voiceMapper.listByPackageId(1);
        System.out.println(voiceList);
        int sum =  (int) voiceList.stream().filter(e -> e.getProperty().getValidity() == VOICE_VALID).count();
        System.out.println(sum);
    }

}