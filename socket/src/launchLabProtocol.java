

public class launchLabProtocol {

    private static ClientServerSocket theServer;
    private static boolean hasBegun;

    // SIZE <XML, BIN> Size
    private static void handleSizeRequest(boolean isXML, int numBytes) {
        // @TODO
    }


    // Handle user's requests
    private static void requestsHandler(String cmd) {
        if (cmd.isEmpty()) return;
        if (!hasBegun) {
            // Command invalid since the session hasn't started yet
            theServer.sendString("Invalid session (BEGIN; to create new session)\n");
            return;
        }

        if (!cmd.substring(cmd.length() - 1).equals(";")) {
            // Command not ends with ;
            theServer.sendString("Invalid command " + cmd + " (should end with ;)\n");
            return;
        }

        String [] cmd_parts = cmd.split("\\s+");
        assert cmd_parts.length != 0;
        switch (cmd_parts[0]) {
            case "SIZE" :

                // @TODO
                break;
        }
    }

    private static void sendOK() {
        theServer.sendString("OK\n");
    }

    public static void main(String[] args) {

        hasBegun = false;
        theServer = new ClientServerSocket("localhost", 45000);
        theServer.startServer();

        while (true) {
            theServer.sendString(">");
            String receivedStr = theServer.recvString();
            if (receivedStr.equals("END;")) break;
            if (receivedStr.equals("BEGIN;")) {
                hasBegun = true;
                sendOK();
            }
            requestsHandler(receivedStr);
        }

    }
}
