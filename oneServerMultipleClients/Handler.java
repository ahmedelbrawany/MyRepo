import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;


public class Handler implements  Runnable {
    private final ClientInfo clientInfo;
    private final List<ClientInfo> clientsList;
    private String line = "A New Client Joined The Server";
    private volatile boolean isSent;
    public static int clientsNum=0;
    Handler(ClientInfo clientInfo , List<ClientInfo> clientsList){

        this.clientsList = clientsList;
        this.clientInfo = clientInfo;
        this.clientsNum +=1;

    }

    @Override
    public void run() {
        try(Scanner input = new Scanner(clientInfo.clientSocket.getInputStream(), String.valueOf(StandardCharsets.UTF_8))){
            Thread getClientData = new Thread(()->{
                while(true){
                    if (input.hasNextLine()) {
                        line = input.nextLine();
                        System.out.println(line);
                        if(line.toUpperCase().equals("EXIT")){
                            try {
                                clientInfo.clientSocket.close();
                                input.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            clientsNum--;
                            System.out.println("The server is currently serving "+clientsNum+" Client(s)");
                            clientsList.remove(clientInfo);
                            return;
                        }
                        else
                            isSent = Boolean.FALSE;
                    }
                }
            });
            getClientData.start();

            while(true){
                if (!isSent){
                    clientsList.stream().filter((client)-> client.clientSocket != clientInfo.clientSocket).forEach(client ->{
                            PrintWriter output = null;
                            try{
                                output = new PrintWriter(new OutputStreamWriter(client.clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);

                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                            output.println("Client "+clientInfo.clientNum+": "+line);
                    });
                    isSent = Boolean.TRUE;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
