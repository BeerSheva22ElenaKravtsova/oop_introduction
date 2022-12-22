package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import telran.util.test.SortComparator;

public class MyArrays {

	public static <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--;
		} while (moveMaxAtEnd(objects, length, comparator));
	}

	public static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
		boolean res = false;
		for (int i = 0; i < length; i++) {
			if (comp.compare(objects[i], objects[i + 1]) > 0) {
				swap(objects, i, i + 1);
				res = true;
			}
		}
		return res;
	}

	public static <T> void swap(T[] objects, int i, int j) {
		T tmp = objects[i];
		objects[i] = objects[j];
		objects[j] = tmp;
	}

	public static <T> int binarySearch(T[] array, T searchedNumber, Comparator<T> comp) {
		int left = 0;
		int right = array.length - 1;
		int middle = right / 2;
		while (left <= right && !array[middle].equals(searchedNumber)) {
			if (comp.compare(array[middle], searchedNumber) > 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = left + (right - left) / 2;
		}
		return left > right ? -(left+1) : middle;
	}

	public static <T> T[] filter(T[] array, Predicate<T> predicate) {
		int countPredicate = getCountPredicate(array, predicate);
		T[] res = Arrays.copyOf(array, countPredicate);
		int index = 0;
		for (T element : array) {
			if (predicate.test(element)) {
				res[index++] = element;
			}
		}
		return res;
	}

	private static <T> int getCountPredicate(T[] array, Predicate<T> predicate) {
		int res = 0;
		for (T element : array) {
			if (predicate.test(element)) {
				res++;
			}
		}
		return res;
	}
	

	
	
}
