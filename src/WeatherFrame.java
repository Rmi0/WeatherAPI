import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

public class WeatherFrame extends JFrame {

    private Point mouse;

    public WeatherFrame() throws Exception {
        this.setTitle("WeatherAPI");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        Font font = Font.createFont(Font.TRUETYPE_FONT, WeatherFrame.class.getResourceAsStream("segoeuil.ttf"));

        int width = 720, height = 720;
        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(WeatherFrame.class.getResourceAsStream("bg.png")))));
        this.getContentPane().setMinimumSize(new Dimension(width, height));
        this.getContentPane().setMaximumSize(new Dimension(width, height));
        this.getContentPane().setPreferredSize(new Dimension(width, height));
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        JLabel topLabel = new JLabel(new ImageIcon(ImageIO.read(WeatherFrame.class.getResourceAsStream("top.png"))));
        topLabel.setSize(720,40);
        topLabel.setLocation(0,0);
        topLabel.setOpaque(true);
        topLabel.setBackground(new Color(50,50,70,255));
        topLabel.setForeground(Color.WHITE);
        topLabel.setFont(font.deriveFont(20f));
        this.mouse = null;
        topLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouse = new Point(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouse = null;
            }
        });
        topLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouse == null) return;
                setLocation(e.getXOnScreen()-mouse.x, e.getYOnScreen()-mouse.y);
            }
        });
        this.getContentPane().add(topLabel);

        JButton exitButton = new JButton("X");
        exitButton.setSize(40,40);
        exitButton.setLocation(680,0);
        exitButton.setOpaque(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(font.deriveFont(22f));
        exitButton.setFocusPainted(false);
        exitButton.setBorder(null);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        topLabel.add(exitButton);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setSize(540,25);
        cityLabel.setLocation(10,50);
        cityLabel.setOpaque(false);
        cityLabel.setForeground(Color.WHITE);
        cityLabel.setFont(font.deriveFont(20f));
        this.getContentPane().add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setSize(540,40);
        cityField.setLocation(10,80);
        cityField.setOpaque(false);
        cityField.setForeground(Color.WHITE);
        cityField.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        cityField.setFont(font.deriveFont(20f));
        cityField.setCaretColor(Color.WHITE);
        this.getContentPane().add(cityField);

        JLabel countryLabel = new JLabel("Country code:");
        countryLabel.setSize(150,40);
        countryLabel.setLocation(560,50);
        countryLabel.setOpaque(false);
        countryLabel.setForeground(Color.WHITE);
        countryLabel.setFont(font.deriveFont(20f));
        this.getContentPane().add(countryLabel);

        JTextField countryField = new JTextField("SK");
        countryField.setSize(150,40);
        countryField.setLocation(560,80);
        countryField.setOpaque(false);
        countryField.setForeground(Color.WHITE);
        countryField.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        countryField.setFont(font.deriveFont(20f));
        countryField.setCaretColor(Color.WHITE);
        this.getContentPane().add(countryField);

        JButton sendButton = new JButton("Search");
        sendButton.setSize(150,40);
        sendButton.setLocation(295,140);
        sendButton.setOpaque(false);
        sendButton.setForeground(Color.WHITE);
        sendButton.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        sendButton.setFont(font.deriveFont(20f));
        sendButton.setFocusPainted(false);
        sendButton.setContentAreaFilled(false);
        this.getContentPane().add(sendButton);

        JLabel cityNameLabel = new JLabel("---");
        cityNameLabel.setSize(700,30);
        cityNameLabel.setLocation(10,240);
        cityNameLabel.setOpaque(false);
        cityNameLabel.setForeground(Color.WHITE);
        cityNameLabel.setFont(font.deriveFont(25f));
        cityNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(cityNameLabel);

        JLabel temperatureLabel = new JLabel("---°C");
        temperatureLabel.setSize(150,60);
        temperatureLabel.setLocation(295,280);
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
                cityNameLabel.setText(cityField.getText()+", "+countryField.getText());
            }
        });

        JLabel statusLabel = new JLabel("Snow");
        statusLabel.setSize(150,30);
        statusLabel.setLocation(295,350);
        statusLabel.setOpaque(false);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(font.deriveFont(25f));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(statusLabel);
    }

}
