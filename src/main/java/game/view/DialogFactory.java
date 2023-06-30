package game.view;

import game.model.*;
import javafx.geometry.Pos;

public class DialogFactory {
    private static Model model;

    public static ShowDialog create(DialogType type) {
        String dynamicText;

        switch (type) {
            case NO_GAME:
                return new ShowDialog(Titles.NO_GAME, Icons.BLOCK, Messages.NO_CLUE, DialogSize.FIRST_SIZE);
            case MANUAL:
                return new ShowDialog(Titles.MANUAL, Icons.MANUAL, Messages.MANUAL, DialogSize.FOURTH_SIZE);
            case YOU_WELCOME:
                return new ShowDialog(Titles.YOU_WELCOME, Icons.THANKS, Messages.BYE, DialogSize.FIRST_SIZE);
            case NOT_KNOW:
                return new ShowDialog(Titles.NOT_FUNNY, Icons.FUNNY, Messages.NOT_KNOW, DialogSize.FIRST_SIZE);
            case LOW:
                return new ShowDialog(Titles.WRONG, Icons.LOW, Messages.LOW, DialogSize.FIRST_SIZE);
            case HIGH:
                return new ShowDialog(Titles.WRONG, Icons.HIGH, Messages.HIGH, DialogSize.FIRST_SIZE);
            case CHANGE_NUMBER:
                dynamicText = String.format(Messages.CHANGE_NUMBER, model.getUseNumber());
                return new ShowDialog(Titles.WRONG, Icons.QUESTION, dynamicText, DialogSize.SECOND_SIZE);
            case GAME_OVER:
                dynamicText = String.format(Messages.ANSWER, model.getSecretNumber());
                return new ShowDialog(Titles.YOU_LOSE, Icons.BLOCK, dynamicText,
                        Buttons.REBOOT.toUpperCase(), Pos.CENTER, DialogSize.FIRST_SIZE);
            case VICTORY:
                dynamicText = String.format(Titles.VICTORY, model.getSecretNumber());
                return new ShowDialog(dynamicText, Icons.VICTORY, Messages.HINT, DialogSize.SECOND_SIZE);
            case REBOOT:
                return new RebootDialog(Titles.REBOOT, Icons.REBOOT, Messages.REBOOT, DialogSize.THIRD_SIZE);
            case EMPTY_STRING:
            default:
                return new ShowDialog(Titles.ERROR, Icons.ERROR, Messages.EMPTY_STRING, DialogSize.SECOND_SIZE);
        }
    }

    public static void setModel(Model model) {
        DialogFactory.model = model;
    }
}
