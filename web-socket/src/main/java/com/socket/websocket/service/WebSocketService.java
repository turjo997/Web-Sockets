package com.socket.websocket.service;

import com.socket.websocket.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;


    @Autowired
    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate, NotificationService notificationService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyFrontend(final String message){
        ResponseMessage responseMessage = new ResponseMessage(message);
        notificationService.sendGlobalNotification();
        simpMessagingTemplate.convertAndSend("/topic/messages" , responseMessage);
    }

    public void notifyUser(final String id , final String message){
        ResponseMessage responseMessage = new ResponseMessage(message);
        notificationService.sendPrivateNotification(id);
        simpMessagingTemplate.convertAndSendToUser(id , "/topic/private-messages" , responseMessage);
    }




}
