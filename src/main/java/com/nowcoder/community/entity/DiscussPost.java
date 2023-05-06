package com.nowcoder.community.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * (DiscussPost)表实体类
 *
 * @author makejava
 * @since 2023-04-30 19:08:22
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
//分片：6 副本：3  实体DiscussPost 和 discusspost索引相映射
@Document(indexName = "discusspost", type = "_doc", shards = 6,replicas = 3)
public class DiscussPost {

    @Id
    private Integer id;
    @Field(type = FieldType.Integer)
    //发布者
    private Integer userId;

    //互联网校招    存储时的分词器     搜索时的分词器
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    //标题
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    //内容
    private String content;

    @Field(type = FieldType.Integer)
    //0-普通; 1-置顶;
    private Integer type;

    @Field(type = FieldType.Integer)
    //0-正常; 1-精华; 2-拉黑;
    private Integer status;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Integer)
    //帖子的评论总量
    private Integer commentCount;

    @Field(type = FieldType.Double)
    //分数，帖子排名用的
    private Double score;

}

