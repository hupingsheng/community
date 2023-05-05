package com.nowcoder.community.entity;

import java.util.HashMap;
import java.util.Map;

//将消息封装成事件
public class Event {
    //主题  业务角度：就是事件的类型，评论/点赞/关注
    private String topic;
    //事件的触发人，由系统代替触发人发送消息给触发事件实体所属user
    private Integer userId;
    //事件发生在哪个实体上
    private Integer entityType;
    //事件的实体所属userid
    private Integer entityUserId;

    private Integer entityId;

    public Integer getEntityId() {
        return entityId;
    }

    public Event setEntityId(Integer entityId) {
        this.entityId = entityId;
        return this;
    }

    //
    private Map<String, Object> data = new HashMap<>();

    public String getTopic() {
        return topic;
    }

    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Event setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public Event setEntityType(Integer entityType) {
        this.entityType = entityType;
        return this;
    }

    public Integer getEntityUserId() {
        return entityUserId;
    }

    public Event setEntityUserId(Integer entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Event setData(String key,Object value) {
        this.data.put(key, value);
        return this;
    }
}
