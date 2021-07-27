public class MyArrayList<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 16;

    Object[] array;
    int size;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int size) {
        array = new Object[size];
        this.size = size;
    }


    @Override
    public boolean add(T element) {
        checkCapacity();
        array[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        checkCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object temp = array[index];
        System.arraycopy(array, index + 1, array, index, size - index + 1);
        size--;
        return (T) temp;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1)
            return false;
        System.arraycopy(array, index + 1, array, index, size - index + 1);
        size--;
        return true;
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        Object temp = array[index];
        array[index] = element;
        return (T) temp;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0)
            throw new IllegalArgumentException("Index should be positive or zero");
        if (index > size)
            throw new IllegalArgumentException("Index should be less then collection size");
    }

    private void checkCapacity() {
        if (size >= array.length) {
            Object[] temp = array;
            array = new Object[(array.length) * 3 / 2 + 1];
            System.arraycopy(temp, 0, array, 0, temp.length);
        }
    }
}
