package project.tastyfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tastyfood.model.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture,String> {
Picture findByPictureUrl(String pictureUrl);

}
