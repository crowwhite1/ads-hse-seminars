class Node:
    """Узел двусвязного списка для Deque."""

    def __init__(self, data, prev=None, next=None):
        """
        Создаёт новый узел.

        :param data: значение, хранящееся в узле
        :param prev: ссылка на предыдущий узел
        :param next: ссылка на следующий узел
        """
        self.data = data
        self.prev = prev
        self.next = next


class Deque:
    """Двусторонняя очередь (Deque) на основе двусвязного списка."""

    def __init__(self):
        """
        Создаёт пустой deque.
        """
        self.head = None  # левый конец
        self.tail = None  # правый конец
        self._size = 0

    def push_front(self, value):
        """
        Добавляет элемент в начало deque.

        :param value: значение для добавления
        """
        node = Node(value, prev=None, next=self.head)

        if self.head is None:
            self.head = self.tail = node
        else:
            self.head.prev = node
            self.head = node

        self._size += 1

    def push_back(self, value):
        """
        Добавляет элемент в конец deque.

        :param value: значение для добавления
        """
        node = Node(value, prev=self.tail, next=None)

        if self.tail is None:
            self.head = self.tail = node
        else:
            self.tail.next = node
            self.tail = node

        self._size += 1

    def pop_front(self):
        """
        Удаляет и возвращает элемент из начала deque.

        :return: значение из начала deque
        :raises IndexError: если deque пуст
        """
        if self.is_empty():
            raise IndexError("pop_front from empty deque")

        value = self.head.data
        self.head = self.head.next

        if self.head is None:
            self.tail = None
        else:
            self.head.prev = None

        self._size -= 1
        return value

    def pop_back(self):
        """
        Удаляет и возвращает элемент из конца deque.

        :return: значение из конца deque
        :raises IndexError: если deque пуст
        """
        if self.is_empty():
            raise IndexError("pop_back from empty deque")

        value = self.tail.data
        self.tail = self.tail.prev

        if self.tail is None:
            self.head = None
        else:
            self.tail.next = None

        self._size -= 1
        return value

    def peek_front(self):
        """
        Возвращает элемент из начала deque без удаления.

        :return: значение из начала deque
        :raises IndexError: если deque пуст
        """
        if self.is_empty():
            raise IndexError("peek_front from empty deque")
        return self.head.data

    def peek_back(self):
        """
        Возвращает элемент из конца deque без удаления.

        :return: значение из конца deque
        :raises IndexError: если deque пуст
        """
        if self.is_empty():
            raise IndexError("peek_back from empty deque")
        return self.tail.data

    def is_empty(self):
        """
        Проверяет, пуст ли deque.
        """
        return self._size == 0

    def size(self):
        """
        Возвращает количество элементов в deque.
        """
        return self._size

    def __len__(self):
        return self._size

    def __bool__(self):
        return self._size != 0

    def __repr__(self):
        left = None if self.is_empty() else self.head.data
        right = None if self.is_empty() else self.tail.data
        return f"Deque(size={self._size}, front={left!r}, back={right!r})"

    def __iter__(self):
        """
        Итерация слева направо (от head к tail).
        """
        cur = self.head
        while cur is not None:
            yield cur.data
            cur = cur.next

    def to_list(self):
        """
        Преобразует deque в список слева направо.
        """
        return list(self)
