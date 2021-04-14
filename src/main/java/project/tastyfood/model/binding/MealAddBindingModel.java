package project.tastyfood.model.binding;

import org.hibernate.validator.constraints.Length;
import project.tastyfood.model.entity.enums.CategoryName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MealAddBindingModel {
    private String name;
    private Integer grams;
    private String ingredients;
    private Double price;
    private CategoryName category;

    public MealAddBindingModel() {
    }

    @Length(min=3, message = "Името на ястието трябва да бъде поне 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Не трябва да бъде празно.")
    @Min(value = 1,message = "Грамажът трябва да бъде поне 1.")
    public Integer getGrams() {
        return grams;
    }

    public void setGrams(Integer grams) {
        this.grams = grams;
    }

    @Length(min=3, message = "Съставките трябва да бъдат поне 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @NotNull(message = "Не трябва да бъде празно.")
    @Min(value = 1,message = "Цената трябва да бъде поне 1.")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @NotNull(message = "Ти трябва да избереш категория.")
    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }
}
