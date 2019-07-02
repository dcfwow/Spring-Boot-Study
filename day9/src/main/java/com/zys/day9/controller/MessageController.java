package com.zys.day9.controller;

import com.zys.day9.model.Message;
import com.zys.day9.service.impl.InMemoryMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@RestController
public class MessageController {
    @Autowired
    private InMemoryMessageRepository messageRepository;

    //获取所有的消息
    @GetMapping(value = "messages")
    public List<Message> list(){
        return messageRepository.findAll();
    }

    //创建一个消息
    @PostMapping(value = "message")
    public Message create(Message message){
        return messageRepository.save(message);
    }

    //使用Put请求修改一个消息
    @PutMapping(value = "message")
    public Message modify(Message message){
        return messageRepository.update(message);
    }

    //修改一个消息的text字段
    @PatchMapping(value = "/message/text")
    public Message patch(Message message){
        return messageRepository.updateText(message);
    }

    //查找某个商品
    @GetMapping(value = "message/{id}")
    public Message get(@PathVariable("id") Long id){
        return messageRepository.findMessage(id);
    }

    //删除某个商品
    @DeleteMapping(value = "message/{id}")
    public Message delete(@PathVariable("id") Long id){
        return messageRepository.deleteMessage(id);
    }

}
