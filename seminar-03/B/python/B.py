def merge_sort(a, l, r):
    if r - l < 2:
        return
    c = (l + r) // 2
    merge_sort(a, l, c)
    merge_sort(a, c, r)
    merge(a, l, c, r)


# Процедура объединения двух отсортированных массивов
def merge(a, l, c, r):
    buf = []
    i = l  # Индекс следующего элемента из первого подмассива
    j = c  # Индекс следующего элемента из второго подмассива
    while i < c or j < r:
        if i == c:  # Элементы первого подмассива закончились
            buf.append(a[j])
            j += 1
        elif j == r:  # Элементы второго подмассива закончились
            buf.append(a[i])
            i += 1
        # Выбираем меньший элемент из доступных
        elif a[i] <= a[j]:
            buf.append(a[i])
            i += 1
        else:  # if a[i] > a[j]
            buf.append(a[j])
            j += 1
    # Нужно перекопировать все элементы из буфера в оригинальный массив
    for i in range(len(buf)):
        a[l + i] = buf[i]


n = int(input())
a = list(map(int, input().split()))
merge_sort(a, 0, n)
print(*a)
