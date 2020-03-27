package ru.akhitev.rp.fleet.entity;

import javax.persistence.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "small_aircraft")
public class SmallAircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer crew;

    @Column
    private String link;

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

    public Integer getCrew() {
        return crew;
    }

    public void setCrew(Integer crew) {
        this.crew = crew;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toHtmlString() {
        return "<h3>" + name + "</h3>\n<hr/>" +
                "<b>Экипаж</b> " + crew + "<br/>" +
                "<br/><a href='" + link + "'>Ссылка на источник данных</a>";
    }
}
