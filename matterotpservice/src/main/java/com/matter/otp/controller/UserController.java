package com.matter.otp.controller;

import com.matter.otp.client.SmsClient;
import com.matter.otp.model.SmsVo;
import com.matter.otp.model.UserVo;
import com.matter.otp.service.Sms;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
//import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    @Autowired
    private Sms sms;
    @Autowired
    private SmsClient smsClient;
    @Autowired
    private UserVo user;

    @PostMapping("/sms")
    @Operation(summary = "Operation Send an SMS", description = "Response Codes:<br/>"
          + "<br/>200: Service executed successfully.")
    public String sendSms(@RequestBody  String phno) throws MalformedURLException {
      //  LOGGER.debug("sendSms {} ",request.getPhone());
        String response = sms.getCode(phno);

        if (response!=null) {
            //getTextsms from db
            String textSms = "";
            if (textSms != null) {
                SmsVo smsVO = new SmsVo();
                smsVO.setNumber(phno);
                smsVO.setMessage(textSms);
                String code = smsClient.sendSms(smsVO);
                if ("200".equals(code)) {
                    String success = "success";
                } else {
                    String success = "failure";
                }
            } else {
                String success = "fail";;
            }
        }
        return response;
    }

    @PostMapping("/validate")
    @ResponseBody
    @Operation(summary = "Operation to validate OTP", description = "Response Codes:<br/>"
            + "<br/>200: Service executed successfully.")
    public String confirmClient(@RequestBody String phNo,String code) throws MalformedURLException {
        //LOGGER.debug("confirmClient {} ",request.getPhone());
        String response = sms.validateCode(phNo,code);
        return response;
    }

    @PostMapping("/register")
    @Operation(summary = "Operation to register a new user", description = "Response Codes:<br/>"
            + "<br/>200: Service executed successfully.")
    public String saveClient(@RequestBody UserVo request) throws MalformedURLException {
        //LOGGER.debug("saveClient {} ", request.getPhone());
        String response = sms.saveUser(request);
        return response;
    }
}

