package telran.util;

import java.util.function.Predicate;

public interface Collection <T>{
	boolean add(T element);
	boolean remove (T pattern);
	boolean removeIf(Predicate <T> predicate);
	boolean isEmpty();
	int size();
	boolean contains(T pattern);
	T[] toArray(T[] arr);
}
