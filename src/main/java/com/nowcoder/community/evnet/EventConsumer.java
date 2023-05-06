package com.nowcoder.community.evnet;

import com.alibaba.fastjson2.JSONObject;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Event;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.ElasticsearchService;
import com.nowcoder.community.service.MessageService;
import com.nowcoder.community.util.CommunityConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class EventConsumer implements CommunityConstant {

    @Autowired
    private MessageService messageService;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private ElasticsearchService elasticsearchService;

    /**
     * 消费三个主题的数据
     * @param record
     */
    @KafkaListener(topics = {TOPIC_COMMENT, TOPIC_FOLLOW, TOPIC_LIKE})
    public void handleCommentMessage(ConsumerRecord record) {
        if (record == null || record.value() == null) {
            return;
        }

        Event event = JSONObject.parseObject(record.value().toString(), Event.class);
        if(event == null){
            return;
        }

        //发送站内通知，实际上就是插入到message表中
        //将消息队列中的消息转化为message实体插入数据库中
        Message message = new Message();
        message.setFromId(SYSTEM_USER_ID);          //系统用户：1
        message.setToId(event.getEntityUserId());   // 消息的接收者
        message.setConversationId(event.getTopic());    //主题，业务上就是实体类型
        message.setCreateTime(new Date());

        Map<String, Object> content = new HashMap<>();
        content.put("entityType", event.getEntityType());
        content.put("userId",event.getUserId());
        content.put("entityId", event.getEntityId());

        if(!event.getData().isEmpty()){
            for(Map.Entry<String, Object> entry : event.getData().entrySet()) {
                content.put(entry.getKey(), entry.getValue());
            }
        }
        message.setContent(JSONObject.toJSONString(content));
        messageService.addMessage(message);
    }

    //消费发帖事件
    @KafkaListener(topics = {TOPIC_PUBLISH})
    public void handlePublishMessage(ConsumerRecord record){
        if (record == null || record.value() == null) {
            return;
        }

        Event event = JSONObject.parseObject(record.value().toString(), Event.class);
        if(event == null){
            return;
        }

        DiscussPost post = discussPostService.findDiscussPostById(event.getEntityId());
        elasticsearchService.saveDiscussPost(post);


    }

}
