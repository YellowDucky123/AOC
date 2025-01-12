import sys

file = sys.stdin

req = {"red": 12, "green": 13, "blue": 14}

count = 0
for line in file:
    flag = True
    tokens = line.split(":")
    sets = tokens[1].split(';')
    for s in sets:
        cubes = s.split(',')
        for c in cubes:
            c = c.strip()
            # print(c)
            numWord = c.split(' ')
            # print(numWord)
            # print("1:" + numWord[1], "0:" + numWord[0])
            if req.get(numWord[1]) < int(numWord[0]):
                flag = False
    if flag:
        id = tokens[0].strip().split(' ')
        count += int(id[1])

print(count)