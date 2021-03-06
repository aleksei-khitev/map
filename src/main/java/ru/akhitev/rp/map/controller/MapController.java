package ru.akhitev.rp.map.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akhitev.rp.conf.AbstractController;
import ru.akhitev.rp.map.drawer.ScalingManager;
import ru.akhitev.rp.map.drawer.EmblemDrawer;
import ru.akhitev.rp.map.drawer.GridOfCoordinatesDrawer;
import ru.akhitev.rp.star_system.controller.star_system_general.CreateSystemDialogController;
import ru.akhitev.rp.star_system.controller.star_system_general.EditSystemDialogController;
import ru.akhitev.rp.star_system.drawer.PolicyStarSystemDrawingManager;
import ru.akhitev.rp.star_system.drawer.PopulationStarSystemDrawingManager;
import ru.akhitev.rp.star_system.drawer.ResourceStarSystemDrawingManager;
import ru.akhitev.rp.star_system.drawer.StarSystemDrawingManager;
import ru.akhitev.rp.star_system.entity.StarSystem;
import ru.akhitev.rp.map.hyperspace.EmpireLightSpeedCalculator;
import ru.akhitev.rp.map.hyperspace.VortexSpeedCalculator;
import ru.akhitev.rp.star_system.repo.StarSystemRepository;
import ru.akhitev.rp.state_hood.repo.StateHoodRepository;
import ru.akhitev.rp.super_state_hood.repo.SuperStateHoodRepository;
import ru.akhitev.rp.map.router.Router;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Component
public class MapController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(MapController.class);
    private static final Integer MAP_WIDTH = 2000;
    private static final Integer MAP_HEIGHT = 1000;
    @FXML private Canvas map;
    @FXML private Canvas emblems;
    @FXML private WebView objectInfo;
    @FXML RadioMenuItem scaleX1;
    @FXML RadioMenuItem scaleX2;
    @FXML RadioMenuItem scaleX3;
    @FXML RadioMenuItem policyMap;
    @FXML RadioMenuItem populationMap;
    @FXML RadioMenuItem resourceMap;
    private ContextMenu contextMenu;
    private double contextX;
    private double contextY;
    private final StarSystemRepository starSystemRepository;
    private StateHoodRepository stateHoodRepository;
    private SuperStateHoodRepository superStateHoodRepository;
    private final GridOfCoordinatesDrawer gridOfCoordinatesDrawer;
    private final EmblemDrawer emblemDrawer;
    private StarSystemDrawingManager starSystemDrawingManager;
    private final ScalingManager scalingManager;
    private final Router router;
    private final VortexSpeedCalculator vortexSpeedCalculator;
    private final EmpireLightSpeedCalculator empireLightSpeedCalculator;

    @Autowired
    public MapController(StarSystemRepository starSystemRepository,
                         StateHoodRepository stateHoodRepository,
                         SuperStateHoodRepository superStateHoodRepository,
                         GridOfCoordinatesDrawer gridOfCoordinatesDrawer,
                         EmblemDrawer emblemDrawer,
                         PolicyStarSystemDrawingManager starSystemDrawingManager,
                         PopulationStarSystemDrawingManager populationStarSystemDrawingManager,
                         ScalingManager scalingManager, Router router,
                         VortexSpeedCalculator vortexSpeedCalculator,
                         EmpireLightSpeedCalculator empireLightSpeedCalculator) {
        this.starSystemRepository = starSystemRepository;
        this.stateHoodRepository = stateHoodRepository;
        this.superStateHoodRepository = superStateHoodRepository;
        this.gridOfCoordinatesDrawer = gridOfCoordinatesDrawer;
        this.emblemDrawer = emblemDrawer;
        this.starSystemDrawingManager = starSystemDrawingManager;
        this.scalingManager = scalingManager;
        this.router = router;
        this.vortexSpeedCalculator = vortexSpeedCalculator;
        this.empireLightSpeedCalculator = empireLightSpeedCalculator;
        scalingManager.setScale(1);
    }


    @FXML
    public void setScale(int scale) {
        scalingManager.setScale(scale);
        drawMap();
    }

    @FXML
    public void initialize() {
        starSystemDrawingManager = getContext().getBean(PolicyStarSystemDrawingManager.class);
        drawMap();
        prepareContextMenu();
        scaleX1.setOnAction((actionEvent) -> setScale(1));
        scaleX2.setOnAction((actionEvent) -> setScale(2));
        scaleX3.setOnAction((actionEvent) -> setScale(3));
        policyMap.setOnAction((actionEvent) -> drawPolicyMap());
        populationMap.setOnAction((actionEvent) -> drawPopulationMap());
        resourceMap.setOnAction((actionEvent) -> drawResourceMap());
        map.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    contextX = scalingManager.reScaleCoordinate(event.getX());
                    contextY = scalingManager.reScaleCoordinate(event.getY());
                    processInfoQuering();
                }
            }
        });
    }

    private void drawPolicyMap() {
        starSystemDrawingManager = getContext().getBean(PolicyStarSystemDrawingManager.class);
        drawMap();
    }

    private void drawPopulationMap() {
        starSystemDrawingManager = getContext().getBean(PopulationStarSystemDrawingManager.class);
        drawMap();
    }

    private void drawResourceMap() {
        starSystemDrawingManager = getContext().getBean(ResourceStarSystemDrawingManager.class);
        drawMap();
    }

    private void drawMap() {
        clearMap();
        map.setWidth(MAP_WIDTH * scalingManager.getScale());
        map.setHeight(MAP_HEIGHT * scalingManager.getScale());
        GraphicsContext gc = map.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, map.getWidth(), map.getHeight());
        gridOfCoordinatesDrawer.draw(map);
        starSystemDrawingManager.draw(gc);
    }

    private void prepareContextMenu() {
        contextMenu = new ContextMenu();
        MenuItem create = new MenuItem("Добавить систему");
        create.setOnAction((actionEvent) -> {
            CreateSystemDialogController createSystemDialog = new CreateSystemDialogController(contextX, contextY, getContext());
            URL resource = getClass().getResource("/ru/akhitev/rp/star_system/addEditSystemDialog_general.fxml");
            FXMLLoader loader = new FXMLLoader(resource);
            loader.setController(createSystemDialog);
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                logger.error("Ошибка при открытии диалога", e);
            }
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Добавление системы");
            stage.showAndWait();
            drawMap();
        });
        MenuItem edit = new MenuItem("Редактировать систему");
        edit.setOnAction((actionEvent) -> {
            getStarSystemByContextMenuCoordinates().ifPresent(starSystem -> {
                EditSystemDialogController editSystemDialogController = new EditSystemDialogController(starSystem, getContext());
                URL resource = getClass().getResource("/ru/akhitev/rp/star_system/addEditSystemDialog_general.fxml");
                FXMLLoader loader = new FXMLLoader(resource);
                loader.setController(editSystemDialogController);
                Parent parent = null;
                try {
                    parent = loader.load();
                } catch (IOException e) {
                    logger.error("Ошибка при открытии диалога", e);
                }
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setTitle("Редактирование системы");
                stage.showAndWait();
                drawMap();
            });
        });
        MenuItem delete = new MenuItem("Удалить систему");
        delete.setOnAction((actionEvent) -> {
            List<StarSystem> starSystems = starSystemRepository.findNearCoordinates(contextX, contextY);
            if (starSystems != null && starSystems.size() > 0) {
                StarSystem starSystem = starSystems.get(0);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Подтверждение удаления");
                alert.setHeaderText("Вы уверены, что хотите удалить эту систему?");
                alert.setContentText(starSystem.toString());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    starSystemRepository.delete(starSystem);
                    try (FileWriter fileWriter = new FileWriter("queries_history.sql", true);
                         PrintWriter printWriter = new PrintWriter(fileWriter)) {
                        printWriter.println("DELETE FROM Star_System where id = " + starSystem.getId());
                    } catch (IOException e) {
                        logger.error("Ошибка при сохранении запроса в файл", e);
                    }
                }
                drawMap();
            } else {
                objectInfo.getEngine().loadContent("В выбранном участке +-15 единиц системы <b>не найдены.</b>");
            }
        });
        MenuItem info = new MenuItem("Информация о системе");
        info.setOnAction((actionEvent) -> {
            processInfoQuering();
        });
        MenuItem hyperSpaceRouteStart = new MenuItem("Прыжок: начальная точка");
        hyperSpaceRouteStart.setOnAction((actionEvent) -> {
            processRouteCalculation(true);
        });
        MenuItem hyperSpaceRouteFinish = new MenuItem("Прыжок: конечная точка");
        hyperSpaceRouteFinish.setOnAction((actionEvent) -> {
            processRouteCalculation(false);
        });
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        contextMenu.getItems().addAll(info, hyperSpaceRouteStart, hyperSpaceRouteFinish , separatorMenuItem, create, edit, delete);
        map.setOnContextMenuRequested(event -> {
            contextX = scalingManager.reScaleCoordinate(event.getX());
            contextY = scalingManager.reScaleCoordinate(event.getY());
            contextMenu.show(map, event.getScreenX(), event.getScreenY());
        });
    }

    private void clearMap() {
        GraphicsContext gc = map.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, map.getWidth(), map.getHeight());
        gc.clearRect(0, 0, map.getWidth(), map.getHeight());
    }

    private Optional<StarSystem> getStarSystemByContextMenuCoordinates() {
        List<StarSystem> starSystems = starSystemRepository.findNearCoordinates(contextX, contextY);
        if (starSystems != null && starSystems.size() > 0) {
            return Optional.of(starSystems.get(0));
        } else {
            return Optional.empty();
        }
    }

    private void processInfoQuering() {
        GraphicsContext gc = emblems.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, emblems.getWidth(), emblems.getHeight());
        gc.clearRect(0, 0, emblems.getWidth(), emblems.getHeight());
        Optional<StarSystem> optionalStarSystem = getStarSystemByContextMenuCoordinates();
        if (optionalStarSystem.isPresent()) {
            StarSystem starSystem = optionalStarSystem.get();
            String info = starSystem.toHtmlString();
            objectInfo.getEngine().loadContent(info);
            emblemDrawer.drawEmblems(emblems, starSystem);
        } else {
            objectInfo.getEngine().loadContent("В выбранном участке +-15 единиц системы <b>не найдены.</b>");
        }
    }

    private void processRouteCalculation(boolean isStart) {
        if (isStart) {
            router.startRoute(contextX, contextY);
        } else {
            final String format = "<h3>Машрут:</h3><b>Координаты точек маршрута: </b><br/>%s<br/><b>Дистанция: </b>%.2f св.лет<br/><h4>Время на полет:</h4><b>В вортекс пространстве:</b> %.2fч.,<br/><b>В имперском гипперпространстве:</b> %.2fч.";
            double distance = router.finishRouteAndCalculate(contextX, contextY);
            double timeInVortex = vortexSpeedCalculator.calculate(distance);
            double timeInEmpireLightSpeed = empireLightSpeedCalculator.calculate(distance);
            objectInfo.getEngine().loadContent(String.format(format, router.getPointsInfo(), distance, timeInVortex, timeInEmpireLightSpeed));
            router.reset();
        }
    }
}
