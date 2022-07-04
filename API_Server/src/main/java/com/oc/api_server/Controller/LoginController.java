package com.oc.api_server.Controller;

import com.oc.api_server.DTO.UserDTO;
import com.oc.api_server.Service.UserService;
import com.oc.api_server.VO.OrUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/Login")
public class LoginController {

    private final UserService uService;

    public LoginController(UserService uService) {
        this.uService = uService;
    }


    /**
     * 회원가입
     * @param ID 닉네임용 아이디
     * @param Pw 비밀번호
     * @param Email 이메일
     * @param request
     * @return
     */
    @PostMapping("/Signup")
    @ResponseBody
    public String SignUp(String ID, String Pw, String Email, HttpServletRequest request){
        HttpSession session = request.getSession();
        try{
            if((boolean)session.getAttribute("MailCertificationPass")){
                uService.SignUp(ID,Pw,Email);
                return "TRUE";
            }else{
                return "Need_mailCertification";
            }
        }catch (Exception e){
            return "ERR";
        }
    }


    /**
     * 로그인 기능
     * @param Pw 비밀번호
     * @param Email 이메일
     * @param request
     * @return
     */
    @PostMapping("/SignIn")
    @ResponseBody
    public String SignIn(String Pw, String Email, HttpServletRequest request){
        request.getSession(true);
        HttpSession session = request.getSession();
        try{
            if(session.getAttribute("try") == null || (int)session.getAttribute("trt") < 5){
                Optional<OrUser> read = uService.SingIn(Pw,Email);
                if(read.isPresent()){
                    session.setAttribute("User",new UserDTO().setID(read.get().getId()).setEmail(read.get().getEmail()));
                    return "TRUE";
                }else{
                    if(session.getAttribute("try") != null){
                        session.setAttribute("try", (int)session.getAttribute("try")+1);
                    }else{
                        session.setAttribute("try",1);
                    }
                    return "FALSE";
                }
            }else{
                return "ToomanyWrong";
            }
        }catch (Exception e){
            return "ERR";
        }
    }


    /**
     * 회원 탈퇴
     * @param Pw 비밀번호
     * @param Email 이메일
     * @param request
     * @return
     */
    @PostMapping("/Resign")
    @ResponseBody
    public String Resign(String Pw, String Email, HttpServletRequest request){
        HttpSession session = request.getSession();
        try{
            if(uService.Resign(Pw,Email)){
                session.removeAttribute("User");
                return "TRUE";
            }else{
                return "Wrong";
            }
        }catch (Exception e){
            return "ERR";
        }
    }




    @PostMapping("/SetNickName")
    @ResponseBody
    public String SetNickName(String Want,HttpServletRequest request){
        HttpSession session = request.getSession();
        if((UserDTO)session.getAttribute("User") != null){
            uService.SetNickName(((UserDTO)(session.getAttribute("User"))).getId(),Want);
            return "Done";
        }else{
            return "NeedToLogin";
        }
    }

    @PostMapping("/SetEmail")
    @ResponseBody
    public String SetEmail(String Want,HttpServletRequest request){
        HttpSession session = request.getSession();
        if((UserDTO)session.getAttribute("User") != null){
            uService.SetNickName(((UserDTO)(session.getAttribute("User"))).getId(),Want);
            return "Done";
        }else{
            return "NeedToLogin";
        }
    }




}
