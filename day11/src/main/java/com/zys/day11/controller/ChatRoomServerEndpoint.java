package com.zys.day11.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import static com.zys.day11.utils.WebSocketUtils.ONLINE_USER_SESSIONS;
import static com.zys.day11.utils.WebSocketUtils.sendMessageAll;

@RestController
//监听该地址的WebSocket信息
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);

    //@Onopen表示用户建立连接时触发
    @OnOpen
    public void openSession(@PathParam("username") String username, Session session){
        ONLINE_USER_SESSIONS.put(username, session);
        String message = "欢迎用户["+username+"]来到聊天室！";
        logger.info("用户登录："+message);
        sendMessageAll(message);
    }

    //监听发送消息的事件
    @OnMessage
    public void onMessage(@PathParam("username") String username,String message){
        logger.info("发送消息"+message);
        sendMessageAll("用户["+username+"]:"+message);
    }

    //监听用户断开连接的事件
    @OnClose
    public void onClose(@PathParam("username") String username,Session session){
        //移除当前的session
        ONLINE_USER_SESSIONS.remove(username);
        //通告其他人当前用户已经离开聊天室
        sendMessageAll("用户["+username+"]已经离开聊天室了！");
        try {
            session.close();
        } catch (IOException e) {
            logger.error("onClose Error",e);
        }
    }

    //监听连接异常事件
    @OnError
    public void onError(Session session,Throwable throwable){
        try {
            session.close();
        } catch (IOException e) {
            logger.error("onError Exception",e);
        }
        logger.info("Throwable msg"+throwable.getMessage());
    }
}