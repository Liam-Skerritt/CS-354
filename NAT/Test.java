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
    //Add an external IP and internal IP key value pair to NAT_table
    public static void add_to_NAT(Paquet in_paquet){
        NAT_table.put(in_paquet.getDestIP(), in_paquet.getSourceIP());
    }
    //Removes external ip and internal ip key value pair from NAT_table
    //NAT_table.get(external_ip) will return NULL
    public static void remove_from_NAT(Paquet in_paquet){
        NAT_table.remove(in_paquet.getDestIP());
    }
    /**
     * @param args the command line arguments
     */
    
    static Hashtable<String, String> available_ip = new Hashtable<String, String>();
    static Hashtable<String, String> available_mac = new Hashtable<String, String>();
    static Hashtable<String, String> NAT_table = new Hashtable<String, String>();
    public static void main(String[] args) {
        Paquet test = new Paquet("192.168.0.1", "215.14.32.16", "ab:ac:ad:ae:af:fa", 100, "");
        add_to_NAT(test);
        System.out.println(NAT_table.get("215.14.32.16"));
        remove_from_NAT(test);
        System.out.println(NAT_table.get("215.14.32.16"));
        // TODO code application logic here
    }
      
}

