#include <stdexcept>

template<typename T>
class Queue {
private:
    T* data;
    int front;
    int size_;
    int capacity;

    void resize(int newCapacity) {
        T* newData = new T[newCapacity];
        for (int i = 0; i < size_; i++) {
            newData[i] = data[front + i];
        }
        delete[] data;
        data = newData;
        front = 0;
        capacity = newCapacity;
    }

public:
    Queue(int initialCapacity = 4)
        : front(0), size_(0), capacity(initialCapacity) {
        data = new T[capacity];
    }

    ~Queue() {
        delete[] data;
    }

    void enqueue(const T& value) {
        if (front + size_ == capacity) {
            resize(capacity * 2);
        }
        data[front + size_] = value;
        size_++;
    }

    T dequeue() {
        if (isEmpty()) {
            throw std::runtime_error("dequeue from empty queue");
        }

        T value = data[front];
        front++;
        size_--;

        if (size_ == 0) {
            front = 0;
        }

        return value;
    }

    T peek() const {
        if (isEmpty()) {
            throw std::runtime_error("peek from empty queue");
        }
        return data[front];
    }

    bool isEmpty() const {
        return size_ == 0;
    }

    int size() const {
        return size_;
    }
};
