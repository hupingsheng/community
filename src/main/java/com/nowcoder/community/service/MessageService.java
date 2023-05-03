package com.nowcoder.community.service;

import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public List<Message> findConversations(Integer userId, Integer offset, Integer limit){
        return messageMapper.selectConversations(userId, offset, limit);
    }

    public Integer findConversationCount(Integer userId){
        return messageMapper.selectConversationCount(userId);
    }

    public List<Message> findLetters(String conversationId, int offset, int limit){
        return messageMapper.selectLetters(conversationId, offset, limit);
    }

    public Integer findLetterCount(String conversationId){
        return messageMapper.selectLetterCount(conversationId);
    }

    public Integer findLetterUnreadCount(Integer userId, String conversationId){
        return messageMapper.selectLetterUnreadCount(userId, conversationId);
    }

}
