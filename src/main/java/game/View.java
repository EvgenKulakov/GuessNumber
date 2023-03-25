package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Controller controller;
    private final JPanel panelMain = new JPanel();
    private final JPanel panelImg = new JPanel();
    private final JPanel panelText = new JPanel();
    private final JPanel panelButton = new JPanel();
    private final ImageIcon img = new ImageIcon(
            "C:\\Users\\Stvolya\\IdeaProjects\\QuessTheNumber\\src\\main\\resources\\Мистер Число3.png");
    private final JLabel labelImg = new JLabel(img, JLabel.CENTER);
    private final JLabel labelText = new JLabel();
    private final JButton buttonLeft = new JButton();
    private final JButton buttonRight = new JButton();
    private final JTextField inputText = new JTextField(3);

    public View() {
        initView();
    }

    public void initView() {
        // Инициализация главного окна
        setTitle("Угадай число");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setIconImage(img.getImage());

        // Панель с картинкой
        panelImg.add(labelImg);
        panelMain.add(panelImg, BorderLayout.PAGE_START);

        // Панель с текстом
        labelText.setText("<html><center>Я загадаю любое число от 0 до 999.</center>"
                + "<br>Отгадаешь число с 10 попыток, получишь подсказку! Начнём?</html>");
        panelText.add(labelText);

        inputText.setHorizontalAlignment(JTextField.CENTER);
        inputText.setVisible(false);
        panelText.add(inputText);

        panelMain.add(panelText, BorderLayout.CENTER);

        // Панель с кнопками
        buttonLeft.setText("Пожалуй, не стоит");
        buttonRight.setText("Давай сыграем!");
        buttonLeft.setPreferredSize(new Dimension(150, 50));
        buttonRight.setPreferredSize(new Dimension(150, 50));

        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonLeft.getText().equals("Пожалуй, не стоит")) {
                    errorMessage(buttonLeft.getText(), "Нет игры - нет подсказки!");
                } else {
                    showDialog(buttonLeft.getText(),
                            "Компьютер загадал рандомное число от 0 до 999,\n" +
                                    "у вас всего 10 попыток, чтобы его отгадать.\n" +
                                    "Если у вас не получится, то компьютер загадает новое число\n" +
                                    "и у вас снова будет 10 попыток...");
                }
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonRight.getText().equals("Далее")) {
                    nextMove();
                } else {
                    startGame();
                }
            }
        });

        panelButton.add(buttonLeft);
        panelButton.add(buttonRight);
        panelMain.add(panelButton, BorderLayout.PAGE_END);

        getContentPane().add(panelMain);
        revalidate();
    }

    void startGame() {
        initStartGame();
        controller.startGame();
    }

    void initStartGame() {
        buttonLeft.setText("Инструкция");
        buttonRight.setText("Далее");
        labelText.setText("<html><center>Попытка №1, введи число:</center></html>");
        inputText.setVisible(true);
        inputText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextMove();
            }
        });
        revalidate();
    }

    private void nextMove() {
        String text = inputText.getText().trim();
        try {
            controller.nextMove(Integer.parseInt(text));
        } catch (NumberFormatException ignored) {
            errorMessage("Ошибка", "Ты, наверно, ошибся. Нужно ввести цифры.");
        }
    }

    public void repeatGame() {
        View newView = new View();
        Controller newController = new Controller(newView);
        newView.setController(newController);
        newView.buttonRight.setText("Играть ещё!");
        this.dispose();
    }

    public void showDialog(String title, String message) {
        JOptionPane.showMessageDialog(getContentPane(),
                message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void wrongMessage(String message, int count) {
        JOptionPane.showMessageDialog(getContentPane(),
                message, "Неправильно", JOptionPane.WARNING_MESSAGE);
        labelText.setText("<html><center>Попытка №" + count + " введи число:</center></html>");
    }

    public void errorMessage(String title, String message) {
        JOptionPane.showMessageDialog(getContentPane(),
                message, title, JOptionPane.ERROR_MESSAGE);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
