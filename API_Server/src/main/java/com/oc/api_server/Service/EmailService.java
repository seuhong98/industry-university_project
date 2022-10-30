package com.oc.api_server.Service;

import com.oc.api_server.Repository.UserRepository;
import com.oc.api_server.VO.OrUser;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.security.SecureRandom;
import java.util.Random;

public class EmailService {

    private JavaMailSender emailSender;
    private SecureRandom random = new SecureRandom();




    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public boolean SendEmail(String to,String title,String Text){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("TeamORMail@gmail.com");
            message.setTo(to);
            message.setSubject(title);
            message.setText(Text);
            emailSender.send(message);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String makeRandomNumber(){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public String sendCertificationEmail(String to){
        try {
            String temp = makeRandomNumber();
            if(SendEmail(to,"OR 인증 메일입니다",
                    "OR 서비스 입니다. \n인증번호는 "+temp+" 입니다")){
                return temp;
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

}
