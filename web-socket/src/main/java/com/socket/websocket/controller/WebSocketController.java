package com.socket.websocket.controller;

import com.socket.websocket.dto.Message;
import com.socket.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;


    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message){
        webSocketService.notifyFrontend(message.getMessageContent());
    }


}
