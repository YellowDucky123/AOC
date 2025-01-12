import sys

file = sys.stdin

count = 0
for line in file:
    most = {"red": 0, "blue": 0, "green": 0}
    tokens = line.split(":")
    sets = tokens[1].split(';')
    for s in sets:
        cubes = s.split(',')
        for c in cubes:
            c = c.strip()
            numWord = c.split(' ')
            if most[numWord[1]] < int(numWord[0]):
                most[numWord[1]] = int(numWord[0])

    num = 1
    for k in most:
        num *= most[k]
    count += num

print(count)