package project.tastyfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tastyfood.model.entity.Announcement;

import java.time.Instant;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement,String> {
    void deleteByUpdatedOnBefore(Instant before);
}
