#include <stdexcept>

template<typename T>
class Deque {
private:
    T* data;
    int front;     // индекс первого элемента
    int size_;     // количество элементов
    int capacity;  // ёмкость массива

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
    Deque(int initialCapacity = 4)
        : front(0), size_(0), capacity(initialCapacity) {
        data = new T[capacity];
    }

    ~Deque() {
        delete[] data;
    }

    void pushBack(const T& value) {
        if (front + size_ == capacity) {
            resize(capacity * 2);
        }
        data[front + size_] = value;
        size_++;
    }

    T popFront() {
        if (isEmpty()) {
            throw std::runtime_error("pop_front from empty deque");
        }
        T value = data[front];
        front++;
        size_--;

        if (size_ == 0) {
            front = 0;
        }
        return value;
    }

    T peekFront() const {
        if (isEmpty()) {
            throw std::runtime_error("peek_front from empty deque");
        }
        return data[front];
    }

    T peekBack() const {
        if (isEmpty()) {
            throw std::runtime_error("peek_back from empty deque");
        }
        return data[front + size_ - 1];
    }

    T popBack() {
        if (isEmpty()) {
            throw std::runtime_error("pop_back from empty deque");
        }
        int backIndex = front + size_ - 1;
        T value = data[backIndex];
        size_--;

        if (size_ == 0) {
            front = 0;
        }
        return value;
    }

    void pushFront(const T& value) {
        if (size_ == 0) {
            data[0] = value;
            front = 0;
            size_ = 1;
            return;
        }

        if (front > 0) {
            front--;
            data[front] = value;
            size_++;
            return;
        }

        // front == 0: слева нет места
        if (size_ == capacity) {
            resize(capacity * 2);
        }

        // сдвиг вправо на 1 (O(n))
        for (int i = front + size_ - 1; i >= front; i--) {
            data[i + 1] = data[i];
        }
        data[front] = value;
        size_++;
    }

    bool isEmpty() const {
        return size_ == 0;
    }

    int size() const {
        return size_;
    }
};
