package com.example.javawork.service;

import com.example.javawork.DO.ResultInfo;
import com.example.javawork.DO.ToEmail;

public interface EmailService {
    /**
     * 发送验证码
     * */
    public ResultInfo sendVerifyCode(ToEmail toEmail, String from);

}
