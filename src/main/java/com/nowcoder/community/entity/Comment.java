package com.nowcoder.community.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Comment)表实体类
 *
 * @author makejava
 * @since 2023-05-03 13:30:26
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment  {
    private Integer id;

    private Integer userId;
    
    private Integer entityType;
    
    private Integer entityId;
    
    private Integer targetId;
    
    private String content;
    
    private Integer status;
    
    private Date createTime;

}

