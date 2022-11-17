package projet.wcs.starter.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import projet.wcs.starter.models.enums.ColorType;
import projet.wcs.starter.models.enums.StateOfWearType;

import java.util.ArrayList;
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
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "stateOfWear")
    private StateOfWearType stateOfWear;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "sneakers")
    private List<Picture> pictures;

    private Date createdDate = new Date();

    private Date updatedDate = new Date();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "mainColor")
    private ColorType mainColor;

    public Sneakers() {}

    public Sneakers(String brand, String model, int size) {
        this.brand = brand;
        this.model = model;
        this.size = size;
    }

    public Sneakers(int id) {
        this.id = id;
    }

    public Integer getId() {
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
        if(pictures != null) {
            return pictures;
        }
        return new ArrayList<>();
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
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

    public ColorType getMainColor() {

            return mainColor;
    }

    public void setMainColor(ColorType mainColor) {
        this.mainColor = mainColor;
    }
}
