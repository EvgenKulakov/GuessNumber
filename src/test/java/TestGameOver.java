import game.controller.Controller;
import game.view.DialogFactory;
import game.model.Model;
import game.view.RebootDialog;
import game.view.ShowDialog;
import game.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestGameOver extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();
        View view = new View(primaryStage, model);
        Controller controller = new Controller(model, view);
        view.setController(controller);
        ShowDialog.setParentWindow(view);
        RebootDialog.setController(controller);
        DialogFactory.setModel(model);

        view.renderStartGame();
        controller.gameOver();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
