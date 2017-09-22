package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.ES.RecyclableVoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author 毛伟
 * @Date 9/18/17  10:28
 */
public interface RecyclableVoiceRepository extends ElasticsearchRepository<RecyclableVoice, String> {
}
