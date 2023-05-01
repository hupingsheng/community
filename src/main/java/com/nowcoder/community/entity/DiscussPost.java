package com.nowcoder.community.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class DiscussPost {
    private Integer id;

    //发布者
    private Integer userId;
    //标题
    private String title;
    //内容
    private String content;
    //0-普通; 1-置顶;
    private Integer type;
    //0-正常; 1-精华; 2-拉黑;
    private Integer status;
    
    private Date createTime;
    //帖子的评论总量
    private Integer commentCount;
    //分数，帖子排名用的
    private Double score;

}

