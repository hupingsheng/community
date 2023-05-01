package com.nowcoder.community.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (LoginTicket)表实体类
 *
 * @author makejava
 * @since 2023-05-01 18:23:16
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginTicket  {
    private Integer id;

    private Integer userId;
    
    private String ticket;
    //0-有效; 1-无效;
    private Integer status;
    
    private Date expired;

}

