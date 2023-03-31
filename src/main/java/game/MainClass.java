package game;

public class MainClass {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        View view = new View();
        Model model = new Model();
        Controller.setGame(new Controller(view, model));
        view.setController(Controller.getGame());
    }
}
