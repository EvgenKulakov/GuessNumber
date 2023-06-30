package game.controller;

import game.model.*;
import game.view.DialogFactory;
import game.view.ShowDialog;
import game.view.View;
import javafx.stage.Stage;

public class Controller {
    private final Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /* левая кнопка */
    public void actionLeftButton(String buttonText) {
        switch (buttonText) {
            case Buttons.NO_GAME:
                DialogFactory.create(DialogType.NO_GAME).showAndWait();
                break;
            case Buttons.MANUAL:
                DialogFactory.create(DialogType.MANUAL).showAndWait();
                break;
            case Buttons.THANKS:
                DialogFactory.create(DialogType.YOU_WELCOME).showAndWait();
                break;
        }
    }

    /* центральная кнопка */
    public void actionCenterButton() {
        DialogFactory.create(DialogType.REBOOT).showAndWait();
    }

    /* правая кнопка */
    public void actionRightButton(String buttonText, String inputText) {
        switch (buttonText) {
            case Buttons.MOVE:
                parseAndMove(inputText);
                break;
            case Buttons.KNOW:
                DialogFactory.create(DialogType.NOT_KNOW).showAndWait();
                break;
            case Buttons.LETS_GAME:
            case Buttons.PLAY_MORE:
                view.renderStartGame();
                break;
        }
    }

    public void parseAndMove(String inputText) {
        if (!inputText.isEmpty()) {
            nextMove(Integer.parseInt(inputText));
        } else {
            DialogFactory.create(DialogType.EMPTY_STRING).showAndWait();
        }
    }

    private void nextMove(int useNumber) {
        model.setUseNumber(useNumber);

        if (model.getUseNumbers().contains(useNumber)) {
            DialogFactory.create(DialogType.CHANGE_NUMBER).showAndWait();
            return;
        }

        model.incrementMoveNumber();

        if (isGameOver()) return;

        if (model.getSecretNumber() < useNumber) {
            DialogFactory.create(DialogType.LOW).showAndWait();
        }
        if (model.getSecretNumber() > useNumber) {
            DialogFactory.create(DialogType.HIGH).showAndWait();
        }

        /* ход состоялся: изменение главного текста и сохранение использованного числа */
        view.getText().setText(String.format(Messages.ENTER, model.getMoveNumber()));
        model.getUseNumbers().add(useNumber);
    }

    private boolean isGameOver() {
        boolean end = false;

        if (model.getMoveNumber() > 10 && model.getSecretNumber() != model.getUseNumber()) {
            gameOver();
            end = true;
        }

        if (model.getSecretNumber() == model.getUseNumber()) {
            victory();
            end = true;
        }

        return end;
    }

    public void gameOver() {
        DialogFactory.create(DialogType.GAME_OVER).showAndWait();
        repeatGame();
    }

    public void victory() {
        DialogFactory.create(DialogType.VICTORY).showAndWait();
        view.victoryRendering();
    }

    public void repeatGame() {
        model.resetModel();
        double pastX = view.getStage().getX();
        double pastY = view.getStage().getY();
        view.getStage().close();
        View newView = new View(new Stage(), pastX, pastY, model, this);
        this.view = newView;
        ShowDialog.setParentWindow(newView);
    }
}
