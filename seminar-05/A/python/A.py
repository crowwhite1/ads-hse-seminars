class Node:
    def __init__(self, value, next_node=None):
        self.value = value
        self.next = next_node


class Stack:
    def __init__(self):
        self.current = None

    def push(self, value):
        self.current = Node(value, self.current)

    def pop(self):
        if self.is_empty():
            return None
        value = self.current.value
        self.current = self.current.next
        return value

    def peek(self):
        if self.is_empty():
            return None
        return self.current.value

    def is_empty(self):
        return self.current is None


def priority(s: str) -> int:
    if s in "+-":
        return 0
    else:
        return 1


stack = Stack()
input_arr = input().split()
for elem in input_arr:
    if elem.isdigit():
        print(elem, end=' ')
    elif elem in '+-*/':
        current = stack.peek()
        while current is not None and current in "+-*/" and priority(current) >= priority(elem):
            stack.pop()
            print(current, end=' ')
            current = stack.peek()
        stack.push(elem)
    elif elem == '(':
        stack.push(elem)
    elif elem == ')':
        current = stack.pop()
        while current != '(':
            print(current, end=' ')
            current = stack.pop()
while not stack.is_empty():
    print(stack.pop(), end=' ')
