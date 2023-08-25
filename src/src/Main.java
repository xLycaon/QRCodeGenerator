import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class Main {
    public static void main(String[] args) throws WriterException, IOException {
        System.out.println("What kind of QR code do you want to generate?");
        System.out.println("1. Simple QR code (pop-up message)");
        System.out.println("2. QR code with multiple colors");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter the you want to encode");
                String message = readString();
                generateSimpleQR(message);
                break;
            case 2:
                generateColoredQR();
                break;
            default:
                System.out.println("Invalid choice");
        }
        sc.close();
    }

    private static void generateSimpleQR(String message) throws WriterException, IOException {
        String path = "QRcode.jpg";
        System.out.println("The message is: " + message);
        BitMatrix matrix = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE, 500, 500);
         if(Paths.get(path).toFile().exists()) {
            Paths.get(path).toFile().delete();
        }
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
    }

    // TODO: implement this method
    // TODO: add a feature to customize the QR code
    // TODO: add a feature to insert multiple colors
    // TODO: store random colors and QR patterns in a file with their respective
    // seeds and colors

    private static void generateColoredQR() {

    }

    private static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}