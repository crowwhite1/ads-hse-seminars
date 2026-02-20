public class DequeByArray<T> {

    private Object[] data;
    private int front; // индекс первого элемента
    private int size;  // количество элементов

    public DequeByArray(int capacity) {
        data = new Object[capacity];
        front = 0;
        size = 0;
    }

    public DequeByArray() {
        this(4);
    }

    private void resize(int newCapacity) {
        Object[] newData = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[front + i];
        }
        data = newData;
        front = 0;
    }

    public void pushBack(T value) {
        if (front + size == data.length) {
            resize(data.length * 2);
        }
        data[front + size] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T popFront() {
        if (isEmpty()) {
            throw new IllegalStateException("pop_front from empty deque");
        }
        T value = (T) data[front];
        data[front] = null;
        front++;
        size--;

        if (size == 0) {
            front = 0;
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException("peek_front from empty deque");
        }
        return (T) data[front];
    }

    @SuppressWarnings("unchecked")
    public T peekBack() {
        if (isEmpty()) {
            throw new IllegalStateException("peek_back from empty deque");
        }
        return (T) data[front + size - 1];
    }

    @SuppressWarnings("unchecked")
    public T popBack() {
        if (isEmpty()) {
            throw new IllegalStateException("pop_back from empty deque");
        }
        int backIndex = front + size - 1;
        T value = (T) data[backIndex];
        data[backIndex] = null;
        size--;

        if (size == 0) {
            front = 0;
        }
        return value;
    }

    public void pushFront(T value) {
        if (size == 0) {
            data[0] = value;
            front = 0;
            size = 1;
            return;
        }

        if (front > 0) {
            front--;
            data[front] = value;
            size++;
            return;
        }

        // front == 0: слева нет места
        if (size == data.length) {
            resize(data.length * 2);
        }

        // сдвиг вправо на 1 (O(n))
        for (int i = front + size - 1; i >= front; i--) {
            data[i + 1] = data[i];
        }
        data[front] = value;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Object left = isEmpty() ? null : data[front];
        Object right = isEmpty() ? null : data[front + size - 1];
        return "Deque(size=" + size + ", front=" + left + ", back=" + right + ")";
    }
}
