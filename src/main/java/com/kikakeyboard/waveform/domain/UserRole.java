package com.kikakeyboard.waveform.domain;


import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @Author 毛伟
 * @Date 8/25/17  10:54
 */
@Data
@Alias("UserRole")
public class UserRole {
    private int id;
    private int userId;
    private int roleId;
    private LocalDateTime createTime;
}
