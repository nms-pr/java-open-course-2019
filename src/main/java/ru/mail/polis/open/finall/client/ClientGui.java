package ru.mail.polis.open.finall.client;

import ru.mail.polis.open.finall.network.Connection;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;

public class ClientGui extends JFrame implements ClientInterface {

    Logger logger = Logger.getLogger(ClientGui.class.getName());

    private static final int WIDTH = 500;
    private static final int HEIGT = 600;

    private final JTextArea log = new JTextArea();
    private final JLabel textFieldNickName = new JLabel("");
    private final JTextField textFieldInput = new JTextField();
    private final JTextField textNameInput = new JTextField();
    private final String nickName = "Nick Name";
    private final JLabel labelName = new JLabel("Enter " + nickName);
    private final JLabel labelMes = new JLabel("Message: ");

    private Connection connection;

    private boolean visible = false;

    public ClientGui() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        log.setEditable(false);
        log.setLineWrap(true);

        enterName();
        setLayout(null);

        textFieldNickName.setSize(100, 20);
        textFieldNickName.setLocation(80, 10);
        log.setSize(400, 460);
        log.setLocation(30, 50);
        labelMes.setSize(80, 20);
        labelMes.setLocation(10, 520);
        textFieldInput.setSize(350, 20);
        textFieldInput.setLocation(80, 520);

        add(textFieldNickName);
        add(log);
        add(labelMes);
        add(textFieldInput);

        textFieldInput.setVisible(visible);
        log.setVisible(visible);
        textFieldNickName.setVisible(visible);
        labelMes.setVisible(visible);


        setVisible(true);
    }

    private void enterName() {

        labelName.setSize(100, 20);
        labelName.setLocation(100, 100);
        add(labelName);

        textNameInput.setSize(80, 20);
        textNameInput.setLocation(200, 100);
        add(textNameInput);

        textNameInput.addActionListener(e -> {
            String msg = textNameInput.getText();
            if (msg.equals("")) {
                return;
            }

            textFieldNickName.setText(msg);
            visible = true;
            textFieldInput.setVisible(true);
            log.setVisible(visible);
            textFieldNickName.setVisible(visible);
            labelMes.setVisible(visible);
            labelName.setLocation(10, 10);
            labelName.setText(nickName);
            textNameInput.setVisible(false);

        });
    }

    @Override
    public void addConnection(Connection connection) {
        this.connection = connection;
        textFieldInput.addActionListener(e -> {
            String msg = textFieldInput.getText();
            if (msg.equals("")) {
                return;
            }
            textFieldInput.setText(null);
            connection.sendString(textFieldNickName.getText() + ":  " + msg);

        });
    }

    @Override
    public void printMsg(String msg) {

        SwingUtilities.invokeLater(() -> {
            log.append(msg + "\n");
            log.setCaretPosition(log.getDocument().getLength());

            logger.info(msg);
        });
    }

    @Override
    public void getText() {
        System.out.println("TEXT Gui !!!!");
    }

}
