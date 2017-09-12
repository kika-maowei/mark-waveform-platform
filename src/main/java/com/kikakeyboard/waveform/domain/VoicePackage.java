package com.kikakeyboard.waveform.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;

import static com.kikakeyboard.waveform.constant.Constants.PACKAGE_CHECK_COUNT_ZERO;
import static com.kikakeyboard.waveform.constant.Constants.PACKAGE_STATUS_UNCHECKED;

/**
 * @Author 毛伟
 * @Date 9/11/17  17:18
 */
@Data
@Alias("VoicePackage")
public class VoicePackage {
    private int id;
    private int markerId;
    private int status ;
    private float passRate;
    private int checkCount;
    private LocalDate createTime;
}
