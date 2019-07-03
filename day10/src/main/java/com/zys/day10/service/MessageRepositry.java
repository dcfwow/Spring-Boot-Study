package com.zys.day10.service;

import com.zys.day10.model.Message;

import java.util.List;

public interface MessageRepositry {
    List<Message> findAll();
    Message save(Message message);
    Message update(Message message);
    Message updateText(Message message);
    Message findMessage(Long id);
    Message deleteMessage(Long id);
}
