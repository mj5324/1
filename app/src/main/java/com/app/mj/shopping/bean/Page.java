package com.app.mj.shopping.bean;

import java.util.List;

/**
 * Created by mj on 2015/11/19.
 */
public class Page<T> {
//每一页的返回数据
    //    参见URL:http://112.124.22.238:8081/course_api/wares/hot?curPage=1&pageSize=10


    private  int currentPage;
    private  int pageSize;
    private  int totalPage;
    private  int totalCount;

    private List<T> list;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
