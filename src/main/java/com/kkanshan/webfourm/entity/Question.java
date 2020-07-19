package com.kkanshan.webfourm.entity;


public class Question {
    private int id;
    private String title;
    private String description;
    private int createId;
    private int comment_count;
    private int view_count;
    private int like_count;
    private int tag;
    private String createTime;

    public Question(){

    }
    public Question(int id, String title, String description,
                    int createId, int tag, String createTime){
        this.id=id;
        this.title=title;
        this.description=description;
        this.createId=createId;
        this.tag=tag;
        this.createTime=createTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCreateId() {
        return createId;
    }

    public int getComment_count() {
        return comment_count;
    }

    public int getView_count() {
        return view_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public int getTag() {
        return tag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
