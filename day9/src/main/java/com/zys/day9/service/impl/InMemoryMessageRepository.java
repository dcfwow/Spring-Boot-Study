package com.zys.day9.service.impl;

import com.zys.day9.model.Message;
import com.zys.day9.service.MessageRepositry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service("messageRepositry")
public class InMemoryMessageRepository implements MessageRepositry {
    //对长整形进行原子操作，保证线程安全
    private static AtomicLong counter = new AtomicLong();
    //使用ConcurrentHashMap模拟对Message对象的增删改查
    private final ConcurrentHashMap<Long, Message> messages = new ConcurrentHashMap<Long, Message>();

    /**
     * 查询所有用户，将Map中的所有信息全部返回
     * @return
     */
    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<Message>(this.messages.values());
        return messages;
    }

    /**
     * 保存消息，需要判断是否存在id，如果没有需要，需要使用AtomicLong获取一个不重复的id
     * @param message
     * @return
     */
    @Override
    public Message save(Message message) {
        Long id = message.getId();
        if(id == null){
            id = counter.incrementAndGet();
            message.setId(id);
        }
        this.messages.put(id,message);
        return message;
    }

    /**
     * 更新消息
     * @param message
     * @return
     */
    @Override
    public Message update(Message message) {
        this.messages.put(message.getId(),message);
        return message;
    }

    /**
     * 更新Text字段
     * @param message
     * @return
     */
    @Override
    public Message updateText(Message message) {
        Message msg = this.messages.get(message.getId());
        msg.setText(message.getText());
        this.messages.put(msg.getId(),msg);
        return msg;
    }

    /**
     * 查找消息
     * @param id
     * @return
     */
    @Override
    public Message findMessage(Long id) {
        return this.messages.get(id);
    }

    /**
     * 删除消息
     * @param id
     * @return
     */
    @Override
    public Message deleteMessage(Long id) {
        return this.messages.remove(id);
    }
}
