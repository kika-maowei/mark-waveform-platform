package com.kikakeyboard.waveform.constant;

/**
 * @Author 毛伟
 *
 * @Date 17/7/18  18:48
 */
public interface Constants {
    int VOICE_STATUS_MARKING = 0;
    int VOICE_STATUS_EXPIRED = 0;

    int VOICE_STATUS_INVALID = 1;
    int VOICE_STATUS_UNSUBMIT = 3;
    int VOICE_STATUS_UNCHECKED = 3;
    int VOICE_STATUS_QUALIFIED = 4;
    int VOICE_STATUS_UNQUALIFIED = 5;
    int VOICE_STATUS_SUBMITTED = 8;

    int VOICE_INVALID = 0;
    int VOICE_VALID = 1;

    int VOICE_RETURN_THREE_TIMES = 3;

    //语音包状态
    int PACKAGE_STATUS_UNCHECKED = 0;
    int PACKAGE_STATUS_UNQUALIFIED = 1;
    int PACKAGE_STATUS_QUALIFIED = 2;

    //语音包检查次数
    int PACKAGE_CHECK_COUNT_ZERO = 0;

    int PERMISSION_CHECKER = 3;
    int PERMISSION_MARKER = 4;
}
