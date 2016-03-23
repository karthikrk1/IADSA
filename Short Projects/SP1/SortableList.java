import java.util.Iterator;

public class SortableList<T extends Comparable<? super T>, E> extends
		SinglyLinkedList<T> {
	public SortableList() {

	}

	public void mergeSort() {
		SortableList<T, E> list2; // 2nd List
		Entry<T> x = this.header; // 1st List reference
		Entry<T> mid = null; // reference to be alloted to the list2.header.next
		int length = 1; // length count of the original list
		int midLength = (this.size()) / 2; /*
											 * to find the mid length of the
											 * original list
											 */

		// termination condition of the recursive function
		if (x == null || x.next == null || midLength == 0) {
			return;
		}
		// traverse the list to find the middle element
		while (x.next != null) {
			x = x.next;
			if (length == midLength) {
				/*
				 * break the original list to 2 parts, 1 list containing only
				 * the 1st half of the original elements List 2 starting from
				 * the next to the middle element reference of the original list
				 */
				mid = x.next;
				x.next = null;
				break;
			}
			length++;
		}
		list2 = new SortableList<T, E>();
		list2.header.next = mid;
		// recursive calls to 1st and 2nd list to keep on dividing
		this.mergeSort();
		list2.mergeSort();
		// call to combine the 2 lists to a single sorted list
		this.merge(list2);
	}

	// Conquering into a sige sorted list
	public void merge(SortableList<T, E> list) {
		Entry<T> temp = this.header; // temporary output list
		Entry<T> list1 = temp.next; // list 1 reference
		Entry<T> list2 = list.header.next; // list 2 reference
		while (list1 != null && list2 != null) {
			/*
			 * list1 element is greater / lesser than list2 element, accordingly
			 * copy to the output list move the corresponding reference
			 */
			if (list1.compareTo(list2) <= 0) {
				temp.next = list1;
				temp = list1;
				list1 = list1.next;
			} else {
				temp.next = list2;
				temp = list2;
				list2 = list2.next;
			}
		}
		// Copy the rest of the other list if one of the list gets exhausted
		if (list1 == null) {
			temp.next = list2;
		} else {
			temp.next = list1;
		}
	}

	public static void main(String[] args) {
		int n = 10;
		SortableList list = new SortableList();
		for (int i = 1; i <= n; i++) {
			list.add(new Integer(11 - i));
		}
		list.printList();
		list.mergeSort();
		list.printList();

	}

}
