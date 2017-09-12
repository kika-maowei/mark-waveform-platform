package com.kikakeyboard.waveform.service;


import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.domain.VoicePackage;

import java.time.LocalDate;
import java.util.List;

public interface VoiceService {
    VoicePackage newTaskByMarker(VoicePackage voicePackage);

    Voice getByRandom(int packageId);

    List<Voice> listByMarkerIdAndDate(int packageId);

    boolean submitByMarker(Voice voice);

    boolean uploadByMarkerIdAndDate(int packageId);
}
