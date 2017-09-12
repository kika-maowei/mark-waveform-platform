package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.Property;
import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.domain.VoicePackage;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author 毛伟
 * @Date 8/4/17  15:16
 */

public interface VoiceMapper {

    @Select("SELECT * FROM t_voice WHERE package_id = #{packageId})")
    List<Voice> listByPackageId(int packageId);

    @Update("UPDATE t_voice SET property = #{property}, status = #{status} WHERE id=#{id}")
    //boolean updateByMarkerIdAndVoiceId(@Param("property")Property property, @Param("status") int status, @Param("markerId") int markerId, @Param("markTime") LocalDate markTime, @Param("id") int voiceId);
    boolean update(Voice voice);

    @Update("UPDATE t_voice SET status = #{status} WHERE package_id = #{packageId})")
    boolean updateStatusToUnchecked(@Param("status") int status, @Param("packageId") int packageId);

    @Insert("INSERT INTO t_voice(id,property,package_id,marker_id,mark_time) VALUES (#{id}, #{property}, #{packageId},#{markerId}, #{markTime})")
    boolean save(Voice voice);







    @Select("SELECT * FROM t_voice WHERE status=7 AND id in (SELECT voice_id FROM r_user_voice WHERE user_id = #{id})")
    Voice getMarkingVoice(@Param("id") int markerId);

    @Select("SELECT * FROM t_voice WHERE status=2 ORDER BY id limit 1")
    Voice queryLatestVoice();

    @Insert("insert  into t_voice(url,content) values(#{url},#{content})")
    boolean insert(@Param("url") String url, @Param("content") String content);

    @Select("select status from t_voice where id = (select voice_id from r_user_voice where user_id = #{markerId} and create_time=#{date} order by voice_id limit 1)")
    int queryOne(@Param("markerId") int markerId, @Param("date") LocalDate date);

    @Select("SELECT * FROM t_voice WHERE id = #{id}")
    Voice queryByVoiceId(@Param("id") int id);

    @Update("UPDATE t_voice SET status = #{status} WHERE id=#{id}")
    boolean changeStatus(@Param("id") int id, @Param("status") int status);

    @Select("select * from t_voice where id in (select voice_id from r_user_voice where user_id = #{markerId} AND create_time = #{date})")
    List<Voice> queryByTimeAndMarkerId(@Param("markerId") int markerId, @Param("date") LocalDate date);

    boolean updateByMarkerAfterSubmit(Voice voice);

    @Update("UPDATE t_voice SET status=#{status}, note=#{note} WHERE id = #{id}")
    boolean updateByChecker(@Param("id") int id, @Param("status") int status, @Param("note") String note);

    @Insert("insert  into t_test(package_id) values(#{packageId})")
    boolean insertt(int packageId);

    boolean updateAllByMarkerAfterStop(@Param("date") LocalDate date, @Param("userId") int userId, @Param("status") int status);
}
