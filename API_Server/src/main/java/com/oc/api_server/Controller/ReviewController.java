package com.oc.api_server.Controller;


import com.oc.api_server.Service.ConfirmService;
import com.oc.api_server.Service.ReviewService;
import com.oc.api_server.Service.Security;
import com.oc.api_server.Useful.DataMapper;
import com.oc.api_server.VO.OrUser;
import com.oc.api_server.VO.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Review")
public class ReviewController {

    @Autowired
    private ReviewService service;
    @Autowired
    private ConfirmService confirmService;

    @Autowired
    Security security;

    @Autowired
    DataMapper dataMapper;


    @PostMapping("/GetCurrentList")
    @ResponseBody
    public String getCurrentList(HttpServletRequest request){
        HttpSession session = request.getSession();
        return security.encryptionBySessionKey(service.getCurrentList(((OrUser)session.getAttribute("User")).getUni_code()),(String)session.getAttribute("SessionKey"));
    }

    @PostMapping("/getMyCurrent")
    @ResponseBody
    public String getMyCurrent(HttpServletRequest request){
        HttpSession session = request.getSession();
        return security.encryptionBySessionKey(service.getMyCurrent(((OrUser)session.getAttribute("User")).getId()),(String)session.getAttribute("SessionKey"));
    }

    @PostMapping("/getMyGood")
    @ResponseBody
    public String getMyGood(HttpServletRequest request){
        HttpSession session = request.getSession();
        return security.encryptionBySessionKey(service.getMyGood(((OrUser)session.getAttribute("User")).getId()),(String)session.getAttribute("SessionKey"));
    }

    @PostMapping("/GetFilterList")
    @ResponseBody
    public String GetFilterList(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirmService.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        return security.encryptionBySessionKey(service.getFilterList(((OrUser)session.getAttribute("User")).getUni_code(),param[0]),(String)session.getAttribute("SessionKey"));
    }


    @PostMapping("/getReview")
    @ResponseBody
    public String getReview(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirmService.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        Review review = service.getReview(Long.parseLong(param[0]),((OrUser)session.getAttribute("User")).getUni_code());

        System.out.println((((OrUser)session.getAttribute("User")).getId() == review.getReview_owner()) +"   "+(((OrUser)session.getAttribute("User")).getId() + "  "+ review.getReview_owner() ));

        return security.encryptionBySessionKey((dataMapper.ReviewSerialization(review)+(
                "|Sep|" + (((OrUser)session.getAttribute("User")).getId().equals(review.getReview_owner()))
                +"|Sep|"+service.GetNickName(review.getReview_owner()))
                ),(String)session.getAttribute("SessionKey"));
    }

    @PostMapping("/saveReview")
    @ResponseBody
    public String saveReview(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirmService.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        try {
            service.saveReview(param,((OrUser)session.getAttribute("User")).getId(),((OrUser)session.getAttribute("User")).getUni_code());
            return security.encryptionBySessionKey("PASS",(String)session.getAttribute("SessionKey"));
        }catch (Exception e){
            e.printStackTrace();
            return security.encryptionBySessionKey("FAIL",(String)session.getAttribute("SessionKey"));
        }
    }

    @PostMapping("/good")
    @ResponseBody
    public String Good(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirmService.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        if(service.is_good(Long.parseLong(param[0]),((OrUser)session.getAttribute("User")).getId()) ==0){
            service.Good(Long.parseLong(param[0]));
            service.Good_Count(Long.parseLong(param[0]),((OrUser)session.getAttribute("User")).getId());
            return security.encryptionBySessionKey("True",(String)session.getAttribute("SessionKey"));
        }else{
            return security.encryptionBySessionKey("False",(String)session.getAttribute("SessionKey"));
        }
    }

    @PostMapping("/bad")
    @ResponseBody
    public String Bad(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirmService.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        if(service.is_Bad(Long.parseLong(param[0]),((OrUser)session.getAttribute("User")).getId()) ==0){
            service.Bad(Long.parseLong(param[0]));
            service.Bad_Count(Long.parseLong(param[0]),((OrUser)session.getAttribute("User")).getId());
            return security.encryptionBySessionKey("True",(String)session.getAttribute("SessionKey"));
        }else{
            return security.encryptionBySessionKey("False",(String)session.getAttribute("SessionKey"));
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public String Delete(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirmService.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        service.Delete(Long.parseLong(param[0]),((OrUser)session.getAttribute("User")).getId());
        return security.encryptionBySessionKey("fin",(String)session.getAttribute("SessionKey"));
    }

    @PostMapping("/getXY")
    @ResponseBody
    public String getXY(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return security.encryptionBySessionKey(service.getXY(((OrUser)session.getAttribute("User")).getUni_code()), (String) session.getAttribute("SessionKey"));

    }

    @PostMapping("/GetXYList")
    @ResponseBody
    public String GetXYList(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] param = confirmService.Data(session,params,Signature);
        if(param == null){
            return security.encryptionBySessionKey("Deodorization",(String)session.getAttribute("SessionKey"));
        }
        return security.encryptionBySessionKey(service.GetXYList(Double.parseDouble(param[0]),Double.parseDouble(param[1])),(String)session.getAttribute("SessionKey"));
    }

}
