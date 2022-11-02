package com.oc.api_server.Controller;

import com.oc.api_server.Service.ConfirmService;
import com.oc.api_server.Service.Security;
import com.oc.api_server.Service.UserService;
import com.oc.api_server.VO.OrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Login")
public class LoginController {
    
    @Autowired
    private UserService uService;
    @Autowired
    private Security security;

    @Autowired
    private ConfirmService confirm;



    /**
     * 로그인 기능
     * @param params 참조값
     * @param request
     * @return
     */
    @PostMapping("/SignIn")
    @ResponseBody
    public String SignIn(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirm.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        try{
            if(session.getAttribute("try") == null || (int)session.getAttribute("try") < 5){
                OrUser read = uService.SingIn(param[0],param[1]);
                if(read != null){
                    session.setAttribute("User",read);
                    return security.encryptionBySessionKey("TRUE",(String)session.getAttribute("SessionKey"));
                }else{
                    if(session.getAttribute("try") != null){
                        session.setAttribute("try", (int)session.getAttribute("try")+1);
                    }else{
                        session.setAttribute("try",1);
                    }
                    return security.encryptionBySessionKey("FALSE",(String)session.getAttribute("SessionKey"));
                }
            }else{
                return security.encryptionBySessionKey("TooManyWrong",(String)session.getAttribute("SessionKey"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return security.encryptionBySessionKey("ERR",(String)session.getAttribute("SessionKey"));
        }
    }


    /**
     * 회원 탈퇴
     * @param request
     * @return
     */
    @PostMapping("/Resign")
    @ResponseBody
    public String Resign(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirm.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        String lastKey = (String)session.getAttribute("SessionKey");
        try{
            uService.Resign(((OrUser)session.getAttribute("User")).getId());
            session.invalidate();
            return security.encryptionBySessionKey("GoodByeThankYou",lastKey);
        }catch (Exception e){
            e.printStackTrace();
            return security.encryptionBySessionKey("ERR",lastKey);
        }
    }


    /**
     * 닉네임 변경
     * @param request
     * @return
     */
    @PostMapping("/SetNickName")
    @ResponseBody
    public String SetNickName(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirm.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        if(uService.CheckIsUniqueNickname(param[0])){
            try {
                uService.SetNickName(((OrUser)session.getAttribute("User")).getId(),param[0]);
                return security.encryptionBySessionKey("Change",(String)session.getAttribute("SessionKey"));
            }catch (Exception e){
                e.printStackTrace();
                return security.encryptionBySessionKey("ERR",(String)session.getAttribute("SessionKey"));
            }
        }else{
            return security.encryptionBySessionKey("Exist",(String)session.getAttribute("SessionKey"));
        }
    }

    /**
     * 비밀번호 변경
     * @return
     */
    @PostMapping("/SetPw")
    @ResponseBody
    public String SetPw(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirm.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        uService.SetPw(((OrUser)(session.getAttribute("User"))).getId(),param[0]);
        return security.encryptionBySessionKey("Done",(String)session.getAttribute("SessionKey"));
    }
}
