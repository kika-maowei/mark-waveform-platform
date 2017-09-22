package com.kikakeyboard.waveform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

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
    private long packageId;
    private int checkerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private LocalDateTime createTime;
}
