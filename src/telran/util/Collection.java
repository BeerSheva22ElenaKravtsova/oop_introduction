package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.*;

public interface Collection<T> extends Iterable<T> {

	boolean add(T element);

	boolean remove(T pattern);

	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while (it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
			}
		}
		return oldSize > size();
	}

	boolean isEmpty();

	int size();

	boolean contains(T pattern);

	/**
	 * @param arr
	 * @return array containing elements of a collection if arr refers to memory
	 *         that enough for all elements of collection than new array is not
	 *         created otherwise there will be created new array if arr refers to
	 *         memory that is greater the required for all elements of Collection
	 *         will be put in the array and rest of memory will be filled by null's
	 */
	default T[] toArray(T[] arr) {
		if (arr.length < size()) {
			arr = Arrays.copyOf(arr, size());
		}
		Iterator<T> it = iterator();
		int i = 0;
		T obj = it.next();
		while (it.hasNext() && i < size()) {
			arr[i] = obj;
			obj = it.next();
			i++;
		}
		arr[i] = obj;
		Arrays.fill(arr, size(), arr.length, null);
		return arr;
	}
}
