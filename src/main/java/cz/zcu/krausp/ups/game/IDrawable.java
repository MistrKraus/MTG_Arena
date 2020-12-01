package cz.zcu.krausp.ups.game;

import javafx.scene.canvas.GraphicsContext;

public interface IDrawable {

    void draw(GraphicsContext g, int zoneX, int zoneY, int zoneWidth, int zoneHeight, int cardsInZone, int order);

    void update(int zoneWidth, int zoneHeight);
}
