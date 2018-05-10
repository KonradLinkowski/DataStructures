package algoritms;

import java.util.NoSuchElementException;

public interface Stack<T> {
	int getSize();

	T pop() throws NoSuchElementException;
	
	void add(T object);
	
	T peek() throws NoSuchElementException;
	
	boolean isEmpty();
	
	void clear();
}
