import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.awt.SunHints;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class WeatherFrame extends JFrame {

    private Point mouse;

    private JLabel cityNameLabel;
    private JLabel temperatureLabel;
    private JLabel statusLabel;
    private JLabel windSpeedValLabel;
    private JLabel humidityValLabel;
    private JLabel sunriseValLabel;
    private JLabel sunsetValLabel;
    private JLabel notificationLabel;
    private Timer t;
    private int count;

    public WeatherFrame() throws Exception {
        this.setTitle("Weather");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        Font font = Font.createFont(Font.TRUETYPE_FONT, WeatherFrame.class.getResourceAsStream("segoeuil.ttf"));

        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(WeatherFrame.class.getResourceAsStream("bg.png")))));
        this.getContentPane().setMinimumSize(new Dimension(720, 720));
        this.getContentPane().setMaximumSize(new Dimension(720, 720));
        this.getContentPane().setPreferredSize(new Dimension(720, 720));
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        BufferedImage top = ImageIO.read(WeatherFrame.class.getResourceAsStream("top.png"));
        Graphics2D topg2d = (Graphics2D) top.getGraphics();
        topg2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        topg2d.setColor(Color.WHITE);
        topg2d.setFont(font.deriveFont(22f));
        topg2d.drawString(this.getTitle(), 50, 28);

        JLabel topLabel = new JLabel(new ImageIcon(top));
        topLabel.setSize(720,40);
        topLabel.setLocation(0,0);
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
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(Color.WHITE);
            }
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
        cityField.setSelectionColor(new Color(0,100,195));
        cityField.setSelectedTextColor(Color.WHITE);
        this.getContentPane().add(cityField);

        JLabel countryLabel = new JLabel("Country code:");
        countryLabel.setSize(150,40);
        countryLabel.setLocation(560,50);
        countryLabel.setOpaque(false);
        countryLabel.setForeground(Color.WHITE);
        countryLabel.setFont(font.deriveFont(20f));
        this.getContentPane().add(countryLabel);

        JTextField countryField = new JTextField();
        countryField.setSize(150,40);
        countryField.setLocation(560,80);
        countryField.setOpaque(false);
        countryField.setForeground(Color.WHITE);
        countryField.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        countryField.setFont(font.deriveFont(20f));
        countryField.setCaretColor(Color.WHITE);
        countryField.setSelectionColor(new Color(0,100,195));
        countryField.setSelectedTextColor(Color.WHITE);
        countryField.setDocument(new PlainDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null) return;
                if ((getLength() + str.length()) <= 2) super.insertString(offs, str, a);
            }
        });
        countryField.setText("SK");
        this.getContentPane().add(countryField);

        JButton sendButton = new JButton("Search");
        sendButton.setSize(150,40);
        sendButton.setLocation(285,140);
        sendButton.setOpaque(false);
        sendButton.setForeground(Color.WHITE);
        sendButton.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        sendButton.setFont(font.deriveFont(20f));
        sendButton.setFocusPainted(false);
        sendButton.setContentAreaFilled(false);
        this.getContentPane().add(sendButton);

        this.cityNameLabel = new JLabel("---");
        cityNameLabel.setSize(700,30);
        cityNameLabel.setLocation(10,240);
        cityNameLabel.setOpaque(false);
        cityNameLabel.setForeground(Color.WHITE);
        cityNameLabel.setFont(font.deriveFont(20f));
        cityNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(cityNameLabel);

        this.temperatureLabel = new JLabel("---°C");
        temperatureLabel.setSize(150,60);
        temperatureLabel.setLocation(285,280);
        temperatureLabel.setOpaque(false);
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setFont(font.deriveFont(48f));
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        temperatureLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(temperatureLabel);

        APIRequest request = new APIRequest(null, this);
        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendButton.setForeground(Color.CYAN);
                sendButton.setBorder(new MatteBorder(0,0,1,0,Color.CYAN));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendButton.setForeground(Color.WHITE);
                sendButton.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String country = countryField.getText().length()==2?","+countryField.getText():"";
                    String url = "http://api.openweathermap.org/data/2.5/weather?q="+cityField.getText()+country+
                            "&appid=84b509b8771fd1e2aabb24e5af885bf9";
                    request.setHtmlRequest(new HTMLRequest(url));
                    request.update();
                } catch (Exception ex) {ex.printStackTrace();}
            }
        });

        this.statusLabel = new JLabel("---");
        statusLabel.setSize(150,30);
        statusLabel.setLocation(285,350);
        statusLabel.setOpaque(false);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(font.deriveFont(20f));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(statusLabel);

        //DETAILS

        JLabel windSpeedTopLabel = new JLabel("Wind speed");
        windSpeedTopLabel.setSize(340,30);
        windSpeedTopLabel.setLocation(10,420);
        windSpeedTopLabel.setOpaque(false);
        windSpeedTopLabel.setForeground(Color.WHITE);
        windSpeedTopLabel.setFont(font.deriveFont(25f));
        windSpeedTopLabel.setHorizontalAlignment(SwingConstants.CENTER);
        windSpeedTopLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(windSpeedTopLabel);

        this.windSpeedValLabel = new JLabel("---");
        windSpeedValLabel.setSize(340,30);
        windSpeedValLabel.setLocation(10,450);
        windSpeedValLabel.setOpaque(false);
        windSpeedValLabel.setForeground(Color.WHITE);
        windSpeedValLabel.setFont(font.deriveFont(20f));
        windSpeedValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        windSpeedValLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(windSpeedValLabel);

        JLabel humidityTopLabel = new JLabel("Humidity");
        humidityTopLabel.setSize(340,30);
        humidityTopLabel.setLocation(370,420);
        humidityTopLabel.setOpaque(false);
        humidityTopLabel.setForeground(Color.WHITE);
        humidityTopLabel.setFont(font.deriveFont(25f));
        humidityTopLabel.setHorizontalAlignment(SwingConstants.CENTER);
        humidityTopLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(humidityTopLabel);

        this.humidityValLabel = new JLabel("---");
        humidityValLabel.setSize(340,30);
        humidityValLabel.setLocation(370,450);
        humidityValLabel.setOpaque(false);
        humidityValLabel.setForeground(Color.WHITE);
        humidityValLabel.setFont(font.deriveFont(20f));
        humidityValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        humidityValLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(humidityValLabel);

        JLabel sunriseTopLabel = new JLabel("Sunrise");
        sunriseTopLabel.setSize(340,30);
        sunriseTopLabel.setLocation(10,570);
        sunriseTopLabel.setOpaque(false);
        sunriseTopLabel.setForeground(Color.WHITE);
        sunriseTopLabel.setFont(font.deriveFont(25f));
        sunriseTopLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sunriseTopLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(sunriseTopLabel);

        this.sunriseValLabel = new JLabel("---");
        sunriseValLabel.setSize(340,30);
        sunriseValLabel.setLocation(10,600);
        sunriseValLabel.setOpaque(false);
        sunriseValLabel.setForeground(Color.WHITE);
        sunriseValLabel.setFont(font.deriveFont(20f));
        sunriseValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sunriseValLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(sunriseValLabel);

        JLabel sunsetTopLabel = new JLabel("Sunset");
        sunsetTopLabel.setSize(340,30);
        sunsetTopLabel.setLocation(370,570);
        sunsetTopLabel.setOpaque(false);
        sunsetTopLabel.setForeground(Color.WHITE);
        sunsetTopLabel.setFont(font.deriveFont(25f));
        sunsetTopLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sunsetTopLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(sunsetTopLabel);

        this.sunsetValLabel = new JLabel("---");
        sunsetValLabel.setSize(340,30);
        sunsetValLabel.setLocation(370,600);
        sunsetValLabel.setOpaque(false);
        sunsetValLabel.setForeground(Color.WHITE);
        sunsetValLabel.setFont(font.deriveFont(20f));
        sunsetValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sunsetValLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(sunsetValLabel);

        this.notificationLabel = new JLabel();
        notificationLabel.setSize(720,40);
        notificationLabel.setLocation(0,680);
        notificationLabel.setOpaque(false);
        this.getContentPane().add(notificationLabel);

        this.count = 0;
        this.t = null;

    }

    public void setCityName(String cityName) {
        this.cityNameLabel.setText(cityName);
    }

    public void setTemperature(double kelvin) {
        double celsius = kelvin-273.15;
        this.temperatureLabel.setText((int)Math.floor(celsius+0.5)+"°C");
    }

    public void setStatus(String status) {
        this.statusLabel.setText(status);
    }

    public void setWindSpeed(double speedms) {
        this.windSpeedValLabel.setText(speedms+" m/s");
    }

    public void setHumidity(long humidity) {
        this.humidityValLabel.setText(humidity+"%");
    }

    public void setSunrise(long s) {
        this.sunriseValLabel.setText(new SimpleDateFormat("HH:mm").format(new Date(s*1000)));
    }

    public void setSunset(long s) {
        this.sunsetValLabel.setText(new SimpleDateFormat("HH:mm").format(new Date(s*1000)));
    }

    public void sendNotification(Image img) {
        this.notificationLabel.setIcon(new ImageIcon(img));
        if (t != null) t.stop();
        this.count = 0;
        this.t = new Timer(15, e ->{
            count++;
            float alpha = 1-count*0.005f;
            if (alpha <= 0) {
                this.t.stop();
                this.count = 0;
                this.notificationLabel.setIcon(null);
                return;
            }
            BufferedImage newImg = new BufferedImage(720,40,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) newImg.getGraphics();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(img, 0,0, null);
            this.notificationLabel.setIcon(new ImageIcon(newImg));
        });
        this.t.start();
    }

}
