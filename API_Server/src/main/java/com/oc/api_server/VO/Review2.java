package com.oc.api_server.VO;


import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class Review2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id; //자동 생성
    String title; //제목
    String main; //메인 글 내용
    String photo; //사진 주소 저장(Nas 스토리지) | <--구분자
    int address_code; //주소 코드
    byte clean; //위생
    byte facility; //시설물
    byte capacity; //수용 인원
    byte total; //전체적인 점수

    String review_owner;//글쓴이
    int point; //이 글로 얻은 포인트 인증글로 변환시 지금까지 얻은 포인트 더 얻음
    boolean is_verification; //검증 되었는가 확인 true : 검즘됨, false : 검증 안됨  -->호실배정 안내로
}
