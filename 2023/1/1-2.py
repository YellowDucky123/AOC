import sys

file = sys.stdin

count = 0

values = {"one": '1', "two": '2', "three": '3', "four": '4', "five": '5', "six": '6', "seven": '7', "eight": '8', "nine": '9'}

for line in file:
    l = []
    for i, c in enumerate(line):
        if c.isdigit():
            l.append(c)
            continue

        string = line[i:]
        for k in values:
            if string.startswith(k):
                l.append(values.get(k))

    if len(l) < 1:
        break

    num = l[0] + l[-1]  
    count += int(num)

    

print(count)
