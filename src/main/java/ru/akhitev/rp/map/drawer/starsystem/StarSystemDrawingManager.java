package ru.akhitev.rp.map.drawer.starsystem;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.rp.map.drawer.ScalingManager;
import ru.akhitev.rp.map.entity.StarSystem;
import ru.akhitev.rp.map.repository.StarSystemRepository;


@Service
public class StarSystemDrawingManager {
    @Autowired
    private StarSystemRepository starSystemRepository;
    @Autowired
    private ScalingManager scalingManager;

    public void draw(GraphicsContext graphicsContext2D) {
        starSystemRepository.findAll().forEach(starSystem -> {drawSystem(graphicsContext2D, starSystem);});
    }

    private void drawSystem(GraphicsContext graphicsContext2D, StarSystem starSystem) {
        AbstractStarSystemDrawer systemDrawer;
        switch (starSystem.getSystemImportance()) {
            case SUPER_STATEHOOD_CAPITAL:
                systemDrawer = new CapitalOfSuperStateHoodDrawer(graphicsContext2D, starSystem, scalingManager);
                break;
            case STATEHOOD_CAPITAL:
                systemDrawer = new CapitalOfStateHoodDrawer(graphicsContext2D, starSystem, scalingManager);
                break;
            case IMPORTANT_SYSTEM:
                systemDrawer = new ImportantSystemDrawer(graphicsContext2D, starSystem, scalingManager);
                break;
            default:
                systemDrawer = new StarSystemDrawer(graphicsContext2D, starSystem, scalingManager);
                break;
        }
        systemDrawer.drawStarSystemFigure();
        systemDrawer.drawStarSystemName();
    }
}
