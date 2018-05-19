import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Vector;
import static java.lang.System.out;


public class ClientServerSocket {
    private String ipAddr;
    private int portNum;
    private Socket socket;
    private DataInputStream inData;
    private DataOutputStream outData;

    public ClientServerSocket(String inIPAddr, int inPortNum) {
        socket = null;
        inData = null;
        outData = null;
        ipAddr = inIPAddr;
        portNum = inPortNum;
    }

    public void startClient() {
        try {
            socket = new Socket(ipAddr, portNum);
            inData = new DataInputStream(socket.getInputStream());
            outData = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioe) {
            out.println("Starting a client failed");
            System.exit(10);
        }
    }

    public void startServer() {
        try {
            ServerSocket serverSock = new ServerSocket(portNum);
            out.println("Waiting for clients' request, at port=" + portNum);

            socket = serverSock.accept();
            inData = new DataInputStream(socket.getInputStream());
            outData = new DataOutputStream(socket.getOutputStream());

            out.println("Accept client request");
        } catch (IOException ioe) {
            out.println("Starting a client failed");
            System.exit(7);
        }
    }

    public boolean sendString(String strToSend) {
        boolean success = false;
        try {
            outData.writeBytes(strToSend);
            outData.writeByte(0);
            success = true;
        } catch (IOException ioe) {
            out.println("Send string failed");
            System.exit(-1);
        }

        return success;
    }

    public String recvString() {
        Vector<Byte> byteVec = new Vector<Byte>();
        byte [] byteAry;
        byte recvByte;
        String receivedString = "";

        try {
            recvByte = inData.readByte();
            while (!(recvByte == 0 || recvByte == 10)) {
                byteVec.add(recvByte);
                recvByte = inData.readByte();
            }

            byteAry = new byte[byteVec.size()];
            for (int i = 0; i < byteVec.size(); ++ i) {
                byteAry[i] = byteVec.elementAt(i).byteValue();
            }
            receivedString = new String(byteAry);
        } catch (IOException ioe) {
            out.println("Receive string failed");
            System.exit(8);
        }
        return receivedString;
    }


}
