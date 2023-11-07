import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPChatServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("UDP Chat Server is running...");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                receiveData = new byte[1024]; // Clear receive buffer

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData());
                System.out.println("Client: " + message);

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting server...");
                    break;
                }

                InetAddress clientIPAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.print("Enter message to send: ");
                String message1 = scanner.nextLine();
                System.out.println("Waiting for the Client ...");

                sendData = message1.getBytes();

                //String response = "Server: Message received!";
                //sendData = response.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
                serverSocket.send(sendPacket);
            }
            serverSocket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
