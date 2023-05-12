package game.view;

import game.controller.Controller;
import game.model.DialogSize;
import game.model.Styles;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class RebootDialog extends ShowDialog {
    private final Controller controller;

    public RebootDialog(Controller controller, View parent, String title,
                        String typeIcon, String text, DialogSize dialogSize) {
        super(parent, title, typeIcon, text, "НЕТ", Pos.BASELINE_RIGHT,  dialogSize);
        this.controller = controller;
        rebootRendering();
    }

    private void rebootRendering() {
        Button yes = new Button("ДА");
        yes.setMinWidth(50);
        yes.setStyle(Styles.BUTTON_BLUE);

        yes.setOnMouseClicked(event -> {
            if (event.getButton().name().equals("PRIMARY")) {
                close();
                controller.repeatGame();
            }
        });

        yes.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                close();
                controller.repeatGame();
            }
        });

        hBoxButton.setSpacing(5);
        hBoxButton.getChildren().add(0, yes);
    }
}
