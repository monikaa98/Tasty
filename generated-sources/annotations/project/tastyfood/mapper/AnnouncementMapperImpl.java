package project.tastyfood.mapper;

import javax.annotation.processing.Generated;
import project.tastyfood.model.binding.AnnouncementAddBindingModel;
import project.tastyfood.model.entity.Announcement;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-13T15:40:09+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class AnnouncementMapperImpl implements AnnouncementMapper {

    @Override
    public Announcement mapAnnouncementBindingToEntity(AnnouncementAddBindingModel announcementAddBindingModel) {
        if ( announcementAddBindingModel == null ) {
            return null;
        }

        Announcement announcement = new Announcement();

        announcement.setId( announcementAddBindingModel.getId() );
        announcement.setCreatedOn( announcementAddBindingModel.getCreatedOn() );
        announcement.setUpdatedOn( announcementAddBindingModel.getUpdatedOn() );
        announcement.setTitle( announcementAddBindingModel.getTitle() );
        announcement.setDescription( announcementAddBindingModel.getDescription() );

        return announcement;
    }

    @Override
    public AnnouncementAddBindingModel mapAnnouncementToBindingModel(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }

        AnnouncementAddBindingModel announcementAddBindingModel = new AnnouncementAddBindingModel();

        announcementAddBindingModel.setId( announcement.getId() );
        announcementAddBindingModel.setCreatedOn( announcement.getCreatedOn() );
        announcementAddBindingModel.setUpdatedOn( announcement.getUpdatedOn() );
        announcementAddBindingModel.setTitle( announcement.getTitle() );
        announcementAddBindingModel.setDescription( announcement.getDescription() );

        return announcementAddBindingModel;
    }
}
