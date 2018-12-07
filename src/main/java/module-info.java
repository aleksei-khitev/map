module rp.map {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.context;
    requires spring.beans;
    requires java.persistence;
    requires java.sql;
    requires spring.orm;
    requires spring.jdbc;
    requires spring.data.jpa;
    requires spring.tx;

    opens ru.akhitev.rp.map to javafx.graphics, spring.core, spring.beans, spring.context;
    opens ru.akhitev.rp.map.configs to spring.core, spring.beans, spring.context;
    opens ru.akhitev.rp.map.repository to spring.core, spring.beans, spring.context;
    opens ru.akhitev.rp.map.entity to spring.core, spring.beans, spring.context;
    opens ru.akhitev.rp.map.controller to spring.core, spring.beans, spring.context;
    opens ru.akhitev.rp.map.drawer to spring.core, spring.beans, spring.context;
    opens ru.akhitev.rp.map.drawer.starsystem to spring.core, spring.beans, spring.context;
    opens ru.akhitev.rp.map.hyperspace to spring.core, spring.beans, spring.context;
    opens ru.akhitev.rp.map.router to spring.core, spring.beans, spring.context;
    exports ru.akhitev.rp.map;
}