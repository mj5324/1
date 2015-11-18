package com.app.mj.shopping.bean;


public class Category extends BaseBean {

    //一个Category有：标题、大图1、小图2

    private String name;

    public Category() {
    }

    public Category(String name) {

        this.name = name;
    }

    public Category(long id ,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
