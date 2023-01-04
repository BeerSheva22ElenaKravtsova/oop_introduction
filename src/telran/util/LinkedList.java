package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			current = current.next;
			return res;
		}
	}

	@Override
	public boolean add(T element) {
		Node<T> node = new Node<>(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int i = indexOf(pattern);
		if (i >= 0) {
			remove(i);
			res = true;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int previousSize = size;
		int i = 0;
		while (i < size) {
			if (predicate.test(getNode(i).obj)) {
				remove(i);
			} else {
				i++;
			}
		}
		return previousSize > size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			ar[i] = current.obj;
			current = current.next;
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, 0, size);
		if (index == size) {
			add(element);
		} else if (index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}
	}

	private void addMiddle(int index, T element) {
		Node<T> node = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = nodeIndex.prev = node;
		node.prev = nodePrev;
		node.next = nodeIndex;
		size++;
	}

	private Node<T> getNode(int index) {
		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void addHead(T element) {
		Node<T> node = new Node<>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size - 1);
		T res = get(index);
		if (index == size - 1) {
			removeTail(index);
		} else if (index == 0) {
			removeHead(index);
		} else {
			removeMiddle(index);
		}
		return res;
	}

	private void removeMiddle(int index) {
		Node<T> nodeIndex = getNode(index);
		nodeIndex.prev.next = nodeIndex.next;
		nodeIndex.next.prev = nodeIndex.prev;
		nodeIndex.prev = nodeIndex.next = null;
		nodeIndex.obj = null;
		size--;
	}

	private void removeHead(int index) {
		head.next.prev = null;
		head.obj = null;
		head = head.next;
		size--;
	}

	private void removeTail(int index) {
		tail.obj = null;
		if (tail.prev != null) {
			tail.prev.next = null;
			tail = tail.prev;
		}
		size--;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int i = 0;
		while (res == -1 && i < size) {
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
		return get(index) == null ? get(index) == pattern : get(index).equals(pattern);
	}

	@Override
	public T get(int index) {
		checkIndex(index, 0, size - 1);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, 0, size - 1);
		Node<T> node = getNode(index);
		node.obj = element;
	}
}