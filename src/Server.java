import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static ServerSocket server;

    public static void main(String[] args) {

        System.out.println("Binding to port " + 5001 + ", please wait  ...");

        try {
            server = new ServerSocket(5001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server started: " + server);

        while (true) {
            System.out.println("Waiting for a client ...");
            Socket socket = null;
            try {
                socket = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client accepted: " + socket);

            Thread thread = new Thread(new Handler(socket), "thread");
            thread.start();
        }
    }
}
