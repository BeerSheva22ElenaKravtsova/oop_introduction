package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class ArrayListTest {
	ArrayList<String> strings = new ArrayList<String>();
	ArrayList<Integer> numbers = new ArrayList<Integer>();

	@Test
	void testAddT() {
		// testIsEmpty
		assertTrue(numbers.isEmpty());
		assertTrue(strings.isEmpty());

		// testAdd
		assertTrue(strings.add("abcd"));
		assertTrue(strings.add("abcde"));
		assertTrue(strings.add(null));
		assertTrue("abcd".equals(strings.get(0)));
		assertEquals(3, strings.size());
		assertFalse(strings.isEmpty());

		assertTrue(numbers.add(-7));
		assertTrue(numbers.add(0));
		assertTrue(numbers.add(null));
		Integer i = 0;
		assertTrue(i.equals(numbers.get(1)));
		assertEquals(3, numbers.size());
		assertFalse(numbers.isEmpty());

		// testGet
		try {
			numbers.get(5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals(-7, numbers.get(0));
		assertEquals(null, strings.get(2));

		// testSet
		try {
			strings.get(5);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Integer j = 70;
		numbers.set(1, (Integer) 70);
		assertTrue(j.equals(numbers.get(1)));
		strings.set(1, "edcba");
		assertTrue("edcba".equals(strings.get(1)));

		// testIndexOf
		assertEquals(0, numbers.indexOf(-7));
		assertEquals(2, strings.indexOf(null));

		// testLastIndexOf
		assertTrue(strings.add("zzz"));
		assertTrue(strings.add(null));
		assertTrue(strings.add("abcd"));
		assertEquals(6, strings.size());
		assertEquals(5, strings.lastIndexOf("abcd"));

		assertTrue(numbers.add(-90));
		assertTrue(numbers.add(null));
		assertTrue(numbers.add(5));
		assertEquals(6, numbers.size());
		assertEquals(4, numbers.lastIndexOf(null));

		// testToArray
		String[] arrayStr = new String[6];
		strings.toArray(arrayStr);

		Integer[] arrayInt = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		numbers.toArray(arrayInt);

		Integer[] arrayIntSmall = new Integer[] { 0, 0 };
		numbers.toArray(arrayIntSmall);

		// testRemove
		assertTrue(numbers.remove((Integer) 70));
		assertEquals(5, numbers.size());
		assertEquals(null, numbers.get(1));

		assertTrue(strings.remove("zzz"));
		assertEquals(5, numbers.size());
		assertEquals(null, strings.get(3));

		// testContains
		assertTrue(numbers.contains(null));
		assertTrue(numbers.contains(-7));
		assertFalse(numbers.contains(7));

		assertTrue(strings.contains(null));
		assertTrue(strings.contains("abcd"));
		assertFalse(strings.contains("a"));

		// testRemoveInt
		try {
			strings.remove(6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals(null, strings.remove(2));
		assertEquals(4, strings.size());

		try {
			numbers.remove(-1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		assertEquals(5, numbers.remove(4));
		assertEquals(4, numbers.size());

		// testAddIntT
		try {
			strings.add(-1, "zzzz");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		strings.add(1, "zzz");
		assertEquals("abcd", strings.get(4));
		assertEquals(5, strings.size());

		try {
			numbers.add(6, 3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		numbers.add(4, 200);
		assertEquals(5, numbers.size());

		// testRemoveIf
		Predicate<String> predicateStrNull = s -> s == null;
		strings.removeIf(predicateStrNull);
		String subStr = "z";
		Predicate<String> predicateSubStr = s -> s.contains(subStr);
		strings.removeIf(predicateSubStr);
		assertEquals(3, strings.size());

		Predicate<Integer> predicateNull = t -> t == null;
		numbers.removeIf(predicateNull);
		assertEquals(3, numbers.size());

		Predicate<Integer> predicateEven = t -> t % 2 == 0;
		numbers.removeIf(predicateEven);
	}
}
