package project.tastyfood.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
@Transactional
public class UserEntity extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roleName;
    private List<RoleEntity> roles;
    private List<Meal>bag=new ArrayList<>();
    private List<Meal>products=new ArrayList<>();

    public UserEntity() {
    }

    @Column(name="first_name",nullable = false)
    @Length(min=3, message = "Името трябва да бъде поне 3 символа.")
    @NotBlank
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name",nullable = false)
    @Length(min=3,message = "Фамилията трябва да бъде поне 3 символа.")
    @NotBlank
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name="email",nullable = false,unique = true)
    @Email(message = "Въведи валиден имейл.")
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="password",nullable = false)
    @Length(min=3, message = "Паролата трябва да бъде поне 3 символа.")
    @NotBlank
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="user_id")
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    public List<Meal> getBag() {
        return bag;
    }

    public void setBag(List<Meal> bag) {
        this.bag = bag;
    }

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="user_id")
    public List<Meal> getProducts() {
        return products;
    }

    public void setProducts(List<Meal> products) {
        this.products = products;
    }

    @Column(name = "role_name", nullable = false)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
