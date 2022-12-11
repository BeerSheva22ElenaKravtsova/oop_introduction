package telran.base.cipher.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.base.cipher.BaseCipher;

class BaseCipherTest {

	@Test
	void cipherTest() {
		BaseCipher basecipher = new BaseCipher(8);
		assertEquals(98765, basecipher.decipher(basecipher.cipher(98765)));		
		
		basecipher.setKey("01");
		assertEquals("11000000111001101", basecipher.cipher(98765));
		assertEquals(98765, basecipher.decipher("11000000111001101"));
		
		basecipher.setKey("01234567");
		assertEquals("300715", basecipher.cipher(98765));
		assertEquals(98765, basecipher.decipher("300715"));
		
		basecipher.setKey("0123456789ABCDEF");
		assertEquals("181CD", basecipher.cipher(98765));
		assertEquals(98765, basecipher.decipher("181CD"));
	}
}
