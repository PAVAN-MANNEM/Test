import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class TCPChatClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 6789);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream serverWriter = new DataOutputStream(clientSocket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter message to send: ");
                String message = scanner.nextLine();

                serverWriter.writeBytes(message + '\n');
                System.out.println("Waiting for Server ...");

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting client...");
                    break;
                }

                String serverResponse = serverReader.readLine();
                System.out.println("Server: " + serverResponse);
            }


            clientSocket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
