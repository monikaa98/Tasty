package project.tastyfood.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class TownAddBindingModel {
    private String name;

    public TownAddBindingModel() {
    }

    @Length(min=3,message = "Името на града трябва да бъде поне 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
