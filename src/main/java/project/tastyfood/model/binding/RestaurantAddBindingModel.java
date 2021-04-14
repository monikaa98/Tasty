package project.tastyfood.model.binding;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RestaurantAddBindingModel {
    private String name;
    private String address;

    public RestaurantAddBindingModel() {
    }

    @Length(min=3,message = "Името на ресторанта трябва да бъде поне 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 3,message = "Адресът трябва да бъде повече от 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
