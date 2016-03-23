import java.util.*;

public class SinglyLinkedList<T> {

	// inner class
	public class Entry<T> implements Comparable<Entry<T>> {
		Integer element;
		Entry<T> next;

		Entry(T x, Entry<T> nxt) {
			element = (Integer) x;
			next = nxt;
		}

		@Override
		public int compareTo(Entry<T> another) {
			// TODO Auto-generated method stub
			return this.element - another.element;
		}

	}

	Entry<T> header, tail;

	SinglyLinkedList() {
		header = new Entry<>(null, null);
		tail = null;

	}

	// determines the size of the list
	public int size() {
		int size = 0;
		Entry<T> x = header.next;
		while (x != null) {

			x = x.next;
			size++;
		}
		return size;
	}

	// adds element to the list
	void add(T x) {
		if (tail == null) {
			header.next = new Entry<T>(x, header.next);
			tail = header.next;
		} else {
			tail.next = new Entry<T>(x, null);
			tail = tail.next;
		}

	}

	void printList() {
		Entry<T> x = header.next;
		while (x != null) {
			System.out.print(x.element + " ");
			x = x.next;
		}
		System.out.println();
	}

}
