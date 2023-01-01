package telran.util;

import java.util.function.*;

public interface Collection<T> extends Iterable<T> {
	
	boolean add(T element);

	boolean remove(T pattern);

	boolean removeIf(Predicate<T> predicate);

	boolean isEmpty();

	int size();

	boolean contains(T pattern);

	T[] toArray(T[] arr);
}
