package com.oc.api_server.Controller;


import com.oc.api_server.Service.ConfirmService;
import com.oc.api_server.Service.ReviewService;
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


    @PostMapping("/GetCurrentList")
    @ResponseBody
    public String getCurrentList(){
        return service.getCurrentList();
    }

    @PostMapping("/GetList")
    @ResponseBody
    public String getList(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] data = confirmService.Data(session,params,Signature);
        return service.getList(Long.parseLong(data[0]));
    }

    @PostMapping("/getCurrentListByPlace")
    @ResponseBody
    public String getCurrentListByPlace(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        //0: 지역
        String[] data = confirmService.Data(session,params,Signature);
        return service.getCurrentListByPlace(Integer.parseInt(data[0]));
    }

    @PostMapping("/getListByPlace")
    @ResponseBody
    public String getListByPlace(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        //0: 지역 1: id
        String[] data = confirmService.Data(session,params,Signature);
        return service.getListByPlace(Integer.parseInt(data[0]),Long.parseLong(data[1]));
    }

    @PostMapping("/getCurrentListByRating")
    @ResponseBody
    public String getCurrentListByRating(){
        return service.getCurrentListByRating();
    }

    @PostMapping("/getListByRating")
    @ResponseBody
    public String getListByRating(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        //0: id
        String[] data = confirmService.Data(session,params,Signature);
        return service.getListByRating(Long.parseLong(data[0]));
    }

    @PostMapping("/getMyList")
    @ResponseBody
    public String getMyList(HttpServletRequest request){
        HttpSession session = request.getSession();
        return service.getMyList(((OrUser)session.getAttribute("User")).getId());
    }

    @PostMapping("/saveReview")
    @ResponseBody
    public void saveReview(String Signature,String params, HttpServletRequest request){
        HttpSession session = request.getSession();
        String[] data = confirmService.Data(session,params,Signature);
        service.saveReview(data,((OrUser)session.getAttribute("User")).getId());
    }

}
