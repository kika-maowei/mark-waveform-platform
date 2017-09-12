package com.kikakeyboard.waveform.service.impl;

import com.kikakeyboard.waveform.domain.ES.UnmarkedVoice;
import com.kikakeyboard.waveform.domain.Property;
import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.domain.VoicePackage;
import com.kikakeyboard.waveform.mapper.UserVoiceRepository;
import com.kikakeyboard.waveform.mapper.VoiceMapper;
import com.kikakeyboard.waveform.mapper.VoicePackageMapper;
import com.kikakeyboard.waveform.mapper.VoiceRepository;
import com.kikakeyboard.waveform.service.VoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

import static com.kikakeyboard.waveform.constant.Constants.VOICE_STATUS_UNCHECKED;
import static com.kikakeyboard.waveform.constant.Constants.VOICE_STATUS_UNSUBMIT;


@Service
public class VoiceServiceImpl implements VoiceService {
    @Resource
    VoiceMapper voiceMapper;
    @Resource
    VoicePackageMapper voicePackageMapper;
    @Resource
    VoiceRepository voiceRepository;
    @Resource
    UserVoiceRepository userVoiceRepository;



    @Override
    public VoicePackage newTaskByMarker(VoicePackage voicePackage) {
        voicePackageMapper.save(voicePackage);
        return voicePackage;
    }

    @Override
    public Voice getByRandom(int packageId) {
        //TODO 从unmarked_voice中取出一条
        //UnmarkedVoice unmarkedVoice = voiceRepository.getByRandom();
        UnmarkedVoice unmarkedVoice = new UnmarkedVoice();
        unmarkedVoice.setId("adfadf");
        unmarkedVoice.setProperty(new Property());

        VoicePackage voicePackage = voicePackageMapper.getById(packageId);

        Voice voice = new Voice();
        voice.setId(unmarkedVoice.getId());
        voice.setProperty(unmarkedVoice.getProperty());
        voice.setMarkerId(voicePackage.getMarkerId());
        voice.setMarkTime(voicePackage.getCreateTime());
        voice.setPackageId(voicePackage.getId());
        voiceMapper.save(voice);
        return voice;
    }

    @Override
    public List<Voice> listByMarkerIdAndDate(int packageId) {
        return voiceMapper.listByPackageId(packageId);
    }

    @Override
    public boolean submitByMarker(Voice voice) {
        return voiceMapper.update(voice);
    }

    @Override
    public boolean uploadByMarkerIdAndDate(int packageId) {
        boolean result = voiceMapper.updateStatusToUnchecked(VOICE_STATUS_UNCHECKED, packageId);
        //TODO 根据此语音包，存储标注员标注了那些语音
        //result = result && userVoiceRepository.saveBatch();

        return result;
    }


}
