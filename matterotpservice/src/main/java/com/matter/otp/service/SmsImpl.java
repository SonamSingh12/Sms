package com.matter.otp.service;

import com.matter.otp.model.UserVo;

public class SmsImpl implements Sms{
    @Override
    public String getCode(String request) {
        //find user from db with phone no in req we have phone no
        return "null";

    }

    @Override
    public String validateCode(String phno, String code) {

        //find user with phno
        //if user exist
        //then check user.getCode =code
        //if code matches save user
        return null;
    }

    @Override
    public String saveUser(UserVo request) {
       String phno = request.getPhone();
       //find user by phno
      //  if user not found return
        //else save user
        return null;

    }


}
