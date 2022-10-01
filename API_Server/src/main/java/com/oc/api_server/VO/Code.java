package com.oc.api_server.VO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter @Getter
@NoArgsConstructor
public class Code {


    long id;

    int bigCode; //대코드
    int smallCode;  //소코드
    String description; //설명
}
