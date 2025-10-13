package com.git.luisdeveloper.wargames_tournament.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(unique=true, nullable = false, length = 100)
    private String name;

    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate beginDate;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate endDate;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String location;

    @PositiveOrZero
    @Column(nullable = false)
    private double prize;

    @PositiveOrZero
    @Column(nullable = false)
    private double entryPrice;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "tournament_players",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> competitors = new ArrayList<>();

    @OneToMany(mappedBy="tournament" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Round> rounds = new ArrayList<>();

    public Tournament() {
    }

    public Tournament(String name, LocalDate beginDate, LocalDate endDate, String location, double entryPrize) {
        this();
    	this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.location = location;
        this.entryPrice = entryPrize;
    }
    
    public Tournament(Long id, String name, LocalDate beginDate, LocalDate endDate, String location, double prize, double entryPrize) {
        this();
    	this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.location = location;
        this.entryPrice = entryPrize;
        this.prize = prize;
        
    }
    
    

    // 🔹 Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public double getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(double entryPrize) {
        this.entryPrice = entryPrize;
    }

    public List<Player> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(List<Player> competitors) {
        this.competitors = competitors;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
    
    public void addRound(Round round) {
    	this.rounds.add(round);
    	round.setTournament(this);
    }
}