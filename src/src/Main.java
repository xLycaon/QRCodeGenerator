import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;



//TODO: create the main function
//TODO: manage to insert logo in the QR code
//TODO: (optional) mmanage to change colors in a .png file



public class Main {

    public static void main(String[] args) throws WriterException, IOException {
        generateQRCode("Test");

    }

    private static void generateQRCode(String message) throws WriterException, IOException {
        String path = "QRcode.jpg";
        System.out.println("The message is: " + message);
        BitMatrix matrix = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE, 500, 500);
        
        //if the file already exists, delete it
         if(Paths.get(path).toFile().exists()) {
            Paths.get(path).toFile().delete();
        }

        MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(path));
        ImageProcessor imageProcessor = new ImageProcessor(Paths.get(path).toFile());
        BufferedImage qrCode = imageProcessor.colorQR(Color.BLACK);
        saveQRCode(qrCode, "QRcode.png");
    }

    private static void saveQRCode(BufferedImage qrCode, String filename) throws IOException {
        File file = new File(filename);
        ImageIO.write(qrCode, "png", file);
    }

    private static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}