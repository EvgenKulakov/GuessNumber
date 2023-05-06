package game.controller;

import game.MainClass;
import game.model.*;
import game.view.View;
import game.view.ShowDialog;

public class Controller {
    private final View view;
    private final Model model;
    private int useNumber;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /* левая кнопка */
    public void actionLeftButton(String buttonText) {
        switch (buttonText) {
            case Buttons.NO_GAME:
                DialogFactory.create(DialogType.NO_GAME);
                break;
            case Buttons.MANUAL:
                DialogFactory.create(DialogType.MANUAL);
                break;
            case Buttons.THANKS:
                DialogFactory.create(DialogType.YOU_WELCOME);
                break;
        }
    }

    /* центральная кнопка */
    public void actionCenterButton() {
        DialogFactory.create(DialogType.REBOOT);
    }

    /* правая кнопка */
    public void actionRightButton(String buttonText, String inputText) {
        switch (buttonText) {
            case Buttons.MOVE:
                parseAndMove(inputText);
                break;
            case Buttons.KNEW:
                DialogFactory.create(DialogType.NOT_KNOW);
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
            DialogFactory.create(DialogType.EMPTY_STRING);
        }
    }

    private void nextMove(int useNumber) {
        this.useNumber = useNumber;

        /* число уже было? */
        if (model.getUseNumbers().contains(useNumber)) {
            DialogFactory.create(DialogType.CHANGE_NUMBER);
            return;
        }

        /* увеличение количества попыток */
        model.incrementMoveNumber();

        /* проверка окончания игры */
        if (isGameOver()) return;

        /* действия после хода */
        if (model.getSecretNumber() < useNumber) {
            DialogFactory.create(DialogType.LOW);
        }
        if (model.getSecretNumber() > useNumber) {
            DialogFactory.create(DialogType.HIGH);
        }

        view.getTextLabel().setText(String.format(Messages.ENTER, model.getMoveNumber()));
        model.getUseNumbers().add(useNumber);
    }

    private boolean isGameOver() {
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
        DialogFactory.create(DialogType.GAME_OVER);
        repeatGame();
    }

    public void victory() {
        DialogFactory.create(DialogType.VICTORY);
        view.getTextLabel().setText(Messages.HINT_FINAL);
        view.getTextAndInputBox().getChildren().remove(1);
        view.getBottomBox().getChildren().remove(1);
        view.getButtonLeft().setPrefWidth(235);
        view.getButtonRight().setPrefWidth(235);
        view.getButtonLeft().setText(Buttons.THANKS);
        view.getButtonRight().setText(Buttons.KNEW);
    }

    public void repeatGame() {
        Model.notFirstGame();
        view.close();
        MainClass.startWindow();
    }

    public View getView() {
        return view;
    }
    public Model getModel() {
        return model;
    }
    public int getUseNumber() {
        return useNumber;
    }
}
