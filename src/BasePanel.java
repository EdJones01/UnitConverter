import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BasePanel extends JPanel implements ActionListener {
    private double[] ratioToUnitZero;

    private final NumericTextField inputTextField;
    private final JTextField outputTextField;
    private final JList<String> inputList;
    private final JList<String> outputList;
    private int inputIndex = 0;
    private int outputIndex = 1;

    public BasePanel(String[] units, double[] ratioToUnitZero) {
        this.ratioToUnitZero = ratioToUnitZero;

        setLayout(new BorderLayout());

        outputTextField = new JTextField();
        outputTextField.setEditable(false);
        inputTextField = new NumericTextField(this);
        inputTextField.setText("1");

        inputList = new JList<>(units);
        inputList.setSelectedIndex(0);
        outputList = new JList<>(units);
        outputList.setSelectedIndex(1);

        inputList.addListSelectionListener(e -> {
            inputIndex = inputList.getSelectedIndex();
            calculate();
        });

        outputList.addListSelectionListener(e -> {
            outputIndex = outputList.getSelectedIndex();
            calculate();
        });

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(inputTextField);
        topPanel.add(outputTextField);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(new JScrollPane(inputList));
        bottomPanel.add(new JScrollPane(outputList));
        add(bottomPanel, BorderLayout.CENTER);

        calculate();
    }

    public void changeUnits(String[] units, double[] ratioToUnitZero) {
        inputList.setListData(units);
        inputList.setSelectedIndex(0);
        outputList.setListData(units);
        outputList.setSelectedIndex(1);
        this.ratioToUnitZero = ratioToUnitZero;
        inputTextField.setText("1");
        calculate();
    }

    private String formatDouble(String input) {
        if (input.endsWith(".0")) {
            return input.substring(0, input.length() - 2);
        }
        return input;
    }

    private void calculate() {
        try {
            double inputToUnitZero = Double.parseDouble(inputTextField.getText()) / ratioToUnitZero[inputIndex];
            double output = inputToUnitZero * ratioToUnitZero[outputIndex];
            outputTextField.setText(formatDouble("" + output));
        } catch (Exception e) {
            outputTextField.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("calculate"))
            calculate();
    }
}