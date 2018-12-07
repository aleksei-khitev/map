package ru.akhitev.rp.map.drawer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.springframework.stereotype.Service;
import ru.akhitev.rp.map.entity.StarSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

@Service
public class EmblemDrawer {
    public void drawEmblems(Canvas map, StarSystem starSystem) {
        int horisontalIndent = 0;
        GraphicsContext graphicsContext2D = map.getGraphicsContext2D();
        if (starSystem.getSuperStatehood()!= null && starSystem.getSuperStatehood().getImage() != null) {
            drawIcon(graphicsContext2D, starSystem, starSystem.getSuperStatehood().getImage(), horisontalIndent);
            horisontalIndent = horisontalIndent + 80;
        }
        if (starSystem.getStatehood()!= null && starSystem.getStatehood().getImage() != null) {
            drawIcon(graphicsContext2D, starSystem, starSystem.getStatehood().getImage(), horisontalIndent);
        }
    }

    private void drawIcon(GraphicsContext graphicsContext2D, StarSystem starSystem, String image, int horisontalIndent) {
        graphicsContext2D.drawImage(new Image(EmblemDrawer.class.getResourceAsStream("/" + image)), horisontalIndent, 0,80,80);
    }
}
