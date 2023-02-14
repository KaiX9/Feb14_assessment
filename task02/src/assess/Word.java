package assess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.*;
import java.util.Arrays;

public class Word {
 
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Creating a File object for directory
        File directoryPath = new File("seuss");

        // List of all files
        File filesList[] = directoryPath.listFiles();
        System.out.println("List of files in the directory");
        Scanner sc = null;
        StringBuffer sb = new StringBuffer();
        for (File file : filesList) {
            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getAbsoluteFile());
            System.out.println("Size: " + file.getTotalSpace());

            // Instantiating the Scanner class
            sc = new Scanner(file);
            String input = "";
            String[] words;
            List<String> list = new ArrayList<String>();
            Map<String, Integer> map = new HashMap<String, Integer>();
            Integer frequency = 0;
            Double probability;

            while (sc.hasNextLine()) {
                // removing all punctuations 
                input = sc.nextLine().replaceAll("\\p{P}", "");
                sb.append(input + " ");

                // converting all words to lower case and split the words by whitespace
                words = input.toLowerCase().split(" ");

                // to find the frequency of words in all files
                for (int x = 0; x < words.length; x++) {
                    String s = words[x].trim();
                    if (s.length() <= 0) {
                        continue;
                    }
                    Integer count = map.get(words[x]);
                    if (count == null) {
                        map.put(words[x], 1);
                    } else {
                        map.put(words[x], count + 1);
                    }
                }
                }
                for (Map.Entry mp : map.entrySet()) {
                    System.out.println(mp.getKey() + " " + mp.getValue());
                }

                // to find out frequency and probability of next words
            while (sc.hasNextLine()) {
                input = sc.nextLine().replaceAll("\\p{P}", "");
                sb.append(input + " ");
                words = input.toLowerCase().split(" ");
                for (int x = 0; x < words.length; x++) {
                    // merge first and second words together, capped up to the length of array
                    String s = words[x] + (words[x + 1]);
                    if (s.length() <= 0) {
                        continue;
                    }
                    frequency = map.get(s);
                    // putting the unique Strings of s into a map to find the frequency and probability 
                    if (frequency == null) {
                        map.put(s, 1);
                    } else {
                        map.put(s, frequency + 1);
                    }
                }
                }
                for (Map.Entry mp : map.entrySet()) {
                    System.out.println(mp.getKey() + " " + mp.getValue());
                }
                }
                

    }
}
