import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import java.util.UUID;

@Controller
public class DocumentVerificationController {
    private final String UPLOAD_DIRECTORY = "uploads/";

    @PostMapping("/verify")
    @ResponseBody
    public void verifyDocument(@RequestBody DocumentVerificationRequest request) throws MessagingException, IOException {
        // Создание уникального имени файла для сохранения
        String uniqueFileName = UUID.randomUUID().toString() + ".pdf";
        String filePath = UPLOAD_DIRECTORY + uniqueFileName;

        // Упаковка фотографий в один документ
        createDocument(request, filePath);

        // Получение почтового адреса сервисного центра
        String serviceCenterEmail = getServiceCenterEmail(request.getServiceCenter());

        // Отправка документа на почту сервисного центра
        sendEmail(serviceCenterEmail, "Документ для верификации", filePath);
    }

    private void createDocument(DocumentVerificationRequest request, String filePath) throws IOException {
        // Создание временной директории для сохранения фотографий
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Создание PDF документа с фотографиями
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Добавление фотографий в документ
        addPhotosToDocument(request, document);

        document.close();
    }

    private void addPhotosToDocument(DocumentVerificationRequest request, Document document) throws IOException, DocumentException {
        // Добавление фотографий в документ
        // request.getPhotos() - предполагается, что в объекте DocumentVerificationRequest есть список фотографий

        for (String photoPath : request.getPhotos()) {
            File photoFile = new File(photoPath);
            if (photoFile.exists()) {
                Image image = Image.getInstance(photoPath);
                image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                document.add(image);
            }
        }
    }

    private String getServiceCenterEmail(String serviceCenter) {
        // Логика для получения почты сервисного центра
        // Возвращаем почтовый адрес в зависимости от сервисного центра
    }

    private void sendEmail(String toEmail, String subject, String attachmentPath) throws MessagingException, IOException {
        // Конфигурация настроек почтового клиента
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Аутентификация почтового клиента
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("your-email@gmail.com", "your-password");
            }
        });

        // Создание сообщения
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("your-email@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);

        // Создание вложения (документа)
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachmentPath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName("verification_document.pdf");

        // Создание тела письма и добавление вложения
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        // Отправка сообщения
        Transport.send(message);

        // Удаление временного файла
        Files.deleteIfExists(new File(attachmentPath).toPath());
    }
}
