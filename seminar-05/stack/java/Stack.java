public class Stack<T> {

    /**
     * Узел односвязного списка.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> top; // вершина стека
    private int size;    // количество элементов

    /**
     * Создаёт пустой стек.
     */
    public Stack() {
        top = null;
        size = 0;
    }

    /**
     * Добавляет элемент на вершину стека.
     */
    public void push(T data) {
        top = new Node<>(data, top);
        size++;
    }

    /**
     * Удаляет и возвращает верхний элемент.
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("pop from empty stack");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    /**
     * Возвращает верхний элемент без удаления.
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("peek from empty stack");
        }
        return top.data;
    }

    /**
     * Проверяет, пуст ли стек.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Возвращает размер стека.
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "Stack(size=" + size +
                ", top=" + (top == null ? "null" : top.data) + ")";
    }
}
