public class StackByArray<T> {
    private Object[] data; // внутренний массив
    private int size;      // текущее количество элементов

    /**
     * Создаёт пустой стек.
     */
    public StackByArray(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public StackByArray() {
        this(4);
    }

    /**
     * Увеличивает ёмкость массива.
     */
    private void resize(int newCapacity) {
        Object[] newData = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Добавляет элемент на вершину стека.
     */
    public void push(T value) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[size] = value;
        size++;
    }

    /**
     * Удаляет и возвращает верхний элемент.
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("pop from empty stack");
        }
        size--;
        T value = (T) data[size];
        data[size] = null; // очищаем ссылку
        return value;
    }

    /**
     * Возвращает верхний элемент без удаления.
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("peek from empty stack");
        }
        return (T) data[size - 1];
    }

    /**
     * Проверяет, пуст ли стек.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает размер стека.
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Object top = isEmpty() ? null : data[size - 1];
        return "Stack(size=" + size + ", top=" + top + ")";
    }
}
