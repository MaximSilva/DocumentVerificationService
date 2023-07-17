package com.example.demo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class DocumentVerificationController {
    private final String UPLOAD_DIRECTORY = "uploads/";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public DocumentVerificationController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/verify")
    public void verifyDocument(@RequestBody DocumentVerificationRequest request) throws IOException, MessagingException, DocumentException, jakarta.mail.MessagingException {
        // Создание уникального имени файла для сохранения
        String uniqueFileName = UUID.randomUUID().toString() + ".pdf";
        String filePath = UPLOAD_DIRECTORY + uniqueFileName;

        // Упаковка фотографий в один документ
        createDocument(request.getPhotos(), filePath);

        // Получение почтового адреса сервисного центра
        String serviceCenterEmail = getServiceCenterEmail(request.getServiceCenter());

        // Отправка документа на почту сервисного центра
        sendEmail(serviceCenterEmail, "Документ для верификации", filePath);
    }

    private void createDocument(List<String> photoPaths, String filePath) throws IOException, DocumentException {
        // Создание временной директории для сохранения фотографий
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Создание PDF документа с фотографиями
        // Вам нужно использовать библиотеку, такую как iText, для создания PDF и добавления фотографий в него.
        // В данном примере предполагается, что у вас уже есть реализация для создания PDF-документа.

        // Пример использования iText для создания PDF и добавления фотографий:
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        for (String photoPath : photoPaths) {
            File photoFile = new File(photoPath);
            if (photoFile.exists()) {
                // Добавление фотографии в PDF-документ
                // ...
            }
        }

        document.close();
    }

    private String getServiceCenterEmail(String serviceCenter) {
        // Логика для получения почты сервисного центра
        // Возвращаем почтовый адрес в зависимости от сервисного центра
        return serviceCenter;
    }

    private void sendEmail(String toEmail, String subject, String attachmentPath) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("spimax002@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText("Please find the document attached.");

        File attachment = new File(attachmentPath);
        helper.addAttachment("verification_document.pdf", attachment);

        javaMailSender.send(message);
    }
}
