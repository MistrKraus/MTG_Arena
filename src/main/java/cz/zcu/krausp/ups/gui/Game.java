package cz.zcu.krausp.ups.gui;

import cz.zcu.krausp.ups.game.IDrawable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application {

    public static final int MIN_HEIGHT = 200;
    public static final int MIN_WIDTH = 400;

    private GraphicsContext g;

    private double height;
    private double width;
    private Canvas c = new Canvas();

    private ArrayList<IDrawable> gameItems = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.g = this.c.getGraphicsContext2D();

        primaryStage.setTitle("Game");
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setScene(createScene());

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.c.setWidth(newVal.intValue());
            redraw();
        });
        this.c.setWidth(primaryStage.getWidth());

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            this.c.setHeight(newVal.intValue());
            redraw();
        });

        primaryStage.show();

        redraw();
    }

    public Scene createScene() {
        return new Scene(new Pane(this.c));
    }

    public void redraw() {
        update();

        this.g.setFill(Color.LIGHTCORAL);
        this.g.fillRect(0, 0, this.width, this.height);
    }

    public void update() {
        this.height = this.c.getScene().getHeight();
        this.width = this.c.getScene().getWidth();
    }
}
