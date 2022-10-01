package com.oc.api_server.Controller;

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
                session.removeAttribute("MailCertificationPass");
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
        HttpSession session = request.getSession();
        try{
            if(session.getAttribute("try") == null || (int)session.getAttribute("try") < 5){
                OrUser read = uService.SingIn(Pw,Email);
                if(read != null){
                    session.setAttribute("User",read);
                    return session.getId();
                }else{
                    if(session.getAttribute("try") != null){
                        session.setAttribute("try", (int)session.getAttribute("try")+1);
                    }else{
                        session.setAttribute("try",1);
                    }
                    return "FALSE";
                }
            }else{
                return "TooManyWrong";
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


    /**
     * 닉네임 변경
     * @param Want 원하는것
     * @param request
     * @return
     */
    @PostMapping("/SetNickName")
    @ResponseBody
    public String SetNickName(String Want,HttpServletRequest request){
        HttpSession session = request.getSession();
        if((OrUser)session.getAttribute("User") != null){
            uService.SetNickName(((OrUser)(session.getAttribute("User"))).getId(),Want);
            return "Done";
        }else{
            return "NeedToLogin";
        }
    }


    /**
     * 이메일 변경 메일 인증 컨트롤러 필요
     * @param Want 원하는 것
     * @param request
     * @return
     */
    @PostMapping("/SetEmail")
    @ResponseBody
    public String SetEmail(String Want,HttpServletRequest request){
        HttpSession session = request.getSession();
        if( ((OrUser)session.getAttribute("User") != null)  && (session.getAttribute("MailCertificationPass") != null ? (boolean)session.getAttribute("MailCertificationPass"): false)){
            uService.SetEmail(((OrUser)(session.getAttribute("User"))).getId(),Want);
            return "Done";
        }else{
            return "NeedToLogin";
        }

    }


    /**
     * 비밀번호 변경
     * @param Want 원하는 것
     * @param request
     * @return
     */
    @PostMapping("/SetPw")
    @ResponseBody
    public String SetPw(String Want,HttpServletRequest request){
        HttpSession session = request.getSession();
        if((OrUser)session.getAttribute("User") != null){
            uService.SetPw(((OrUser)(session.getAttribute("User"))).getId(),Want);
            return "Done";
        }else{
            return "NeedToLogin";
        }
    }


    /**
     * 테스트 나중에 삭제
     * @param request
     * @return
     */
    @PostMapping("/Test")
    @ResponseBody
    public String TTTTT(HttpServletRequest request){
        HttpSession session = request.getSession();
        if((OrUser)session.getAttribute("User") != null){
            return "세션 유지";
        }else{
            return "세션 유지 못함";
        }
    }
}
