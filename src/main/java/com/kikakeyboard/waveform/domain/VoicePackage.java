package com.kikakeyboard.waveform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;

/**
 * @Author 毛伟
 * @Date 9/11/17  17:18
 */
@Data
@Alias("VoicePackage")
public class VoicePackage {
    private int id;
    private long packageId;
    private int markerId;
    private int checkerId;
    private int validCount;
    private int invalidCount;
    private int passCount;
    private int notPassCount;
    private int status ;
    private float passRate;
    private float totalTime;
    private int checkCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private LocalDate markTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private LocalDate checkTime;

    private String markerName;
    private String checkerName;
}
