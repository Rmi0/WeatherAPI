import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Notification {

    private Notification() { }

    public static BufferedImage create(String message, Color textColor,Color indicator) throws Exception {
        BufferedImage img = ImageIO.read(Notification.class.getResourceAsStream("notification.png"));

        Graphics2D g2d = (Graphics2D) img.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(indicator);
        g2d.fillRect(0,0,6,40);


        g2d.setColor(textColor);
        Font font = Font.createFont(Font.TRUETYPE_FONT, WeatherFrame.class.getResourceAsStream("segoeuil.ttf"));
        g2d.setFont(font.deriveFont(30f));
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(message, 10, 40-fm.getHeight()/4);

        return img;
    }

}
