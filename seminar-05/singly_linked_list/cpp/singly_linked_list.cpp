#include <stdexcept>
#include <vector>

template<typename T>
class SinglyLinkedList {
private:
    struct Node {
        T value;
        Node* next;
        Node(const T& value, Node* next = nullptr) : value(value), next(next) {}
    };

    Node* head;
    Node* tail;
    int size_;

public:
    SinglyLinkedList() : head(nullptr), tail(nullptr), size_(0) {}

    ~SinglyLinkedList() {
        clear();
    }

    void clear() {
        Node* cur = head;
        while (cur != nullptr) {
            Node* nxt = cur->next;
            delete cur;
            cur = nxt;
        }
        head = tail = nullptr;
        size_ = 0;
    }

    bool isEmpty() const {
        return head == nullptr;
    }

    void pushFront(const T& value) {
        Node* node = new Node(value, head);
        head = node;
        if (tail == nullptr) {
            tail = node;
        }
        size_++;
    }

    void pushBack(const T& value) {
        Node* node = new Node(value);
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
            throw std::runtime_error("pop_front from empty list");
        }
        Node* node = head;
        T value = node->value;
        head = head->next;
        delete node;
        size_--;
        if (head == nullptr) {
            tail = nullptr;
        }
        return value;
    }

    bool find(const T& value) const {
        Node* cur = head;
        while (cur != nullptr) {
            if (cur->value == value) {
                return true;
            }
            cur = cur->next;
        }
        return false;
    }

    /**
     * Удаляет первое вхождение value.
     * @return true если удалили, иначе false
     */
    bool removeFirst(const T& value) {
        Node* prev = nullptr;
        Node* cur = head;

        while (cur != nullptr) {
            if (cur->value == value) {
                if (prev == nullptr) {
                    head = cur->next;
                } else {
                    prev->next = cur->next;
                }

                if (cur == tail) {
                    tail = prev;
                }

                delete cur;
                size_--;

                if (head == nullptr) {
                    tail = nullptr;
                }
                return true;
            }

            prev = cur;
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
        while (cur != nullptr) {
            res.push_back(cur->value);
            cur = cur->next;
        }
        return res;
    }
};
