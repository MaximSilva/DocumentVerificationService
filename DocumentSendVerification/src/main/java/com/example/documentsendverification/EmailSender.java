package com.example.documentsendverification;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailSender {

    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmailWithAttachments(String toEmail, String subject, String body, MultipartFile[] files) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body);

            List<File> tempFiles = new ArrayList<>();
            for (MultipartFile file : files) {
                File tempFile = File.createTempFile("temp", file.getOriginalFilename());
                file.transferTo(tempFile);
                helper.addAttachment(file.getOriginalFilename(), tempFile);
                tempFiles.add(tempFile);
            }

            mailSender.send(message);

            // Delete temporary files after sending the email
            for (File tempFile : tempFiles) {
                tempFile.delete();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
