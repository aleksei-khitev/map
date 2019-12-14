package ru.akhitev.rp.fleet.fleet_node.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "Fleet_node")
@SequenceGenerator(name = "seq", initialValue = 20)
public class FleetNode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "parent_node_id", nullable = false)
    private FleetNode parentNode;

    @Column(name = "minimum_commander_rank", nullable = false)
    private String mininumCommanderRank;

    @Column(name = "commander_officer")
    private String commanderOfficer;

}
