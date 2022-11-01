package com.oc.api_server.Controller;

import com.oc.api_server.Repository.SessionRepository;
import com.oc.api_server.Service.Security;
import com.oc.api_server.VO.publicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/Session")
public class Session {
    private String Separator = "|Sep|";
    private String UnSeparator = "\\|Sep\\|";

    @Autowired
    SessionRepository repository;

    @Autowired
    private Security security;


    @RequestMapping("/MakeSession")
    @ResponseBody
    public String MakeSession(HttpServletRequest request, String params){
        params = ConvertNormalToSpecial(params).split(UnSeparator)[0];
        HttpSession session = request.getSession();
        String[] data = params.split(" ");
        int start = (int)(Math.random()*100000 +1);
        session.setAttribute("SessionKey",security.RSADecryption(Integer.parseInt(data[0]),data[1]));
        session.setAttribute("Count",start);
        System.out.println("SessionKey "+(String)session.getAttribute("SessionKey"));

        return security.encryptionBySessionKey((session.getId()+Separator+start),(String)session.getAttribute("SessionKey"));
    }

    @RequestMapping("/GetPublicKey")
    @ResponseBody
    public String GetPublicKey(){
        System.out.println("get_key");
        try {
            StringBuffer sb = new StringBuffer();
            List<publicKey> list = repository.getPublicKey();
            list.sort(new Comparator<publicKey>() {
                @Override
                public int compare(publicKey o1, publicKey o2) {
                    return o1.getKey_order()- o2.getKey_order();
                }
            });
            sb.append(list.get(0).getMake_sequence()+Separator);
            for(publicKey t : list){
                sb.append(t.getPublickey()+Separator);
            }
            return sb.toString();

        }catch (Exception e){
            return "ERR";
        }
    }
    @RequestMapping("/TouchPublicKey")
    @ResponseBody
    public boolean TouchPublicKey(int make_sequence){
        try {
            int get = repository.getCurrent().getMake_sequence();
            return (get == make_sequence);
        }catch (Exception e){
            return false;
        }
    }


    private String ConvertNormalToSpecial(String Normal){
        return Normal.replace("a**b**a","&").replace("b**a**b","=").replace("c**b**c","%").replace("*space*","+");
    }

}
