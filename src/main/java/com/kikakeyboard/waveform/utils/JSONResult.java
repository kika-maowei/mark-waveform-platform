package com.kikakeyboard.waveform.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 毛伟
 * @Email wei.mao@xinmei365.com
 * @Date 16/12/16  下午3:40
 */
@AllArgsConstructor
@Data
public class JSONResult {
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_INVALID_SIGN = 30001;
    public static final int CODE_INVALID_REQUEST_PARAM = 30005;
    public static final int CODE_SERVICE_FATAL_ERROR = 30006;
    public static final int CODE_NO_DATA = 40001;

    public static final String CODE_SUCCESS_MSG = "ok";
    public static final String CODE_INVALID_SIGN_MSG = "invalid interface sign";
    public static final String CODE_INVALID_REQUEST_PARAM_MSG = "invalid request parameter";
    public static final String CODE_SERVICE_FATAL_ERROR_MSG = "service fatal error";
    public static final String CODE_NO_DATA_MSG = "no data";

    public static final Map<Integer, String> ErrorMap = new HashMap<>();

    static {
        ErrorMap.put(CODE_SUCCESS, CODE_SUCCESS_MSG);
        ErrorMap.put(CODE_INVALID_SIGN, CODE_INVALID_SIGN_MSG);
        ErrorMap.put(CODE_INVALID_REQUEST_PARAM, CODE_INVALID_REQUEST_PARAM_MSG);
        ErrorMap.put(CODE_SERVICE_FATAL_ERROR, CODE_SERVICE_FATAL_ERROR_MSG);
        ErrorMap.put(CODE_NO_DATA, CODE_NO_DATA_MSG);
    }

    public static final JSONResult INVALID_SIGN = new JSONResult(CODE_INVALID_SIGN, CODE_INVALID_SIGN_MSG, null);
    public static final JSONResult INVALID_REQUEST_PARAM = new JSONResult(CODE_INVALID_REQUEST_PARAM,
            CODE_INVALID_REQUEST_PARAM_MSG, null);
    public static final JSONResult SERVICE_FATAL_ERROR = new JSONResult(CODE_SERVICE_FATAL_ERROR,
            CODE_SERVICE_FATAL_ERROR_MSG, null);

    public static final JSONResult NO_DATA = new JSONResult(CODE_NO_DATA,
            CODE_NO_DATA_MSG, null);

    private Integer errorCode;

    private String errorMsg;

    private JSONObject data;

    public static JSONResult getSuccessWithDataJSONResult(JSONObject data) {
        return new JSONResult(CODE_SUCCESS, CODE_SUCCESS_MSG, data);
    }

    public static JSONResult getErrorResult(int code, JSONObject data) {
        return new JSONResult(code, ErrorMap.getOrDefault(code, ""), data);
    }
}
