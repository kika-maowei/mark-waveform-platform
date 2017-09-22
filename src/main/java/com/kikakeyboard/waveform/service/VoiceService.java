package com.kikakeyboard.waveform.service;


import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.domain.VoicePackage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoiceService {
    //marker service
    List<VoicePackage> listByMarkerId(int markerId);

    int checkIfMarkerCanNewTask(long packageId, int markerId);

    VoicePackage newTaskByMarker(VoicePackage voicePackage);

    Optional<Voice> getByRandom(long packageId);

    Voice getByVoiceId(String voiceId);

    List<Voice> listByPackageId(long packageId);

    boolean submitByMarker(Voice voice);

    boolean uploadByMarker(long packageId);


    //checker service
    VoicePackage newTaskByChecker(int checkerId);

    List<VoicePackage> listByCheckerId(int checkerId);

    int checkIfCheckerCanNewTask(int checkerId);

    boolean submitByChecker(String voiceId, int status, int checkerId, String remark);

    boolean uploadByChecker(long packageId);

    List<VoicePackage> getTotalInfoByCheckerId(int checkerId);

    List<VoicePackage> getMonthInfoByCheckerId(int checkerId, String yearAndMonth);

    List<VoicePackage> getDayInfoByCheckerId(int checkerId, LocalDate checkTime);

    //admin service
    List<VoicePackage> getTheDayBeforeInfo();

    boolean saveVoiceToUnmarkedVoice();

}
