package game.view;

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

    private final View parent;
    private final String title;
    private final ImageView typeIcon;
    private final Label text;
    private final Button button;
    private final Pos posButton;
    private final boolean bigWindow;


    public ShowDialog(View parent, String title, String typeIcon, String text, boolean bigWindow) {
        this(parent, title, typeIcon, text, "OK", Pos.BASELINE_RIGHT, bigWindow);
    }

    public ShowDialog(View parent, String title, String typeIcon, String text,
                             String buttonTxt, Pos posButton, boolean bigWindow) {
        this.parent = parent;
        this.title = title;
        this.typeIcon = new ImageView(new Image(getClass().getResourceAsStream(typeIcon)));
        this.text = new Label(text);
        this.button = new Button(buttonTxt);
        this.posButton = posButton;
        this.bigWindow = bigWindow;
        dialogRendering();
    }

    private void dialogRendering() {
        setScene(scene);
        setTitle(title);
        setResizable(false);
        getIcons().add(parent.getWindowIcon());
        initModality(Modality.APPLICATION_MODAL);
        root.setMinWidth(bigWindow ? 360 : 320);

        typeIcon.setFitWidth(40);
        typeIcon.setFitHeight(40);
        text.setStyle("-fx-font-weight: bold;");
        hBoxText.setAlignment(Pos.CENTER_LEFT);
        hBoxText.setSpacing(15);
        hBoxText.setPadding(new Insets(10, 10, 2, 10));
        hBoxText.getChildren().addAll(typeIcon, text);

        button.setOnAction(event -> close());
        button.setMinWidth(50);
        hBoxButton.setPadding(new Insets(0, 10, 8, 10));
        hBoxButton.setAlignment(posButton);
        hBoxButton.getChildren().add(button);

        vBoxMain.getChildren().addAll(hBoxText, hBoxButton);
        root.setCenter(vBoxMain);

        setOnShown(event -> {
            /* выравнивание текста */
            double alignment = text.getWidth() + ((hBoxText.getWidth() - text.getWidth()) / 2) - 65;
            text.setMinWidth(alignment);
            text.setAlignment(Pos.CENTER_RIGHT);

            /* положение ShowDialog зависит от положения View */
            double positionX = parent.getX() + ((parent.getWidth() - this.getWidth()) / 2);
            double positionY = parent.getY() + 250 + ((167 - this.getHeight()) / 2);
            setX(positionX);
            setY(positionY);
        });

        showAndWait();
    }
}
