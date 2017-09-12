import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenExternalIP {
	static List<String> externalIPs = new ArrayList<String>();
	
	static String GenerateExIP(){
		Random rn = new Random();
		int n1 = rn.nextInt(256) ;
		int n2 = rn.nextInt(256);
		int n3 = rn.nextInt(256);
		int n4 = rn.nextInt(256);
		String ip = "";
		
		while (n1 == 192){
			n1 = rn.nextInt(256);
		}
		
		while (n2 == 168){
			n2 = rn.nextInt(256);
		}
		
		while (n3 == 0){
			n3 = rn.nextInt(256);
		}
		
		n4 = rn.nextInt(256);
		
		ip = Integer.toString(n1) + "." + Integer.toString(n2) + "." + Integer.toString(n3) + "." + Integer.toString(n4);
		return ip ;
	}
	
	static void addExternalIP(){
		String ip = GenerateExIP();
		if (!externalIPs.contains(ip)){
			externalIPs.add(ip);
		}
	}
	
	public static void main(String[] args){
		for(int i = 1; i <= 255; i++){
			addExternalIP();
		}
		String[] Arr = new String[externalIPs.size()];
		Arr = externalIPs.toArray(Arr);

		for(String s : Arr)
		    System.out.println(s);
	}
}
