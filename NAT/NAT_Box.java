
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 19458517
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NAT_Box {

    private int maxUsers = 255;
    private ServerSocket service = null;
    private Socket userSocket = null;
    private userThread[] users = new userThread[maxUsers];
    private String myMacAddr = "";
    private String myIPAddr = "192.168.0.0";

    public NAT_Box(int PortNumber) {
        try {
            service = new ServerSocket(PortNumber);
        } catch (IOException e) {
            System.out.println("Could not create server");
        }
    }

    public void startNAT() {
        //create a new connection for each user
        while (true) {
            try {
                userSocket = service.accept();
                int i;
                for (i = 0; i < maxUsers; i++) {
                    if (users[i] == null) {
                        users[i] = new userThread(userSocket, users);
                        users[i].start();
                        break;
                    }
                }
                if (i == maxUsers) {
                    PrintStream output = new PrintStream(userSocket.getOutputStream());
                    output.println("Server is too busy. Try again later.");
                    output.close();
                    userSocket.close();
                }

            } catch (IOException e) {
                System.out.println("Could not accept user.");
            }
        }
    }
    
    public static void main(String[] args) {
        NAT_Box n = new NAT_Box(8000);
        n.startNAT();
    }
}

class userThread extends Thread {

    
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;
    private int maxUsers;
    private Socket userSocket = null;
    private userThread[] users = null;

    public userThread(Socket userSocket, userThread[] users) {
        this.userSocket = userSocket;
        this.users = users;
        maxUsers = users.length;
    }
    
    public void run() {
        try {
            input = new ObjectInputStream(userSocket.getInputStream());
            output = new ObjectOutputStream(userSocket.getOutputStream());
            System.out.println("New User connected");
            String internal = "";
            try {
                internal = (String) input.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(userThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            //internal or external user has been determined. Now assign user's IP and MAC address
        } catch (IOException ex) {
            Logger.getLogger(userThread.class.getName()).log(Level.SEVERE, null, ex);
        }
			 
    }
}
