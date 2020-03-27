package ru.akhitev.rp.fleet.entity;

import javax.persistence.*;

@Entity
@Table(name = "ship_crew")
public class ShipCrew {
    @Id
    @Column(name = "ship_id")
    private Long id;

    @Column
    private Integer minimal;

    @Column
    private Integer normal;

    @OneToOne
    @MapsId
    private Ship ship;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinimal() {
        return minimal;
    }

    public void setMinimal(Integer minimal) {
        this.minimal = minimal;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    @Override
    public String toString() {
        return "Экипаж {" +
                ((minimal != null)?"минимальный: " + minimal:"") +
                ((normal != null)?", полноценный: " + normal:"") +
                '}';
    }

    String toHtmlString() {
        return "<h4>Экипаж</h4>" +
                ((minimal != null)?"<b>минимальный:</b> " + minimal + "<br/>":"") +
                ((normal != null)?"<b>полноценный:</b> " + normal + "<br/>":"");
    }
}
