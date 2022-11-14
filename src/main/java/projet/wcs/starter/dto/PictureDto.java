package projet.wcs.starter.dto;

public class PictureDto {
    private Integer id;
    private String url;
    private int sneakersId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSneakersId() {
        return sneakersId;
    }

    public void setSneakersId(int sneakers) {
        this.sneakersId = sneakers;
    }
}
