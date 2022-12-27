package telran.util;

public interface List<T> extends Collection<T>{
	void add(int index, T element);//добавляем по этому индексу, если он не правильный - exception
	T remove(int index);//? что возвращается если элемента нет
	int indexOf(T pattern);//если элемента нет - возвращается -1
	int lastIndexOf(T pattern);
	T get(int index);
	void set(int index, T element);

}
