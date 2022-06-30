package com.oc.api_server.VO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    int bigCode; //대코드
    int smallCode;  //소코드
    String description; //설명
}
