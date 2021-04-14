package project.tastyfood.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="meals")
@Transactional
public class Meal extends BaseEntity{
private String name;
private Integer grams;
private String ingredients;
private Double price;
private Picture picture;
private Restaurant restaurant;
private Category category;

    public Meal() {
    }

    @NotBlank
    @Column(name="name",nullable = false,unique = true)
    @Length(min=3, message = "Името на ястието трябва да бъде поне 3 символа.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Column(name="grams",nullable = false)
    @Min(value = 1,message = "Грамажът трябва да бъде поне 1.")
    public Integer getGrams() {
        return grams;
    }

    public void setGrams(Integer grams) {
        this.grams = grams;
    }

    @NotBlank
    @Column(name="ingredients",nullable = false)
    @Length(min=3, message = "Съставките трябва да бъдат поне 3 символа.")
    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @NotNull
    @Column(name="price",nullable = false)
    @Min(value = 1,message = "Цената трябва да бъде поне 1.")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToOne
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @ManyToOne
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @ManyToOne
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
