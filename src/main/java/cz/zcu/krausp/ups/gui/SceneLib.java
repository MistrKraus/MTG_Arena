package cz.zcu.krausp.ups.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class SceneLib {

    public static Stage primaryStage;

    private static Scene lobbyScene;
    private static Scene gameScene;
    private static Scene activeScene;

    public static synchronized void setPrimaryStage(Stage primaryStage) {
        SceneLib.primaryStage = primaryStage;
    }

    public static synchronized void switchToGame() {
        SceneLib.activeScene = gameScene;

        SceneLib.primaryStage.setTitle("Game");
        SceneLib.primaryStage.setScene(gameScene);
        SceneLib.primaryStage.setMinWidth(GameBoard.MIN_WIDTH);
        SceneLib.primaryStage.setMinHeight(GameBoard.MIN_HEIGHT);
    }

    public static synchronized void switchToLobby() {
        SceneLib.activeScene = lobbyScene;

        SceneLib.primaryStage.setTitle("Lobby");
        SceneLib.primaryStage.setScene(lobbyScene);
    }

    public static synchronized void setLobbyScene(Lobby lobby) {
        SceneLib.lobbyScene = new Scene(lobby);
    }

    public static synchronized void setGameScene(GameBoard game) {
        SceneLib.gameScene = new Scene(game, GameBoard.MIN_WIDTH, GameBoard.MIN_HEIGHT, GameBoard.BACKGROUND_COLOR);
    }

    public static synchronized Scene getActiveScene() {
        return SceneLib.activeScene;
    }

    public static synchronized Scene getGameScene() {
        return SceneLib.gameScene;
    }
}
