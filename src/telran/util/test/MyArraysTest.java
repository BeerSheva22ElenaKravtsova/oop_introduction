package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {
	Integer numbers[] = { 13, 2, -8, 47, 100, 10, -7, 7 };
	String strings[] = { "ab", "abm", "abmb", "abmbc" };

	Comparator<Integer> evenOddComparator = this::evenOddCompare; // This is Method Reference

	@Test
	@Disabled
	void sortTest() {
		String[] strings = { "abcd", "lmn", "zz" };
		String[] expected = { "zz", "lmn", "abcd" };
		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
	}

	@Test
	void evenOddTest() {
		Integer expected[] = { -8, 2, 10, 100, 47, 13, 7, -7 };
		MyArrays.sort(numbers, evenOddComparator);
		assertArrayEquals(expected, numbers);
	}

	@Test
	void binarySearchTest() {
		Comparator<String> comp = new StringsComparator();
		assertEquals(0, MyArrays.binarySearch(strings, "ab", comp));
		assertEquals(2, MyArrays.binarySearch(strings, "abmb", comp));
		assertEquals(3, MyArrays.binarySearch(strings, "abmbc", comp));
		assertEquals(-1, MyArrays.binarySearch(strings, "a", comp));
		assertEquals(-3, MyArrays.binarySearch(strings, "abma", comp));
		assertEquals(-5, MyArrays.binarySearch(strings, "lmn", comp));
	}

	@Test
	void filterTest() {
		int dividor = 2;
		String subStr = "m";
		Predicate<Integer> predicateEven = t -> t % dividor == 0; // the same if there is Class DividorPredicate: = new
																	// DividorPredicate(dividor);
		Predicate<String> predicateSubStr = s -> s.contains(subStr);
		String expectedStr[] = { "abm", "abmb", "abmbc" };
		Integer expectedInt[] = { 2, -8, 100, 10 };
		assertArrayEquals(expectedStr, MyArrays.filter(strings, predicateSubStr));
		assertArrayEquals(expectedInt, MyArrays.filter(numbers, predicateEven));
	}

	int evenOddCompare(Integer o1, Integer o2) {// this is the Method
		int remainder = Math.abs(o1) % 2; // we need an absolute value, since numbers can have a negative sign
		int res = remainder - Math.abs(o2) % 2;
		if (res == 0) {
			res = remainder != 0 ? Integer.compare(o2, o1) : Integer.compare(o1, o2);
		}
		return res;

	}
}