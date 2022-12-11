package telran.base.cipher;

import java.util.Arrays;

import javax.sound.sampled.ReverbType;

public class BaseCipher {
	private static final char MIN_ASCII_VALUE = 33;
	private static final char MAX_ASCII_VALUE = 126;
	private static final int MIN_LENGTH_OF_KEY = 2;
	private static final int MAX_LENGTH_OF_KEY = MAX_ASCII_VALUE - MIN_ASCII_VALUE + 1;
	private String key;

	public BaseCipher(int length) {
		if (length < MIN_LENGTH_OF_KEY) {
			length = MIN_LENGTH_OF_KEY;
		} else if (length > MAX_LENGTH_OF_KEY) {
			length = MAX_LENGTH_OF_KEY;
		}
		key = getRandomValuesOfASCII(length);
	}

	public void setKey(String key) {
		this.key = key;
	}

	private static String getRandomValuesOfASCII(int length) {
		char[] res = new char[length];
		boolean[] helper = new boolean[MAX_LENGTH_OF_KEY];
		char randomValue;
		for (int i = 0; i < length; i++) {
			randomValue = getRandomNumber(MIN_ASCII_VALUE, MAX_ASCII_VALUE);
			if (helper[randomValue - MIN_ASCII_VALUE] == false) {
				res[i] = randomValue;
				helper[randomValue - MIN_ASCII_VALUE] = true;
			}
		}
		return new String(res);
	}

	public static char getRandomNumber(char min, char max) {
		return (char) (min + Math.random() * (max - min + 1));
	}

	public String cipher(int number) {
		int lengthOfKey = key.length();
		String res = "";
		while (number > 0) {
			res += String.valueOf(key.charAt(number % lengthOfKey));
			number = number / lengthOfKey;
		}
		return reverse(res);
	}

	private String reverse(String lastRes) {
		String res = "";
		for (int i = lastRes.length() - 1; i >= 0; i--) {
			res += lastRes.charAt(i);
		}
		return res;
	}

	public Integer decipher(String cipher) {
		Integer res = null;
		int lengthOfCipher = cipher.length();
		if (lengthOfCipher != 0) {
			res = 0;
			for (int i = 0; i < lengthOfCipher; i++) {
				res += key.indexOf(cipher.charAt(i)) * pow(key.length(), lengthOfCipher - i - 1);
			}
		}
		return res;
	}

	public int pow(int number, int powValue) {
		return (int) Math.pow(number, powValue);
	}
}