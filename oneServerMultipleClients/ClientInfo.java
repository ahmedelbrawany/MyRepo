import java.net.Socket;

public class ClientInfo {

    public final Socket clientSocket;
    public final int clientNum;

    ClientInfo(Socket clientSocket, int clientNum){

        this.clientSocket = clientSocket;
        this.clientNum = clientNum;
    }
}
