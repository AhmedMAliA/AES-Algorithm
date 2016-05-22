import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Encryption {

	static String[][] cipher_key = new String[4][4];
	static String[][] input = new String[4][4];
	static ArrayList<String[]> keys;
	public Encryption() {
	}

	public static void encryption() throws IOException {
		System.out.println("Enter The Plain text : ");
		Scanner s = new Scanner(System.in);
		String plain = s.next();
		plain = plain.toLowerCase();
		input = putInput(plain);
		for (int j = 0; j < input.length; j++) {
			for (int k = 0; k < input.length; k++) {
				System.out.print(input[j][k] + " ");
			}
			System.out.println();
		}

		System.out.println("Enter The cipher key : ");
		String key = s.next();
		key = key.toLowerCase();
		cipher_key = putInput(key);
		for (int j = 0; j < input.length; j++) {
			for (int k = 0; k < input.length; k++) {
				System.out.print(cipher_key[j][k] + " ");
			}
			System.out.println();
		}
		 keys = new KeySchedule(cipher_key,"encryption").generateNewKey();
		String[][] intial_input = new AddRoundKey(input, cipher_key)
				.intialRound();
		String[][] cipher = new String[4][4];
		String[][] sub = new String[4][4];
		int index = 4;
		for (int i = 0; i < 10; i++) {
			intial_input = new SubBytes(intial_input).findSubBytes();
			intial_input = new ShiftRows(intial_input).shift();
			if (i != 9)
				intial_input = new MixColumns(intial_input).mixedColumns();
			/*
			 * for (int j = 0; j < intial_input.length; j++) { for (int k = 0; k
			 * < intial_input.length; k++) {
			 * 
			 * //int decimal = Integer.parseInt(intial_input[k][j], 16);
			 * //intial_input[k][j] = Integer.toBinaryString(decimal);
			 * cipher[k][j] = MixColumns.xOR(intial_input[k][j],
			 * keys.get(index)[k]);
			 * 
			 * } index++; intial_input = cipher;
			 */
			sub = Encryption.subKey(keys, index);
			intial_input = new AddRoundKey(intial_input, sub).intialRound();
			index += 4;
			cipher = intial_input;
		}

		System.out.println("The Cipher Text is : ");
		for (int j = 0; j < cipher.length; j++) {
			for (int k = 0; k < cipher.length; k++) {

				if (cipher[j][k].length() > 2) {

					int decimal = Integer.parseInt(cipher[j][k], 2);
					cipher[j][k] = Integer.toString(decimal, 16);
				}
				if (cipher[j][k].length() == 1)
					cipher[j][k] = "0" + intial_input[j][k];
				System.out.print(cipher[j][k] + " ");
			}
			System.out.println();
		}
	}

	public static String[][] subKey(ArrayList<String[]> key, int start) {
		String sub[][] = new String[4][4];
		for (int j = 0; j < sub.length; j++) {
			for (int k = 0; k < sub.length; k++) {
				if(key.get(start)[k].length()>2)
					sub[k][j] = Integer.toHexString(Integer.parseInt(key.get(start)[k],2));
				else
					sub[k][j] =key.get(start)[k];
					
			}
			start++;
		}
		return sub;
	}

	public static String[][] putInput(String input_text) {
		String[][] ret = new String[4][4];
		input_text = input_text.substring(0, 32);
		String subPlain = "";

		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret.length; j++) {
				subPlain = input_text.substring(0, 2);
				ret[j][i] = subPlain;
				input_text = input_text.substring(2, input_text.length());
			}

		}
		return ret;
	}
}
