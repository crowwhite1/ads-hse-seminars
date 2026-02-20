class Stack:
    """Стек (LIFO) на основе динамического массива с ручным расширением."""

    def __init__(self, capacity=4):
        """
        Создаёт пустой стек.

        :param capacity: начальная ёмкость массива
        """
        self._data = [None] * capacity  # внутренний массив
        self._size = 0  # текущее количество элементов

    def _resize(self, new_capacity):
        """
        Изменяет ёмкость внутреннего массива.

        :param new_capacity: новая ёмкость
        """
        new_data = [None] * new_capacity
        for i in range(self._size):
            new_data[i] = self._data[i]
        self._data = new_data

    def push(self, value):
        """
        Добавляет элемент на вершину стека.

        :param value: значение для добавления
        :return: None
        """
        # если массив заполнен — увеличиваем его
        if self._size == len(self._data):
            self._resize(len(self._data) * 2)

        self._data[self._size] = value
        self._size += 1

    def pop(self):
        """
        Удаляет и возвращает верхний элемент стека.

        :return: значение верхнего элемента
        :raises IndexError: если стек пуст
        """
        if self.is_empty():
            raise IndexError("pop from empty stack")

        self._size -= 1
        value = self._data[self._size]
        self._data[self._size] = None  # очищаем ссылку
        return value

    def peek(self):
        """
        Возвращает верхний элемент без удаления.

        :return: значение верхнего элемента
        :raises IndexError: если стек пуст
        """
        if self.is_empty():
            raise IndexError("peek from empty stack")
        return self._data[self._size - 1]

    def is_empty(self):
        """
        Проверяет, пуст ли стек.

        :return: True, если стек пуст, иначе False
        """
        return self._size == 0

    def size(self):
        """
        Возвращает количество элементов в стеке.

        :return: размер стека
        """
        return self._size

    def __len__(self):
        """
        Возвращает размер стека для функции len().

        :return: размер стека
        """
        return self._size

    def __bool__(self):
        """
        Возвращает True, если стек не пуст.

        :return: bool
        """
        return self._size != 0

    def __repr__(self):
        """
        Строковое представление стека для отладки.

        :return: строка с информацией о стеке
        """
        top = None if self.is_empty() else self._data[self._size - 1]
        return f"Stack(size={self._size}, top={top!r})"

    def __iter__(self):
        """
        Итератор по элементам стека сверху вниз.

        :yield: значения элементов стека
        """
        for i in range(self._size - 1, -1, -1):
            yield self._data[i]

    def to_list(self):
        """
        Преобразует стек в список (сверху вниз).

        :return: список элементов стека
        """
        return list(self)
