import game.Controller;
import game.model.Model;
import game.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestVictory extends Application {

    @Override
    public void start(Stage primaryStage) {
        startWindow(primaryStage);
    }

    public static void startWindow(Stage primaryStage) {
        View view = new View(primaryStage);
        Model model = new Model();
        Controller controller = new Controller(view, model);
        view.setController(controller);

        view.initStartGame();
        controller.victory();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
