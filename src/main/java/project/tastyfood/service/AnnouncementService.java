package project.tastyfood.service;

import project.tastyfood.model.binding.AnnouncementAddBindingModel;

import java.util.List;

public interface AnnouncementService {
    List<AnnouncementAddBindingModel>findAll();
    void cleanUpOldAnnouncements();
    void createOrUpdateAnnouncement(AnnouncementAddBindingModel announcementAddBindingModel);
    void deleteAll();
}
