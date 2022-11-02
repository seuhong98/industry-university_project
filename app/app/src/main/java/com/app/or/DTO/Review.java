package com.app.or.DTO;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Review {
    Long id; //자동생성 ID (PK)

    String title; //제목
    String preview; //10글자 이내의 내용 글

    String main; //메인 글 내용
    List<Bitmap> image_txt;

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
    Float size_room;
    Float noise; //소음
    Float service; //집주인 서비스
    Float hygiene; //위생(ex.곰팡이,벌레 등등)
    Float safety; //보안
    Float temperature;//냉난방

    String create_day; //생성 날짜

    String input_address;

    Boolean own;

    String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getOwn() {
        return own;
    }

    public void setOwn(Boolean own) {
        this.own = own;
    }

    public String getInput_address() {
        return input_address;
    }

    public void setInput_address(String input_address) {
        this.input_address = input_address;
    }

    public List<Bitmap> getImage_txt() {
        if(image_txt != null){
            return image_txt;
        }else{
           return new ArrayList<Bitmap>();
        }
    }

    public void setImage_txt(List<Bitmap> image_txt) {
        this.image_txt = image_txt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getReview_type() {
        return review_type;
    }

    public void setReview_type(Integer review_type) {
        this.review_type = review_type;
    }

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public Long getReview_owner() {
        return review_owner;
    }

    public void setReview_owner(Long review_owner) {
        this.review_owner = review_owner;
    }

    public Float getOwner_rating() {
        return owner_rating;
    }

    public void setOwner_rating(Float owner_rating) {
        this.owner_rating = owner_rating;
    }

    public Float getSize() {
        return size_room;
    }

    public void setSize(Float size) {
        this.size_room = size;
    }

    public Float getNoise() {
        return noise;
    }

    public void setNoise(Float noise) {
        this.noise = noise;
    }

    public Float getService() {
        return service;
    }

    public void setService(Float service) {
        this.service = service;
    }

    public Float getHygiene() {
        return hygiene;
    }

    public void setHygiene(Float hygiene) {
        this.hygiene = hygiene;
    }

    public Float getSafety() {
        return safety;
    }

    public void setSafety(Float safety) {
        this.safety = safety;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public String getCreate_day() {
        return create_day;
    }

    public void setCreate_day(String create_day) {
        this.create_day = create_day;
    }
}
