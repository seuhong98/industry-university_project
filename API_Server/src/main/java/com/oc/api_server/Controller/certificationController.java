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
        String get = null;
        HttpSession session = request.getSession();
        if(!(to.split("@")[1].equals("kangwon.ac.kr"))){
            return "no_uni";
        }else{
            if(service.CheckIsUniqueEmail(to)){
                get = service.sendCertificationEmail(to); //인증 번호 보내고 그 번호 받아오기
                session.setAttribute("Mail",get);
            }else{
                return "ist_unique";
            }
        }
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


    /**
     *
     * @param input 폰에서 넣은 번호
     * @param request
     * @return
     */
    @PostMapping("/mailResponse")
    @ResponseBody
    public String MailCertificationResponse(String input, HttpServletRequest request){
        HttpSession session = request.getSession();
        try {
            if(((String)session.getAttribute("Mail")).equals(input)){
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
