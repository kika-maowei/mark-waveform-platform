package com.kikakeyboard.waveform.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;

/**
 * @Author 毛伟
 *
 * @Date 16/11/8  上午10:17
 */
@Alias(value = "Voice")
@Data
public class Voice {
    private String id;
    private Property property;
    private int status;
    private int packageId;
    private int markerId;
    private int checkerId;
    private LocalDate markTime;
    private LocalDate checkTime;
}
