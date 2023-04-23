package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application {

    @Override
    public void start(Stage primaryStage) {
        startWindow(primaryStage);
    }

    public static void startWindow(Stage primaryStage) {
        View view = new View(primaryStage);
        Model model = new Model();
        Controller controller = new Controller(view, model);
        view.setController(controller);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
