package projet.wcs.starter.dto;

public class RoomDto {
    private Integer id;

    private String uri;

    private Integer ownerId;

    private Integer sneakersId;

    private int initialPrice;

    private Integer winnerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return "/room/".concat(String.valueOf(this.id));
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getSneakersId() {
        return sneakersId;
    }

    public void setSneakersId(Integer sneakersId) {
        this.sneakersId = sneakersId;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
    }
}
