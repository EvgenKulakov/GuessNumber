package game.controller;

import game.model.*;
import game.view.RebootDialog;
import game.view.ShowDialog;
import game.view.View;
import javafx.geometry.Pos;

public class DialogFactory {

    /* ShowDialog с обычным текстом */
    public static void create(View parent, DialogType type) {
        switch (type) {
            case NO_GAME:
                new ShowDialog(parent, Titles.NO_GAME, Icons.BLOCK, Messages.NO_CLUE, false);
                break;
            case MANUAL:
                new ShowDialog(parent, Titles.MANUAL, Icons.MANUAL, Messages.MANUAL, true);
                break;
            case YOU_WELCOME:
                new ShowDialog(parent, Titles.YOU_WELCOME, Icons.THANKS, Messages.BYE, false);
                break;
            case NOT_KNOW:
                new ShowDialog(parent, Titles.NOT_FUNNY, Icons.FUNNY, Messages.NOT_KNOW, false);
                break;
            case EMPTY_STRING:
                new ShowDialog(parent, Titles.ERROR, Icons.ERROR, Messages.EMPTY_STRING, true);
                break;
            case LOW:
                new ShowDialog(parent, Titles.WRONG, Icons.LOW, Messages.LOW, false);
                break;
            case HIGH:
                new ShowDialog(parent, Titles.WRONG, Icons.HIGH, Messages.HIGH, false);
                break;
        }
    }

    /* ShowDialog с динамическим текстом */
    public static void create(View parent, DialogType type, int arg) {
        String dynamicText;

        switch (type) {
            case CHANGE_NUMBER:
                dynamicText = String.format(Messages.CHANGE_NUMBER, arg);
                new ShowDialog(parent, Titles.WRONG, Icons.QUESTION, dynamicText, true);
                break;
            case GAME_OVER:
                dynamicText = String.format(Messages.ANSWER, arg);
                new ShowDialog(parent, Titles.YOU_LOSE, Icons.BLOCK, dynamicText,
                        Buttons.REBOOT.toUpperCase(), Pos.CENTER, false);
                break;
            case VICTORY:
                dynamicText = String.format(Titles.VICTORY, arg);
                new ShowDialog(parent, dynamicText, Icons.VICTORY, Messages.HINT, true);
                break;
        }
    }

    /* RebootDialog */
    public static void createReboot(Controller controller, View parent) {
        new RebootDialog(controller, parent, Titles.REBOOT, Icons.REBOOT, Messages.REBOOT, true);
    }
}
