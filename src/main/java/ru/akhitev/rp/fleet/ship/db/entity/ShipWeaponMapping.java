package ru.akhitev.rp.fleet.ship.db.entity;

import ru.akhitev.rp.fleet.weapon.db.entity.Weapon;

import javax.persistence.*;

@Entity
@Table(name = "Ship_weapon_mapping")
@SequenceGenerator(name = "seq", initialValue = 20)
public class ShipWeaponMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "weapon_id")
    private Weapon weapon;

    @Column(name = "weapon_count", nullable = false)
    private Integer weaponCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Integer getWeaponCount() {
        return weaponCount;
    }

    public void setWeaponCount(Integer weaponCount) {
        this.weaponCount = weaponCount;
    }
}
