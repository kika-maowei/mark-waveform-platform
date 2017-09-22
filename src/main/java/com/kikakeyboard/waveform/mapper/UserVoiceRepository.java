package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.ES.UserVoice;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author 毛伟
 * @Date 9/11/17  18:16
 */
public interface UserVoiceRepository extends ElasticsearchRepository<UserVoice, String> {
    //TODO 批量插入
    //boolean saveBatch(List<UserVoice> userVoiceList);
}
