public class QueueByArray<T> {
    private Object[] data;
    private int front;
    private int size;

    public QueueByArray(int capacity) {
        data = new Object[capacity];
        front = 0;
        size = 0;
    }

    public QueueByArray() {
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

    public void enqueue(T value) {
        if (front + size == data.length) {
            resize(data.length * 2);
        }
        data[front + size] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("dequeue from empty queue");
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
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("peek from empty queue");
        }
        return (T) data[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Object frontVal = isEmpty() ? null : data[front];
        return "Queue(size=" + size + ", front=" + frontVal + ")";
    }
}
