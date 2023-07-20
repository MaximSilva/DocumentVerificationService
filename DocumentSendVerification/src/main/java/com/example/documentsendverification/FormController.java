package com.example.documentsendverification;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class FormController {

    private final EmailSender emailSender;

    public FormController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/submit")
    public String handleSubmit(@RequestParam("front") MultipartFile frontFile,
                               @RequestParam("back") MultipartFile backFile,
                               @RequestParam("series") String series,
                               @RequestParam("number") String number,
                               @RequestParam("date") String date,
                               @RequestParam("issuedBy") String issuedBy,
                               @RequestParam("signature") MultipartFile signatureFile) {

        MultipartFile[] files = {frontFile, backFile, signatureFile};
        emailSender.sendEmailWithAttachments("spimax002@gmail.com", "Заявка на верифікацію", "Дякуємо за заявку!", files);

        return "Документи успішно збережено та відправлено на пошту.";
    }

    private void saveFiles(MultipartFile frontFile, MultipartFile backFile) {

        try {
            byte[] frontFileBytes = frontFile.getBytes();
            byte[] backFileBytes = backFile.getBytes();


            FileOutputStream frontOutputStream = new FileOutputStream("path/to/front/file");
            frontOutputStream.write(frontFileBytes);
            frontOutputStream.close();

            FileOutputStream backOutputStream = new FileOutputStream("path/to/back/file");
            backOutputStream.write(backFileBytes);
            backOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void packFilesToZip(String zipFileName, MultipartFile frontFile, MultipartFile backFile) {

        try {
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            addToZip(zipOut, frontFile, "front.pdf");
            addToZip(zipOut, backFile, "back.pdf");

            zipOut.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToZip(ZipOutputStream zipOut, MultipartFile file, String fileName) throws IOException {
        byte[] bytes = file.getBytes();
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        zipOut.write(bytes);
        zipOut.closeEntry();
    }
}

