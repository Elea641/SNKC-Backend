package projet.wcs.starter.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToOne
    @JoinColumn(name = "sneakers_id")
    private Sneakers sneakers;

    @NotNull
    private int initialPrice;

    @NotNull
    private LocalDateTime startDate = LocalDateTime.now();

    @NotNull
    private Date endDate = getAWeekLater();

    @ManyToMany
    @JoinTable(name = "room_user",
            joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> attendees = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    private User winner;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Auction> auctions = new ArrayList<>();

    public Room() {
    }

    public Room(User owner, Sneakers sneakers, int initialPrice) {
        this.owner = owner;
        this.sneakers = sneakers;
        this.initialPrice = initialPrice;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Sneakers getSneakers() {
        return sneakers;
    }

    public void setSneakers(Sneakers sneakers) {
        this.sneakers = sneakers;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
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

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public User getWinner() {
        if(isClosed() && this.auctions.size() > 0) {
                Auction winningAuction = this.auctions.stream().max(Comparator.comparing(
                        Auction::getOffer)).get();
                return winningAuction.getUser();
        } else if (isClosed() && this.auctions.size() == 0) {
            return this.owner;
        }
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    private Date getAWeekLater() {
        Date out = Date.from(this.startDate.atZone(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();
        c.setTime(out);

        c.add(Calendar.DATE, 7);

        return c.getTime();
    }

    private boolean isClosed() {
        long now = new Date().getTime();
        long endDate = this.endDate.getTime();
        return now >= endDate;
    }
}
