package cop2805;

import static cop2805.Create.*;
import static cop2805.Constants.*;
import static cop2805.posForms.*;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.HttpURLConnection;



public class Tools {

    public static void prtln(String txt) { System.out.println(txt); }  //  Saving silly typing



    public static void logEvent(String eventDescription) {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Format the current date and time
        String formattedDateTime = now.format(formatter);

        // Print the event description with the current date and time
        prtln("\n" + formattedDateTime + ": " + eventDescription);
    }




    public static void ImageChooser(Container frame) throws IOException, InterruptedException {
        // Define Pictures folder location
        String imagePath = "";

        //  Relative Path: We dynamically fetch from the user's Pictures folder (based on the system's user directory)
        String userHome = System.getProperty("user.home");

        //  With a JFileChooser we browse for an image in the user's "Pictures" folder
        JFileChooser fileChooser = new JFileChooser(new File(userHome, "Pictures"));

        // Filter to show only image files
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));


        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(null);

        //  User selects an image and clicks OK
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath();
        }
        //  Place the photo in the box
        ImageLabel photo  = new ImageLabel(frame, imagePath, possPhoto);

        //  give the path to the AddNew String.
        uploadImageToServer(imagePath);
    }







    // UPLOAD PHOTO to the server. Version 1: for only the selected image uploads.
    private static void uploadImageToServer(String imagePath) throws IOException
    {
        File file = new File(imagePath);
        //  SERVER CLIENT CONNECTION
        try (Socket socket = new Socket(HOST, PORTCLIENT); // Local server URL
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             FileInputStream fis = new FileInputStream(new File(imagePath)))
        {
            //  Send the file name and size first (if needed)
            dos.writeUTF(file.getName());
            dos.writeLong(file.length());

            // Send the file contents
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }
            dos.flush();
            System.out.println("Image uploaded successfully.");
        }
    }





    // Method to create custom icon
    public static Icon customCheckBoxIcon(Color backgroundColor, Color checkColor)
    {
        int size = 25;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        //  Create the type of image that will replace the standard Check Box:
        Graphics2D g1 = image.createGraphics();  //  The Outside Square box

        g1.setColor(MDGRAY);
        g1.drawRect(0, 0, size - 1, size - 1);


        Graphics2D g2 = image.createGraphics();  //  Check mark

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.setColor(backgroundColor);

        //  The Check mark
        g2.setColor(checkColor);

        //  Alternatives. Not used here

        //  INSIDE SQUARE
        //  g2.fillRect(in[0], in[1], in[2], in[3]);

        //  INSIDE CIRCLE
        //g2.fillOval(in[0], in[1], in[2], in[3]);

        //  STAR. dims are too small.
        //int[] xPoints = {8, 10, 15, 11, 12, 8, 4, 5, 1, 6};//  define the coordinates of the star's X & Y vertices.
        //int[] yPoints = {1, 6, 6, 9, 14, 11, 14, 9, 6, 6};
        //g2.fillPolygon(xPoints, yPoints, xPoints.length);  //  fills a star shape.

        //  CHECK MARK
        int[] xPoints = { 3, 10, 22, 16, 10,  5};   //  x-coordinates of the vertices
        int[] yPoints = {13, 23,  4,  7, 15, 14};   //  y-coordinates of the vertices
        int nPoints = xPoints.length;               //  Number of points
        g2.fillPolygon(xPoints, yPoints, nPoints);  //  Draw the check mark using fillPolygon

        //  End drawing
        g1.dispose();
        g2.dispose();

        return new ImageIcon(image);
    }




    // Method to create custom icon
    public static Icon customRadioIcon(Color backgroundColor, Color checkColor)
    {
        int size = 25;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        //  Create the type of image that will replace the standard Check Box:
        Graphics2D g1 = image.createGraphics();  //  Circle
        Graphics2D g2 = image.createGraphics();  //  Check mark

        //  Circle
        g1.setColor(backgroundColor);
        g1.fillOval(0, 0, size, size);

        g1.setColor(MDGRAY);
        g1.drawRect(0, 0, size - 1, size - 1);


        //  Check Mark
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(checkColor);

        //int[] xPoints = { 6, 12, 20, 14, 12,  8};   // x-coordinates of the vertices
        //int[] yPoints = {11, 18,  8, 12, 16, 13};   // y-coordinates of the vertices
        //int nPoints = xPoints.length;               // Number of points
        //g2.fillPolygon(xPoints, yPoints, nPoints);  // Draw the check mark using fillPolygon


        //  Alternatives. Not used here

        //  SQUARE
        //  g2.fillRect(in[0], in[1], in[2], in[3]);

        //  STAR.  dims are too small.
        //int[] xPoints = {8, 10, 15, 11, 12, 8, 4, 5, 1, 6};//  define the coordinates of the star's X & Y vertices.
        //int[] yPoints = {1, 6, 6, 9, 14, 11, 14, 9, 6, 6};
        //g2.fillPolygon(xPoints, yPoints, xPoints.length);  //  fills a star shape.

        //  CHECK MARK
        int[] xPoints = { 3, 10, 22, 16, 10,  5};   //  x-coordinates of the vertices
        int[] yPoints = {13, 23,  4,  7, 15, 14};   //  y-coordinates of the vertices
        int nPoints = xPoints.length;               //  Number of points
        g2.fillPolygon(xPoints, yPoints, nPoints);  //  Draw the check mark using fillPolygon


        //  End drawing
        g1.dispose();
        g2.dispose();

        return new ImageIcon(image);
    }



}
