public class DoublyLinkedList<T> {

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
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
            throw new IllegalStateException("pop_front from empty list");
        }

        T value = head.value;
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
            throw new IllegalStateException("pop_back from empty list");
        }

        T value = tail.value;
        tail = tail.prev;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        size--;
        return value;
    }

    public boolean find(T value) {
        Node<T> cur = head;
        while (cur != null) {
            if ((value == null && cur.value == null) ||
                (value != null && value.equals(cur.value))) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean removeFirst(T value) {
        Node<T> cur = head;

        while (cur != null) {
            boolean eq = (value == null && cur.value == null) ||
                         (value != null && value.equals(cur.value));

            if (eq) {
                if (cur.prev != null) {
                    cur.prev.next = cur.next;
                } else {
                    head = cur.next;
                }

                if (cur.next != null) {
                    cur.next.prev = cur.prev;
                } else {
                    tail = cur.prev;
                }

                size--;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
}
