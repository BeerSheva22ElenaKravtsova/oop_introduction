package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	private class ArrayListIterator implements Iterator<T> {
		int index = 0;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[index++];
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
	public boolean remove(T pattern) {
		boolean res = false;
		int i = indexOf(pattern);
		if (i >= 0) {
			remove(i);
			res = true;
			array[size] = null;
		}
		return res;
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
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		int i = 0;
		while (res != true && i < array.length) {
			res = array[i] == null ? pattern == null : array[i].equals(pattern);
			i++;
		}
		return res;
	}

	/**
	 * @param arr
	 * @return array containing elements of a collection if arr refers to memory
	 *         that enough for all elements of collection than new array is not
	 *         created otherwise there will be created new array if arr refers to
	 *         memory that is greater the required for all elements of Collection
	 *         will be put in the array and rest of memory will be filled by null's
	 */
	@Override
	public T[] toArray(T[] arr) {
		if (arr.length < size) {
			arr = Arrays.copyOf(arr, size);
		}
		Arrays.fill(arr, size, arr.length, null);
		System.arraycopy(array, 0, arr, 0, size);
		return arr;
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
		size--;
		return res;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int i = 0;
		while (res == -1 && i < array.length) {
			if (isEquals(pattern, i)) {
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
			if (isEquals(pattern, i)) {
				res = i;
			}
			i--;
		}
		return res;
	}

	private boolean isEquals(T pattern, int index) {
		return array[index] == null ? array[index] == pattern : array[index].equals(pattern);
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
