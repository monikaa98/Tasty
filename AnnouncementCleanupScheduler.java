package project.tastyfood.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.tastyfood.service.AnnouncementService;

@Component
public class AnnouncementCleanupScheduler {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementCleanupScheduler(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @Scheduled(cron="${tastyfood.clean-up}")
    public void cleanUpOldAnnouncements(){
        announcementService.cleanUpOldAnnouncements();
    }
}