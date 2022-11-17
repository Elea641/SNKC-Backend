package projet.wcs.starter.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import projet.wcs.starter.models.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Auction> auctions  = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Sneakers> sneakers = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> userRooms = new ArrayList<>();

    @ManyToMany(mappedBy = "attendees")
    private List<Room> attendingRooms = new ArrayList<>();

    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> winningRooms = new ArrayList<>();

    @NotNull(message = "User roles can't be null")
    @ManyToMany
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() { }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public List<Sneakers> getSneakers() {
        return sneakers;
    }

    public void setSneakers(List<Sneakers> sneakers) {
        this.sneakers = sneakers;
    }

    public List<Room> getAttendingRooms() {
        return userRooms;
    }

    public void setAttendingRooms(List<Room> rooms) {
        this.attendingRooms = rooms;
    }

    public List<Room> getUserRooms() {
        return userRooms;
    }

    public void setUserRooms(List<Room> userRooms) {
        this.attendingRooms = userRooms;
    }

    public List<Room> getWinningRooms() {
        return winningRooms;
    }

    public void setWinningRooms(List<Room> winningRooms) {
        this.winningRooms = winningRooms;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}