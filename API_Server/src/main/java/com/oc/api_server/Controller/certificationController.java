package com.oc.api_server.Controller;

import com.oc.api_server.Service.CertificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/certification")
public class certificationController {
    private final CertificationService service;

    public certificationController(CertificationService service) {
        this.service = service;
    }


    /**
     * 이메일 인증
     * 랜덤 이메일 번호를 세션에 저장하고
     * @param to 원하는 이메일
     * @return
     */
    @PostMapping("/mail")
    @ResponseBody
    public String MailCertification(String to, HttpServletRequest request){
        HttpSession session = request.getSession();
        String get = service.sendCertificationEmail(to);
        try{
            if(get.length()==0){
                return "error_code_null";
            }else{
                session.setAttribute("Mail",get);
                return "TRUE";
            }
        }catch (Exception e){
            return "ERR";
        }
    }


    @PostMapping("/mailResponse")
    @ResponseBody
    public String MailCertificationResponse(String input, HttpServletRequest request){
        HttpSession session = request.getSession();
        try {
            if(session.getAttribute("Mail").equals(input)){
                session.removeAttribute("Mail");
                session.setAttribute("MailCertificationPass",true);
                return "TRUE";
            }else{
                return "FALSE";
            }
        }catch (Exception e){
            return "ERR";
        }
    }



}
