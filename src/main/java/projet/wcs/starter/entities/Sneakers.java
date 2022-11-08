package projet.wcs.starter.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import projet.wcs.starter.models.enums.ColorType;
import projet.wcs.starter.models.enums.StateOfWearType;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sneakers")
public class Sneakers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private int size;

    @NotNull
    private StateOfWearType stateOfWear;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "sneakers")
    private List<Picture> pictures;

    private Date createdDate = new Date();

    private Date updatedDate = new Date();

    private int follows = 0;

    private Date dateOfPurchase;

    private ColorType mainColor;

    private boolean authentification;

    public Sneakers() {}

    public Sneakers(String brand, String model, int size) {
        this.brand = brand;
        this.model = model;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public ColorType getMainColor() {
        return mainColor;
    }

    public void setMainColor(ColorType mainColor) {
        this.mainColor = mainColor;
    }

    public boolean isAuthentification() {
        return authentification;
    }

    public void setAuthentification(boolean authentification) {
        this.authentification = authentification;
    }
}
