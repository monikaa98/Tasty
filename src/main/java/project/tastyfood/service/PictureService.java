package project.tastyfood.service;

import project.tastyfood.model.service.PictureServiceModel;

public interface PictureService {
    PictureServiceModel addPicture(PictureServiceModel pictureServiceModel);
    PictureServiceModel getPicture(String pictureUrl);
}
