package project.tastyfood.model.service;

public class TownServiceModel {
    private String id;
    private String name;
    private PictureServiceModel pictureServiceModel;
    private String pictureUrl;

    public TownServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
