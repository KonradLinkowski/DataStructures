package algoritms;

public class LinkedList<T> implements List<T> {
	private class Element<U> {
		private U value;
		private Element<U> next;
		private Element<U> prev;
		
		public Element(U object) {
			this.value = object;
		}
		
		public U getValue() {
			return value;
		}

		public void setValue(U value) {
			this.value = value;
		}

		public Element<U> getNext() {
			return next;
		}

		public void setNext(Element<U> next) {
			this.next = next;
		}

		public Element<U> getPrev() {
			return prev;
		}

		public void setPrev(Element<U> prev) {
			this.prev = prev;
		}
	}
	
	private Element<T> head;
	private Element<T> tail;
	private int count;
	
	public LinkedList() {}
	
	@Override
	public int getSize() {
		return count;
	}
	
	@Override
	public void add(T object) {
		Element<T> temp = new Element<T>(object);
		count++;
		if (head == null) {
			head = temp;
			tail = temp;
			return;
		}
		tail.setNext(temp);
		temp.setPrev(tail);
		tail = temp;
	}

	@Override
	public void insert(T object, int index) {
		if (index > count) {
			throw new IndexOutOfBoundsException();
		}
		count++;
		Element<T> temp = new Element<T>(object);
		if (index == 0) {
			temp.setNext(head);
			head = temp;
			return;
		}
		if(index == count) {
			temp.setPrev(tail);
			tail.setNext(temp);
			tail = temp;
			return;
		}
		Element<T> tempPrev = getElement(index - 1);
		Element<T> tempNext = tempPrev.getNext();
		tempPrev.setNext(temp);
		tempNext.setPrev(temp);
		temp.setPrev(tempPrev);
		temp.setNext(tempNext);
	}

	@Override
	public T remove(int index) {
		if (index >= count) {
			throw new IndexOutOfBoundsException();
		}
		count--;
		if (index == 0) {
			T value = head.getValue();
			head = head.getNext();
			head.setPrev(null);
			return value;
		}
		Element<T> temp = getElement(index);
		Element<T> tempPrev = temp.getPrev();
		Element<T> tempNext = temp.getNext();
		tempNext.setPrev(tempPrev);
		tempPrev.setNext(tempNext);
		return temp.getValue();
	}

	@Override
	public T get(int index) {
		if (index >= count) {
			throw new IndexOutOfBoundsException();
		}
		Element<T> temp = getElement(index);
		return temp.getValue();
	}

	@Override
	public void set(T object, int index) throws IndexOutOfBoundsException {
		if (index >= count) {
			throw new IndexOutOfBoundsException();
		}
		Element<T> temp = getElement(index);
		temp.setValue(object);
	}
	
	private Element<T> getElement(int index) {
		if (index >= count) {
			throw new IndexOutOfBoundsException();
		}
		Element<T> temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.getNext();
		}
		return temp;
	}
}
