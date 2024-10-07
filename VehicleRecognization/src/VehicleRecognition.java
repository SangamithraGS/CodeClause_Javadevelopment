package src;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.MatOfRect;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VehicleRecognition {

    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    // A mapping of car brand names to keywords for detection
    static final Map<String, String[]> carBrands = new HashMap<>();

    static {
        carBrands.put("Toyota", new String[]{"Toyota"});
        carBrands.put("Honda", new String[]{"Honda"});
        carBrands.put("Ford", new String[]{"Ford"});
        carBrands.put("Chevrolet", new String[]{"Chevrolet"});
        carBrands.put("Nissan", new String[]{"Nissan"});
        carBrands.put("BMW", new String[]{"BMW"});
        carBrands.put("Mercedes-Benz", new String[]{"Mercedes", "Benz"});
        carBrands.put("Audi", new String[]{"Audi"});
        carBrands.put("Hyundai", new String[]{"Hyundai"});
        carBrands.put("Kia", new String[]{"Kia"});
        carBrands.put("Volkswagen", new String[]{"Volkswagen"});
        carBrands.put("Subaru", new String[]{"Subaru"});
        carBrands.put("Mazda", new String[]{"Mazda"});
        carBrands.put("Porsche", new String[]{"Porsche"});
        carBrands.put("Lexus", new String[]{"Lexus"});
        carBrands.put("Jaguar", new String[]{"Jaguar"});
        carBrands.put("Land Rover", new String[]{"Land Rover"});
        carBrands.put("Volvo", new String[]{"Volvo"});
        carBrands.put("Chrysler", new String[]{"Chrysler"});
        carBrands.put("Dodge", new String[]{"Dodge"});
        carBrands.put("Ram", new String[]{"Ram"});
        carBrands.put("Mitsubishi", new String[]{"Mitsubishi"});
        // Add more brands as needed, totaling more than 100
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Vehicle Recognition System!");
        System.out.println("This system will help you recognize vehicles from an image.");
        
        // Get image path from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the path to the image: ");
        String imagePath = scanner.nextLine();
        
        // Load the image
        Mat image = Imgcodecs.imread(imagePath);
        if (image.empty()) {
            System.out.println("No image found at the specified path. Please upload another image to proceed.");
            return;
        }
        
        System.out.println("Image loaded: " + image.width() + "x" + image.height());

        // Load the Haar Cascade for car detection
        CascadeClassifier carCascade = new CascadeClassifier("src/haarcascade_car.xml");
        if (carCascade.empty()) {
            System.out.println("Error loading cascade classifier. Please check the path.");
            return;
        }

        // Convert image to grayscale
        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);
        
        // Optionally, apply GaussianBlur to reduce noise
        Imgproc.GaussianBlur(grayImage, grayImage, new Size(5, 5), 0);
        
        // Detect cars in the image
        MatOfRect carsArray = new MatOfRect();
        System.out.println("Detecting cars...");
        carCascade.detectMultiScale(grayImage, carsArray, 1.1, 2, 0, new Size(30, 30), new Size());
        
        // Convert MatOfRect to Rect array
        Rect[] cars = carsArray.toArray();
        System.out.println("Detected cars: " + cars.length);

        // Check if any cars were detected
        if (cars.length > 0) {
            System.out.println("Your uploaded vehicle is recognized!");
            System.out.println("Vehicle Type: car");
            String detectedBrand = detectCarBrand(cars);
            System.out.println("Brand: " + detectedBrand);

            // Draw rectangles around detected cars
            for (Rect rect : cars) {
                Imgproc.rectangle(image, rect.tl(), rect.br(), new Scalar(0, 255, 0), 3); // Draw rectangle
            }

            // Save the output image
            String outputPath = "output.jpg";
            Imgcodecs.imwrite(outputPath, image);
            System.out.println("Output image with detected cars saved as " + outputPath);
        } else {
            System.out.println("No vehicle detected in the image. Please upload another image to proceed.");
        }
    }

    // Function to detect car brand based on predefined keywords
    private static String detectCarBrand(Rect[] cars) {
        // In this simplified version, we're assuming that we can identify brands based on a known feature set.
        // In a real-world application, you would need a better method to determine the brand from the image.
        
        for (Rect car : cars) {
            // For simplicity, return the first brand found in the map. 
            // You should replace this with actual detection logic based on the vehicle image features.
            return "Brand name detection logic not implemented"; // Return a placeholder for now.
        }
        return "Unknown";
    }
}
