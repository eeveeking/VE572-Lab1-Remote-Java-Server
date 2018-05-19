import static java.lang.System.out;

public class launchServerDemo {
    public static void main(String [] args) {
        ClientServerSocket theServer = new ClientServerSocket("localhost", 45000);
        String receivedStr = "";
        theServer.startServer();
        receivedStr = theServer.recvString();
        out.println("Server received a string: " + receivedStr);
        boolean success = theServer.sendString("Server has received the string: " + receivedStr);
        assert success;
    }
}
