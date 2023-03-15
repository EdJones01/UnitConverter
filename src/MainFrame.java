import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    String[] lengthUnits = new String[]{"Kilometer", "Meter", "Centimeter", "Millimeter",
            "Micrometer", "Nanometer", "Mile", "Yard", "Foot", "Inch", "Nautical Mile", "Light Year"};
    double[] lengthRatioToUnitZero = new double[]{1, 1000, 100000, 1e+6, 1e+9, 1e+12, 0.621371, 1093.61,
            3280.84, 39370.1, 0.539957, 1.057e-13};

    String[] timeUnits = new String[]{"Second", "Millisecond", "Microsecond", "Nanosecond",
            "Picosecond", "Minute", "Hour", "Day", "Week", "Month", "Year"};
    double[] timeRatioToUnitZero = new double[]{1, 1000, 1e+6, 1e+9, 1e+12, 0.0166667, 0.000277778,
            1.1574083333e-5, 1.653440476142857e-6, 3.80517391202858972e-7, 3.170981735068493655e-8};

    String[] temperatureUnits = new String[]{"Celsius", "Kelvin", "Fahrenheit"};
    double[] temperatureRatioToUnitZero = new double[]{1, 274.15, 33.8};

    private BasePanel panel;

    public MainFrame() {
        setTitle("Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new MenuBar(this));

        panel = new BasePanel(timeUnits, timeRatioToUnitZero);
        panel.setPreferredSize(new Dimension(500, 300));
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("time")) {
            panel.changeUnits(timeUnits, timeRatioToUnitZero);
        }
        if (cmd.equals("length")) {
            panel.changeUnits(lengthUnits, lengthRatioToUnitZero);
        }
        if (cmd.equals("temperature")) {
            panel.changeUnits(temperatureUnits, temperatureRatioToUnitZero);
        }
    }
}