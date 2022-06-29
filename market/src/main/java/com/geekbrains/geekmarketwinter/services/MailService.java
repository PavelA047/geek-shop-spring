package com.geekbrains.geekmarketwinter.services;

import contract.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Objects;

//package com.geekbrains.geekmarketwinter.services;
//
//import contract.entities.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailException;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

@Service
public class MailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

//    private JavaMailSender sender;
//    private MailMessageBuilder messageBuilder;
//    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//    @Autowired
//    public void setSender(JavaMailSender sender) {
//        this.sender = sender;
//    }
//
//    @Autowired
//    public void setMessageBuilder(MailMessageBuilder messageBuilder) {
//        this.messageBuilder = messageBuilder;
//    }
//
//    public void sendMail(String email, String subject, String text) {
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
//
//        try {
//            helper.setTo(email);
//            helper.setText(text, true);
//            helper.setSubject(subject);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            executorService.submit(() -> sender.send(message));
//        } catch (MailException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sendOrderMail(Order order) {
//        sendMail(order.getUser().getEmail(), String.format("Заказ %d%n отправлен в обработку", order.getId()), messageBuilder.buildOrderEmail(order));

    public void sendEmail() {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom("zzzzfogzzzz@gmail.com");
            helper.setTo("zzzzfogzzzz@gmail.com");
            helper.setText("Thank you!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    public void sendEmailWithAttachment(Order order) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setFrom("zzzzfogzzzz@gmail.com");
        helper.setTo("zzzzfogzzzz@gmail.com");
        helper.setSubject("Your order is confirmed");
//        helper.setText("<h1>Check attachment for image!</h1>", true);
        helper.setText(
                "cost of order: " + order.getPrice() + "\n" +
                        "address of order: " + order.getDeliveryAddress().getAddress() + "\n" +
                        "date of order: " + order.getDeliveryDate()
        );

//        FileSystemResource file = new FileSystemResource("C:\\Users\\Павлик\\Desktop\\geek-shop-spring\\images\\ddc88251-f6f1-4f6f-b3d6-03b37e2b95e1nout.jpg");
//        helper.addAttachment(file.getFilename(), file);

        javaMailSender.send(msg);

    }
}
