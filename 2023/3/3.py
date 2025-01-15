import re

def isSymbol(c):
    if not c.isdigit() and c != '.':
        return True
    return False

inp = []
with open("input.txt", "r") as file:
    for line in file:
        inp.append(line.strip())

count = 0

for i, line in enumerate(inp):
    for n in re.finditer(r'\d+', line):
        pos = n.span()
        if (pos[0] - 1 >= 0 and isSymbol(line[pos[0] - 1])) or (pos[1] < len(line) and isSymbol(line[pos[1]])):
            count += int(n.group())
            continue
        
        for j in range(pos[0] - 1, pos[1] + 1):
            if j >= len(line):
                continue
            if (i - 1 >= 0 and isSymbol(inp[i - 1][j])) or (i + 1 < len(inp) and isSymbol(inp[i + 1][j])):
                count += int(n.group())
                continue
print(count)




