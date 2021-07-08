package com.hazards.bean;

import java.util.List;

/**
 * 分特工具类
 *
 * @param <T> 分页的对象,即根据什么数据进行分页
 */
public class Page<T> {
    //每页默认显示五条数据
    private Integer initSize = 5;
    //总共有多少条数据
    private Integer countNum;
    //总共有多少页数据
    private Integer countPage;
    //当前页
    private Integer currentPage;
    //上一页
    private Integer prePage;
    //下一页
    private Integer nextPage;
    //页面数据
    private List<T> pageList;

    public Page(Integer initSize, Integer countNum, Integer currentPage, List<T> pageList) {
        this.initSize = initSize;
        this.countNum = countNum;
        this.currentPage = currentPage;
        this.pageList = pageList;
        int num = this.countNum / this.initSize;
        this.countPage = this.countNum % this.initSize == 0 ? num : num + 1;
        this.prePage = this.currentPage > 1 ? this.currentPage - 1 : this.currentPage;
        this.nextPage = this.currentPage < this.countPage ? this.currentPage + 1 : this.currentPage;
    }

    public Page(Integer countNum, Integer currentPage, List<T> pageList) {
        this.countNum = countNum;
        this.currentPage = currentPage;
        this.pageList = pageList;
        int num = this.countNum / this.initSize;
        this.countPage = this.countNum % this.initSize == 0 ? num : num + 1;
        this.prePage = this.currentPage > 1 ? this.currentPage - 1 : this.currentPage;
        this.nextPage = this.currentPage < this.countPage ? countPage + 1 : this.currentPage;
    }

    public Integer getInitSize() {
        return initSize;
    }

    public void setInitSize(Integer initSize) {
        this.initSize = initSize;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getCountPage() {
        return countPage;
    }

    public void setCountPage(Integer countPage) {
        this.countPage = countPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    @Override
    public String toString() {
        return "Page{" +
                "initSize=" + initSize +
                ", countNum=" + countNum +
                ", countPage=" + countPage +
                ", currentPage=" + currentPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", pageList=" + pageList +
                '}';
    }
}
