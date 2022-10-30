package com.oc.api_server.Service;

import java.util.HashMap;
import java.util.Map;

public class DataService {
    final Map<String,Integer> uni = new HashMap<String,Integer>() {{
        put("kangwon.ac.kr",0);
        put("cnue.ac.kr",1);
        put("hallym.ac.kr",2);
        put("kopo.ac.kr",3);
        put("hsc.ac.kr",4);
    }};

    public Integer getUniCode(String input) {
        return uni.get(input);
    }
}
