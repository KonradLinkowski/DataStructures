package algoritms;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T>, Stack<T> {

	private int count = 0;
	private T[] elements;
	
	@SuppressWarnings("unchecked")
	public ArrayList() {
		elements = (T[]) new Object[8];
	}
	
	@Override
	public int getSize() {
		return count;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index >= count) {
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}

	@Override
	public void set(T object, int index) throws IndexOutOfBoundsException {
		if (index >= count) {
			throw new IndexOutOfBoundsException();
		}
		elements[index] = object;
	}

	@Override
	public void add(T object) {
		tryToResize();
		elements[count] = object;
		count++;
	}

	@Override
	public void insert(T object, int index) throws IndexOutOfBoundsException {
		if (index > count) {
			throw new IndexOutOfBoundsException();
		}
		tryToResize();
		for (int i = count; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		count++;
		elements[index] = object;
	}

	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		if (index >= count) {
			throw new IndexOutOfBoundsException();
		}
		T temp = elements[index];
		tryToResize();
		for (int i = index; i < count; i++) {
			elements[i] = elements[i + 1];
		}
		count--;
		return temp;
	}

	@SuppressWarnings("unchecked")
	private void tryToResize() {
		if (count == elements.length) {
			T[] array = (T[]) new Object[elements.length * 2];
			for (int i = 0; i < count; i++) {
				array[i] = elements[i];
			}
			elements = array;
		} else if (count < elements.length / 4) {
			T[] array = (T[]) new Object[elements.length / 4];
			for (int i = 0; i < count; i++) {
				array[i] = elements[i];
			}
			elements = array;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		elements = (T[]) new Object[8];
		count = 0;
	}

	@Override
	public T pop() throws NoSuchElementException {
		T value = elements[count - 1];
		count--;
		tryToResize();
		return value;
	}

	@Override
	public T peek() throws NoSuchElementException {
		return elements[count - 1];
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}
}
