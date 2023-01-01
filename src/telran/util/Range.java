package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
	private static final String EXCEPTION_MESSAGE = "Max value must be greater than Min value";
	private int min;
	private int max;

	private class RangeIterator implements Iterator<Integer> {
		int current = min;

		@Override
		public boolean hasNext() {//when they are equal returns false
			return current < max;
		}

		@Override
		public Integer next() {
			if(!hasNext()) {//==false
				throw new NoSuchElementException();
			}
			return current++;//until it == max
		}
	}

	public Range(int min, int max) {
		if (max <= min) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
		this.min = min;
		this.max = max;
	}

	public boolean checkNumber(int number) {
		return number >= min && number < max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		if (min >= max) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}

		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		if (max <= min) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
		this.max = max;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}
}
