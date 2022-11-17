package projet.wcs.starter.dto;

import projet.wcs.starter.models.enums.ColorType;
import projet.wcs.starter.models.enums.StateOfWearType;

import java.util.List;

public class SneakersDto {
    private Integer id;

    private String uri;

    private String brand;

    private String model;

    private int size;

    private String stateOfWear;

    private Integer userId;

    private List<Integer> picturesId;

    private String mainColor;


    public void setUri(String uri) {
        this.uri = uri;
    }

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

    public String getStateOfWear() {
        switch(this.stateOfWear) {
            case StateOfWearType.NEUF:
                return "Neuf";

            case StateOfWearType.TRES_BON_ETAT:
                return "Très bon état";

            case StateOfWearType.BON_ETAT:
                return "Bon état";

            case StateOfWearType.ETAT_MOYEN:
                return "Etat moyen";

            case StateOfWearType.USE:
                return "Usé";

            default:
                throw new Error("StateOfWear not recognized");
        }
    }
    }

    public void setStateOfWear(String stateOfWear) {
        this.stateOfWear = stateOfWear;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getPicturesId() {
        return picturesId;
    }

    public void setPicturesId(List<Integer> pictures) {
        this.picturesId = pictures;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

}
