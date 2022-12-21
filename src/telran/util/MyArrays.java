package telran.util;

import java.util.Comparator;

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

	public static <T> int binarySearch(T[] array, int searchedNumber, Comparator<T> comp) {
		int left = 0;
		int right = array.length - 1;
		int middle = right / 2;
		Object searchedNumberInObject = searchedNumber;
			while (left <= right && array[middle] != searchedNumberInObject) {
				if (comp.compare((T) searchedNumberInObject, array[middle]) > 0) {
					right = middle - 1;
				} else {
					left = middle + 1;
				}
				middle = left + (right - left) / 2;
			}
		return left > right ? -1 : middle;
	}
}