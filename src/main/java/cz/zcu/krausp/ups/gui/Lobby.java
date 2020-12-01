package cz.zcu.krausp.ups.gui;

import cz.zcu.krausp.ups.lobby.LobbyController;
import cz.zcu.krausp.ups.net.CmdManager;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Lobby extends BorderPane {

    private final TextField nameTF = new TextField();
    private final Label messageLbl = new Label();
    private final Button joinBtn = new Button("Join game");
    private final Button closeBtn = new Button("Close");

    private final LobbyController controller;

    public Lobby(LobbyController controller) {
        this.controller = controller;

        this.setCenter(createTop());
        this.setBottom(createBottom());
        this.setPadding(Constants.DEF_INSETS);

        setEventHandlers();
    }

    public void enableToJoin() {

    }

    private void setEventHandlers() {
        this.joinBtn.setOnAction(event -> {
            //CmdManager.INSTANCE.login(this.nameTF.getText());
            SceneLib.switchToGame();          // TODO Change ...obviously
        });

        this.closeBtn.setOnAction(event -> {
            CmdManager.INSTANCE.exitLobby();

        });
    }

    private Node createTop() {
        HBox hBox = new HBox();

        Label lblName = new Label("Nickname:");
        lblName.setPadding(Constants.DEF_INSETS);
        this.joinBtn.setPadding(Constants.DEF_INSETS);

        hBox.getChildren().addAll(lblName, this.nameTF, this.joinBtn);
        hBox.setPadding(Constants.DEF_INSETS);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);

        return hBox;
    }

    private Node createBottom() {
        HBox hBox = new HBox();

        hBox.getChildren().addAll(this.closeBtn);
        hBox.setAlignment(Pos.CENTER_LEFT);

        return hBox;
    }
}
