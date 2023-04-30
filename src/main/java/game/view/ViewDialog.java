package game.view;

import game.model.Icons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewDialog {

    public static void showDialog(String title, String message, String icon) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.setX(primaryStage.getX() + 85);
//        alert.setY(primaryStage.getY() + 274);
//
//        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//        stage.setAlwaysOnTop(true);
//        alert.showAndWait();

        // Создаем новый объект Stage
        BorderPane dialogRoot = new BorderPane();
        Scene dialogScene = new Scene(dialogRoot);
        final Stage stageDialog = new Stage();
        stageDialog.setScene(dialogScene);
        stageDialog.setTitle(title);
        stageDialog.setResizable(false);
        stageDialog.getIcons().add(new Image(
                ViewDialog.class.getResourceAsStream(Icons.MAIN_IMAGINE)));
        stageDialog.initModality(Modality.APPLICATION_MODAL);

        // Создаем содержимое диалогового окна
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(10));
        Image image = new Image(ViewDialog.class.getResourceAsStream(icon));
        ImageView iV = new ImageView(image);
        iV.setFitWidth(40);
        iV.setFitHeight(40);
        Label text = new Label(message);
        text.setAlignment(Pos.CENTER);
        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageDialog.close();
            }
        });
        hBox.getChildren().addAll(iV, text);
        HBox box = new HBox();
        box.getChildren().add(button);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setTranslateX(-8);
        box.setTranslateY(-8);
        dialogRoot.setCenter(hBox);
        dialogRoot.setBottom(box);
//        // Загружаем CSS-стиль для диалогового окна
//        stageDialog.getScene().getStylesheets().add(getClass().getResource("/alert.css").toExternalForm());
//
//        // Устанавливаем стиль для содержимого диалогового окна
//        vbox.getStyleClass().add("alert");

        // Отображаем диалоговое окно и ждем, пока пользователь его закроет
        stageDialog.showAndWait();
    }

    public static void showDialog(String title, String message, String buttonText, String icon) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.setX(primaryStage.getX() + 85);
//        alert.setY(primaryStage.getY() + 274);
//
//        ButtonType buttonType = new ButtonType(buttonText);
//        alert.getButtonTypes().setAll(buttonType);
//
//        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//        stage.setAlwaysOnTop(true);
//        alert.showAndWait();
        BorderPane dialogRoot = new BorderPane();
        Scene dialogScene = new Scene(dialogRoot);
        final Stage stageDialog = new Stage();
        stageDialog.setScene(dialogScene);
        stageDialog.setTitle(title);
        stageDialog.setResizable(false);
        stageDialog.getIcons().add(new Image(
                ViewDialog.class.getResourceAsStream(Icons.MAIN_IMAGINE)));
        stageDialog.initModality(Modality.APPLICATION_MODAL);

        // Создаем содержимое диалогового окна
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(10));
        Image image = new Image(ViewDialog.class.getResourceAsStream(icon));
        ImageView iV = new ImageView(image);
        iV.setFitWidth(40);
        iV.setFitHeight(40);
        Label text = new Label(message);
        text.setAlignment(Pos.CENTER);
        Button button = new Button(buttonText);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageDialog.close();
            }
        });
        hBox.getChildren().addAll(iV, text);
        HBox box = new HBox();
        box.getChildren().add(button);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setTranslateX(-8);
        box.setTranslateY(-8);
        dialogRoot.setCenter(hBox);
        dialogRoot.setBottom(box);
        stageDialog.showAndWait();
    }
}
