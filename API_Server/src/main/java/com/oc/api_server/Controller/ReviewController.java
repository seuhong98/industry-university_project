package com.oc.api_server.Controller;


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



    @PostMapping("/GetList")
    @ResponseBody
    public String getList(HttpServletRequest request){

        return service.getList();
    }

    @PostMapping("/GetListContinue")
    @ResponseBody
    public String getListContinue(long id){
        return service.GetListContinue(id);
    }

    @PostMapping("/saveReview")
    @ResponseBody
    public String saveReview(Review review, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            long id = ((OrUser)session.getAttribute("User")).getId();
            service.SaveReview(review,id);
            return "Pass";
        }catch (Exception e){
            e.printStackTrace();
            return "Fail";
        }
    }

    @PostMapping("/saveImage")
    @ResponseBody
    public String saveImage(String image,int count,long id,HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            long userid = ((OrUser)session.getAttribute("User")).getId();
            service.SaveImage(image,count,userid,id);
            return "Pass";
        }catch (Exception e){
            e.printStackTrace();
            return "Fail";
        }
    }
}
