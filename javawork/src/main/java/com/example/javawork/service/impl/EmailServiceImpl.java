package com.example.javawork.service.impl;

import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.ToEmail;
import com.example.javawork.service.EmailService;
import com.example.javawork.utils.VerCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public ResultInfo sendVerifyCode(ToEmail toEmail, String from){
        // 创建邮件消息
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);

        message.setTo(toEmail.getTos());

        message.setSubject("您本次的验证码是");

        String verCode = toEmail.getContent();

        message.setText("尊敬的xxx,您好:\n"
                + "\n本次请求的邮件验证码为:\n" + verCode + "（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）");

        try {
            mailSender.send(message);
            return ResultInfo.successInfo("已发送验证码到邮箱");
        }catch (Exception e)
        {
            return ResultInfo.failInfo("发送失败，请检查邮箱格式");
        }
    }
}
