package project.tastyfood.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="orders")
public class Order extends BaseEntity{
    private String address;
    private String phoneNumber;
    private String status;
    private Double totalPrice;
    private UserEntity userEntity;
    private List<Meal> mealList;

    public Order() {
    }

    @Column(name="address",nullable=false)
    @Length(min=3, message = "Адресът трябва да бъде поне 3 символа")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="phone_number",nullable = false)
    @Length(min = 9, message = "Телефонният номер трябва да бъде поне 9 символа")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name="status",nullable = false)
    @Length(min=3, message = "Status must be more than 3 symbols")
    @NotBlank
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="total_price",nullable = false)
    @Min(value = 2)
    @NotNull
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @OneToMany
    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
    }
}
