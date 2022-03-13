import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String [] args) throws IOException{
        while (true){
            Socket clientSocket = new Socket("localhost", 5050);
            try(InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream()){

                System.out.println("Connected to Server");

                Scanner input = new Scanner(inputStream, String.valueOf(StandardCharsets.UTF_8));
                Scanner keyboardInput = new Scanner(System.in, String.valueOf(StandardCharsets.UTF_8));
                PrintWriter output = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);


                Thread getData = new Thread(()->{
                    while(!clientSocket.isClosed()){
                        if( input.hasNextLine()){
                            System.out.println(input.nextLine());
                        }
                    }
                });
                getData.start();

                // to send data or to exit or connect again after exit
                String clientMsg;
                while(true){
                    clientMsg = keyboardInput.nextLine();
                    output.println(clientMsg);
                    if (clientMsg.toUpperCase().equals("EXIT")) {

                        try {
                            clientSocket.close();
                            input.close();
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if(clientMsg.toUpperCase().equals("CONNECT"))
                        break;
                }




            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
