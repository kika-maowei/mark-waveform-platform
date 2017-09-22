package com.kikakeyboard.waveform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kikakeyboard.waveform.domain.Voice;
import com.kikakeyboard.waveform.domain.VoicePackage;
import com.kikakeyboard.waveform.service.impl.VoiceServiceImpl;
import com.kikakeyboard.waveform.utils.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.kikakeyboard.waveform.constant.Constants.*;

/**
 * @Author 毛伟
 *
 * @Date 16/11/7  下午4:40
 */
@Slf4j
@RestController
@RequestMapping("/voice")
public class VoiceController extends BaseController {
    @Resource
    VoiceServiceImpl voiceService;

    /**
     * 标注员的controller start
     */
    @RequestMapping(value = "/list-by-marker-id")
    public JSONResult listByMarkerId(HttpServletRequest request) {
        List<VoicePackage> voicePackageList = voiceService.listByMarkerId(getUserId(request));
        JSONObject result = new JSONObject();
        result.put("voice_package", voicePackageList);
        return JSONResult.getSuccessWithDataJSONResult(result);
    }

    @RequestMapping(value = "/new-task-by-marker")
    public JSONResult newTaskByMarker(HttpServletRequest request) {
        VoicePackage voicePackage = new VoicePackage();
        voicePackage.setMarkerId(getUserId(request));
        voicePackage.setMarkTime(LocalDate.now());
        voicePackage.setPackageId(Long.parseLong(voicePackage.getMarkTime().toString().replaceAll("-", "")+voicePackage.getMarkerId()));
        voicePackage.setStatus(PACKAGE_STATUS_MARKING);

        int flag = voiceService.checkIfMarkerCanNewTask(voicePackage.getPackageId(), voicePackage.getMarkerId());
        if (flag == HAVE_NEW_TASK_TODAY) {
            return JSONResult.INVALID_REQUEST_PARAM;
        }

        if (flag == EXIT_UNSUBMIT_PACKAGE) {
            return JSONResult.INVALID_REQUEST_PARAM;
        }

        if (flag == NO_VOICE) {
            return JSONResult.NO_DATA;
        }

        VoicePackage resultVoicePackage = voiceService.newTaskByMarker(voicePackage);
        JSONObject result = new JSONObject();
        result.put("voice_package", resultVoicePackage);
        return JSONResult.getSuccessWithDataJSONResult(result);
    }

    @RequestMapping(value = "/get-by-random")
    public JSONResult getByRandom(@RequestParam("packageId") long packageId) {
        Optional<Voice> voice = voiceService.getByRandom(packageId);
        if (voice.isPresent()) {
            JSONObject result = new JSONObject();
            result.put("voice", voice.get());
            return JSONResult.getSuccessWithDataJSONResult(result);
        }
        return JSONResult.NO_DATA;
    }

    @RequestMapping(value = "/list-by-package-id")
    public JSONResult listByPackageId(@RequestParam("packageId") long packageId) {
        List<Voice> voiceList = voiceService.listByPackageId(packageId);
        //JSONArray voiceArray = JSONArray.parseArray(voiceList.toString());
        JSONObject result = new JSONObject();
        result.put("voice_list", voiceList);
        return JSONResult.getSuccessWithDataJSONResult(result);
    }

    @RequestMapping(value = "/get-by-voice-id")
    @ResponseBody
    public JSONResult getByVoiceId(@RequestParam("id") String voiceId) {
        Voice voice = voiceService.getByVoiceId(voiceId);
        JSONObject result = new JSONObject();
        result.put("voice", voice);
        return JSONResult.getSuccessWithDataJSONResult(result);
    }

    @RequestMapping(value = "/submit-by-marker")
    public JSONResult submitByMarker(@RequestParam("voice") String voiceString) {
        Voice voice = JSON.parseObject(voiceString, Voice.class);
        voice.setStatus(VOICE_STATUS_SUBMIT);
        return voiceService.submitByMarker(voice) ? JSONResult.getSuccessWithDataJSONResult(null) : JSONResult.SERVICE_FATAL_ERROR;
    }

    @RequestMapping(value = "/upload-by-marker")
    public JSONResult uploadByMarker(@RequestParam("packageId") long packageId) {
        return voiceService.uploadByMarker(packageId) ? JSONResult.getSuccessWithDataJSONResult(null) : JSONResult.SERVICE_FATAL_ERROR;
    }

    /**
     * 标注员的controller end
     * 检查员的controller start
     */

    @RequestMapping(value = "/new-task-by-checker")
    public JSONResult newTaskByChecker(HttpServletRequest request) {
        int checkerId = getUserId(request);

        int flag = voiceService.checkIfCheckerCanNewTask(checkerId);

        if (flag == EXIT_CHECKING_PACKAGE) {
            return JSONResult.NO_DATA;
        }
        if (flag == NO_UNCHECKED_PACKAGE) {
            return JSONResult.INVALID_REQUEST_PARAM;
        }

        VoicePackage resultVoicePackage = voiceService.newTaskByChecker(checkerId);
        JSONObject result = new JSONObject();
        result.put("voice_package", resultVoicePackage);
        return JSONResult.getSuccessWithDataJSONResult(result);
    }

    @RequestMapping(value = "/list-by-checker-id")
    public JSONResult listByCheckerId(HttpServletRequest request) {
        List<VoicePackage> voicePackageList = voiceService.listByCheckerId(getUserId(request));
        JSONObject result = new JSONObject();
        result.put("voice_package", voicePackageList);
        return JSONResult.getSuccessWithDataJSONResult(result);
    }


    @RequestMapping(value = "/submit-by-checker")
    public JSONResult submitByChecker(@RequestParam("status") int status, @RequestParam("id") String voiceId , @RequestParam("remark") String remark,  HttpServletRequest request) {
        return voiceService.submitByChecker(voiceId, status, getUserId(request), remark) ? JSONResult.getSuccessWithDataJSONResult(null) : JSONResult.SERVICE_FATAL_ERROR;
    }

    @RequestMapping(value = "/upload-by-checker")
    public JSONResult uploadByChecker(@RequestParam("packageId") long packageId) {
        return voiceService.uploadByChecker(packageId) ? JSONResult.getSuccessWithDataJSONResult(null) : JSONResult.SERVICE_FATAL_ERROR;
    }

    @RequestMapping(value = "/get-total-info-by-checker-id")
    public JSONResult getTotalInfoByCheckerId(HttpServletRequest request) {
        return parseJsonResult(voiceService.getTotalInfoByCheckerId(getUserId(request)));
    }

    @RequestMapping(value = "/get-month-info-by-checker-id")
    public JSONResult getMonthInfoByCheckerId(@RequestParam("date") String yearAndMonth , HttpServletRequest request) {
        return parseJsonResult(voiceService.getMonthInfoByCheckerId(getUserId(request), yearAndMonth));
    }

    @RequestMapping(value = "/get-day-info-by-checker-id")
    public JSONResult getDayInfoByCheckerId(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkTime, HttpServletRequest request) {
        return parseJsonResult(voiceService.getDayInfoByCheckerId(getUserId(request), checkTime));
    }

    /**
     * 检查员的controller end
     * 管理员的controller start
     */

    @RequestMapping(value = "/get-the-day-before-info")
    public JSONResult getTheDayBeforeInfo() {
        List<VoicePackage> voicePackageList = voiceService.getTheDayBeforeInfo();
        JSONObject result = new JSONObject();
        result.put("voice_package", voicePackageList);
        return JSONResult.getSuccessWithDataJSONResult(result);
    }


    /**
     * 管理员的controller end
     */

    private int getPassSum(List<VoicePackage> voicePackageList) {
        return voicePackageList.stream().mapToInt(e-> e.getPassCount()).sum();
    }

    private int getNotPassSum(List<VoicePackage> voicePackageList) {
        return voicePackageList.stream().mapToInt(e -> e.getNotPassCount()).sum();
    }

    private JSONResult parseJsonResult(List<VoicePackage> voicePackageList) {
        JSONObject result = new JSONObject();
        result.put("pass_count", getPassSum(voicePackageList));
        result.put("not_pass_count", getNotPassSum(voicePackageList));

        return JSONResult.getSuccessWithDataJSONResult(result);
    }
}
