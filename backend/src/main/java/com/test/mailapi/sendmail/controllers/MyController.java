package com.test.mailapi.sendmail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.mailapi.sendmail.model.EmailMessage;
import com.test.mailapi.sendmail.services.MyService;

@RestController
public class MyController {
    @Autowired
    private  MyService myService;

    @GetMapping("/home")
    public String home() {
        return "This is home page;";
    }
    @PostMapping("/sendemail")
    public ResponseEntity<?> sendMail(@RequestBody EmailMessage message) {
        // myService.sendEmail(message.getBody(), message.getSubject(), message.getTo());
        // System.out.println("Message: " + message);
        // myService.sendSimpleMail(message);
        myService.sendMailWithAttachment(message);
        return ResponseEntity.ok("Email sent successfully");
    }
}
