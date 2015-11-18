package com.app.mj.shopping.bean;

import java.io.Serializable;

/**
 * Created by mj on 2015/11/19.
 */
public class Wares implements Serializable {

//    每一页数据List中每一Item包含的数据
//    参见URL:http://112.124.22.238:8081/course_api/wares/hot?curPage=1&pageSize=10

    private Long id;
    private String name;
    private String imgUrl;
    private String description;
    private Float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
