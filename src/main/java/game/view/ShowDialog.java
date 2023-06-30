package game.view;

import game.model.Buttons;
import game.model.DialogSize;
import game.model.Styles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowDialog extends Stage {
    private static View parentView;
    private static Stage parentStage;
    private final BorderPane root = new BorderPane();
    private final Scene scene = new Scene(root);
    private final VBox vBoxMain = new VBox();
    private final HBox hBoxText = new HBox();
    protected final HBox hBoxButton = new HBox();

    private final String title;
    private final ImageView typeIcon;
    private final Label text;
    protected final Button buttonClose;
    private final Pos posButton;
    private final DialogSize dialogSize;


    public ShowDialog(String title, String typeIcon, String text, DialogSize dialogSize) {
        this(title, typeIcon, text, Buttons.OK, Pos.BASELINE_RIGHT, dialogSize);
    }

    public ShowDialog(String title, String typeIcon, String text,
                      String buttonTxt, Pos posButton, DialogSize dialogSize) {
        this.title = title;
        this.typeIcon = new ImageView(new Image(getClass().getResourceAsStream(typeIcon)));
        this.text = new Label(text);
        this.buttonClose = new Button(buttonTxt);
        this.posButton = posButton;
        this.dialogSize = dialogSize;
        dialogRendering();
        addListeners();
    }

    private void dialogRendering() {
        setScene(scene);
        initOwner(parentStage);
        setX(parentStage.getX() + dialogSize.getPlusX());
        setY(parentStage.getY() + dialogSize.getPlusY());
        setTitle(title);
        setResizable(false);
        getIcons().add(parentView.getIcon());
        initModality(Modality.APPLICATION_MODAL);
        root.setMinWidth(dialogSize.getMinWindow());

        typeIcon.setFitWidth(40);
        typeIcon.setFitHeight(40);
        text.setStyle(Styles.FONT_BOLD);
        hBoxText.setAlignment(Pos.CENTER_LEFT);
        hBoxText.setSpacing(15);
        hBoxText.setPadding(new Insets(10, 10, 2, 10));
        hBoxText.getChildren().addAll(typeIcon, text);

        buttonClose.setMinWidth(50);
        hBoxButton.setPadding(new Insets(0, 10, 8, 10));
        hBoxButton.setAlignment(posButton);
        hBoxButton.getChildren().add(buttonClose);

        vBoxMain.getChildren().addAll(hBoxText, hBoxButton);
        root.setCenter(vBoxMain);

        setOnShown(event -> {
            /* выравнивание текста */
            double alignment = text.getWidth() + ((hBoxText.getWidth() - text.getWidth()) / 2) - 65;
            text.setMinWidth(alignment);
            text.setAlignment(Pos.CENTER_RIGHT);
        });
    }

    private void addListeners() {
        buttonClose.setOnMouseClicked(event -> {
            if (event.getButton().name().equals(Buttons.MOUSE_LEFT)) close();
        });

        buttonClose.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) close();
        });
    }

    public static void setParentWindow(View parentView) {
        ShowDialog.parentView = parentView;
        ShowDialog.parentStage = parentView.getStage();
    }
}
