package assess;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ObjectOutputStream;
import java.util.ObjectInputStream;
import java.util.FileOutputStream;
import java.util.FileInputStream;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Socket socket = new Socket("localhost", 5000);

        String messageReceived = "";
        String input = "";
        float sum = 0;
        float average;
        float std_d;
        float squareSum = 0;

    try {
        while (true) {

            OutputStream os = socket.getOutputStream();
            FileOutputStream fos = new FileOutputStream(os);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            InputStream is = socket.getInputStream();
            FileInputStream fis = new FileInputStream(is);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            oos.writeUTF(input);
            oos.flush();

            messageReceived = ois.readUTF();
            System.out.println("From the server: " + messageReceived);

            List<String> meanList = new ArrayList<String>(Arrays.asList(messageReceived.split(",")));

            for (int i = 0; i < meanList.size(); i++) {
                sum = sum + Float.parseFloat(meanList.get(i));
                squareSum += Math.pow((Float.parseFloat(meanList.get(i)) - average), 2);
            }
            average = sum / meanList.size();
            std_d = Math.sqrt((squareSum) / meanList.size());

            oos.writeUTF("Koh Kai Xiang");
            oos.writeUTF("koh_kaixiang_8@hotmail.com");
            oos.writeFloat(average);
            oos.writeFloat(std_d);

        
        oos.close();
        ois.close();
        socket.close();
        }
     } catch (EOFException ex) {
            ex.printStackTrace();
            socket.close(); 
    }
}
}