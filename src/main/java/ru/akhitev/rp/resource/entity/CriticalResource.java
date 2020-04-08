package ru.akhitev.rp.resource.entity;

import javafx.scene.paint.Color;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.akhitev.rp.star_system.entity.PlanetResource;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "critical_resource")
public class CriticalResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String color;

    @OneToMany(mappedBy = "criticalResource",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PlanetResource> planetResources;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return Color.valueOf(color);
    }

    @Override
    public String toString() {
        return name;
    }
}
