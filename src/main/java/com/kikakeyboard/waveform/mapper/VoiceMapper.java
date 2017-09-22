package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.Voice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author 毛伟
 * @Date 8/4/17  15:16
 */

public interface VoiceMapper {

    @Select("SELECT * FROM t_voice WHERE package_id = #{packageId}")
    List<Voice> listByPackageId(long packageId);

    @Select("SELECT * FROM t_voice WHERE id = #{voiceId}")
    Voice getByVoiceId(String voiceId);

    @Update("UPDATE t_voice SET property = #{property}, status = #{status} WHERE id=#{id}")
        //boolean updateByMarkerIdAndVoiceId(@Param("property")Property property, @Param("status") int status, @Param("markerId") int markerId, @Param("markTime") LocalDate markTime, @Param("id") int voiceId);
    boolean update(Voice voice);

    //@Update("UPDATE t_voice SET status = #{status} WHERE package_id = #{packageId}")
    //boolean updateStatusToUnchecked(@Param("status") int status, @Param("packageId") long packageId);


    @Update("UPDATE t_voice SET status = #{status}, checker_id = #{checkerId}, property = #{property} WHERE id = #{id}")
    @Options(useGeneratedKeys = true)
    boolean updateStatusAndCheckerIdAndProperty(Voice voice);

    @Insert("INSERT INTO t_voice(id,property,package_id) VALUES (#{id}, #{property}, #{packageId})")
    boolean save(Voice voice);

}
