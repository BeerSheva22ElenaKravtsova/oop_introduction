package telran.util;

import java.util.function.Predicate;

public interface Collection <T>{
	boolean add(T element);
	boolean remove (T pattern);
	boolean removeIf(Predicate <T> predicate);
	boolean isEmpty();
	int size();
	boolean contains(T Pattern);
	
	/**
	 * если в array который принимается
	 * @param arr
	 * @return array containing elements of a collection
	 * if ar refers to memory that enough for all elements of collection than new array is not created
	 * otherwise there will be created new array
	 * если выделенного места достаточно для того чтобы поместить туда элементы коллекции - новый массив ен выделяется, этот же и используется
	 * если ar содержит меньшее количетсво элементов, чем то, которое у нас есть
	 * тогда мы строим новый массив (но не new) - см какие были методы
	 * if ar refers to memory that is greater the required for all elements of Collection will be put in the array and rest of memory will be filled by null's
	 *  если массив обращается к памяти, которая больше, чем нужно, то новый массив не строится, а оставшаяся часть заполняется null's
	 */
	T[] toArray(T[] arr);

}
