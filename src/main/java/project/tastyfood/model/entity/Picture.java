package project.tastyfood.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name="pictures")
@Transactional
public class Picture extends BaseEntity {
    private String pictureUrl;

    public Picture() {
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

}
