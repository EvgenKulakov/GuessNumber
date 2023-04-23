package game;

import javax.swing.*;
import javax.swing.border.Border;
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
        /* Инициализация главного окна */
        setTitle(Messages.MAIN_WINDOW);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setIconImage(img.getImage());

        /* Панель с картинкой */
        panelImg.add(labelImg);
        panelMain.add(panelImg, BorderLayout.PAGE_START);

        /* Панель с текстом */
        labelText.setText(Messages.START_TEXT);
        panelText.add(labelText);
        panelMain.add(panelText, BorderLayout.CENTER);

        /* Панель с кнопками */
        buttonLeft.setText(Messages.BUTTON_NO_GAME);
        String buttonRightText = Model.isNotFirstGame()
                ? Messages.BUTTON_PLAY_MORE
                : Messages.BUTTON_LETS_GAME;
        buttonRight.setText(buttonRightText);
        buttonLeft.setPreferredSize(new Dimension(150, 50));
        buttonRight.setPreferredSize(new Dimension(150, 50));

        /* слушатели для кнопок */
        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.actionLeftButton(buttonLeft.getText());
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.actionRightButton(buttonRight.getText(), inputText.getText());
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

        inputText.setHorizontalAlignment(JTextField.CENTER);
        /* фильтр ввода цифр */
        inputText.setDocument(new NumericDocument());
        /* можно нажать на Enter вместо правой кнопки */
        inputText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.parseAndMove(inputText.getText());
            }
        });
        panelText.add(inputText);

        /* рамка */
        Border borderLine = BorderFactory.createLineBorder(Color.darkGray, 2, true);
        Border borderEmpty = BorderFactory.createEmptyBorder(0, 3, 0, 3);
        Border borderCompound = BorderFactory.createCompoundBorder(borderLine, borderEmpty);
        panelText.setBorder(borderCompound);

        revalidate();
    }

    public void showDialog(String title, String message, int jOptionPane) {
        JOptionPane.showMessageDialog(getContentPane(),
                message, title, jOptionPane);
    }

    public void showDialog(String title, String message, String buttonText, int jOptionPane) {
        Object[] options = {buttonText};
        JOptionPane.showOptionDialog(
                getContentPane(),
                message, title,
                JOptionPane.DEFAULT_OPTION,
                jOptionPane, null,
                options, options[0]
        );
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
    public JPanel getPanelText() {
        return panelText;
    }
}
