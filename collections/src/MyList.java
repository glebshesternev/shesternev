public interface MyList<T> {
    boolean add(T element);

    void add(int index, T element);

    T get(int index);

    int indexOf(Object o);

    boolean isEmpty();

    T remove(int index);

    boolean remove(Object o);

    T set(int index, T element);

    int size();
}
