package com.kikakeyboard.waveform.mapper;

import com.kikakeyboard.waveform.domain.VoicePackage;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author 毛伟
 * @Date 9/11/17  17:39
 */
public interface VoicePackageMapper {
    @Select("SELECT * FROM t_voice_package WHERE marker_id = #{markerId} AND package_id = #{packageId}")
    VoicePackage getByPackageIdAndMarkerId(@Param("packageId") long packageId, @Param("markerId") int markerId);

    @Select("SELECT * FROM t_voice_package WHERE marker_id = #{markerId} AND status = #{status}")
    VoicePackage getByStatusAndMarkerId(@Param("status") int status, @Param("markerId") int markerId);

    @Select("SELECT * FROM t_voice_package WHERE checker_id = #{checkerId} AND status = #{status}")
    VoicePackage getByCheckerIdAndStatus(@Param("checkerId") int checkerId, @Param("status") int status);

    @Select("SELECT * FROM t_voice_package WHERE checker_id = #{checkerId} AND status = #{status}")
    List<VoicePackage> getByStatus(@Param("status") int status);

    @Insert("INSERT INTO t_voice_package(package_id,marker_id,mark_time) VALUES (#{packageId}, #{markerId}, #{markTime})")
    @Options(useGeneratedKeys = true)
    boolean save(VoicePackage voicePackage);

    @Delete("DELETE FROM t_voice_package where package_id = #{packageId}")
    boolean deleteByPackageId(long packageId);

    @Select("SELECT * FROM t_voice_package WHERE package_id = #{packageId}")
    VoicePackage getByPackageId(long packageId);

    //@Update("UPDATE t_voice_package SET status = #{status} WHERE package_id = #{packageId}")
    //boolean updateStatus(VoicePackage voicePackage);

    @Select("SELECT * FROM t_voice_package WHERE status = #{status} ORDER BY id LIMIT 1")
    VoicePackage getEarliestByStatus(int status);

    @Update("UPDATE t_voice_package SET status = #{status}, valid_count = #{validCount}, invalid_count = #{invalidCount}, total_time = #{totalTime} WHERE package_id = #{packageId}")
    boolean updateStatusAndValidCountAndInvalidCountAndTotalTime(VoicePackage voicePackage);

    @Update("UPDATE t_voice_package SET status = #{status}, checker_id = #{checkerId}, check_time = #{checkTime} WHERE package_id = #{packageId}")
    boolean updateCheckerIAndCheckTimeAndStatus(VoicePackage voicePackage);

    @Update("UPDATE t_voice_package SET status = #{status}, check_count = #{checkCount}, pass_count = #{passCount}, not_pass_count = #{notPassCount}, pass_rate = #{passRate} WHERE package_id = #{packageId}")
    boolean updateStatusAndCheckCountAndPass_countAndNot_pass_countAndPassRate(VoicePackage voicePackage);

    @Select("SELECT * FROM t_voice_package WHERE checker_id = #{checkerId}")
    List<VoicePackage> listByCheckerId(int checkerId);

    @Select("SELECT * FROM t_voice_package WHERE mark_time = #{date} OR check_time = #{date}")
    List<VoicePackage> listByDate(LocalDate date);

    @Select("SELECT * FROM t_voice_package WHERE checker_id = #{checkerId} AND check_time = #{checkTime}")
    List<VoicePackage> listByCheckerIdAndCheckTime(@Param("checkerId") int checkerId, @Param("checkTime") LocalDate checkTime);

    @Select("SELECT * FROM t_voice_package WHERE marker_id = #{markerId}")
    List<VoicePackage> listByMarkerId(int markerId);

}
