package com.oc.api_server.VO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class SimpleReview {

    Long id; //자동생성 ID (PK)

    String title; //제목
    String preview; //10글자 이내의 내용 글

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
    Integer money; //금액(월세) or 관리비

    Integer good; //좋아요 수


    Long review_owner; //글쓴이 id;

    Float owner_rating; //사용자 총 별점
    Float size;
    Float noise; //소음
    Float service; //집주인 서비스
    Float hygiene; //위생(ex.곰팡이,벌레 등등)
    Float safety; //보안
    Float temperature;//냉난방

    String create_day; //생성 날짜
}
