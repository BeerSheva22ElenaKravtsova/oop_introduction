

package telran.util;

import java.util.Arrays;
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
	public boolean add(T element) {// на каждый добавленный элемент не делается new, только если нет места для
									// элемента - тогда увеличиваем в 2 раза
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
	public boolean remove(T pattern) {// удаляет первый попавшийся, никогда не строит массив, только сдвигает с помощью Array.copy
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T Pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T[] toArray(T[] arr) {//если длина массива достаточно - то просто заполняем
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public T remove(int index) {// если нет бросается exception (no such element exception)
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(T pattern) {//если не правильно - бросаем Exception
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T get(int index) {// неправильный индекс - возвращается null? Exception
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int index, T element) {
		// TODO Auto-generated method stub

	}

}
