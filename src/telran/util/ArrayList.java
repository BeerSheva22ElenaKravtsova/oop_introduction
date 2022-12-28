package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

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
		int i = 0;
		while (res != true && i < size) {
			if (pattern.equals(array[i])) {
				System.arraycopy(array, i + 1, array, i, size - i);
				res = true;
				size--;
			}
			i++;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		int i = 0;
		while (i < size) {
			if (predicate.test(array[i])) {
				System.arraycopy(array, i + 1, array, i, size - i);
				res = true;
				size--;
			} else {
				i++;
			}
		}
		return res;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
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

		if (arr.length > size) {
			for (int j = size; j < arr.length; j++) {
				arr[j] = null;
			}
		}
		System.arraycopy(array, 0, arr, 0, size);
		return arr;
	}

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(
					"You tried to call index: " + index + ", But size of array is: " + size);
		}
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(
					"You tried to call index: " + index + ", But size of array is: " + size);
		}
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
			res = (array[i] == null && pattern == null) || array[i].equals(pattern) ? i : -1;
			i++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		int i = size - 1;
		while (res == -1 && i >= 0) {
			res = (array[i] == null && pattern == null) || array[i].equals(pattern) ? i : -1;
			i--;
		}
		return res;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(
					"You tried to call index: " + index + ", But size of array is: " + size);
		}
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(
					"You tried to call index: " + index + ", But size of array is: " + size);
		}
		array[index] = element;
	}
}
