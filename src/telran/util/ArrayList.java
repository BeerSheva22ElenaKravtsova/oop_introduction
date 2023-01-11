package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;

	private class ArrayListIterator implements Iterator<T> {
		int current = 0;
		boolean ableToRemove = false;
		int previous = -1;

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			ableToRemove = true;
			previous++;
			return array[current++];
		}

		@Override
		public void remove() {
			if (!ableToRemove) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(--previous);
			ableToRemove = false;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T element) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int previousSize = size;
		int indexCounter = 0;
		for (int i = 0; i < previousSize; i++) {
			if (!predicate.test(array[i])) {
				array[indexCounter++] = array[i];
			}
		}
		size = indexCounter;
		Arrays.fill(array, previousSize - size, previousSize, null);
		return previousSize > size;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, 0, size);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size - 1);
		T res = array[index];
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		size--;
		return res;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int i = 0;
		while (res == -1 && i < array.length) {
			if (isEqual(pattern, array[i])) {
				res = i;
			}
			i++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		int i = size - 1;
		while (res == -1 && i >= 0) {
			if (isEqual(pattern, array[i])) {
				res = i;
			}
			i--;
		}
		return res;
	}

	@Override
	public T get(int index) {
		checkIndex(index, 0, size - 1);
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, 0, size - 1);
		array[index] = element;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
}
