import sys

file = sys.stdin

count = 0

for line in file:
    l = []
    for c in line:
        if c >= '1' and c <= '9':
            l.append(c)

    num = l[0] + l[-1]
    count += int(num)

print(count)
