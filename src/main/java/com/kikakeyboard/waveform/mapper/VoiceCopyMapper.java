package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.VoiceCopy;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 毛伟
 * @Date 9/18/17  18:51
 */
public interface VoiceCopyMapper {

    @Select("SELECT * FROM t_voice_copy1")
    List<VoiceCopy> getAll();
}
