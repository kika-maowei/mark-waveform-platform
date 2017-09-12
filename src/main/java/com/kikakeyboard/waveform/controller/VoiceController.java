package com.kikakeyboard.waveform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.domain.VoicePackage;
import com.kikakeyboard.waveform.service.impl.VoiceServiceImpl;
import com.kikakeyboard.waveform.utils.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author 毛伟
 *
 * @Date 16/11/7  下午4:40
 */
@Slf4j
@Controller
@RequestMapping("/voice")
public class VoiceController extends BaseController {
    @Resource
    VoiceServiceImpl voiceService;

    /**
     * 标注员的controller start
     */
    @RequestMapping(value = "/list-by-marker-id")
    @ResponseBody
    public JSONResult listByMarkerId(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/new-task-by-marker")
    @ResponseBody
    public JSONResult newTaskByMarker(HttpServletRequest request) {
        VoicePackage voicePackage = new VoicePackage();
        voicePackage.setMarkerId(getUserId(request));
        voicePackage.setCreateTime(LocalDate.now());
        VoicePackage resultVoicePackage = voiceService.newTaskByMarker(voicePackage);
        JSONObject result = new JSONObject();
        result.put("package_id", resultVoicePackage.getId());
        result.put("create_time", resultVoicePackage.getCreateTime());
        return JSONResult.getSuccessWithDataJSONResult(result);
    }

    @RequestMapping(value = "/get-by-random")
    @ResponseBody
    public JSONResult getByRandom(@RequestParam("package_id") int packageId) {
        Voice voice = voiceService.getByRandom(packageId);
        JSONObject result = new JSONObject();
        result.put("voice", voice);
        return JSONResult.getSuccessWithDataJSONResult(result);
    }

    @RequestMapping(value = "/list-by-package-id")
    @ResponseBody
    public JSONResult listByPackageId(@RequestParam("package_id") int packageId) {
        List<Voice> voiceList = voiceService.listByMarkerIdAndDate(packageId);
        JSONArray voiceArray = JSONArray.parseArray(voiceList.toString());
        JSONObject result = new JSONObject();
        result.put("voice_list", voiceArray);
        return JSONResult.getSuccessWithDataJSONResult(result);
    }

    @RequestMapping(value = "/get-by-voice-id")
    @ResponseBody
    public JSONResult getByVoiceId(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/submit-by-marker")
    @ResponseBody
    public JSONResult submitByMarker(@RequestParam("voice") Voice voice) {
        return voiceService.submitByMarker(voice) ? JSONResult.getSuccessWithDataJSONResult(null) : JSONResult.SERVICE_FATAL_ERROR;
    }

    @RequestMapping(value = "/upload-by-marker")
    @ResponseBody
    public JSONResult uploadByMarker(@RequestParam("package_id") int packageId) {
        return voiceService.uploadByMarkerIdAndDate(packageId) ? JSONResult.getSuccessWithDataJSONResult(null) : JSONResult.SERVICE_FATAL_ERROR;
    }

    /**
     * 标注员的controller end
     * 检查员的controller start
     */

    @RequestMapping(value = "/new-task-by-checker")
    @ResponseBody
    public JSONResult newTaskByChecker(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/list-by-checker-id")
    @ResponseBody
    public JSONResult listByCheckerId(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/list-by-checker-id-and-date")
    @ResponseBody
    public JSONResult listByCheckerIdAndDate(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/update-by-checker-id-and-voice-id")
    @ResponseBody
    public JSONResult updateByCheckerIdAndVoiceId(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/upload-by-checker-id")
    @ResponseBody
    public JSONResult uploadByCheckerId(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/get-total-info-by-checker-id")
    @ResponseBody
    public JSONResult getTotalInfoByCheckerId(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/get-month-info-by-checker-id")
    @ResponseBody
    public JSONResult getMonthInfoByCheckerId(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/get-day-info-by-checker-id")
    @ResponseBody
    public JSONResult getDayInfoByCheckerId(HttpServletRequest request) {
        return null;
    }

    /**
     * 检查员的controller end
     * 管理员的controller start
     */

    @RequestMapping(value = "/get-the-day-before-info")
    @ResponseBody
    public JSONResult getTheDayBeforeInfo(HttpServletRequest request) {
        return null;
    }


}
