package com.oc.api_server.Controller;

import com.oc.api_server.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Login")
public class LoginController {

    private final UserService uService;

    public LoginController(UserService uService) {
        this.uService = uService;
    }

    @PostMapping("/Signup")
    @ResponseBody
    public String SignUp(String ID, String Pw, String Email, HttpServletRequest request){
        HttpSession session = request.getSession();
        try{
            if((boolean)session.getAttribute("MailCertificationPass")){
                uService.SignIn(ID,Pw,Email);
                return "TRUE";
            }else{
                return "Need_mailCertification";
            }
        }catch (Exception e){
            return "ERR";
        }
    }

    @PostMapping("/SignIn")
    @ResponseBody
    public String SignIn(String Pw, String Email, HttpServletRequest request){
        HttpSession session = request.getSession();
        try{

            return "TRUE";
        }catch (Exception e){
            return "ERR";
        }
    }

}
