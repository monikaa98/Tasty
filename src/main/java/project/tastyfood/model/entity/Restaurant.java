package project.tastyfood.model.entity;

import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="restaurants")
@Transactional
public class Restaurant extends BaseEntity {
private String name;
private String address;
private UserEntity userEntity;
private Town town;
private Picture picture;

    public Restaurant() {
    }

    @NotBlank
    @Column(name="name",nullable = false,unique = true)
    @Length(min=3, message = "Името трябва да бъде поне 3 символа.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @Column(name="address",nullable = false)
    @Length(min=3, message = "Адресът трябва да бъде поне 3 символа.")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    
    @ManyToOne
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
    
    @ManyToOne
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

}
