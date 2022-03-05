import java.io.*;

import java.net.Socket;
import java.util.Scanner;

public class MyClient {


    public static void main(String [] args) throws IOException {
        System.out.println("Client is connecting to Server...");
        Socket socket = new Socket("localhost",9091);
        System.out.println("Client is connect to Server");

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        String ServerMsg = dataInputStream.readUTF();
        System.out.println("Server message: "+ServerMsg);

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        System.out.println("enter Client message: ");
        Scanner input = new Scanner(System.in);
        String ClientMsg = input.nextLine();

        dataOutputStream.writeUTF(ClientMsg);



        socket.close();
        outputStream.close();
        dataOutputStream.close();
        inputStream.close();
        dataInputStream.close();

    }
}
