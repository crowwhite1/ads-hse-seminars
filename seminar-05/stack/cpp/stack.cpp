#include <stdexcept>
#include <iostream>

template<typename T>
class Stack {
private:
    struct Node {
        T data;
        Node* next;

        Node(const T& data, Node* next)
            : data(data), next(next) {}
    };

    Node* top;
    int size_;

public:
    /**
     * Создаёт пустой стек.
     */
    Stack() : top(nullptr), size_(0) {}

    /**
     * Деструктор — освобождает память.
     */
    ~Stack() {
        while (!isEmpty()) {
            pop();
        }
    }

    /**
     * Добавляет элемент на вершину стека.
     */
    void push(const T& data) {
        top = new Node(data, top);
        size_++;
    }

    /**
     * Удаляет и возвращает верхний элемент.
     */
    T pop() {
        if (isEmpty()) {
            throw std::runtime_error("pop from empty stack");
        }
        Node* node = top;
        T data = node->data;
        top = top->next;
        delete node;
        size_--;
        return data;
    }

    /**
     * Возвращает верхний элемент без удаления.
     */
    T peek() const {
        if (isEmpty()) {
            throw std::runtime_error("peek from empty stack");
        }
        return top->data;
    }

    /**
     * Проверяет, пуст ли стек.
     */
    bool isEmpty() const {
        return top == nullptr;
    }

    /**
     * Возвращает размер стека.
     */
    int size() const {
        return size_;
    }
};
