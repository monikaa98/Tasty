package project.tastyfood.model.service;

import org.springframework.web.multipart.MultipartFile;
import project.tastyfood.model.entity.Picture;

public class PictureServiceModel {
    private MultipartFile file;
    private Picture picture;

    public PictureServiceModel() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
