package com.nowcoder.community.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Message)表实体类
 *
 * @author makejava
 * @since 2023-05-03 20:41:05
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message  {
    private Integer id;

    
    private Integer fromId;
    
    private Integer toId;
    
    private String conversationId;
    
    private String content;
    //0-未读;1-已读;2-删除;
    private Integer status;
    
    private Date createTime;

}

