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
    private final ImageIcon img = new ImageIcon(getClass().getResource(Messages.IMAGINE));
    private final JLabel labelImg = new JLabel(img, JLabel.CENTER);
    private final JLabel labelText = new JLabel();
    private final JButton buttonLeft = new JButton();
    private final JButton buttonRight = new JButton();
    private final JTextField inputText = new JTextField(3);

    public View() {
        initView();
    }

    private void initView() {
        // Инициализация главного окна
        setTitle(Messages.MAIN_WINDOW);
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
        labelText.setText(Messages.START_TEXT);
        panelText.add(labelText);

        inputText.setHorizontalAlignment(JTextField.CENTER);
        inputText.setVisible(false);
        panelText.add(inputText);

        panelMain.add(panelText, BorderLayout.CENTER);

        // Панель с кнопками
        buttonLeft.setText(Messages.BUTTON_NO_GAME);
        String buttonRightText = Model.isNotFirstGame()
                ? Messages.BUTTON_PLAY_MORE
                : Messages.BUTTON_LETS_GAME;
        buttonRight.setText(buttonRightText);
        buttonLeft.setPreferredSize(new Dimension(150, 50));
        buttonRight.setPreferredSize(new Dimension(150, 50));

        // слушатели для кнопок
        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.actionLeftButton(buttonLeft.getText());
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.actionRightButton(buttonRight.getText());
            }
        });

        panelButton.add(buttonLeft);
        panelButton.add(buttonRight);
        panelMain.add(panelButton, BorderLayout.PAGE_END);

        getContentPane().add(panelMain);
        revalidate();
    }

     public void initStartGame() {
        buttonLeft.setText(Messages.BUTTON_MANUAL);
        buttonRight.setText(Messages.BUTTON_FORTH);
        labelText.setText(String.format(Messages.ENTER_NUMBER, 1));
        inputText.setVisible(true);
        // можно нажать на Enter вместо правой кнопки
        inputText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.actionRightButton(buttonRight.getText());
            }
        });
        revalidate();
    }

    public void showDialog(String title, String message, int jOptionPane) {
        JOptionPane.showMessageDialog(getContentPane(),
                message, title, jOptionPane);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public JButton getButtonRight() {
        return buttonRight;
    }
    public JButton getButtonLeft() {
        return buttonLeft;
    }
    public JLabel getLabelText() {
        return labelText;
    }
    public JTextField getInputText() {
        return inputText;
    }
}
