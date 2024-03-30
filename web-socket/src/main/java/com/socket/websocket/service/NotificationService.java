package com.socket.websocket.service;

import com.socket.websocket.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendGlobalNotification() {
        ResponseMessage response = new ResponseMessage("Global notification");
        simpMessagingTemplate.convertAndSend("/topic/global-notifications", response);
    }

    public void sendPrivateNotification(final String userId){
        ResponseMessage response = new ResponseMessage("Private notification");
        simpMessagingTemplate.convertAndSendToUser(userId,"/topic/private-notifications" , response);
    }

}
