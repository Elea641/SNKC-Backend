package projet.wcs.starter.dto;

import java.time.LocalDateTime;

public class AuctionDto {

    private Integer id;

    private Integer userId;

    private Integer roomId;

    private int offer;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user) {
        this.userId = user;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer room) {
        this.roomId = room;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

}
