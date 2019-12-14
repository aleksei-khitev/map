package ru.akhitev.rp.fleet.ship.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "Ship")
@SequenceGenerator(name = "seq", initialValue = 20)
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "length", nullable = false)
    private Double length;

    @Column(name = "crew", nullable = false)
    private Integer crew;

    @Column(name = "minimal_crew")
    private Integer minimalCrew;

    @Column(name = "troops")
    private Integer troops;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "link", nullable = false)
    private String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getCrew() {
        return crew;
    }

    public void setCrew(Integer crew) {
        this.crew = crew;
    }

    public Integer getMinimalCrew() {
        return minimalCrew;
    }

    public void setMinimalCrew(Integer minimalCrew) {
        this.minimalCrew = minimalCrew;
    }

    public Integer getTroops() {
        return troops;
    }

    public void setTroops(Integer troops) {
        this.troops = troops;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
