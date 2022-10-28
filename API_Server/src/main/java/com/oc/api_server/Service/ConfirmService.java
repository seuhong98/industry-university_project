package com.oc.api_server.Service;

import javax.servlet.http.HttpSession;

public class ConfirmService {
    private String Separator = "|Sep|";
    private final Security security;

    public ConfirmService(Security security) {
        this.security = security;
    }

    public String[] Data(HttpSession session, String params, String signature){
        params = ConvertNormalToSpecial(params);
        signature = ConvertNormalToSpecial(signature);
        params = security.decryptionBySessionKey(params,(String)session.getAttribute("SessionKey"));
        System.out.println("인증 서비스에서 온거 : "+(int)session.getAttribute("Count"));
        if(signature.equals(security.Signature(params,(int)session.getAttribute("Count")))){
            session.setAttribute("Count",((int)session.getAttribute("Count")+1));
        }else{
            System.out.println("카운트가 틀려요!!!");
            return null;
        }
        return params.split(Separator);
    }

    private String ConvertNormalToSpecial(String Normal){
        return Normal.replace("a**b**a","&").replace("b**a**b","=").replace("c**b**c","%").replace("*space*","+");
    }
}
