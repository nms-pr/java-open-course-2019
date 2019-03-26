package ru.mail.polis.open.task4;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGui extends JFrame {
    private Parser parser;
    private JPanel panel; // jpanel общая
    private JPanel buttonPanel; // jpanel кнопок
    private JTextField display; // поле текста калькулятора


    public CalculatorGui() {
        super("Calculator");
        setBounds(300, 300, 240, 170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        parser = new Parser();

        panel = new JPanel();
        panel.setLayout(new BorderLayout());


        display = new JTextField(21);
        display.setHorizontalAlignment(JTextField.RIGHT);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,5));

        ActionListener insert = new InsertAction();
        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);

        ActionListener command = new CommandAction();
        addButton("C", command);
        addButton("*", command);

        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);
        addButton("/", command);
        addButton("+", command);

        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);
        addButton("-", command);
        addButton("^", command);

        addButton("0", insert);
        addButton("(", insert);
        addButton(")", insert);
        addButton("=", command);


        panel.add("North", display);
        panel.add("Center", buttonPanel);


        add(panel);
    }

    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        buttonPanel.add(button);
    }

    private class InsertAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            display.setText(display.getText() + input);
        }
    }

    private class CommandAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            if (command.equals("C")) {
                display.setText("");
            } else if (command.equals("=")) {
                String s = display.getText();
                int res = parser.build(s).evaluate();
                display.setText(String.valueOf(res));
            } else {
                display.setText(display.getText() + command);
            }
        }
    }

}
