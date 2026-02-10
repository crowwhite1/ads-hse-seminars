#include <stdexcept>
#include <iostream>

template<typename T>
class Stack {
private:
    T* data;     // динамический массив
    int size_;   // текущее количество элементов
    int capacity;

    /**
     * Увеличивает ёмкость массива.
     */
    void resize(int newCapacity) {
        T* newData = new T[newCapacity];
        for (int i = 0; i < size_; i++) {
            newData[i] = data[i];
        }
        delete[] data;
        data = newData;
        capacity = newCapacity;
    }

public:
    /**
     * Конструктор.
     */
    Stack(int initialCapacity = 4)
        : size_(0), capacity(initialCapacity) {
        data = new T[capacity];
    }

    /**
     * Деструктор.
     */
    ~Stack() {
        delete[] data;
    }

    /**
     * Добавляет элемент на вершину стека.
     */
    void push(const T& value) {
        if (size_ == capacity) {
            resize(capacity * 2);
        }
        data[size_] = value;
        size_++;
    }

    /**
     * Удаляет и возвращает верхний элемент.
     */
    T pop() {
        if (isEmpty()) {
            throw std::runtime_error("pop from empty stack");
        }
        size_--;
        return data[size_];
    }

    /**
     * Возвращает верхний элемент без удаления.
     */
    T peek() const {
        if (isEmpty()) {
            throw std::runtime_error("peek from empty stack");
        }
        return data[size_ - 1];
    }

    /**
     * Проверяет, пуст ли стек.
     */
    bool isEmpty() const {
        return size_ == 0;
    }

    /**
     * Возвращает размер стека.
     */
    int size() const {
        return size_;
    }
};
