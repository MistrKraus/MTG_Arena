package cz.zcu.krausp.ups.game;

import javafx.scene.canvas.GraphicsContext;

public interface IDrawable {

    void draw(GraphicsContext g, double scaleX, double scaleY);
}
