package com.example.demo;

import java.util.List;

public class DocumentVerificationRequest {
    private String serviceCenter;
    private List<String> photos;

    // Геттеры и сеттеры

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
