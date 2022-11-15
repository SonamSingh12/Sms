package com.matter.otp.service;

import com.matter.otp.model.UserVo;

public interface Sms {
    String getCode(String request);

    String validateCode(String phno,String code);

    String saveUser(UserVo request);
}
