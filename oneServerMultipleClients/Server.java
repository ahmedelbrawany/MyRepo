import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String [] args){

        try(ServerSocket serverSocket = new ServerSocket(5050)){
            int clientNum = 1;
            List<ClientInfo> clientsList = new ArrayList<>();

            while(true){
                Socket clientSocket = serverSocket.accept();
                ClientInfo clientInfo = new ClientInfo(clientSocket, clientNum);
                clientsList.add(clientInfo);
                Thread handle = new Thread(new Handler(clientInfo, clientsList));
                System.out.println("The server is currently serving "+Handler.clientsNum+" Client(s)");
                handle.start();


                clientNum++;
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}

