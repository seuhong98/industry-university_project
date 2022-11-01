package com.app.or.DTO;

public class SimpleReview {
    Long id; //자동생성 ID (PK)
    String title;
    String preview;
    Integer good; //좋아요 수
    Float owner_rating; //사용자 총점
    int where;

    public int getWhere() {
        return where;
    }

    public void setWhere(int where) {
        this.where = where;
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

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public Float getOwner_rating() {
        return owner_rating;
    }

    public void setOwner_rating(Float owner_rating) {
        this.owner_rating = owner_rating;
    }
}
