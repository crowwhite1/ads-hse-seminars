public class Queue<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head; // начало очереди
    private Node<T> tail; // конец очереди
    private int size;

    public Queue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void enqueue(T value) {
        Node<T> node = new Node<>(value);

        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("dequeue from empty queue");
        }

        T value = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("peek from empty queue");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Object front = (head == null) ? null : head.data;
        return "Queue(size=" + size + ", front=" + front + ")";
    }
}
