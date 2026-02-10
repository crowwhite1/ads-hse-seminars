#include <iostream>
#include <sstream>
#include <string>

struct Node {
    std::string value;
    Node* next;
    Node(const std::string& value, Node* next) : value(value), next(next) {}
};

class Stack {
private:
    Node* top;

public:
    Stack() : top(nullptr) {}

    ~Stack() {
        while (!isEmpty()) pop();
    }

    void push(const std::string& value) {
        top = new Node(value, top);
    }

    std::string pop() {
        if (isEmpty()) return "";
        Node* node = top;
        std::string value = node->value;
        top = top->next;
        delete node;
        return value;
    }

    std::string peek() const {
        return isEmpty() ? "" : top->value;
    }

    bool isEmpty() const {
        return top == nullptr;
    }
};

static bool isNumber(const std::string& s) {
    if (s.empty()) return false;
    for (char c : s) {
        if (c < '0' || c > '9') return false;
    }
    return true;
}

static bool isOperator(const std::string& s) {
    return s.size() == 1 && (s[0] == '+' || s[0] == '-' || s[0] == '*' || s[0] == '/');
}

static int priority(const std::string& op) {
    return (op == "+" || op == "-") ? 0 : 1;
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    std::string line;
    std::getline(std::cin, line);
    if (line.empty()) return 0;

    Stack st;
    std::ostringstream out;
    bool first = true;

    auto emit = [&](const std::string& tok) {
        if (!first) out << ' ';
        out << tok;
        first = false;
    };

    std::istringstream iss(line);
    std::string tok;
    while (iss >> tok) {
        if (isNumber(tok)) {
            emit(tok);
        } else if (isOperator(tok)) {
            std::string cur = st.peek();
            while (!cur.empty() && isOperator(cur) && priority(cur) >= priority(tok)) {
                emit(st.pop());
                cur = st.peek();
            }
            st.push(tok);
        } else if (tok == "(") {
            st.push(tok);
        } else if (tok == ")") {
            std::string cur = st.pop();
            while (!cur.empty() && cur != "(") {
                emit(cur);
                cur = st.pop();
            }
        }
    }

    while (!st.isEmpty()) {
        std::string cur = st.pop();
        if (cur != "(" && !cur.empty()) emit(cur);
    }

    std::cout << out.str() << "\n";
    return 0;
}
