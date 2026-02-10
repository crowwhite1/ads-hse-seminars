class Node:
    """Узел односвязного списка."""

    def __init__(self, value, next=None):
        self.value = value
        self.next = next


class SinglyLinkedList:
    """Односвязный список."""

    def __init__(self):
        self.head = None
        self.tail = None
        self._size = 0

    def is_empty(self):
        return self.head is None

    def push_front(self, value):
        node = Node(value, self.head)
        self.head = node
        if self.tail is None:
            self.tail = node
        self._size += 1

    def push_back(self, value):
        node = Node(value)
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
        self._size -= 1
        if self.head is None:
            self.tail = None
        return value

    def find(self, value):
        """Возвращает True, если значение найдено."""
        cur = self.head
        while cur is not None:
            if cur.value == value:
                return True
            cur = cur.next
        return False

    def remove_first(self, value):
        """
        Удаляет первое вхождение value.
        :return: True, если удалили, иначе False
        """
        prev = None
        cur = self.head
        while cur is not None:
            if cur.value == value:
                if prev is None:
                    # удаляем голову
                    self.head = cur.next
                else:
                    prev.next = cur.next

                if cur == self.tail:
                    self.tail = prev

                self._size -= 1
                return True

            prev = cur
            cur = cur.next
        return False

    def to_list(self):
        res = []
        cur = self.head
        while cur is not None:
            res.append(cur.value)
            cur = cur.next
        return res

    def __len__(self):
        return self._size

    def __iter__(self):
        cur = self.head
        while cur is not None:
            yield cur.value
            cur = cur.next

    def __repr__(self):
        return f"SinglyLinkedList(size={self._size}, values={self.to_list()})"
