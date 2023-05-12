package game.controller;

import game.model.*;
import game.view.RebootDialog;
import game.view.ShowDialog;
import javafx.geometry.Pos;

public class DialogFactory {
    private static Controller controller;

    public static void create(DialogType type) {
        String dynamicText;

        switch (type) {
            case NO_GAME:
                new ShowDialog(controller.getView(),
                        Titles.NO_GAME, Icons.BLOCK, Messages.NO_CLUE, DialogSize.FIRST_SIZE)
                        .showAndWait(); break;
            case MANUAL:
                new ShowDialog(controller.getView(),
                        Titles.MANUAL, Icons.MANUAL, Messages.MANUAL, DialogSize.FOURTH_SIZE)
                        .showAndWait(); break;
            case YOU_WELCOME:
                new ShowDialog(controller.getView(),
                        Titles.YOU_WELCOME, Icons.THANKS, Messages.BYE, DialogSize.FIRST_SIZE)
                        .showAndWait(); break;
            case NOT_KNOW:
                new ShowDialog(controller.getView(),
                        Titles.NOT_FUNNY, Icons.FUNNY, Messages.NOT_KNOW, DialogSize.FIRST_SIZE)
                        .showAndWait(); break;
            case EMPTY_STRING:
                new ShowDialog(controller.getView(),
                        Titles.ERROR, Icons.ERROR, Messages.EMPTY_STRING, DialogSize.SECOND_SIZE)
                        .showAndWait(); break;
            case LOW:
                new ShowDialog(controller.getView(),
                        Titles.WRONG, Icons.LOW, Messages.LOW, DialogSize.FIRST_SIZE)
                        .showAndWait(); break;
            case HIGH:
                new ShowDialog(controller.getView(),
                        Titles.WRONG, Icons.HIGH, Messages.HIGH, DialogSize.FIRST_SIZE)
                        .showAndWait(); break;
            case CHANGE_NUMBER:
                dynamicText = String.format(Messages.CHANGE_NUMBER, controller.getUseNumber());
                new ShowDialog(controller.getView(),
                        Titles.WRONG, Icons.QUESTION, dynamicText, DialogSize.SECOND_SIZE)
                        .showAndWait(); break;
            case GAME_OVER:
                dynamicText = String.format(Messages.ANSWER, controller.getModel().getSecretNumber());
                new ShowDialog(controller.getView(),
                        Titles.YOU_LOSE, Icons.BLOCK, dynamicText,
                        Buttons.REBOOT.toUpperCase(), Pos.CENTER, DialogSize.FIRST_SIZE)
                        .showAndWait(); break;
            case VICTORY:
                dynamicText = String.format(Titles.VICTORY, controller.getModel().getSecretNumber());
                new ShowDialog(controller.getView(),
                        dynamicText, Icons.VICTORY, Messages.HINT, DialogSize.SECOND_SIZE)
                        .showAndWait(); break;
            case REBOOT:
                new RebootDialog(controller, controller.getView(),
                        Titles.REBOOT, Icons.REBOOT, Messages.REBOOT, DialogSize.THIRD_SIZE)
                        .showAndWait(); break;
        }
    }

    public static void setController(Controller controller) {
        DialogFactory.controller = controller;
    }
}
