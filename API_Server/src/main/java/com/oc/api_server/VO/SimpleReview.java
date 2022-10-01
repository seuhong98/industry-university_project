package com.oc.api_server.VO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class SimpleReview {
    long id;
    String title;
    String simple_main;
    boolean is_image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSimple_main() {
        return simple_main;
    }

    public void setSimple_main(String simple_main) {
        this.simple_main = simple_main;
    }

    public boolean isIs_image() {
        return is_image;
    }

    public void setIs_image(boolean is_image) {
        this.is_image = is_image;
    }
}
