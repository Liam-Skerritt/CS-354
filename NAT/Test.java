/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author 19059965
 */
public class Test {
    static public String Generate_mac(String in_ip){
        String last_digit = in_ip.substring(10, in_ip.length());
        String generated_mac;
        String p1 = Integer.toHexString(Integer.parseInt("192"));
        String p2 = Integer.toHexString(Integer.parseInt("168"));
        String p3 = Integer.toHexString(Integer.parseInt("0"));
        String p4 = Integer.toHexString(Integer.parseInt(last_digit));
        String p5 = Integer.toHexString(Integer.parseInt(last_digit));
        String p6 = Integer.toHexString(Integer.parseInt(last_digit));
        generated_mac = p1 +":" + p2 +":" + p3 +":" + p4 +":" + p5 +":" + p6;
        return generated_mac;
    
    }
    static void load_available_ip(){
        try {
            Scanner scFile = new Scanner(new File("ip.txt"));
            while (scFile.hasNext()) {
                String line = scFile.nextLine();
                Scanner scLine = new Scanner(line);
                String temp_ip = scLine.next();
                available_ip.put(temp_ip, "false");
                available_mac.put(temp_ip, Generate_mac(temp_ip));
            }
        } catch (Exception e) {
            System.out.println("Error while loading ip adresses: " + e);
        }
    }
    /**
     * @param args the command line arguments
     */
    static Hashtable<String, String> available_ip = new Hashtable<String, String>();
    static Hashtable<String, String> available_mac = new Hashtable<String, String>();
    public static void main(String[] args) {
        load_available_ip();
        System.out.println(available_ip.get("192.168.0.1"));
        // TODO code application logic here
    }
      
}

