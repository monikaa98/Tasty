package project.tastyfood.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRegisterBindingModel {
    private String  firstName;
    private String  lastName;
    private String  email;
    private String roleName;
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    @Length(min=3, message = "Името трябва да бъде поне 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min=3,message = "Фамилията трябва да бъде поне 3 символа.")
    @NotBlank(message = "Не трябва да бъде празно.")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull(message = "Ти трябва да избереш роля.")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
