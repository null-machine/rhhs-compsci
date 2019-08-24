import java.util.Iterator;

/**
* A data structure specialized for object pooling.
* Items can only be modified once added, not removed - otherwise defeats the purpose of pooling.
* Improves performance at the cost of loading time and memory.
* @author Glen Wang
* @version 1.0
* @date June 11, 2019
*/
public class PoolingQueue<E> implements Iterable<E> {

	private Node<E> head, tail;

	/**
	* Adds an item to the pool.
	* @param item The item to be pooled.
	*/
	public void pool(E item) {
		if (tail == null) {
			tail = new Node<E>(item, null);
			head = tail;
		} else {
			Node<E> oldTail = tail;
			tail = new Node<E>(item, null);
			oldTail.next = tail;
		}
		return;
	}

	/**
	* Returns the item at the head of the queue, then moves said item to the tail.
	* @return The reference to the item at the head of the queue.
	*/
	public E cycle() {
		if (head == null) {
			return null;
		}
		E returnItem = head.item;
		pool(returnItem);
		if (head.next == null) {
			head = null;
			tail = null;
			return returnItem;
		}
		head = head.next;
		return returnItem;
	}

	@Override
	public Iterator<E> iterator() {
		return new PoolIterator<E>(head);
	}

	private class Node<T> {
		private T item;
		private Node<T> next;

		private Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}

	private class PoolIterator<T> implements Iterator<T> {
		private Node<T> node;

		@Override
		public boolean hasNext() {
			return !(node.next == null);
		}

		@Override
		public T next() {
			node = node.next;
			return node.item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		private PoolIterator(Node<T> head) {
			this.node = head;
		}
	}
}
