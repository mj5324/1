/*
*Campaign.java
*Created on 2015/10/3 下午10:34 by Ivan
*Copyright(c)2014 Guangzhou Onion Information Technology Co., Ltd.
*http://www.cniao5.com
*/
package com.app.mj.shopping.bean;

import java.io.Serializable;

public class Campaign implements Serializable {

//CardView里面的 单张图片
//    API接口：http://112.124.22.238:8081/course_api/campaign/recommend
//    里面的cpOne,cpTwo
    private Long id;
    private String title;
    private String imgUrl;


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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
