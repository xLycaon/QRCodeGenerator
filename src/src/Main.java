import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

//TODO: add e method to create different types of QR codes (links PDFs, etc)
//TODO: find a way to change the way the QR code looks (web patterns, etc)
//TODO: modify the code so that the user will choose the type of QR code they want to generate and how they want it to look


public class Main {



    //TODO: rewrite the main function
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
                generateSimpleQRPopUp(message);
                break;
            case 2:
                generateQRpattern();
                break;
            default:
                System.out.println("Invalid choice");
        }
        sc.close();
    }
    

    //this method generates a simple QR code with a pop-up message (black and white)
    private static void generateSimpleQRPopUp(String message) throws WriterException, IOException {
        String path = "QRcode.jpg";
        System.out.println("The message is: " + message);
        BitMatrix matrix = new MultiFormatWriter().encode(message, BarcodeFormat.QR_CODE, 500, 500);
        //if the file already exists, delete it
         if(Paths.get(path).toFile().exists()) {
            Paths.get(path).toFile().delete();
        }
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
    }

    // TODO: implement this method
    // TODO: add a feature to customize the QR code
    // TODO: add a feature to insert multiple colors
    // TODO: store random colors and QR patterns in a file with their respective seeds and colors

    private static void generateQRpattern() {
        
    }

    private static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}