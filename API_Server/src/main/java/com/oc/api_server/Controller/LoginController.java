package com.oc.api_server.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Login")
public class LoginController {

    @RequestMapping("/SignIn")
    @ResponseBody
    public String SignIn(String ID, String Pw, String Email){
        return "";
    }
}
