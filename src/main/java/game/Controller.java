package game;

import javax.swing.*;
import java.util.HashSet;

public class Controller {
    private final View view;
    private final Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void startGame() {
        view.initStartGame();
        model.setStopGame(false);
        model.setSecretNumber(model.createSecretNumber());
        model.setCount(1);
        model.setUseNumbers(new HashSet<>(10));
    }

    public void nextMove(int useNumber) {
        if (model.isStopGame()) {
            view.showDialog(Messages.SECOND_VICTORY, Messages.SECOND_HINT,
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (useNumber > 999 || useNumber < 0) {
            view.showDialog(Messages.ERROR, Messages.ERROR_NUMBER,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (model.getUseNumbers().contains(useNumber)) {
            view.showDialog(Messages.WRONG,
                    String.format(Messages.CHANGE_NUMBER, useNumber),
                    JOptionPane.QUESTION_MESSAGE);
            return;
        }

        model.incrementCount();

        if (model.getCount() > 10 && model.getSecretNumber() != useNumber) {
            gameOver();
            return;
        }

        if (model.getSecretNumber() == useNumber) {
            model.setStopGame(true);
            view.showDialog(String.format(Messages.VICTORY, model.getSecretNumber()),
                    Messages.HINT,
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (model.getSecretNumber() < useNumber) {
            view.showDialog(Messages.WRONG, Messages.NUMBER_LESS,
                    JOptionPane.WARNING_MESSAGE);
        }

        if (model.getSecretNumber() > useNumber) {
            view.showDialog(Messages.WRONG, Messages.NUMBER_GREATER,
                    JOptionPane.WARNING_MESSAGE);
        }

        view.getLabelText().setText(String.format(Messages.ENTER_NUMBER, model.getCount()));
        model.getUseNumbers().add(useNumber);
    }

    private void gameOver() {
        view.showDialog(Messages.YOU_LOSE,
                String.format(Messages.CORRECT_ANSWER, model.getSecretNumber()),
                JOptionPane.ERROR_MESSAGE);
        repeatGame();
    }

    private void repeatGame() {
        View newView = new View();
        Model newModel = new Model();
        Controller newController = new Controller(newView, newModel);
        newModel.setController(newController);
        newView.setController(newController);
        newView.getButtonRight().setText(Messages.BUTTON_PLAY_MORE);
        view.dispose();
    }
}
