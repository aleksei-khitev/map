package ru.akhitev.rp.star_system.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import ru.akhitev.rp.star_system.entity.Planet;
import ru.akhitev.rp.star_system.entity.StarSystem;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractDialog {
    protected Scene openDialog(Scene scene, Object controller, String fxml) {
        URL resource = getClass().getResource(fxml);
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setController(controller);
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            logger().error("Ошибка при открытии диалога", e);
        }
        scene.setRoot(parent);
        return scene;
    }


    protected abstract Logger logger();
}
