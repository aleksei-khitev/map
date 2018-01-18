package ru.akhitev.rp.map;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.akhitev.rp.map.view.MainView;

@SpringBootApplication
@EnableJpaRepositories
public class Launcher extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(Launcher.class, MainView.class, null, args);
    }
}
