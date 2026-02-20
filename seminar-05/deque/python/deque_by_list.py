class Deque:
    """Двусторонняя очередь (Deque) на массиве с ручным расширением (без кольца)."""

    def __init__(self, capacity=4):
        """
        Создаёт пустой deque.

        :param capacity: начальная ёмкость массива
        """
        self._data = [None] * capacity
        self._front = 0  # индекс первого элемента
        self._size = 0  # количество элементов

    def _resize(self, new_capacity):
        """
        Расширяет массив и переносит элементы так, чтобы front стал 0.
        """
        new_data = [None] * new_capacity
        for i in range(self._size):
            new_data[i] = self._data[self._front + i]
        self._data = new_data
        self._front = 0

    def push_back(self, value):
        """
        Добавляет элемент в конец deque.
        """
        if self._front + self._size == len(self._data):
            self._resize(len(self._data) * 2)

        self._data[self._front + self._size] = value
        self._size += 1

    def pop_front(self):
        """
        Удаляет и возвращает элемент из начала deque.
        """
        if self.is_empty():
            raise IndexError("pop_front from empty deque")

        value = self._data[self._front]
        self._data[self._front] = None
        self._front += 1
        self._size -= 1

        if self._size == 0:
            self._front = 0

        return value

    def peek_front(self):
        """
        Возвращает элемент из начала deque без удаления.
        """
        if self.is_empty():
            raise IndexError("peek_front from empty deque")
        return self._data[self._front]

    def peek_back(self):
        """
        Возвращает элемент из конца deque без удаления.
        """
        if self.is_empty():
            raise IndexError("peek_back from empty deque")
        return self._data[self._front + self._size - 1]

    def pop_back(self):
        """
        Удаляет и возвращает элемент из конца deque.
        """
        if self.is_empty():
            raise IndexError("pop_back from empty deque")

        back_index = self._front + self._size - 1
        value = self._data[back_index]
        self._data[back_index] = None
        self._size -= 1

        if self._size == 0:
            self._front = 0

        return value

    def push_front(self, value):
        """
        Добавляет элемент в начало deque.

        В реализации "без кольца" это неудобная операция:
        - если слева есть место (front > 0), просто сдвигаем front влево
        - иначе делаем resize и сдвигаем элементы вправо на 1
        """
        if self._size == 0:
            # простой частный случай
            self._data[0] = value
            self._front = 0
            self._size = 1
            return

        if self._front > 0:
            self._front -= 1
            self._data[self._front] = value
            self._size += 1
            return

        # front == 0: слева места нет
        # если массив полон, сначала увеличим
        if self._size == len(self._data):
            self._resize(len(self._data) * 2)

        # сдвигаем все элементы вправо на 1 (O(n))
        for i in range(self._front + self._size - 1, self._front - 1, -1):
            self._data[i + 1] = self._data[i]
        self._data[self._front] = value
        self._size += 1

    def is_empty(self):
        return self._size == 0

    def size(self):
        return self._size

    def __len__(self):
        return self._size

    def __bool__(self):
        return self._size != 0

    def __repr__(self):
        if self.is_empty():
            return "Deque(size=0, front=None, back=None)"
        return (f"Deque(size={self._size}, "
                f"front={self._data[self._front]!r}, "
                f"back={self._data[self._front + self._size - 1]!r})")

    def __iter__(self):
        for i in range(self._size):
            yield self._data[self._front + i]

    def to_list(self):
        return list(self)
