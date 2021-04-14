package project.tastyfood.model.binding;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

public class AddressAddBindingModel {
    private String address;
    private String phoneNumber;

    public AddressAddBindingModel() {
    }

    @Length(min=3, message = "Адресът трябва да бъде поне 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Length(min = 9, message = "Телефонният номер трябва да бъде поне 9 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
