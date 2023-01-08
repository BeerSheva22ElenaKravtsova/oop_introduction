package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class LinkedListTest extends ListTest {
	protected Integer[] numbers = { 10, 100, -5, 134, 280, 120, 15 };
	// For Checking
	LinkedList<Integer> list1;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();

		// For Checking
		list1 = new LinkedList<>();
		for (Integer num : numbers) {
			list1.add(num);
		}
	}

	@Test
	void loopTest() {
		assertFalse(list1.isLoop());
		list1.setNext(5, 0);
		assertTrue(list1.isLoop());
	}

	@Test
	void loopTestHead() {
		assertFalse(list1.isLoop());
		list1.setNext(0, 0);
		assertTrue(list1.isLoop());
	}

	@Test
	void loopTestTail() {
		assertFalse(list1.isLoop());
		list1.setNext(6, 6);
		assertTrue(list1.isLoop());
	}
}
