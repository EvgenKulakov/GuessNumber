package game.controller;

import game.MainClass;
import game.model.*;
import game.view.View;
import game.view.ShowDialog;

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
                DialogFactory.create(view, DialogType.NO_GAME);
                break;
            case Buttons.MANUAL:
                DialogFactory.create(view, DialogType.MANUAL);
                break;
            case Buttons.THANKS:
                DialogFactory.create(view, DialogType.YOU_WELCOME);
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
                DialogFactory.create(view, DialogType.NOT_KNOW);
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
            DialogFactory.create(view, DialogType.EMPTY_STRING);
        }
    }

    private void nextMove(int useNumber) {
        /* число уже было? */
        if (model.getUseNumbers().contains(useNumber)) {
            DialogFactory.create(view, DialogType.CHANGE_NUMBER, useNumber);
            return;
        }

        /* увеличение количества попыток */
        model.incrementMoveNumber();

        /* проверка окончания игры */
        if (isGameOver(useNumber)) return;

        /* действия после хода */
        if (model.getSecretNumber() < useNumber) {
            DialogFactory.create(view, DialogType.LOW);
        }
        if (model.getSecretNumber() > useNumber) {
            DialogFactory.create(view, DialogType.HIGH);
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
        DialogFactory.create(view, DialogType.GAME_OVER, model.getSecretNumber());
        repeatGame();
    }

    public void victory() {
        DialogFactory.create(view, DialogType.VICTORY, model.getSecretNumber());
        view.getTextLabel().setText(Messages.HINT_FINAL);
        view.getTextAndInputBox().getChildren().remove(1);
        view.getButtonLeft().setText(Buttons.THANKS);
        view.getButtonRight().setText(Buttons.KNEW);
    }

    public void repeatGame() {
        Model.notFirstGame();
        view.close();
        MainClass.startWindow();
    }
}
