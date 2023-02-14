package assess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;

public class Word {
 
    public static void main(String[] args) throws IOException {
        File directoryPath = new File("seuss");
        File filesList[] = directoryPath.listFiles();
        System.out.println("List of files in the directory");
        Scanner sc = null;
        StringBuffer sb = new StringBuffer();
        for (File file : filesList) {
            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getAbsoluteFile());
            System.out.println("Size: " + file.getTotalSpace());

            sc = new Scanner(file);
            String input;
            while (sc.hasNextLine()) {
                input = sc.nextLine().replaceAll("\\p{P}", "");
                sb.append(input + " ");
            }
            System.out.println("Contents of the file: " + sb.toString());
            System.out.println(" ");
        }
    }
}
