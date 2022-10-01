package com.app.or.DTO;
import android.graphics.Bitmap;

import java.time.LocalDateTime;
import java.util.List;


public class Review {
    long id; //자동생성 ID (PK)
    byte what; //0 -> 자취방 1-> 기숙사

    String title; //제목
    List<String> main; //메인 글 내용

    String preview; //10글자 이내의 내용 글
    List<Bitmap> is_image; //이미지 여부

    String address; //집 주소
    String address_detail; //사용자가 입력한 상세 주소

    int guarantee; //보증금
    byte price; //0-> 월세_반전세 1 -> 전세
    int money; //금액
    int management; //관리비

    int people; //평가 사람 수
    long sum; //평가 총 합


    long review_owner; //글쓴이 닉네임;


    private LocalDateTime createdDate; //생성 시간

    private LocalDateTime modifiedDate; //수정 시간
}
