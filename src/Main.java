import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new WeatherFrame().setVisible(true);
    }

}
