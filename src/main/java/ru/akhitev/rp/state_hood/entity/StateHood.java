package ru.akhitev.rp.state_hood.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.akhitev.rp.star_system.entity.StarSystem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "State_Hood")
@SequenceGenerator(name = "seq", initialValue = 20)
public class StateHood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "color", nullable = false)
    private String color;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "statehood")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<StarSystem> starSystems;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<StarSystem> getStarSystems() {
        return starSystems;
    }

    public void setStarSystems(Set<StarSystem> starSystems) {
        this.starSystems = starSystems;
    }

    @Override
    public String toString() {
        return name;
    }
}
