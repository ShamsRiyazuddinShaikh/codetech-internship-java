import java.io.*;
import java.util.Scanner;

public class FileHandler {

    public static void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void writeFile(String filename, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(content);
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void appendToFile(String filename, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(content);
            bw.newLine();
            System.out.println("Content appended to file successfully.");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    public static void modifyFile(String filename, String oldText, String newText) {
        try {
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    fileContent.append(line.replace(oldText, newText)).append("\n");
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                bw.write(fileContent.toString());
                System.out.println("File content modified successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an operation:");
        System.out.println("1. Read File");
        System.out.println("2. Write File");
        System.out.println("3. Append to File");
        System.out.println("4. Modify File");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the filename: ");
        String filename = scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Reading content of the file...");
                readFile(filename);
                break;
            case 2:
                System.out.print("Enter content to write to file: ");
                String contentToWrite = scanner.nextLine();
                writeFile(filename, contentToWrite);
                break;
            case 3:
                System.out.print("Enter content to append to file: ");
                String contentToAppend = scanner.nextLine();
                appendToFile(filename, contentToAppend);
                break;
            case 4:
                System.out.print("Enter the old text to replace: ");
                String oldText = scanner.nextLine();
                System.out.print("Enter the new text to replace with: ");
                String newText = scanner.nextLine();
                modifyFile(filename, oldText, newText);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}