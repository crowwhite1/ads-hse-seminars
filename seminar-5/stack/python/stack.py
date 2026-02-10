class Node:
    """Узел односвязного списка, используемый в стеке."""

    def __init__(self, data, next=None):
        """
        Создаёт новый узел.

        :param data: значение, хранящееся в узле
        :param next: ссылка на следующий узел
        """
        self.data = data
        self.next = next


class Stack:
    """Стек (LIFO) на основе односвязного списка."""

    def __init__(self):
        """
        Создаёт пустой стек.
        """
        self.top = None
        self._size = 0

    def push(self, data):
        """
        Добавляет элемент на вершину стека.

        :param data: значение для добавления
        :return: None
        """
        self.top = Node(data, self.top)
        self._size += 1

    def pop(self):
        """
        Удаляет и возвращает верхний элемент стека.

        :return: значение верхнего элемента
        :raises IndexError: если стек пуст
        """
        if self.is_empty():
            raise IndexError("pop from empty stack")
        data = self.top.data
        self.top = self.top.next
        self._size -= 1
        return data

    def peek(self):
        """
        Возвращает верхний элемент без удаления.

        :return: значение верхнего элемента
        :raises IndexError: если стек пуст
        """
        if self.is_empty():
            raise IndexError("peek from empty stack")
        return self.top.data

    def is_empty(self):
        """
        Проверяет, пуст ли стек.

        :return: True, если стек пуст, иначе False
        """
        return self.top is None

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
        return f"Stack(size={self._size}, top={None if self.top is None else self.top.data!r})"

    def __iter__(self):
        """
        Итератор по элементам стека сверху вниз.

        :yield: значения элементов стека
        """
        cur = self.top
        while cur is not None:
            yield cur.data
            cur = cur.next

    def to_list(self):
        """
        Преобразует стек в список (сверху вниз).

        :return: список элементов стека
        """
        return list(self)
