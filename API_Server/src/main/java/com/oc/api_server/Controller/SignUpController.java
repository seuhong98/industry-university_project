package com.oc.api_server.Controller;

import com.oc.api_server.DTO.SignUpData;
import com.oc.api_server.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private EmailService service;
    @Autowired
    private UserService ur;
    @Autowired
    private ConfirmService confirm;
    @Autowired
    private DataService data;
    @Autowired
    private Security security;


    /**
     * 이메일 인증
     * 랜덤 이메일 번호를 세션에 저장하고 성공 문자 보내기
     * @return
     */
    @PostMapping("/mail")
    @ResponseBody
    public String MailCertification(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirm.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }

        Integer uniCode = data.getUniCode(param[0].split("@")[1]);
        if(uniCode == null){
            return security.encryptionBySessionKey("No_uni",(String)session.getAttribute("SessionKey"));
        }
        else if(Integer.parseInt(param[1]) != uniCode){
            return security.encryptionBySessionKey("Code_ERR",(String)session.getAttribute("SessionKey"));
        }else{
            if(ur.CheckIsUniqueEmail(param[0])){
                String sendCode = service.sendCertificationEmail(param[0]);
                if(sendCode != null){
                    SignUpData signUpData =  (SignUpData)session.getAttribute("SignUpData");
                    if(signUpData == null){
                        signUpData = new SignUpData();
                    }
                    signUpData.setCode(sendCode);
                    signUpData.setEmail(param[0]);
                    session.setAttribute("SignUpData",signUpData);
                    return security.encryptionBySessionKey("Success",(String)session.getAttribute("SessionKey"));
                }else{
                    return security.encryptionBySessionKey("Email_ERR",(String)session.getAttribute("SessionKey"));
                }
            }else{
                return security.encryptionBySessionKey("Use_Email",(String)session.getAttribute("SessionKey"));
            }
        }
    }


    /**
     *
     * @param params 폰에서 넣은 번호
     * @param request
     * @return
     */
    @PostMapping("/mailResponse")
    @ResponseBody
    public String MailCertificationResponse(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirm.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        try {
            SignUpData signUpData =  (SignUpData)session.getAttribute("SignUpData");
            if(signUpData == null || signUpData.getCode_wrong()>=100){
                return security.encryptionBySessionKey("Cheating",(String)session.getAttribute("SessionKey"));
            }
            if(signUpData.getCode().equals(param[0])){
                signUpData.setCode_Certification(true);
                session.setAttribute("SignUpData",signUpData);
                return security.encryptionBySessionKey("TRUE",(String)session.getAttribute("SessionKey"));
            }else{
                signUpData.setCode_wrong(signUpData.getCode_wrong()+1);
                return security.encryptionBySessionKey("FALSE",(String)session.getAttribute("SessionKey"));
            }
        }catch (Exception e){
            return security.encryptionBySessionKey("ERR",(String)session.getAttribute("SessionKey"));
        }
    }

    @PostMapping("/checkNickname")
    @ResponseBody
    public String checkNickname(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirm.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        SignUpData signUpData =  (SignUpData)session.getAttribute("SignUpData");
        if(signUpData == null){
            signUpData = new SignUpData();
        }
        if(param[0].contains("관리자")||param[0].contains("admin")||param[0].contains("씨발")||param[0].contains("병신")
                ||param[0].contains("tlqkf")||param[0].contains("Tlqkf")||param[0].contains("qudtls")
                ||param[0].toLowerCase().contains("fuck")){
            signUpData.setNickName_Certification(false);
            return security.encryptionBySessionKey("FALSE",(String)session.getAttribute("SessionKey"));
        }

        if(ur.CheckIsUniqueNickname(param[0])){
            signUpData.setNickName(param[0]);
            signUpData.setNickName_Certification(true);
            session.setAttribute("SignUpData",signUpData);
            return security.encryptionBySessionKey("TRUE",(String)session.getAttribute("SessionKey"));
        }else{
            signUpData.setNickName_Certification(false);
            return security.encryptionBySessionKey("FALSE",(String)session.getAttribute("SessionKey"));
        }

    }

    @PostMapping("/SingUp")
    @ResponseBody
    public String SingUp(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirm.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        SignUpData signUpData =  (SignUpData)session.getAttribute("SignUpData");
        if(signUpData == null || !signUpData.isCode_Certification() || !signUpData.isNickName_Certification()){
            return security.encryptionBySessionKey("Cheating",(String)session.getAttribute("SessionKey"));
        }
        if(param[0].length()<8){
            return security.encryptionBySessionKey("Err",(String)session.getAttribute("SessionKey"));
        }
        String c = signUpData.getEmail().split("@")[1];
        if(!ur.SignUp(signUpData.getEmail(),signUpData.getNickName(),param[0],Byte.parseByte(param[1]),Byte.parseByte(param[2]),Byte.parseByte(param[3]),Byte.parseByte(param[4]),data.getUniCode(c))){
            session.removeAttribute("SignUpData");
            return security.encryptionBySessionKey("True",(String)session.getAttribute("SessionKey"));
        }else{
            return security.encryptionBySessionKey("Err",(String)session.getAttribute("SessionKey"));
        }
    }

}
