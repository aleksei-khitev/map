package ru.akhitev.rp.map.entity;

import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "Star_System")
@SequenceGenerator(name = "seq", initialValue = 20)
public class StarSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @ManyToOne @JoinColumn(name = "statehood_id", nullable = false)
    @NonNull
    private StateHood statehood;

    @Column(name = "coordinateX", nullable = false)
    @NonNull
    private Double coordinateX;

    @Column(name = "coordinateY", nullable = false)
    @NonNull
    private Double coordinateY;

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

    public StateHood getStatehood() {
        return statehood;
    }

    public void setStatehood(StateHood statehood) {
        this.statehood = statehood;
    }

    public Double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Double coordinateY) {
        this.coordinateY = coordinateY;
    }
}
