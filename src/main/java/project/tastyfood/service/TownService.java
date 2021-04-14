package project.tastyfood.service;



import project.tastyfood.model.service.PictureServiceModel;
import project.tastyfood.model.service.TownServiceModel;
import project.tastyfood.model.view.TownViewModel;

import java.util.List;

public interface TownService {
    TownServiceModel findById(String id);
    void addTown(TownServiceModel townServiceModel, PictureServiceModel pictureServiceModel);
    List<TownViewModel>getAllTowns();
}
