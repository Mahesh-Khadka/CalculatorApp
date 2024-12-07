package CalculatorApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNum = 0;
    private double secondNum = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    public CalculatorApp() {
        // Create the frame
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "C", "0", "=", "+", 
            "AC"
        };
        

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if ("0123456789".contains(command)) { 
        if (isOperatorPressed) {
            display.setText(command);
            isOperatorPressed = false;
        } else {
            display.setText(display.getText() + command);
        }
    } else if ("/*-+".contains(command)) { 
        firstNum = Double.parseDouble(display.getText());
        operator = command;
        isOperatorPressed = true;
    } else if ("=".equals(command)) { 
        secondNum = Double.parseDouble(display.getText());
        switch (operator) {
            case "+" -> display.setText(String.valueOf(firstNum + secondNum));
            case "-" -> display.setText(String.valueOf(firstNum - secondNum));
            case "*" -> display.setText(String.valueOf(firstNum * secondNum));
            case "/" -> display.setText(secondNum != 0 ? String.valueOf(firstNum / secondNum) : "Error");
        }
        isOperatorPressed = true;
    } else if ("C".equals(command)) { 
        // Clear current input
        display.setText("");
    } else if ("AC".equals(command)) { 
        // Clear everything
        display.setText("");
        firstNum = secondNum = 0;
        operator = "";
        isOperatorPressed = false;
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApp calculator = new CalculatorApp();
            calculator.setVisible(true);
        });
    }
}
