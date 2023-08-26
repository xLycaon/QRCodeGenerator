import java.awt.Color;
import java.util.Random;

public class ColorPalette {
    public static final Color[] COLORS = {
        new Color(148, 0, 211), // Indigo
        new Color(0, 0, 255),   // Blue
        new Color(0, 255, 0),   // Green
        new Color(255, 255, 0), // Yellow
        new Color(255, 127, 0), // Orange
        new Color(255, 0, 0),    // Red
        Color.CYAN,Color.MAGENTA,Color.PINK
    };

    public static Color getRandomColor() {
        Random random = new Random();
        return COLORS[random.nextInt(COLORS.length)];
    }
}