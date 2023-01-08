package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {
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

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flNext = false;

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
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			removeNode(removedNode);
			flNext = false;
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

	private void removeNode(Node<T> current) {
		if (current == head) {
			removeHead();
		} else if (current == tail) {
			removeTail();
		} else {
			removeMiddle(current);
		}
		size--;
	}

	private void removeTail() {
		Node<T> prev = tail.prev;
		prev.next = null;
		tail = prev;
	}

	private void removeHead() {
		if (head.next == null) {
			head = tail = null;
		} else {
			Node<T> next = head.next;
			next.prev = null;
			head = next;
		}
	}

	private void removeMiddle(Node<T> current) {
		Node<T> prev = current.prev;
		Node<T> next = current.next;
		prev.next = next;
		next.prev = prev;
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

	/** 
	 * @param index1 - index of a node in LinkedList
	 * @param index2 - index of a node in LinkedList (index2 should be less or equal to index1
	 * 	The method sets next field of the node at index1 as a reference to the node at index2
	 */
	public void setNext(int index1, int index2) {
		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
		Node<T> node1 = getNode(index1);
		Node<T> node2 = getNode(index2);
		node1.next = node2;
	}

	/**
	 * @return returning true if the LinkedList object has a loop
	 * Limitations
	 * O[N]
	 * No additional collections, no arrays
	 * No using property/method “size”
	 * No using field prev in the class Node
	 */
	public boolean isLoop() {
		boolean res = false;
		Node<T> node = head;
		Node<T> nextNode = head.next;
		while (node != null && nextNode != null && !res) {
			node = node.next;
			nextNode = nextNode.next.next;
			if (node == nextNode) {
				res = true;
			}			
		}
		return res;
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
		Node<T> removedNode = getNode(index);
		if (removedNode == null) {
			throw new IllegalStateException("removedNode in method remove is null");
		}
		removeNode(removedNode);
		return removedNode.obj;
	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head;
		int i = 0;
		while (current != null && !isEqual(current.obj, pattern)) {
			i++;
			current = current.next;
		}
		return current != null ? i : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail;
		int i = size - 1;
		while (current != null && !isEqual(current.obj, pattern)) {
			i--;
			current = current.prev;
		}
		return i;
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