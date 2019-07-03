package com.zys.day10.controller;

import com.zys.day10.config.BaseResult;
import com.zys.day10.model.Message;
import com.zys.day10.service.impl.InMemoryMessageRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Api作用在Controller类上，作为Swagger文档资源，将一个Controller类标注为一个Swagger资源。
@Api(value = "消息",description = "消息操作 API",position = 100,protocols = "http")
@RequestMapping("/")
@RestController
public class MessageController {
    @Autowired
    private InMemoryMessageRepository messageRepository;

    //获取所有的消息
    @GetMapping(value = "messages")
    //response返回的对象
    @ApiOperation(
            value = "消息列表",
            notes = "完整的消息列表",
            produces = "application/json,application/xml",
            consumes = "application/json,application/xml",
            response = List.class
    )
    public List<Message> list(){
        return messageRepository.findAll();
    }

    //创建一个消息
    @PostMapping(value = "message")
    @ApiOperation(value = "添加消息",notes = "根据参数创建消息")
    /**
     * @ApiImplicitParams 用于描述方法的返回信息
     * @ApiImplicitParam 用来描述具体某一个参数的信息，包括参数的名称、类型、限制等信息
     * name接收参数名，value接收参数的意义描述，required参数是否必填，dataType参数的数据类型(只做标志性说明，无实际验证效果)
     * paramType查询参数类型:path以地址形式提交，query直接跟参数完成自动映射赋值，body以流的形式提交(仅支持POST)，header参数在request headers里提交，form以form表单提交(仅支持post)
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "消息 ID",required = true,dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name = "text",value = "正文",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "summary",value = "摘要",required = true,dataType = "String",paramType = "query"),
    })
    public Message create(Message message){
        return messageRepository.save(message);
    }

    //使用Put请求修改一个消息
    @PutMapping(value = "message")
    @ApiOperation(value = "修改消息",notes = "根据参数修改消息")
    /**
     * @ApiResponses 主要封装方法的返回信息
     * @ApiResponse 定义返回的具体信息包括返回码、返回信息等
     * code为http的状态码，message为描述信息，response为默认响应类void，reference为参考，responseHeaders为封装返回信息，responseContainer为字符串
     */
    @ApiResponses({
            @ApiResponse(code = 100,message = "请求参数有误"),
            @ApiResponse(code = 101,message = "未授权"),
            @ApiResponse(code = 103,message = "禁止访问"),
            @ApiResponse(code = 104,message = "请求路径不存在"),
            @ApiResponse(code = 200,message = "服务器内部错误")
    })
    public Message modify(Message message){
        return messageRepository.update(message);
    }

    //修改一个消息的text字段
    @PatchMapping(value = "/message/text")
    public BaseResult<Message> patch(Message message){
        Message result = messageRepository.updateText(message);
        return BaseResult.successWithData(result);
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
