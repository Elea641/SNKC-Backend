package projet.wcs.starter.dto;

import projet.wcs.starter.models.enums.StateOfWearType;

import java.util.List;

public class SneakersDto {
    private Integer id;

    private String uri;

    private String brand;

    private String model;

    private int size;

    private StateOfWearType stateOfWear;

    private Integer userId;

    private List<Integer> pictures;

    private int follows;

//    private String dateOfPurchase;

    private String mainColor;

//    private boolean authentification;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return "/sneakers/".concat(String.valueOf(this.id));
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public StateOfWearType getStateOfWear() {
        return stateOfWear;
    }

    public void setStateOfWear(StateOfWearType stateOfWear) {
        this.stateOfWear = stateOfWear;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getPictures() {
        return pictures;
    }

    public void setPictures(List<Integer> pictures) {
        this.pictures = pictures;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

//    public String getDateOfPurchase() {
//        return dateOfPurchase;
//    }
//
//    public void setDateOfPurchase(String dateOfPurchase) {
//        this.dateOfPurchase = dateOfPurchase;
//    }
//
    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }
//
//    public boolean isAuthentification() {
//        return authentification;
//    }
//
//    public void setAuthentification(boolean authentification) {
//        this.authentification = authentification;
//    }

}
