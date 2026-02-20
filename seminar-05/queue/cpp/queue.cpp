#include <stdexcept>

template<typename T>
class Queue {
private:
    struct Node {
        T data;
        Node* next;

        Node(const T& data) : data(data), next(nullptr) {}
    };

    Node* head;
    Node* tail;
    int size_;

public:
    Queue() : head(nullptr), tail(nullptr), size_(0) {}

    ~Queue() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    void enqueue(const T& value) {
        Node* node = new Node(value);

        if (tail == nullptr) {
            head = tail = node;
        } else {
            tail->next = node;
            tail = node;
        }
        size_++;
    }

    T dequeue() {
        if (isEmpty()) {
            throw std::runtime_error("dequeue from empty queue");
        }

        Node* node = head;
        T value = node->data;
        head = head->next;

        if (head == nullptr) {
            tail = nullptr;
        }

        delete node;
        size_--;
        return value;
    }

    T peek() const {
        if (isEmpty()) {
            throw std::runtime_error("peek from empty queue");
        }
        return head->data;
    }

    bool isEmpty() const {
        return head == nullptr;
    }

    int size() const {
        return size_;
    }
};
