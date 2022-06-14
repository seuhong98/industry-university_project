package com.oc.api_server.VO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


/**
 * 자취방 리뷰
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class Review1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id; //자동생성 ID
    String title; //제목
    String main; //메인 글 내용
    String photo; //사진
    int address_code; //주소 코드
    byte water; //수압점수
    byte sun; //채광점수
    byte cold; //냉난방 점수
    byte clean; //위생
    byte sound; //주변 소음
    byte owner; //집주인 대응
    byte convenience; //편의시실(정수기, 대형세탁기 등등)
    byte total; //전체적인 점수

    String review_owner;//글쓴이
    int point; //이 글로 얻은 포인트 인증글로 변환시 지금까지 얻은 포인트 더 얻음
    boolean is_verification; //검증 되었는가 확인 true : 검즘됨, false : 검증 안됨 --> 계약서로

    @CreatedDate
    private LocalDateTime createdDate; //생성 시간

    @LastModifiedDate
    private LocalDateTime modifiedDate; //수정 시간
}
