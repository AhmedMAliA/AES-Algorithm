import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javax.crypto.Cipher;
import javax.jws.Oneway;

public class main {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		new Encryption().encryption();
		new Decryption().decryption();
		/* ArrayList<String[]> keys = new KeySchedule(new String [][] { { "00", "01", "02", "03" },
			{ "04", "5", "06", "07" }, { "08", "09", "0a", "0b" },
			{ "0c", "0d", "0e", "0f" }} ,"encryption").generateNewKey();

		System.out.println("_____________");
		for (int j = 0; j < keys.size(); j++) {
			for (int k = 0; k < 4; k++) {
				if(keys.get(j)[k].length()>2)
					System.out.print(Integer.toHexString(Integer.parseInt(keys.get(j)[k],2)) + " ");
				else
					System.out.print(keys.get(j)[k] + " ");
			}
			System.out.println();
		}*/
		
		
		}

}
