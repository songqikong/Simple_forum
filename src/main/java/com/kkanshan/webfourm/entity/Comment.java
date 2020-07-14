package com.kkanshan.webfourm.entity;

public class Comment {
    private int id;
    private int parent_id;
    private int type;
    private int commenter;
    private String createTime;
    private int like_count;
    private String content;
    private int commentCount;

    public Comment() {
    }

    public Comment(int id, int parent_id, int type, int commenter,
                   String createTime, int like_count,
                   String content, int commentCount) {
        this.id = id;
        this.parent_id = parent_id;
        this.type = type;
        this.commenter = commenter;
        this.createTime = createTime;
        this.like_count = like_count;
        this.content = content;
        this.commentCount = commentCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCommenter() {
        return commenter;
    }

    public void setCommenter(int commenter) {
        this.commenter = commenter;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
