package assess;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            Console cons = System.console();
            input = cons.readLine("Receive message from server?");
            dos.writeUTF(input);
            dos.flush();

            messageReceived = dis.readUTF();
            System.out.println("From the server: " + messageReceived);

            List<String> meanList = new ArrayList<String>(Arrays.asList(messageReceived.split(",")));

            for (int i = 0; i < meanList.size(); i++) {
                sum = sum + Float.parseFloat(meanList.get(i));
                squareSum += Math.pow((Float.parseFloat(meanList.get(i)) - average), 2);
            }
            average = sum / meanList.size();
            std_d = Math.sqrt((squareSum) / meanList.size());

            dos.writeUTF("Koh Kai Xiang");
            dos.writeUTF("koh_kaixiang_8@hotmail.com");
            dos.writeFloat(average);
            dos.writeFloat(std_d);

        
        dos.close();
        dis.close();
        socket.close();
        }
     } catch (EOFException ex) {
            ex.printStackTrace();
            socket.close(); 
    }
}
}