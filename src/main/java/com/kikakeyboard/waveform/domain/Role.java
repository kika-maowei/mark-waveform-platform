package com.kikakeyboard.waveform.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Author 毛伟
 * @Date 8/24/17  11:59
 */
@Data
@Alias("Role")
public class Role {
    private int id;
    private String name;
}
