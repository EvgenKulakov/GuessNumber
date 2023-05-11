package game.view;

import game.controller.Controller;
import game.model.DialogSize;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

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
        yes.setStyle("-fx-color: #ADD8E6;");
        yes.setOnAction(event -> {
            close();
            controller.repeatGame();
        });
        hBoxButton.getChildren().add(0, yes);
        hBoxButton.setSpacing(5);
    }
}
