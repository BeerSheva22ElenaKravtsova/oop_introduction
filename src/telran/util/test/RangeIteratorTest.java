package telran.util.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import telran.util.*;

public class RangeIteratorTest {
	Integer numbers[] = { 1, 2, 3, 4, 5 };

	@Test
	void test() {//universal pattern
		Range range = new Range(1, 6);
		ArrayList<Integer> list = new ArrayList();
//		Iterator<Integer> it = range.iterator();
//		while(it.hasNext()) {
//			list.add(it.next());//take the current element from the iterator and put it in next	
//		}
		for(Integer num: range) {//this is a pattern when we have to iterate everything and do not delete anything
			list.add(num);
		}
		assertArrayEquals(numbers, list.toArray(new Integer[0]));
	}
}
