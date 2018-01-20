import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class WeatherFrame extends JFrame {

    public WeatherFrame() throws Exception {
        this.setTitle("WeatherAPI");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Font font = Font.createFont(Font.TRUETYPE_FONT, WeatherFrame.class.getResourceAsStream("segoeuil.ttf"));

        int width = 720, height = 720;
        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(WeatherFrame.class.getResourceAsStream("bg1.png")))));
        this.getContentPane().setMinimumSize(new Dimension(width, height));
        this.getContentPane().setMaximumSize(new Dimension(width, height));
        this.getContentPane().setPreferredSize(new Dimension(width, height));
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setSize(540,25);
        cityLabel.setLocation(10,10);
        cityLabel.setOpaque(false);
        cityLabel.setForeground(Color.WHITE);
        cityLabel.setFont(font.deriveFont(20f));
        this.getContentPane().add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setSize(540,40);
        cityField.setLocation(10,40);
        cityField.setOpaque(false);
        cityField.setForeground(Color.WHITE);
        cityField.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        cityField.setFont(font.deriveFont(20f));
        cityField.setCaretColor(Color.WHITE);
        this.getContentPane().add(cityField);

        JLabel countryLabel = new JLabel("Country code:");
        countryLabel.setSize(150,40);
        countryLabel.setLocation(560,10);
        countryLabel.setOpaque(false);
        countryLabel.setForeground(Color.WHITE);
        countryLabel.setFont(font.deriveFont(20f));
        this.getContentPane().add(countryLabel);

        JTextField countryField = new JTextField("SK");
        countryField.setSize(150,40);
        countryField.setLocation(560,40);
        countryField.setOpaque(false);
        countryField.setForeground(Color.WHITE);
        countryField.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        countryField.setFont(font.deriveFont(20f));
        countryField.setCaretColor(Color.WHITE);
        this.getContentPane().add(countryField);

        JButton sendButton = new JButton("Search");
        sendButton.setSize(150,40);
        sendButton.setLocation(295,100);
        sendButton.setOpaque(false);
        sendButton.setForeground(Color.WHITE);
        sendButton.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        sendButton.setFont(font.deriveFont(20f));
        sendButton.setFocusPainted(false);
        sendButton.setContentAreaFilled(false);
        this.getContentPane().add(sendButton);

        JLabel cityInfoLabel = new JLabel("Presov, SK");
        cityInfoLabel.setSize(150,30);
        cityInfoLabel.setLocation(295,195);
        cityInfoLabel.setOpaque(false);
        cityInfoLabel.setForeground(Color.WHITE);
        cityInfoLabel.setFont(font.deriveFont(25f));
        cityInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityInfoLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(cityInfoLabel);

        JLabel temperatureLabel = new JLabel("20°C");
        temperatureLabel.setSize(150,150);
        temperatureLabel.setLocation(295,230);
        temperatureLabel.setOpaque(false);
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setFont(font.deriveFont(48f));
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        temperatureLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(temperatureLabel);

        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendButton.setForeground(Color.CYAN);
                sendButton.setBorder(new MatteBorder(0,0,1,0,Color.CYAN));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendButton.setForeground(Color.WHITE);
                sendButton.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                temperatureLabel.setText(""+new Random().nextInt(30)+"°C");
                cityInfoLabel.setText(cityField.getText()+", "+countryField.getText());
            }
        });
    }

}
