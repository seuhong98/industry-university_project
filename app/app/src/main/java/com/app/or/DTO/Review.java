package com.app.or.DTO;

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

    public Integer getIs_image() {
        return is_image;
    }

    public void setIs_image(Integer is_image) {
        this.is_image = is_image;
    }

    public String getSimple_image() {
        return simple_image;
    }

    public void setSimple_image(String simple_image) {
        this.simple_image = simple_image;
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

    public Integer getManagement() {
        return management;
    }

    public void setManagement(Integer management) {
        this.management = management;
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

    public String getCreate_day() {
        return create_day;
    }

    public void setCreate_day(String create_day) {
        this.create_day = create_day;
    }
}
