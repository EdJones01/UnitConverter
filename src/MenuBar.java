import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    public MenuBar(ActionListener actionListener) {
        JMenu menu = new JMenu("Change unit");

        menu.add(new CustomMenuItem("Length", actionListener));
        menu.add(new CustomMenuItem("Temperature", actionListener));
        menu.add(new CustomMenuItem("Time", actionListener));
        //menu.add(new CustomMenuItem("Area", actionListener));
        //menu.add(new CustomMenuItem("Volume", actionListener));
        //menu.add(new CustomMenuItem("Mass", actionListener));
        add(menu);
    }
}

class CustomMenuItem extends JMenuItem {
    public CustomMenuItem(String title, ActionListener e) {
        super(title);
        addActionListener(e);
        setActionCommand(title.toLowerCase());
    }
}