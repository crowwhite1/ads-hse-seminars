class Node:
    """Узел двусвязного списка."""

    def __init__(self, value, prev=None, next=None):
        self.value = value
        self.prev = prev
        self.next = next


class DoublyLinkedList:
    """Двусвязный список."""

    def __init__(self):
        self.head = None
        self.tail = None
        self._size = 0

    def is_empty(self):
        return self._size == 0

    def push_front(self, value):
        node = Node(value, None, self.head)

        if self.head is None:
            self.head = self.tail = node
        else:
            self.head.prev = node
            self.head = node

        self._size += 1

    def push_back(self, value):
        node = Node(value, self.tail, None)

        if self.tail is None:
            self.head = self.tail = node
        else:
            self.tail.next = node
            self.tail = node

        self._size += 1

    def pop_front(self):
        if self.is_empty():
            raise IndexError("pop_front from empty list")

        value = self.head.value
        self.head = self.head.next

        if self.head is None:
            self.tail = None
        else:
            self.head.prev = None

        self._size -= 1
        return value

    def pop_back(self):
        if self.is_empty():
            raise IndexError("pop_back from empty list")

        value = self.tail.value
        self.tail = self.tail.prev

        if self.tail is None:
            self.head = None
        else:
            self.tail.next = None

        self._size -= 1
        return value

    def find(self, value):
        cur = self.head
        while cur:
            if cur.value == value:
                return True
            cur = cur.next
        return False

    def remove_first(self, value):
        cur = self.head
        while cur:
            if cur.value == value:
                if cur.prev:
                    cur.prev.next = cur.next
                else:
                    self.head = cur.next

                if cur.next:
                    cur.next.prev = cur.prev
                else:
                    self.tail = cur.prev

                self._size -= 1
                return True
            cur = cur.next
        return False

    def to_list(self):
        res = []
        cur = self.head
        while cur:
            res.append(cur.value)
            cur = cur.next
        return res

    def __len__(self):
        return self._size

    def __iter__(self):
        cur = self.head
        while cur:
            yield cur.value
            cur = cur.next

    def __repr__(self):
        return f"DoublyLinkedList(size={self._size}, values={self.to_list()})"
