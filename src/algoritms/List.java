package algoritms;

public interface List <T> {
	int getSize();
	
	T get(int index) throws IndexOutOfBoundsException;
	
	void set(T object, int index) throws IndexOutOfBoundsException;
	
	void add(T object);
	
	void insert(T object, int index) throws IndexOutOfBoundsException;
	
	T remove(int index) throws IndexOutOfBoundsException;
	
	void clear();
}
