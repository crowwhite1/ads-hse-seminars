public class Deque<T> {

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> head; // левый конец
    private Node<T> tail; // правый конец
    private int size;

    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    public void pushFront(T value) {
        Node<T> node = new Node<>(value, null, head);

        if (head == null) {
            head = tail = node;
        } else {
            head.prev = node;
            head = node;
        }
        size++;
    }

    public void pushBack(T value) {
        Node<T> node = new Node<>(value, tail, null);

        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T popFront() {
        if (isEmpty()) {
            throw new IllegalStateException("pop_front from empty deque");
        }

        T value = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }

        size--;
        return value;
    }

    public T popBack() {
        if (isEmpty()) {
            throw new IllegalStateException("pop_back from empty deque");
        }

        T value = tail.data;
        tail = tail.prev;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        size--;
        return value;
    }

    public T peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException("peek_front from empty deque");
        }
        return head.data;
    }

    public T peekBack() {
        if (isEmpty()) {
            throw new IllegalStateException("peek_back from empty deque");
        }
        return tail.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Object left = isEmpty() ? null : head.data;
        Object right = isEmpty() ? null : tail.data;
        return "Deque(size=" + size + ", front=" + left + ", back=" + right + ")";
    }
}
