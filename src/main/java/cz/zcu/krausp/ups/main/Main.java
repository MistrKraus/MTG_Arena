package cz.zcu.krausp.ups.main;

import cz.zcu.krausp.ups.game.Game;
import cz.zcu.krausp.ups.gui.GameBoard;
import cz.zcu.krausp.ups.gui.Lobby;
import cz.zcu.krausp.ups.gui.SceneLib;
import cz.zcu.krausp.ups.lobby.LobbyController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static Game game = new Game(0, "Mader", 1, "Xiaran");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneLib.setLobbyScene(new Lobby(new LobbyController()));
        SceneLib.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Scene");
        //primaryStage.setScene(new Scene(new Lobby(new LobbyController())));
        SceneLib.switchToLobby();
        primaryStage.show();

        SceneLib.setGameScene(new GameBoard(game));


    }


}
