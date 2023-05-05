package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Message)表数据库访问层
 * @author makejava
 * @since 2023-05-03 20:41:03
 */

@Mapper
public interface MessageMapper{

    //查询当前用户的会话列表,针对每个会话只返回一条最新的私信
    List<Message> selectConversations(Integer userId, Integer offset, Integer limit);

    //查询当前用户的会话数量
    Integer selectConversationCount(Integer userId);

    //查询某个会话所包含的私信列表
    List<Message> selectLetters(String conversationId, Integer offset, Integer limit);

    //查询某个会话所包含的私信数量
    Integer selectLetterCount(String conversationId);

    // 查询未读私信的数量
    Integer selectLetterUnreadCount(Integer userId, String conversationId);

    // 新增消息
    Integer insertMessage(Message message);
}

