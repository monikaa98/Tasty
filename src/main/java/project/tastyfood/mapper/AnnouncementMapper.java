package project.tastyfood.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import project.tastyfood.model.binding.AnnouncementAddBindingModel;
import project.tastyfood.model.entity.Announcement;

@Mapper
public interface AnnouncementMapper {
    AnnouncementMapper INSTANCE=Mappers.getMapper(AnnouncementMapper.class);
    Announcement mapAnnouncementBindingToEntity(AnnouncementAddBindingModel announcementAddBindingModel);
    AnnouncementAddBindingModel mapAnnouncementToBindingModel(Announcement announcement);

}
