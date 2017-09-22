package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @Author 毛伟
 * @Date 9/21/17  18:04
 */
public class ESRepositoryTest extends BaseTest {
    @Resource
    MarkedVoiceRepository markedVoiceRepository;


    @Test
    public void deleteMarkedVoice() {
        markedVoiceRepository.deleteAll();
    }
}
