package project.tastyfood.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLoginBindingModel {
    private String email;
    private String password;

    public UserLoginBindingModel() {
    }

    @Email(message = "Въведи валиден имейл.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Length(min=3, message = "Паролата трябва да бъде поне 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
