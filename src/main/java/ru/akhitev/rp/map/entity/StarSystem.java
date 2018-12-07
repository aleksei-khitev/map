package ru.akhitev.rp.map.entity;

import javax.persistence.*;

@Entity
@Table(name = "Star_System")
@SequenceGenerator(name = "seq", initialValue = 20)
public class StarSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne @JoinColumn(name = "statehood_id", nullable = false)
    private StateHood statehood;

    @ManyToOne @JoinColumn(name = "super_statehood_id")
    private SuperStateHood superStatehood;

    @Column(name = "system_importance", nullable = false)
    private StarSystemImportance systemImportance;

    @Column(name = "coordinateX", nullable = false)
    private Double coordinateX;

    @Column(name = "coordinateY", nullable = false)
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

    public SuperStateHood getSuperStatehood() {
        return superStatehood;
    }

    public void setSuperStatehood(SuperStateHood superStatehood) {
        this.superStatehood = superStatehood;
    }

    public StarSystemImportance getSystemImportance() {
        return systemImportance;
    }

    public void setSystemImportance(StarSystemImportance systemImportance) {
        this.systemImportance = systemImportance;
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

    @Override
    public String toString() {
        final String format = "Система %s [%.2f, %.2f]\nПринадлежность: %s";
        StringBuilder builder = new StringBuilder(String.format(format, name, coordinateX, coordinateY, statehood.getName()));
        if (superStatehood != null) {
            builder.append(String.format("\nСверхобъединение: %s", superStatehood.getName()));
        }
        return builder.toString();
    }

    public enum StarSystemImportance {
        SUPER_STATEHOOD_CAPITAL, STATEHOOD_CAPITAL, IMPORTANT_SYSTEM, SYSTEM;
    }
}
