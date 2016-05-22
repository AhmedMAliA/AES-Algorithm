import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Decryption {
	
	static String[][] cipher_key = new String [4][4];
	static String[][] cipher = new String [4][4];
	public Decryption(){}

	public static void decryption() throws IOException {
		
		System.out.println("Enter The cipher text : ");
		Scanner s = new Scanner(System.in);
		String cipher_text = s.next();
		cipher=Encryption.putInput(cipher_text);
		for (int j = 0; j < cipher.length; j++) {
			for (int k = 0; k < cipher.length; k++) {
				System.out.print(cipher[j][k] + " ");
			}
			System.out.println();
		}
		
		/*System.out.println("Enter The Cipher key : ");
		String key = s.next();
		cipher_key =Encryption.putInput(key);
		for (int j = 0; j < cipher_key.length; j++) {
			for (int k = 0; k < cipher_key.length; k++) {
				System.out.print(cipher_key[j][k] + " ");
			}
			System.out.println();
		}
		
		
		ArrayList<String[]> keys = new KeySchedule(cipher_key,"decryption").generateNewKey();
		String[][] sub_40_43 = Encryption.subKey(keys, 40);

		System.out.println("_____________");
		for (int j = 0; j < cipher.length; j++) {
			for (int k = 0; k < cipher.length; k++) {
				System.out.print(sub_40_43[j][k] + " ");
			}
			System.out.println();
		}*/
		String[][] sub_40_43 = Encryption.subKey(Encryption.keys, 40);
		String[][] intial_input = new AddRoundKey(cipher, sub_40_43)
				.intialRound();
		System.out.println("_____________");
		for (int j = 0; j < cipher.length; j++) {
			for (int k = 0; k < cipher.length; k++) {
				System.out.print(intial_input[j][k] + " ");
			}
			System.out.println();
		}
		
		String[][] plain = new String[4][4];
		String[][] sub = new String[4][4];
		int index = 36;
		for (int i = 0; i < 10; i++) {
			intial_input = new Inv_ShiftRows(intial_input).shift();
			intial_input = new Inv_SubBytes(intial_input).inv_SubBytes();
			if (i != 9){
				sub = Encryption.subKey(Encryption.keys, index);
				intial_input = new AddRoundKey(intial_input, sub).intialRound();
				intial_input = new Inv_MixColumns(intial_input).inversedColumns();

			}
			else{
				sub = Encryption.subKey(Encryption.keys, index);
				intial_input = new AddRoundKey(intial_input, sub).intialRound();

			}

			index -= 4;
			plain = intial_input;
		}

		System.out.println("The Plain Text is : ");
		for (int j = 0; j < plain.length; j++) {
			for (int k = 0; k < plain.length; k++) {

				if (plain[j][k].length() > 2) {

					int decimal = Integer.parseInt(plain[j][k], 2);
					plain[j][k] = Integer.toString(decimal, 16);
				}
				if (plain[j][k].length() == 1)
					plain[j][k] = "0" + intial_input[j][k];
				System.out.print(plain[j][k] + " ");
			}
			System.out.println();
		}
	}
	
}
