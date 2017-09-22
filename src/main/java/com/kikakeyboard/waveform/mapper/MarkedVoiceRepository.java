package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.ES.MarkedVoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author 毛伟
 * @Date 9/18/17  10:25
 */
public interface MarkedVoiceRepository extends ElasticsearchRepository<MarkedVoice, String> {

}
