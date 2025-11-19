package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.ServicioMensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class ControladorChat {

    private final SimpMessagingTemplate messagingTemplate;
    private final ServicioMensaje servicioMensaje;

    @Autowired
    public ControladorChat(SimpMessagingTemplate messagingTemplate, ServicioMensaje servicioMensaje) {
        this.messagingTemplate = messagingTemplate;
        this.servicioMensaje = servicioMensaje;
    }


    @MessageMapping("/chat.private.{username}")
    public void sendPrivateMessage(@Payload MensajeDTO chatMessage, @DestinationVariable String username) {
        servicioMensaje.guardarMensaje(chatMessage.getContenido(), chatMessage.getRemitente(), username);

        messagingTemplate.convertAndSendToUser(username, "/queue/reply", chatMessage);
    }
}