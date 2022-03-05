import java.io.*;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void main(String [] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9091);

        System.out.println("Server is listening....");

        Socket socket = serverSocket.accept();

        System.out.println("Server is Connected to Client :)");

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        System.out.println("Enter server message: ");
        Scanner input = new Scanner(System.in);
        String ServerMsg = input.nextLine();

        dataOutputStream.writeUTF(ServerMsg);

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        String clientMsg = dataInputStream.readUTF();
        System.out.println(clientMsg);

        serverSocket.close();
        socket.close();
        outputStream.close();
        dataOutputStream.close();
        inputStream.close();
        dataInputStream.close();

    }
}
