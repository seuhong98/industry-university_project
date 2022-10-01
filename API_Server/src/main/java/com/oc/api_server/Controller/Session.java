package com.oc.api_server.Controller;

import com.oc.api_server.Repository.SessionRepository;
import com.oc.api_server.VO.publicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/Session")
public class Session {

    @Autowired
    SessionRepository repository;


    @RequestMapping("/MakeSession")
    @ResponseBody
    public String MakeSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return session.getId();
    }

    @RequestMapping("/GetPublicKey")
    @ResponseBody
    public String GetPublicKey(){
        try {
            StringBuffer sb = new StringBuffer();
            List<publicKey> list = repository.getPublicKey();
            for(publicKey t : list){
                sb.append(t.getKey()+"\n");
            }
            return sb.toString();

        }catch (Exception e){
            return "ERR";
        }
    }
    @RequestMapping("/TouchPublicKey")
    @ResponseBody
    public boolean TouchPublicKey(LocalDateTime time){
        try {
            LocalDateTime get = repository.getCurrent().getTime();
            return time.isAfter(get);

        }catch (Exception e){
            return false;
        }
    }

}
