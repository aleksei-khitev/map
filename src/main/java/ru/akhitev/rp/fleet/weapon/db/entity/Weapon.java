package ru.akhitev.rp.fleet.weapon.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "Weapon")
@SequenceGenerator(name = "seq", initialValue = 20)
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "damage", nullable = false)
    private Integer damage;

    @Column(name = "range", nullable = false)
    private WEAPON_RANGE_TYPE range;

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

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public WEAPON_RANGE_TYPE getRange() {
        return range;
    }

    public void setRange(WEAPON_RANGE_TYPE range) {
        this.range = range;
    }

    public enum WEAPON_RANGE_TYPE {
        CLOSE, SHORT, MEDIUM, LONG, EXTREME
    }
}
