class Queue:
    """Очередь (FIFO) на динамическом массиве с ручным расширением."""

    def __init__(self, capacity=4):
        """
        Создаёт пустую очередь.

        :param capacity: начальная ёмкость массива
        """
        self._data = [None] * capacity
        self._front = 0
        self._size = 0

    def _resize(self, new_capacity):
        """
        Изменяет ёмкость массива.
        """
        new_data = [None] * new_capacity
        for i in range(self._size):
            new_data[i] = self._data[self._front + i]
        self._data = new_data
        self._front = 0

    def enqueue(self, value):
        """
        Добавляет элемент в конец очереди.
        """
        # если нет места в массиве — расширяем
        if self._front + self._size == len(self._data):
            self._resize(len(self._data) * 2)

        index = self._front + self._size
        self._data[index] = value
        self._size += 1

    def dequeue(self):
        """
        Удаляет и возвращает элемент из начала очереди.
        """
        if self.is_empty():
            raise IndexError("dequeue from empty queue")

        value = self._data[self._front]
        self._data[self._front] = None
        self._front += 1
        self._size -= 1

        # если очередь опустела — сбрасываем front
        if self._size == 0:
            self._front = 0

        return value

    def peek(self):
        """
        Возвращает первый элемент без удаления.
        """
        if self.is_empty():
            raise IndexError("peek from empty queue")
        return self._data[self._front]

    def is_empty(self):
        return self._size == 0

    def size(self):
        return self._size

    def __len__(self):
        return self._size

    def __bool__(self):
        return self._size != 0

    def __repr__(self):
        front = None if self.is_empty() else self._data[self._front]
        return f"Queue(size={self._size}, front={front!r})"

    def __iter__(self):
        for i in range(self._size):
            yield self._data[self._front + i]

    def to_list(self):
        return list(self)
