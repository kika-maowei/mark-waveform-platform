package com.kikakeyboard.waveform.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias(value = "User")
@Data
public class User {

    //用户ID
    private Integer id;
    //描述
    private String description;
    //登录名称
    private String username;
    //登录密码
    private String password;
    //用户邮箱
    private String email;
    //用户姓名
    private String realName;
    //标注员标注什么样的语音，也就是标注员的等级
    private String queryCondition;
    //用户所在组的id
    private int groupId;
    //更新时间
    private LocalDateTime updateTime;
    //创建时间
    private LocalDateTime createTime;
}
