package ru.akhitev.rp.star_system.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.akhitev.rp.state_hood.entity.StateHood;
import ru.akhitev.rp.super_state_hood.entity.SuperStateHood;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Star_System")
@SequenceGenerator(name = "seq", initialValue = 20)
public class StarSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne @JoinColumn(name = "statehood_id", nullable = false)
    private StateHood statehood;

    @ManyToOne @JoinColumn(name = "super_statehood_id")
    private SuperStateHood superStatehood;

    @OneToMany(mappedBy = "starSystem",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Planet> planets;

    @OneToMany(mappedBy = "starSystem",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<StarSystemFleetUnit> starSystemFleetUnits;

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

    public Set<Planet> getPlanets() {
        return planets;
    }

    public Set<StarSystemFleetUnit> getStarSystemFleetUnits() {
        return starSystemFleetUnits;
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

    public String toHtmlString() {
        final String format = "<h3>Система</h3> <b>%s</b> [%.2f, %.2f]<h4>Принадлежность:</h4> %s";
        StringBuilder builder = new StringBuilder(String.format(format, name, coordinateX, coordinateY, statehood.getName()));
        if (superStatehood != null) {
            builder.append(String.format("<h4>Сверхобъединение:</h4> %s", superStatehood.getName()));
        }
        builder.append((starSystemFleetUnits !=null && starSystemFleetUnits.size() > 0)?"<h5>Космические силы гарнизона:</h5>" + starSystemFleetUnits.stream()
                .map(StarSystemFleetUnit::toString)
                .map(s -> s + "<br/>")
                .collect(Collectors.joining()):"" );
        return builder.toString();
    }

    public enum StarSystemImportance {
        SUPER_STATEHOOD_CAPITAL(0, "Столица Сверхобъединения"),
        STATEHOOD_CAPITAL(1, "Столица Государства"),
        IMPORTANT_SYSTEM(2, "Важный мир"),
        SYSTEM(3, "Обычная колония");

        private int id;
        private String name;

        StarSystemImportance(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static StarSystemImportance byId(Integer id) {
            if (id == 0) return SUPER_STATEHOOD_CAPITAL;
            else if (id == 1) return STATEHOOD_CAPITAL;
            else if (id == 2) return IMPORTANT_SYSTEM;
            else return SYSTEM;
        }

        public static StarSystemImportance byName(String name) {
            switch (name){
                case "Столица Сверхобъединения": {
                    return SUPER_STATEHOOD_CAPITAL;
                }
                case "Столица Государства": {
                    return STATEHOOD_CAPITAL;
                }
                case "Важный мир": {
                    return IMPORTANT_SYSTEM;
                }
                default: {
                    return SYSTEM;
                }
            }
        }
    }
}
