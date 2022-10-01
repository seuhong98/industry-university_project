package com.app.or.DTO;

import android.graphics.Bitmap;

public class MakeReview1 {
    String title =null;
    String main[]=null;
    Bitmap imageAddress[]=null;
    String address1=null;
    String address2=null;
    Double x=null;
    Double y = null;
    Integer guarantee=null;
    Integer price=null;
    Integer money=null;
    Integer management=null;
    Float total=null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getMain() {
        return main;
    }

    public void setMain(String[] main) {
        this.main = main;
    }

    public Bitmap[] getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(Bitmap[] imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public Integer getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(Integer guarantee) {
        this.guarantee = guarantee;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
