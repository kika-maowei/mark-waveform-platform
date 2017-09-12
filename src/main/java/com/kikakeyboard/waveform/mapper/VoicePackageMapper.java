package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.VoicePackage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

/**
 * @Author 毛伟
 * @Date 9/11/17  17:39
 */
public interface VoicePackageMapper {
    @Insert("INSERT INTO t_voice_package(marker_id,create_time) VALUES (#{markerId}, #{createTime})")
    @Options(useGeneratedKeys = true)
    boolean save(VoicePackage voicePackage);

    @Select("SELECT * FROM t_voice_package WHERE id = #{packageId}")
    VoicePackage getById(int packageId);
}
