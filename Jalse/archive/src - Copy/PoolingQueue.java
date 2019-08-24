import java.util.Iterator;

/**
* A data structure specialized for object pooling.
* Items can only be modified once added, not removed - otherwise defeats the purpose of pooling.
* Improves performance at the cost of loading time and memory.
* @author Glen Wang
*/
public class PoolingQueue<T> implements Iterable<T> {

	private Node<T> head, tail;

	/**
	* Adds an item to the pool.
	* @param item The item to be pooled.
	*/
	public void pool(T item) {
		if (tail == null) {
			tail = new Node<T>(item, null);
			head = tail;
		} else {
			Node<T> oldTail = tail;
			tail = new Node<T>(item, null);
			oldTail.next = tail;
		}
		return;
	}

	/**
	* Returns the item at the head of the queue, then moves said item to the tail.
	* @return The reference to the item at the head of the queue.
	*/
	public T cycle() {
		if (head == null) {
			return null;
		}
		T returnItem = head.item;
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
	public Iterator<T> iterator() {
		return new PoolIterator<T>(head);
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
			if (node.next == null) {
				System.out.println("node null");
				return node.item;
			}
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
