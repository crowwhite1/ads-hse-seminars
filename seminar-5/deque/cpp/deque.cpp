#include <stdexcept>

template<typename T>
class Deque {
private:
    struct Node {
        T data;
        Node* prev;
        Node* next;

        Node(const T& data, Node* prev, Node* next)
            : data(data), prev(prev), next(next) {}
    };

    Node* head;
    Node* tail;
    int size_;

public:
    Deque() : head(nullptr), tail(nullptr), size_(0) {}

    ~Deque() {
        while (!isEmpty()) {
            popFront();
        }
    }

    void pushFront(const T& value) {
        Node* node = new Node(value, nullptr, head);

        if (head == nullptr) {
            head = tail = node;
        } else {
            head->prev = node;
            head = node;
        }
        size_++;
    }

    void pushBack(const T& value) {
        Node* node = new Node(value, tail, nullptr);

        if (tail == nullptr) {
            head = tail = node;
        } else {
            tail->next = node;
            tail = node;
        }
        size_++;
    }

    T popFront() {
        if (isEmpty()) {
            throw std::runtime_error("pop_front from empty deque");
        }

        Node* node = head;
        T value = node->data;

        head = head->next;
        if (head == nullptr) {
            tail = nullptr;
        } else {
            head->prev = nullptr;
        }

        delete node;
        size_--;
        return value;
    }

    T popBack() {
        if (isEmpty()) {
            throw std::runtime_error("pop_back from empty deque");
        }

        Node* node = tail;
        T value = node->data;

        tail = tail->prev;
        if (tail == nullptr) {
            head = nullptr;
        } else {
            tail->next = nullptr;
        }

        delete node;
        size_--;
        return value;
    }

    T peekFront() const {
        if (isEmpty()) {
            throw std::runtime_error("peek_front from empty deque");
        }
        return head->data;
    }

    T peekBack() const {
        if (isEmpty()) {
            throw std::runtime_error("peek_back from empty deque");
        }
        return tail->data;
    }

    bool isEmpty() const {
        return size_ == 0;
    }

    int size() const {
        return size_;
    }
};
