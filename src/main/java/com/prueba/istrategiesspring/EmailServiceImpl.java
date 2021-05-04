package com.prueba.istrategiesspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender javaMailSender;

    //prueba
    public void emailActivarCuenta(String destinatario, String tema, String texto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplay@spring.com");
        message.setTo(destinatario);
        message.setSubject(tema);
        message.setText(texto);
        javaMailSender.send(message);
    }
}
