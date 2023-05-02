package game;

import game.model.Buttons;
import game.model.Icons;
import game.model.Messages;
import game.model.Model;
import game.view.View;
import game.view.ShowDialog;
import javafx.geometry.Pos;

public class Controller {
    private final View view;
    private final Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /* левая кнопка */
    public void actionLeftButton(String buttonText) {
        switch (buttonText) {
            case Buttons.NO_GAME:
                new ShowDialog(view, Buttons.NO_GAME, Messages.NO_CLUE, Icons.BLOCK);
                break;
            case Buttons.MANUAL:
                new ShowDialog(view, Buttons.MANUAL, Messages.MANUAL, Icons.MANUAL);
                break;
            case Buttons.THANKS:
                new ShowDialog(view, Messages.YOU_WELCOME, Messages.BYE, Icons.THANKS);
                break;
        }
    }

    /* правая кнопка */
    public void actionRightButton(String buttonText, String inputText) {
        switch (buttonText) {
            case Buttons.MOVE:
                parseAndMove(inputText);
                break;
            case Buttons.KNEW:
                new ShowDialog(view, Messages.NOT_FUNNY, Messages.NOT_KNOW, Icons.FUNNY);
                break;
            case Buttons.LETS_GAME:
            case Buttons.PLAY_MORE:
                view.initStartGame();
                break;
        }
    }

    public void parseAndMove(String inputText) {
        if (!inputText.isEmpty()) {
            nextMove(Integer.parseInt(inputText));
        } else {
            new ShowDialog(view, Messages.ERROR, Messages.EMPTY_STRING, Icons.ERROR);
        }
    }

    private void nextMove(int useNumber) {
        /* число уже было? */
        if (model.getUseNumbers().contains(useNumber)) {
            new ShowDialog(view, Messages.WRONG,
                    String.format(Messages.CHANGE_NUMBER, useNumber),
                    Icons.QUESTION);
            return;
        }

        /* увеличение количества попыток */
        model.incrementMoveNumber();

        /* проверка окончания игры */
        if (isGameOver(useNumber)) return;

        /* действия после хода */
        if (model.getSecretNumber() < useNumber) {
            new ShowDialog(view, Messages.WRONG, Messages.NUMBER_LESS, Icons.LOW);
        }
        if (model.getSecretNumber() > useNumber) {
            new ShowDialog(view, Messages.WRONG, Messages.NUMBER_GREATER, Icons.HIGH);
        }

        view.getTextLabel().setText(String.format(Messages.ENTER, model.getMoveNumber()));
        model.getUseNumbers().add(useNumber);
    }

    private boolean isGameOver(int useNumber) {
        boolean end = false;

        if (model.getMoveNumber() > 10 && model.getSecretNumber() != useNumber) {
            gameOver();
            end = true;
        }

        if (model.getSecretNumber() == useNumber) {
            victory();
            end = true;
        }

        return end;
    }

    public void gameOver() {
        new ShowDialog(view, Messages.YOU_LOSE,
                String.format(Messages.ANSWER, model.getSecretNumber()),
                Buttons.REBOOT.toUpperCase(), Pos.CENTER, Icons.BLOCK);
        repeatGame();
    }

    public void victory() {
        new ShowDialog(view,
                String.format(Messages.VICTORY, model.getSecretNumber()),
                Messages.HINT, Icons.VICTORY);
        view.getTextLabel().setText(Messages.HINT);
        view.getInputText().setVisible(false);
        view.getButtonLeft().setText(Buttons.THANKS);
        view.getButtonRight().setText(Buttons.KNEW);
    }

    public void repeatGame() {
        Model.notFirstGame();
        view.close();
        MainClass.startWindow();
    }
}
