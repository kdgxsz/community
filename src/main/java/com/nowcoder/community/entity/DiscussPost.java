package com.nowcoder.community.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * discuss_post表对应的实体类
 *
 * @author 尚郑
 */
@Document(indexName = "discusspost", type = "_doc", shards = 6, replicas = 3)
public class DiscussPost {

    @Id
    private int id; //主键(自增长)

    @Field(type = FieldType.Integer)
    private int userId; // 用户ID

    // 互联网校招 存 ik_max_word 尽可能拆分成多个分词 搜索 ik_smart 匹配到一个分词就可以
    @Field(type = FieldType.Text , analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String title; //帖子标题

    @Field(type = FieldType.Text , analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String content; //帖子内容

    @Field(type = FieldType.Integer)
    private int type; //帖子类型 0-普通; 1-置顶;

    @Field(type = FieldType.Integer)
    private int status; //帖子状态 0-正常; 1-精华; 2-拉黑;

    @Field(type = FieldType.Date)
    private Date createTime; //创建日期

    @Field(type = FieldType.Integer)
    private int commentCount; //评论数量

    @Field(type = FieldType.Double)
    private double score; //帖子分数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "DiscussPost{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                ", commentCount=" + commentCount +
                ", score=" + score +
                '}';
    }
}
