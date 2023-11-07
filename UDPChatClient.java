import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPChatClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress serverIPAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            byte[] sendData;
            byte[] receiveData = new byte[1024];

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter message to send: ");
                String message = scanner.nextLine();
                System.out.println("Waiting for the Server ...");

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting client...");
                    break;
                }

                sendData = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIPAddress, serverPort);
                clientSocket.send(sendPacket);

                receiveData = new byte[1024]; // Clear receive buffer

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String response = new String(receivePacket.getData());
                System.out.println("Server: " + response);
            }
            clientSocket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
