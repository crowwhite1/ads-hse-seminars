n = int(input())
A_i = 1
B_i = 1
C_elem = 0
for _ in range(n):
    if A_i ** 2 < B_i ** 3:
        C_elem = A_i ** 2
        A_i += 1
    elif A_i ** 2 > B_i ** 3:
        C_elem = B_i ** 3
        B_i += 1
    else:  # if A_i ** 2 == B_i ** 3:
        C_elem = A_i ** 2
        A_i += 1
        B_i += 1
print(C_elem)
