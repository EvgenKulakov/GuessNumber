package game.view;

import game.model.Icons;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowDialog extends Stage {
    private final BorderPane root = new BorderPane();
    private final Scene scene = new Scene(root);
    private final VBox vBoxMain = new VBox();
    private final HBox hBoxText = new HBox();
    private final HBox hBoxButton = new HBox();

    private final Stage parent;
    private final String title;
    private final Label message;
    private final Button button;
    private final Pos posButton;
    private final Image windowIcon;
    private final ImageView typeIcon;


    public ShowDialog(Stage parent, String title, String message, String icon) {
        this(parent, title, message, "OK", Pos.BASELINE_RIGHT, icon);
    }

    public ShowDialog(Stage parent, String title, String message, String buttonTxt, Pos posButton, String icon) {
        this.parent = parent;
        this.title = title;
        this.message = new Label(message);
        this.button = new Button(buttonTxt);
        this.posButton = posButton;
        windowIcon = new Image(getClass().getResourceAsStream(Icons.MAIN_IMAGINE));
        typeIcon = new ImageView(new Image(getClass().getResourceAsStream(icon)));
        dialogRendering();
    }

    private void dialogRendering() {
        setScene(scene);
        setTitle(title);
        setResizable(false);
        getIcons().add(windowIcon);
        initModality(Modality.APPLICATION_MODAL);
        root.setMinWidth(350);
//        root.setStyle("-fx-background-color: white;");

        typeIcon.setFitWidth(40);
        typeIcon.setFitHeight(40);
        hBoxText.setAlignment(Pos.CENTER_LEFT);
        hBoxText.setSpacing(15);
        hBoxText.setPadding(new Insets(10, 10, 2, 10));
        hBoxText.getChildren().addAll(typeIcon, message);

        button.setOnAction(event -> close());
        button.setMinWidth(50);
        hBoxButton.setPadding(new Insets(0, 10, 8, 10));
        hBoxButton.setAlignment(posButton);
        hBoxButton.getChildren().add(button);

        vBoxMain.getChildren().addAll(hBoxText, hBoxButton);
        root.setCenter(vBoxMain);

        setOnShown(event -> {
            double positionX = parent.getX() + ((parent.getWidth() - this.getWidth()) / 2);
            double positionY = parent.getY() + 250 + ((167 - this.getHeight()) / 2);
            setX(positionX);
            setY(positionY);
        });

        showAndWait();
    }
}
