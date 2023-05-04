package com.nowcoder.community.dao;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * (LoginTicket)表数据库访问层
 * @author makejava
 * @since 2023-05-01 18:23:14
 */
@Mapper
@Deprecated
public interface LoginTicketMapper {

    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values (#{userId}, #{ticket}, #{status}, #{expired})"
            })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertLoginTicket(LoginTicket loginTicket);


    @Select({
            "select id, user_id, ticket, status, expired ",
            "from login_ticket where ticket = #{ticket}"
    })
    LoginTicket selectByTicket(String ticket);


    @Update({
            "update login_ticket set status = #{status} ",
            "where ticket = #{ticket}"
    })
    int updateStatus(String ticket, int status);

}

