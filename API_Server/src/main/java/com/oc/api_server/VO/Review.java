package com.oc.api_server.VO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;


import java.time.LocalDateTime;


/**
 * 자취방 리뷰
 */
@Setter @Getter
@NoArgsConstructor
public class Review {

    Long id; //자동생성 ID (PK)

    String title; //제목
    String preview; //10글자 이내의 내용 글


    Integer is_image; //이미지 여부
    String simple_image; // 미리보기용 이미지

    String main; //메인 글 내용

    /*
    효자1동 : 0 , 효자2동 : 1, 효자3동 : 2, 후평2동 : 3, 후평3동 : 4, 석사동 : 5, 거두리 : 6, 기숙사 : 7, 기타 : 8
     */
    Integer address;
    Double x; //x축
    Double y; //y축

    /*
    단위 만원
    기숙사는 금액 미기입
     */
    Integer review_type; //0-> 월세_반전세    1 -> 전세    2-> 기숙사
    Integer guarantee; //보증금 or 전세금
    Integer money; //금액(월세)
    Integer management; //관리비

    Integer good; //좋아요 수


    Long review_owner; //글쓴이 id;

    Float owner_rating; //사용자 별점
    String create_day; //생성 날짜
}
