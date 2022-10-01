package com.oc.api_server.VO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


/**
 * 자취방 리뷰
 */
@Setter @Getter
@NoArgsConstructor
public class Review {

    long id; //자동생성 ID (PK)

    String title; //제목
    String main; //메인 글 내용

    String preview; //10글자 이내의 내용 글
    boolean is_image; //이미지 여부

    /*
    효자1동
    효자2동
    효자3동
    후평2동
    후평3동
    석사동
    거두리
    기숙사
     */
    int address;
    Double x;
    Double y;

    int guarantee; //보증금 or 전세금 or 기숙사
    byte price; //0-> 월세_반전세 1 -> 전세
    int money; //금액
    int management; //관리비 or 그거

    int good; //좋아요 수


    long review_owner; //글쓴이 닉네임;
    float total; //사용자 별점


    private LocalDateTime createdDate; //생성 시간

    private LocalDateTime modifiedDate; //수정 시간
}
