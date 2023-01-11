package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public class SetTest extends CollectionTest {
	Set<Integer> set;

	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}

	@Override
	@Test
	void testAdd() {
		assertTrue(set.add(Integer.MAX_VALUE));
		assertFalse(set.add(Integer.MAX_VALUE));

	}

	@Override
	@Test
	void testIterator() {
		Integer actual[] = new Integer[numbers.length];
		int index = 0;
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			actual[index++] = it.next();
		}
		Arrays.sort(numbers, 0, numbers.length);
		Arrays.sort(actual, 0, actual.length);
		assertArrayEquals(numbers, actual);
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}
}
