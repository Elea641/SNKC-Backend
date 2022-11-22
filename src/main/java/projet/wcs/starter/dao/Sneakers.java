package projet.wcs.starter.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import projet.wcs.starter.models.enums.ColorType;
import projet.wcs.starter.models.enums.StateOfWearType;

import java.nio.charset.StandardCharsets;
import java.util.Date;

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

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] picture;

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

    public String setStateOfWear(StateOfWearType stateOfWear) {
        switch(stateOfWear) {
            case NEUF:
                return "Neuf";

            case TRES_BON_ETAT:
                return "Très bon état";

            case BON_ETAT:
                return "Bon état";

            case ETAT_MOYEN:
                return "Etat moyen";

            case USE:
                return "Usé";

            default:
                throw new Error("StateOfWear not recognized");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getPicture() {
        return picture;
    }

    public String getPictureString() {
        return picture != null ? new String(picture, StandardCharsets.UTF_8) : null;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void setPictureString(String picture) {
        this.picture = picture.getBytes();
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

    public String setMainColor(ColorType mainColor) {
        switch(mainColor) {

            case WHITE:
                return "White";

            case BLACK:
                return "Black";

            case RED:
                return "Red";

            case BLUE:
                return "Blue";

            case GREEN:
                return "Green";

            case PURPLE:
                return "Purple";

            case YELLOW:
                return "Yellow";

            case ORANGE:
                return "Orange";

            case PINK:
                return "Pink";

            case BROWN:
                return "Brown";

            case GREY:
                return "Grey";

            case MULTI:
                return "Multi";

            case OTHER:
                return "Other";

            default:
                throw new Error("Colors not recognized");
        }
    }
}
