import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessor {
    private String pathImage;
    private String pathLogo;
    private BufferedImage image;
    private BufferedImage logo;

    public ImageProcessor(File QRimage) throws IOException {
        this.pathImage = QRimage.getAbsolutePath();
        this.image = ImageIO.read(QRimage);
    }

    public ImageProcessor(File imageQR, File imageLogo) throws IOException {
        this.pathImage = imageQR.getAbsolutePath();
        this.image = ImageIO.read(imageQR);
        this.logo = ImageIO.read(imageLogo);
        this.pathLogo = imageLogo.getAbsolutePath();
    }

    public ImageProcessor(BufferedImage imageQR, BufferedImage imageLogo) throws IOException {
        this.image = imageQR;
        this.logo = imageLogo;
        this.pathImage = "QRcode.png";
        this.pathLogo = "logo.png";
    }

    public ImageProcessor(BufferedImage qrCode) {
        this.image = qrCode;
        this.pathImage = "QRcode.png";
    }

    public void setColorOnPixel(int x, int y, int color) {
        this.image.setRGB(x, y, color);
    }

    public boolean checkLogo() {
        return logo != null;
    }

    public BufferedImage insertLogo() {
        this.logo = removeWhiteBackgroundWithAntiAlias(logo);
        BufferedImage canvas = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = canvas.createGraphics();

        graphics.drawImage(image, 0, 0, null);

        int logoX = (canvas.getWidth() - logo.getWidth()) / 2;
        int logoY = (canvas.getHeight() - logo.getHeight()) / 2;

        graphics.drawImage(logo, logoX, logoY, null);
        graphics.dispose();

        return canvas;
    }

    public static BufferedImage removeWhiteBackgroundWithAntiAlias(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = newImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(image, 0, 0, null);

        BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D maskGraphics = mask.createGraphics();
        maskGraphics.drawImage(image, 0, 0, null);
        maskGraphics.dispose();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int maskPixel = mask.getRGB(x, y);
                if (maskPixel == Color.WHITE.getRGB()) {
                    newImage.setRGB(x, y, 0x00FFFFFF);
                }
            }
        }

        g2d.dispose();

        return newImage;
    }

    public BufferedImage colorQR(Color chosenColor) {
        for (int i = 0; i <this.image.getWidth(); i++) {
            for (int j = 0; j <this.image.getHeight(); j++) {
                System.out.println(this.image.getRGB(i, j));
                if (this.image.getRGB(i, j) == -16777216) {
                    this.setColorOnPixel(i, j, chosenColor.getRGB());
                }
            }
        }
        return this.image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getRGB(int x, int y) {
        return image.getRGB(x, y);
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage getLogo() {
        return logo;
    }

    public void saveImage(BufferedImage img) throws IOException {
        File file = new File(pathImage);
        ImageIO.write(img, "png", file);
    }
}
