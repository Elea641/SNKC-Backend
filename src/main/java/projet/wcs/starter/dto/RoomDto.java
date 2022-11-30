package projet.wcs.starter.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class RoomDto {
    private Integer id;

    private String uri;

    private Integer ownerId;

    private Integer sneakersId;

    private int initialPrice;

    private Integer winnerId;

    private LocalDateTime startDate;

    private Date endDate;

    private List<Integer> auctionsId;

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getAuctionsId() {
        return auctionsId;
    }

    public void setAuctionsId(List<Integer> auctions) {
        this.auctionsId = auctions;
    }
}