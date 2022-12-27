package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;

class MyArraysTest {
	private static final int N_NUMBERS = 10_000;
	private static final int N_RUNS = 1_000;
	Integer numbers[] = { 13, 2, -8, 47, 100, 10, -7, 7 };
	String strings[] = { "ab", "abm", "abmb", "abmbc" };

	Comparator<Integer> evenOddComparator = this::evenOddCompare; // This is Method Reference

	@Test
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

	@Test
	void removeIf() {
		int dividor = 2;
		String subStr = "m";
		Predicate<Integer> predicateEven = t -> t % dividor == 0;
		Predicate<String> predicateSubStr = s -> s.contains(subStr);
		String expectedStr[] = { "ab" };
		Integer expectedInt[] = { 13, 47, -7, 7 };
		assertArrayEquals(expectedStr, MyArrays.removeIf(strings, predicateSubStr));
		assertArrayEquals(expectedInt, MyArrays.removeIf(numbers, predicateEven));
	}

	@Test
	void removeRepeated() {
		Integer numbers[] = { 100, 10, 18, 10, 20, 18 };
		Integer expectedNum[] = { 100, 10, 18, 20 };
		assertArrayEquals(expectedNum, MyArrays.removeRepeated(numbers));
		String string[] = { "ab", "abm", "ab", "abmb", "abmbc" };
		String expectedStr[] = { "ab", "abm", "abmb", "abmbc" };
		assertArrayEquals(expectedStr, MyArrays.removeRepeated(string));
	}

	@Test
	void contains() {
		String strings[] = { "ab", null, "abmb", "abmbc" };
		Integer numbers[] = { 13, 2, null, 47, 100, 10, -7, 7 };
		assertTrue(MyArrays.contains(strings, "ab"));
		assertTrue(MyArrays.contains(strings, null));
		assertTrue(MyArrays.contains(numbers, -7));
		assertTrue(MyArrays.contains(numbers, null));
		assertFalse(MyArrays.contains(strings, "abc"));
		assertFalse(MyArrays.contains(numbers, 9));
		assertFalse(MyArrays.contains(numbers, 0));
		assertFalse(MyArrays.contains(strings, ""));

	}

	@Test
	void joinFunctionalTest() {
		String expected = "13,2,-8,47,100,10,-7,7";
		assertEquals(expected, MyArrays.join(numbers, ","), expected);

	}

	@Test
	void joinPerformanceTest() {
		Integer largeArray[] = getLargeNumbersArray();
		for (int i = 0; i < N_RUNS; i++) {
			MyArrays.join(largeArray, ", ");
		}

		String expected = "13,2,-8,47,100,10,-7,7";
		assertEquals(expected, MyArrays.joinString(numbers, ","), expected);

	}

	private Integer[] getLargeNumbersArray() {
		Integer[] res = new Integer[N_NUMBERS];
		Arrays.fill(res, 1000);
		return res;
	}
}