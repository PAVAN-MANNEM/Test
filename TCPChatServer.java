import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("TCP Chat Server is running...");
            Scanner sca = new Scanner(System.in);

            Socket connectionSocket = serverSocket.accept();
            BufferedReader clientReader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream clientWriter = new DataOutputStream(connectionSocket.getOutputStream());

            while (true) {
                String clientMessage = clientReader.readLine();
                System.out.println("Client: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting server...");
                    break;
                }

                //String response = "Message received!\t";
                System.out.print("Enter message to send: ");
                String response = sca.nextLine();
                clientWriter.writeBytes(response + '\n');
                System.out.println("Waiting for Client ...");
            }
            serverSocket.close();
            sca.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
