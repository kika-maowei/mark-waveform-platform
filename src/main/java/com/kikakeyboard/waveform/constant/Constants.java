package com.kikakeyboard.waveform.constant;

/**
 * @Author 毛伟
 *
 * @Date 17/7/18  18:48
 */
public interface Constants {
    //语音状态
    int ES_VOICE_STATUS_UNMARKED = 0;
    int ES_VOICE_STATUS_MARKED= 1;

    int VOICE_STATUS_MARKING = 0;
    int VOICE_STATUS_SUBMIT = 1;

    int VOICE_STATUS_PASS = 2;
    int VOICE_STATUS_NOT_PASS = 3;



    //语音是否有效
    int VOICE_INVALID = 0;
    int VOICE_VALID = 1;

    int VOICE_RETURN_THREE_TIMES = 3;

    //语音包状态
    int PACKAGE_STATUS_MARKING = 0;
    int PACKAGE_STATUS_UNCHECKED = 1;
    int PACKAGE_STATUS_CHECKING = 2;
    int PACKAGE_STATUS_UNQUALIFIED = 3;
    int PACKAGE_STATUS_QUALIFIED = 4;
    int PACKAGE_STATUS_EXPIRED = 5;
    int PACKAGE_STATUS_OBSOLETE = 5;



    //语音包合格率
    float PACKAGE_QUALIFIED_PASS_RATE = 0.95f;

    //语音包检查次数
    int PACKAGE_CHECK_COUNT_ZERO = 0;
    int PACKAGE_CHECK_COUNT_THREE = 3;


    //标注员能否new task
    int MARKER_CAN_NEW_TASK = 0;
    int HAVE_NEW_TASK_TODAY = 1;
    int EXIT_UNSUBMIT_PACKAGE = 2;
    int NO_VOICE = 3;

    //检查员能否new task
    int CHECKER_CAN_NEW_TASK = 0;
    int EXIT_CHECKING_PACKAGE = 1;
    int NO_UNCHECKED_PACKAGE = 2;

}
