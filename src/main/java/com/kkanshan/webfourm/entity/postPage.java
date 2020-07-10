package com.kkanshan.webfourm.entity;

import java.util.List;

public class postPage {
    private List<Question> list;
    private int pageTotal;

    public postPage(List<Question> list, int pageTotal) {
        this.list = list;
        this.pageTotal = pageTotal;
    }

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
}
