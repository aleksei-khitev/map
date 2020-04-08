package ru.akhitev.rp.star_system.drawer;

import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.rp.map.drawer.ScalingManager;
import ru.akhitev.rp.star_system.entity.StarSystem;
import ru.akhitev.rp.star_system.repo.StarSystemRepository;

@Service
public class ResourceStarSystemDrawingManager implements StarSystemDrawingManager {
    @Autowired
    private StarSystemRepository starSystemRepository;
    @Autowired
    private ScalingManager scalingManager;

    public void draw(GraphicsContext graphicsContext2D) {
        starSystemRepository.findAll().forEach(starSystem -> {drawSystem(graphicsContext2D, starSystem);});
    }

    private void drawSystem(GraphicsContext graphicsContext2D, StarSystem starSystem) {
        AbstractStarSystemDrawer systemDrawer = new ResourceSystemDrawer(graphicsContext2D, starSystem, scalingManager);
        systemDrawer.drawStarSystemFigure();
        systemDrawer.drawStarSystemName();
    }
}
