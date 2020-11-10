package cz.zcu.krausp.ups.gui;

import cz.zcu.krausp.ups.net.CmdManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Lobby extends Application {

    private TextField nameTF = new TextField();
    private Label messageLbl = new Label();
    private Button joinBtn = new Button("Join game");
    private Button closeBtn = new Button("Close");

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lobby");
        Scene scene = new Scene(createRoot());
        primaryStage.setScene(scene);
        primaryStage.show();

        setEventHandlers();
    }

    public void enableToJoin() {

    }

    private void setEventHandlers() {
        this.joinBtn.setOnAction(event -> CmdManager.INSTANCE.login(this.nameTF.getText()));
        //this.joinBtn.setOnAction(event -> CmdManager.INSTANCE.);
    }

    private Parent createRoot() {
        BorderPane root = new BorderPane();

        root.setCenter(createTop());
        root.setBottom(createBottom());
        root.setPadding(Constants.DEF_INSETS);

        return root;
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

        return hBox;
    }
}
