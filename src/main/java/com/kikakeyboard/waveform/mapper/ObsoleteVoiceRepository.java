package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.ES.ObsoleteVoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author 毛伟
 * @Date 9/18/17  10:26
 */
public interface ObsoleteVoiceRepository extends ElasticsearchRepository<ObsoleteVoice, String> {
}
