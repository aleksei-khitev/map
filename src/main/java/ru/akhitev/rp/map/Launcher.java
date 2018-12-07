package ru.akhitev.rp.map;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.akhitev.rp.map.configs.SpringConfig;
import ru.akhitev.rp.map.configs.SpringStageLoader;
import ru.akhitev.rp.map.controller.MainController;

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
        MainController controller = context.getBean(MainController.class);
        controller.initialize();
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
