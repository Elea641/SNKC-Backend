package projet.wcs.starter.dto;

import java.util.List;

public class UserDto {
    private Integer id;
    private String uri;
    private String email;
    private List<Integer> sneakersId;
    private List<Integer> userRoomsId;
    private List<Integer> attendingRoomsId;
    private List<Integer> auctionsId;

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
}
