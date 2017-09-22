package com.kikakeyboard.waveform.service.impl;

import com.kikakeyboard.waveform.domain.ES.MarkedVoice;
import com.kikakeyboard.waveform.domain.ES.RecyclableVoice;
import com.kikakeyboard.waveform.domain.ES.UnmarkedVoice;
import com.kikakeyboard.waveform.domain.Property;
import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.domain.VoiceCopy;
import com.kikakeyboard.waveform.domain.VoicePackage;
import com.kikakeyboard.waveform.mapper.*;
import com.kikakeyboard.waveform.service.VoiceService;
import com.sun.org.apache.regexp.internal.RE;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kikakeyboard.waveform.constant.Constants.*;


@Service
public class VoiceServiceImpl implements VoiceService {
    @Resource
    VoiceMapper voiceMapper;
    @Resource
    VoicePackageMapper voicePackageMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    UnmarkedVoiceRepository unmarkedVoiceRepository;
    @Resource
    UserVoiceRepository userVoiceRepository;
    @Resource
    MarkedVoiceRepository markedVoiceRepository;
    @Resource
    VoiceCopyMapper voiceCopyMapper;

    @Override
    public List<VoicePackage> listByMarkerId(int markerId) {
        return voicePackageMapper.listByMarkerId(markerId);
    }

    @Override
    public int checkIfMarkerCanNewTask(long packageId, int markerId) {
        if (voicePackageMapper.getByPackageIdAndMarkerId(packageId, markerId) != null) {
            return HAVE_NEW_TASK_TODAY;
        }

        if (voicePackageMapper.getByStatusAndMarkerId(PACKAGE_STATUS_MARKING, markerId) != null) {
            return EXIT_UNSUBMIT_PACKAGE;
        }

        List<UnmarkedVoice> unmarkedVoiceList = unmarkedVoiceRepository.findFirst2ByStatus(ES_VOICE_STATUS_UNMARKED);
        if (unmarkedVoiceList.isEmpty()) {
            return NO_VOICE;
        }

        return MARKER_CAN_NEW_TASK;
    }

    @Override
    public VoicePackage newTaskByMarker(VoicePackage voicePackage) {
        voicePackageMapper.save(voicePackage);
        getByRandom(voicePackage.getPackageId());
        return voicePackage;
    }

    @Override
    public Optional<Voice> getByRandom(long packageId) {
        List<UnmarkedVoice> unmarkedVoiceList = unmarkedVoiceRepository.findFirst2ByStatus(ES_VOICE_STATUS_UNMARKED);
        if (unmarkedVoiceList.isEmpty()) {
            return Optional.empty();
        }
        UnmarkedVoice unmarkedVoice = unmarkedVoiceList.get(0);
        unmarkedVoice.setStatus(ES_VOICE_STATUS_MARKED);
        unmarkedVoiceRepository.save(unmarkedVoice);
        VoicePackage voicePackage = voicePackageMapper.getByPackageId(packageId);

        Voice voice = new Voice();
        voice.setId(unmarkedVoice.getId());
        voice.setProperty(unmarkedVoice.getProperty());
        voice.setStatus(VOICE_STATUS_MARKING);
        voice.setPackageId(voicePackage.getPackageId());
        voiceMapper.save(voice);
        return Optional.of(voice);
    }

    @Override
    public Voice getByVoiceId(String voiceId) {
        return voiceMapper.getByVoiceId(voiceId);
    }

    @Override
    public List<Voice> listByPackageId(long packageId) {
        return voiceMapper.listByPackageId(packageId).stream().sorted(Comparator.comparing(Voice::getCreateTime)).collect(Collectors.toList());
    }

    @Override
    public boolean submitByMarker(Voice voice) {
        return voiceMapper.update(voice);
    }

    @Override
    public boolean uploadByMarker(long packageId) {
        //boolean result = voiceMapper.updateStatusToUnchecked(VOICE_STATUS_UNCHECKED, packageId);

        List<Voice> voiceList = voiceMapper.listByPackageId(packageId);
        VoicePackage voicePackage = new VoicePackage();
        voicePackage.setPackageId(packageId);
        voicePackage.setStatus(PACKAGE_STATUS_UNCHECKED);
        voicePackage.setValidCount(getValidCount(voiceList));
        voicePackage.setInvalidCount(getInvalidCount(voiceList));
        voicePackage.setTotalTime(getTotalTime(voiceList));

        return voicePackageMapper.updateStatusAndValidCountAndInvalidCountAndTotalTime(voicePackage);
    }

    @Override
    public VoicePackage newTaskByChecker(int checkerId) {
        VoicePackage voicePackage = voicePackageMapper.getEarliestByStatus(PACKAGE_STATUS_UNCHECKED);
        voicePackage.setCheckerId(checkerId);
        voicePackage.setCheckTime(LocalDate.now());
        voicePackage.setStatus(PACKAGE_STATUS_CHECKING);
        voicePackageMapper.updateCheckerIAndCheckTimeAndStatus(voicePackage);
        return voicePackage;
    }

    @Override
    public List<VoicePackage> listByCheckerId(int checkerId) {
        List<VoicePackage> voicePackageList = voicePackageMapper.listByCheckerId(checkerId);
        voicePackageList.stream().forEach(e -> e.setMarkerName(userMapper.getRealNameById(e.getMarkerId())));
        return voicePackageList;
    }

    @Override
    public int checkIfCheckerCanNewTask(int checkerId) {
        if (voicePackageMapper.getByCheckerIdAndStatus(checkerId, PACKAGE_STATUS_CHECKING) != null) {
            return EXIT_CHECKING_PACKAGE;
        }
        if (voicePackageMapper.getByStatus(PACKAGE_STATUS_UNCHECKED) == null) {
            return NO_UNCHECKED_PACKAGE;
        }
        return CHECKER_CAN_NEW_TASK;
    }

    @Override
    public boolean submitByChecker(String voiceId, int status, int checkerId, String remark) {
        Voice voice = voiceMapper.getByVoiceId(voiceId);
        voice.setStatus(status);
        voice.setCheckerId(checkerId);
        voice.getProperty().setRemark(remark);

        return voiceMapper.updateStatusAndCheckerIdAndProperty(voice);
    }

    @Override
    public boolean uploadByChecker(long packageId) {
        VoicePackage voicePackage = voicePackageMapper.getByPackageId(packageId);
        List<Voice> voiceList = voiceMapper.listByPackageId(packageId);

        int checkCount = voicePackage.getCheckCount();
        float passRate = getPassRate(voiceList);

        voicePackage.setCheckCount(++checkCount);

        voicePackage.setPassCount(getPassCount(voiceList));
        voicePackage.setNotPassCount(getNotPassCount(voiceList));
        voicePackage.setPassRate(passRate);

        if (passRate >= PACKAGE_QUALIFIED_PASS_RATE) {
            voicePackage.setStatus(PACKAGE_STATUS_QUALIFIED);
            boolean result = voicePackageMapper.updateStatusAndCheckCountAndPass_countAndNot_pass_countAndPassRate(voicePackage);
            List<MarkedVoice> markedVoiceList = new ArrayList<>();
            voiceList.stream().forEach(e -> markedVoiceList.add(voiceToMarkedVoice(e)));
            Iterable<MarkedVoice> d = markedVoiceRepository.save(markedVoiceList);
            return result && (d != null);
            //删除t_voice中此语音包语音
        } else {

            //如果检查次数超过三次，则作废
            if (checkCount == PACKAGE_CHECK_COUNT_THREE) {
                List<RecyclableVoice> recyclableVoiceList = new ArrayList<>();
                voiceList.stream().forEach(e -> recyclableVoiceList.add(voiceToRecyclableVoice(e)));
                //删除t_voice中此语音包语音
            } else {
                voicePackage.setStatus(PACKAGE_STATUS_UNQUALIFIED);
                voicePackageMapper.updateStatusAndCheckCountAndPass_countAndNot_pass_countAndPassRate(voicePackage);
            }
        }
        return true;
    }

    @Override
    public List<VoicePackage> getTotalInfoByCheckerId(int checkerId) {
        return voicePackageMapper.listByCheckerId(checkerId);
    }

    @Override
    public List<VoicePackage> getMonthInfoByCheckerId(int checkerId, String yearAndMonth) {
        List<VoicePackage> voicePackageList = voicePackageMapper.listByCheckerId(checkerId);
        return voicePackageList.stream().filter(e -> e.getCheckTime().toString().substring(0,7).equals(yearAndMonth)).collect(Collectors.toList());
    }

    @Override
    public List<VoicePackage> getDayInfoByCheckerId(int checkerId, LocalDate checkTime) {
        return voicePackageMapper.listByCheckerIdAndCheckTime(checkerId, checkTime);
    }

    @Override
    public List<VoicePackage> getTheDayBeforeInfo() {
        return voicePackageMapper.listByDate(LocalDate.now().minusDays(1));
    }

    @Override
    public boolean saveVoiceToUnmarkedVoice() {
        List<VoiceCopy> voiceCopyList = voiceCopyMapper.getAll();
        List<UnmarkedVoice> unmarkedVoiceList = new ArrayList<>();
        voiceCopyList.stream().forEach(e -> unmarkedVoiceList.add(voiceToUnmarkedVoice(e)));
        unmarkedVoiceRepository.save(unmarkedVoiceList);
        return false;
    }

    private float getPassRate(List<Voice> voiceList) {
        int sum = voiceList.size();
        int unqualifiedCount = getNotPassCount(voiceList);
        BigDecimal bigDecimal = new BigDecimal((float) unqualifiedCount / sum);
        return 1 - bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private int getValidCount(List<Voice> voiceList) {
        return (int) voiceList.stream().filter(e -> e.getProperty().getValidity() == VOICE_VALID).count();
    }

    private int getInvalidCount(List<Voice> voiceList) {
        return (int) voiceList.stream().filter(e -> e.getProperty().getValidity() == VOICE_INVALID).count();
    }

    private float getTotalTime(List<Voice> voiceList) {
        float startSum = (float) voiceList.stream().mapToDouble(e -> e.getProperty().getStart()).sum();
        float endSum = (float) voiceList.stream().mapToDouble(e -> e.getProperty().getEnd()).sum();
        return endSum - startSum;
    }

    private int getPassCount(List<Voice> voiceList) {
        return (int) voiceList.stream().filter(e -> e.getStatus() == VOICE_STATUS_PASS).count();
    }

    private int getNotPassCount(List<Voice> voiceList) {
        return (int) voiceList.stream().filter(e -> e.getStatus() == VOICE_STATUS_NOT_PASS).count();
    }

    private MarkedVoice voiceToMarkedVoice(Voice voice) {
        MarkedVoice markedVoice = new MarkedVoice();
        markedVoice.setId(voice.getId());
        markedVoice.setProperty(voice.getProperty());
        markedVoice.setCreateTime(LocalDate.now());
        return markedVoice;
    }

    private RecyclableVoice voiceToRecyclableVoice(Voice voice) {
        RecyclableVoice recyclableVoice = new RecyclableVoice();
        recyclableVoice.setId(voice.getId());
        recyclableVoice.setProperty(voice.getProperty());
        recyclableVoice.setCreateTime(LocalDate.now());
        return recyclableVoice;
    }

    private UnmarkedVoice voiceToUnmarkedVoice(VoiceCopy voiceCopy) {
        UnmarkedVoice unmarkedVoice = new UnmarkedVoice();
        Property property = new Property();
        property.setUrl(voiceCopy.getUrl());
        property.setValidity(voiceCopy.getValidity());
        property.setSex(voiceCopy.getSex());
        property.setNoise(voiceCopy.getNoise());
        property.setLanguage(voiceCopy.getLanguage());
        property.setAccent(voiceCopy.getAccent());
        property.setContent(voiceCopy.getContent());
        property.setStart(voiceCopy.getStart());
        property.setEnd(voiceCopy.getEnd());
        unmarkedVoice.setProperty(property);
        unmarkedVoice.setStatus(0);
        unmarkedVoice.setCreateTime(LocalDate.now());
        return unmarkedVoice;
    }
}
