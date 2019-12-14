package ru.akhitev.rp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.akhitev.rp.conf.SpringConfig;
import ru.akhitev.rp.conf.SpringStageLoader;
import ru.akhitev.rp.map.controller.MapController;

import java.io.IOException;

public class Launcher extends Application {
    private static AnnotationConfigApplicationContext context;

    /**
     * Инициализируем контекст
     */
    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Stage mainStage = SpringStageLoader.loadMain();
        mainStage.show();
    }

    /**
     * Освобождаем контекст
     */
    @Override
    public void stop() throws IOException {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
