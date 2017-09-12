
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 19458517
 */
public class Client implements Runnable {

    private static boolean internal;
    private static String ipGiven;
    private static String macAddr;
    private static String natIP;
    // The client socket
    private static Socket clientSocket = null;
    // The output stream
    private static ObjectOutputStream os = null;
    // The input stream
    private static ObjectInputStream is = null;

    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    private static boolean joinedNetwork = false; 

    public static void main(String[] args) {
        inputLine = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input NAT-box IP address");
        try {
            natIP = inputLine.readLine();
            System.out.println("Are you an internal or external client?");
            String temp = inputLine.readLine();
            if (temp.equals("internal")) {
                internal = true;
            } else {
                internal = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        int portNumber = 8000;

        /*
        * Open a socket on a given host and port. Open input and output streams.
         */
        try {
            clientSocket = new Socket(natIP, portNumber);
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            is = new ObjectInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + natIP);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host " + natIP);
        }

        /*
        * If everything has been initialized then we want to write some data to the
        * socket we have opened a connection to on the port portNumber.
         */
        if (clientSocket != null && os != null && is != null) {
            try {

                /* Create a thread to read from the server. */
                new Thread(new Client()).start();
                System.out.println("sending");
                String inter = "external";
                if (internal) {
                    inter = "internal";
                }
                os.writeObject(inter);
                System.out.println("sent");
                while (!closed) {
                    
                    while (!joinedNetwork) {
                        
                    }
                    System.out.println("Enter destination IP address:");
                    String dest = inputLine.readLine();
                    System.out.println("Enter message");
                    String payload = inputLine.readLine();
                    Paquet send = new Paquet(ipGiven, dest, macAddr, portNumber, payload);
                    os.writeObject(send);
                }
                /*
            * Close the output stream, close the input stream, close the socket.
                 */
                System.out.println("made it out!");
                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    public void run() {
        /*
         *  The thread that receives messages from NAT router
         */

        try {
            ipGiven = is.readLine();
            System.out.println("Assigned IP: "+ipGiven);
            macAddr = is.readLine();
            System.out.println("Assigned MAC: "+macAddr);
            joinedNetwork = true;
            Paquet recv;
            try {
                recv = (Paquet) is.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                recv = null;
            }

            while (recv != null) {
                System.out.println("Received packet from:  IP(" + recv.getSourceIP() + ") Mac(" + recv.getSourceMac() + ") Message = " + recv.getPayload());
                try {
                    recv = (Paquet) is.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    recv = null;
                }

            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
