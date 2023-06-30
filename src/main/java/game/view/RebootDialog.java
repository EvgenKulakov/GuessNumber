package game.view;

import game.controller.Controller;
import game.model.Buttons;
import game.model.DialogSize;
import game.model.Styles;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class RebootDialog extends ShowDialog {
    private static Controller controller;
    private final Button buttonYes = new Button(Buttons.YES);

    public RebootDialog(String title, String typeIcon, String text, DialogSize dialogSize) {
        super(title, typeIcon, text, dialogSize);
        rebootRendering();
        addRebootListeners();
    }

    private void rebootRendering() {
        buttonYes.setMinWidth(50);
        buttonYes.setStyle(Styles.BUTTON_BLUE);
        buttonClose.setText(Buttons.NO);
        hBoxButton.setSpacing(5);
        hBoxButton.getChildren().add(0, buttonYes);
    }

    private void addRebootListeners() {
        buttonYes.setOnMouseClicked(event -> {
            if (event.getButton().name().equals(Buttons.MOUSE_LEFT)) {
                close();
                controller.repeatGame();
            }
        });

        buttonYes.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                close();
                controller.repeatGame();
            }
        });
    }

    public static void setController(Controller controller) {
        RebootDialog.controller = controller;
    }
}
