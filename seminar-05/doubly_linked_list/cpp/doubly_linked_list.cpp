#include <stdexcept>
#include <vector>

template<typename T>
class DoublyLinkedList {
private:
    struct Node {
        T value;
        Node* prev;
        Node* next;

        Node(const T& value, Node* prev = nullptr, Node* next = nullptr)
            : value(value), prev(prev), next(next) {}
    };

    Node* head;
    Node* tail;
    int size_;

public:
    DoublyLinkedList() : head(nullptr), tail(nullptr), size_(0) {}

    ~DoublyLinkedList() {
        clear();
    }

    void clear() {
        Node* cur = head;
        while (cur) {
            Node* next = cur->next;
            delete cur;
            cur = next;
        }
        head = tail = nullptr;
        size_ = 0;
    }

    bool isEmpty() const {
        return size_ == 0;
    }

    void pushFront(const T& value) {
        Node* node = new Node(value, nullptr, head);

        if (!head) {
            head = tail = node;
        } else {
            head->prev = node;
            head = node;
        }
        size_++;
    }

    void pushBack(const T& value) {
        Node* node = new Node(value, tail, nullptr);

        if (!tail) {
            head = tail = node;
        } else {
            tail->next = node;
            tail = node;
        }
        size_++;
    }

    T popFront() {
        if (isEmpty()) {
            throw std::runtime_error("pop_front from empty list");
        }

        Node* node = head;
        T value = node->value;

        head = head->next;
        if (!head) {
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
            throw std::runtime_error("pop_back from empty list");
        }

        Node* node = tail;
        T value = node->value;

        tail = tail->prev;
        if (!tail) {
            head = nullptr;
        } else {
            tail->next = nullptr;
        }

        delete node;
        size_--;
        return value;
    }

    bool find(const T& value) const {
        Node* cur = head;
        while (cur) {
            if (cur->value == value) {
                return true;
            }
            cur = cur->next;
        }
        return false;
    }

    bool removeFirst(const T& value) {
        Node* cur = head;

        while (cur) {
            if (cur->value == value) {
                if (cur->prev) {
                    cur->prev->next = cur->next;
                } else {
                    head = cur->next;
                }

                if (cur->next) {
                    cur->next->prev = cur->prev;
                } else {
                    tail = cur->prev;
                }

                delete cur;
                size_--;
                return true;
            }
            cur = cur->next;
        }
        return false;
    }

    int size() const {
        return size_;
    }

    std::vector<T> toVector() const {
        std::vector<T> res;
        res.reserve(size_);
        Node* cur = head;
        while (cur) {
            res.push_back(cur->value);
            cur = cur->next;
        }
        return res;
    }
};
