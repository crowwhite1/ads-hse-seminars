public class SinglyLinkedList<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        Node(T value) {
            this(value, null);
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void pushFront(T value) {
        Node<T> node = new Node<>(value, head);
        head = node;
        if (tail == null) {
            tail = node;
        }
        size++;
    }

    public void pushBack(T value) {
        Node<T> node = new Node<>(value);
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
        size--;
        if (head == null) {
            tail = null;
        }
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

    /**
     * Удаляет первое вхождение value.
     * @return true если удалили, иначе false
     */
    public boolean removeFirst(T value) {
        Node<T> prev = null;
        Node<T> cur = head;

        while (cur != null) {
            boolean eq = (value == null && cur.value == null) ||
                         (value != null && value.equals(cur.value));

            if (eq) {
                if (prev == null) {
                    head = cur.next;
                } else {
                    prev.next = cur.next;
                }

                if (cur == tail) {
                    tail = prev;
                }

                size--;
                if (head == null) {
                    tail = null;
                }
                return true;
            }

            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> cur = head;
        int i = 0;
        while (cur != null) {
            arr[i++] = cur.value;
            cur = cur.next;
        }
        return arr;
    }
}
