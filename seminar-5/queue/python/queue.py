class Node:
    """Узел односвязного списка, используемый в очереди."""

    def __init__(self, data, next=None):
        """
        Создаёт новый узел.

        :param data: значение, хранящееся в узле
        :param next: ссылка на следующий узел
        """
        self.data = data
        self.next = next


class Queue:
    """Очередь (FIFO) на основе односвязного списка."""

    def __init__(self):
        """
        Создаёт пустую очередь.
        """
        self.head = None  # начало очереди
        self.tail = None  # конец очереди
        self._size = 0

    def enqueue(self, value):
        """
        Добавляет элемент в конец очереди.

        :param value: значение для добавления
        """
        node = Node(value)

        if self.tail is None:
            # очередь была пустой
            self.head = self.tail = node
        else:
            self.tail.next = node
            self.tail = node

        self._size += 1

    def dequeue(self):
        """
        Удаляет и возвращает элемент из начала очереди.

        :return: значение из начала очереди
        :raises IndexError: если очередь пуста
        """
        if self.is_empty():
            raise IndexError("dequeue from empty queue")

        value = self.head.data
        self.head = self.head.next

        if self.head is None:
            self.tail = None

        self._size -= 1
        return value

    def peek(self):
        """
        Возвращает первый элемент без удаления.

        :return: значение из начала очереди
        :raises IndexError: если очередь пуста
        """
        if self.is_empty():
            raise IndexError("peek from empty queue")
        return self.head.data

    def is_empty(self):
        """
        Проверяет, пуста ли очередь.
        """
        return self.head is None

    def size(self):
        """
        Возвращает количество элементов.
        """
        return self._size

    def __len__(self):
        return self._size

    def __bool__(self):
        return self._size != 0

    def __repr__(self):
        front = None if self.head is None else self.head.data
        return f"Queue(size={self._size}, front={front!r})"

    def __iter__(self):
        cur = self.head
        while cur is not None:
            yield cur.data
            cur = cur.next

    def to_list(self):
        return list(self)
