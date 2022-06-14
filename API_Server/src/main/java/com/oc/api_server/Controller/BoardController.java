package com.oc.api_server.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Board")
public class BoardController {

    @PostMapping("/SendBoard")
    public List<String> SendBoard(long id){
        List<String> sendList = new ArrayList<>();
        return sendList;
    }
}
