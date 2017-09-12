package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.ES.UnmarkedVoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author 毛伟
 * @Date 9/11/17  16:05
 */
public interface VoiceRepository extends ElasticsearchRepository<UnmarkedVoice, String> {
    //UnmarkedVoice getByRandom();
}
