/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author 19059965
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    static Hashtable<String, String> available_ip = new Hashtable<String, String>();
    static Hashtable<String, String> available_mac = new Hashtable<String, String>();
    public static void main(String[] args) {
        try {
            Scanner scFile = new Scanner(new File("ip.txt"));
            while (scFile.hasNext()) {
                String line = scFile.nextLine();
                Scanner scLine = new Scanner(line);
                String temp_ip = scLine.next();

                int k = available_ip.hashCode();
                available_ip.put(temp_ip, "false");
                String generated_mac;
                String last_digit = temp_ip.substring(10, temp_ip.length());

                String p1 = Integer.toHexString(Integer.parseInt("192"));
                String p2 = Integer.toHexString(Integer.parseInt("168"));
                String p3 = Integer.toHexString(Integer.parseInt("0"));
                String p4 = Integer.toHexString(Integer.parseInt(last_digit));
                String p5 = Integer.toHexString(Integer.parseInt(last_digit));
                String p6 = Integer.toHexString(Integer.parseInt(last_digit));
                generated_mac = p1 +":" + p2 +":" + p3 +":" + p4 +":" + p5 +":" + p6;
                available_mac.put(temp_ip, generated_mac);
                System.out.println(temp_ip + ": " + generated_mac);
                //available_mac.put(temp_ip, generate_mac(temp_ip));
            }
        } catch (Exception e) {
        }
        System.out.println(available_ip.containsKey("192.168.0.10"));
        System.out.println(available_mac.get("192.168.0.10"));
        // TODO code application logic here
    }
    
    public void load_available_ip(){
        
    }
    
}
