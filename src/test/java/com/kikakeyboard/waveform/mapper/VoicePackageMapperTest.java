package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @Author 毛伟
 * @Date 9/20/17  16:18
 */
public class VoicePackageMapperTest extends BaseTest{
    @Resource
    VoicePackageMapper voicePackageMapper;

    @Test
    public void getByPackageIdAndMarkerId() throws Exception {
        long packageId = 201709201;
        int markerId = 1;
        System.out.println(voicePackageMapper.getByPackageIdAndMarkerId(packageId, markerId));;
    }

    @Test
    public void getByCheckerIdAndStatus() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void deleteByPackageId() throws Exception {
    }

    @Test
    public void getByPackageId() throws Exception {
    }

    @Test
    public void getEarliestByStatus() throws Exception {
    }

    @Test
    public void updateStatusAndValidCountAndInvalidCountAndTotalTime() throws Exception {
    }

    @Test
    public void updateCheckerIAndCheckTime() throws Exception {
    }

    @Test
    public void updateStatusAndCheckCountAndPass_countAndNot_pass_countAndPassRate() throws Exception {
    }

    @Test
    public void listByCheckerId() throws Exception {
    }

    @Test
    public void listByDate() throws Exception {
    }

    @Test
    public void listByCheckerIdAndCheckTime() throws Exception {
    }

    @Test
    public void listByMarkerId() throws Exception {
    }

}