package projet.wcs.starter.dto;

import jakarta.persistence.Lob;

import java.util.List;

public class UserDto {
    private Integer id;
    private String uri;
    private String email;
    private String username;
    private List<Integer> sneakersId;
    private List<Integer> userRoomsId;
    private List<Integer> attendingRoomsId;
    private List<Integer> auctionsId;

    @Lob
    private String picture;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return "/users/" .concat(String.valueOf(this.id));
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userUsername) {
        this.username = userUsername;
    }

    public List<Integer> getSneakersId() {
        return sneakersId;
    }

    public void setSneakersId(List<Integer> sneakersId) {
        this.sneakersId = sneakersId;
    }

    public List<Integer> getUserRoomsId() {
        return userRoomsId;
    }

    public void setUserRoomsId(List<Integer> userRoomsId) {
        this.userRoomsId = userRoomsId;
    }

    public List<Integer> getAttendingRoomsId() {
        return attendingRoomsId;
    }

    public void setAttendingRoomsId(List<Integer> attendingRoomsId) {
        this.attendingRoomsId = attendingRoomsId;
    }

    public List<Integer> getAuctionsId() {
        return auctionsId;
    }

    public void setAuctionsId(List<Integer> auctionsId) {
        this.auctionsId = auctionsId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
