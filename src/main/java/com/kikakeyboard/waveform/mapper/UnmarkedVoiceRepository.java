package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.ES.UnmarkedVoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author 毛伟
 * @Date 9/11/17  16:05
 */
public interface UnmarkedVoiceRepository extends ElasticsearchRepository<UnmarkedVoice, String> {

    List<UnmarkedVoice> findFirst2ByStatus(int status);
}
