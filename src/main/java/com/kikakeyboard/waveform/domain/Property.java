package com.kikakeyboard.waveform.domain;

import lombok.Data;


/**
 * @Author 毛伟
 * @Date 8/23/17  15:07
 */
@Data
public class Property {

    private String url;
    //有效性：0-无效；1-有效
    private int validity;
    //性别：0-不确定；1-男性；2-女性
    private int sex;
    //噪音：0-安静；1-有噪音；2-噪音极大
    private int noise;
    //语言：0-中文；1-包含英文；2-其他
    private int language;
    //口音：0-没有；1-有
    private int accent;
    //截幅：0-没有；1-有
    private int swath;
    //内容
    private String content;
    //起始时间点
    private float start;
    //终止时间点
    private float end;
    //批注，检查员使用
    private String remark;
}
