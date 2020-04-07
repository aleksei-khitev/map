package ru.akhitev.rp.star_system.drawer;

import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akhitev.rp.map.drawer.ScalingManager;
import ru.akhitev.rp.star_system.entity.Planet;
import ru.akhitev.rp.star_system.entity.StarSystem;
import ru.akhitev.rp.star_system.repo.StarSystemRepository;

@Service
public class PopulationStarSystemDrawingManager implements StarSystemDrawingManager {
    @Autowired
    private StarSystemRepository starSystemRepository;
    @Autowired
    private ScalingManager scalingManager;

    @Override
    public void draw(GraphicsContext graphicsContext2D) {
        starSystemRepository.findAll().forEach(starSystem -> drawSystem(graphicsContext2D, starSystem));
    }

    private void drawSystem(GraphicsContext graphicsContext2D, StarSystem starSystem) {
        AbstractStarSystemDrawer systemDrawer;
        long populationInStarSystem = starSystem.getPlanets().stream()
                .filter(planet -> planet.getPopulation() != null)
                .mapToLong(Planet::getPopulation).sum();
        if (populationInStarSystem <= 100_000) {
            systemDrawer = new StarSystemDrawer(graphicsContext2D, starSystem, scalingManager);
        } else if (populationInStarSystem <= 1_000_000) {
            systemDrawer = new ImportantSystemDrawer(graphicsContext2D, starSystem, scalingManager);
        } else if (populationInStarSystem <= 1_000_000_000) {
            systemDrawer = new CapitalOfStateHoodDrawer(graphicsContext2D, starSystem, scalingManager);
        } else {
            systemDrawer = new CapitalOfSuperStateHoodDrawer(graphicsContext2D, starSystem, scalingManager);
        }
        systemDrawer.drawStarSystemFigure();
        systemDrawer.drawStarSystemName();
    }
}
