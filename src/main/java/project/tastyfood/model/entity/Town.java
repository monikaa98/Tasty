package project.tastyfood.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="towns")
public class Town extends BaseEntity {
private String name;
private Picture picture;

    public Town() {
    }

    @Column(name="name",nullable = false,unique = true)
    @Length(min=3,message ="Името трябва да бъде поне 3 символа.")
    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

}
