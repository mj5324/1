package com.app.mj.shopping.bean;

/**
 * Created by mj on 2015/11/17.
 */
public class Banner extends BaseBean {

//    轮播广告的实体类Banner
//    数据格式，参照网址：http://112.124.22.238:8081/course_api/banner/query?type=1
    private  String name;
    private  String imgUrl;
    private  String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
