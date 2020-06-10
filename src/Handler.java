import java.io.*;
import java.net.Socket;

public class Handler implements Runnable {

    public Socket socket;
    public DataInputStream streamIn = null;
    public DataOutputStream streamOut=null;

    public Handler(Socket socket) {
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            boolean done = false;
            String line = "";
            while (!done) {
                try {
                    line += streamIn.readUTF();
                    System.out.println(line);
                    if (line.contains("bye")) {
                        done = true;
                        break;
                    }
                } catch (IOException ioe) {
                    done = true;
                }
                try {
                    streamOut=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    streamOut.writeUTF(line);
                    streamOut.flush();
                } catch (IOException ioe) {
                    System.out.println("Sending error: " + ioe.getMessage());
                }


            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
