package com.oc.api_server.VO;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 기숙사 리뷰
 */
@Entity
@Getter @Setter
public class Review2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id; //자동 생성
    String title; //제목
    String main; //메인 글 내용
    String photo; //사진 주소 저장(Nas 스토리지)
    int address_code; //주소 코드
    byte clean; //위생
    byte facility; //시설물
    byte capacity; //수용 인원
    byte total; //전체적인 점수
}
