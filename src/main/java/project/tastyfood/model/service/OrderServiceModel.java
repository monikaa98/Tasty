package project.tastyfood.model.service;

import java.util.List;

public class OrderServiceModel {
    private String address;
    private String phoneNumber;
    private PictureServiceModel pictureServiceModel;
    private String pictureUrl;
    private List<OrderServiceModel> orderServiceModel;

    public OrderServiceModel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PictureServiceModel getPictureServiceModel() {
        return pictureServiceModel;
    }

    public void setPictureServiceModel(PictureServiceModel pictureServiceModel) {
        this.pictureServiceModel = pictureServiceModel;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<OrderServiceModel> getOrderServiceModel() {
        return orderServiceModel;
    }

    public void setOrderServiceModel(List<OrderServiceModel> orderServiceModel) {
        this.orderServiceModel = orderServiceModel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
