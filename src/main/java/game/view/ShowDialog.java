package game.view;

import game.model.DialogSize;
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
    protected final HBox hBoxButton = new HBox();

    private final View parent;
    private final String title;
    private final ImageView typeIcon;
    private final Label text;
    private final Button button;
    private final Pos posButton;
    private final DialogSize dialogSize;


    public ShowDialog(View parent, String title, String typeIcon, String text, DialogSize dialogSize) {
        this(parent, title, typeIcon, text, "OK", Pos.BASELINE_RIGHT, dialogSize);
    }

    public ShowDialog(View parent, String title, String typeIcon, String text,
                             String buttonTxt, Pos posButton, DialogSize dialogSize) {
        this.parent = parent;
        this.title = title;
        this.typeIcon = new ImageView(new Image(getClass().getResourceAsStream(typeIcon)));
        this.text = new Label(text);
        this.button = new Button(buttonTxt);
        this.posButton = posButton;
        this.dialogSize = dialogSize;
        dialogRendering();
    }

    private void dialogRendering() {
        setScene(scene);
        initOwner(parent);
        setX(parent.getX() + dialogSize.getPlusX());
        setY(parent.getY() + dialogSize.getPlusY());
        setTitle(title);
        setResizable(false);
        getIcons().add(parent.getWindowIcon());
        initModality(Modality.APPLICATION_MODAL);
        root.setMinWidth(dialogSize.getMinWindow());

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
            System.out.println(parent.getX() + " " + parent.getY());
        });
    }
}
