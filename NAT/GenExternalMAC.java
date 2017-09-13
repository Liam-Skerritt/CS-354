import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenExternalMAC {
	static List<String> externalMACs = new ArrayList<String>();

	static String GenerateExMAC(){
		String mac;
		while(true){
			Random rn = new Random();
			int n1 = rn.nextInt(256) ;
			int n2 = rn.nextInt(256);
			int n3 = rn.nextInt(256);
			int n4 = rn.nextInt(256);
			int n5 = rn.nextInt(256);
			int n6 = rn.nextInt(256);
			mac = "";
		
			String p1 = Integer.toHexString(n1);
			String p2 = Integer.toHexString(n2);
			String p3 = Integer.toHexString(n3);
			String p4 = Integer.toHexString(n4);
			String p5 = Integer.toHexString(n5);
			String p6 = Integer.toHexString(n6);

		
			mac = p1 + ":" + p2 + ":" + p3 + ":" + p4 + ":" + p5 + ":" + p6;
		
			if (!externalMACs.contains(mac)){
				externalMACs.add(mac);
				break;
			}
		}
		return mac;
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 4; i++) {
			String temp = GenerateExMAC();
		}
		
		String[] Arr = new String[externalMACs.size()];
		Arr = externalMACs.toArray(Arr);

		for (String s : Arr)
			System.out.println(s);
	}
}