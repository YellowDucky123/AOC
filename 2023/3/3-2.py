import re
from collections import defaultdict 

def isSymbol(c):
    return c == '*'

map = None
with open("input.txt", "r") as file:
    map = [line.strip() for line in file]

sym = defaultdict(set)
for i, line in enumerate(map):
    for n in re.finditer(r'\d+', line):
        pos = n.span()
        
        # symbol behind number
        if pos[0] - 1 >= 0 and isSymbol(line[pos[0] - 1]):
            sym[(i, pos[0] - 1)].add(int(n.group()))
        
        # symbol infront number
        if pos[1] < len(line) and isSymbol(line[pos[1]]):
            sym[(i, pos[1])].add(int(n.group()))
        
        for j in range(pos[0] - 1, pos[1] + 1):
            if j < 0 or j >= len(line):
                continue
            
            # line above
            if i > 0 and isSymbol(map[i - 1][j]):
                sym[(i - 1, j)].add(n.group())

            #line below
            if i < len(map) - 1 and isSymbol(map[i + 1][j]):
                sym[(i + 1, j)].add(n.group())

count = 0
for s in sym:
    if len(sym[s]) == 2:
        mult = 1
        for num in sym[s]:
            mult *= int(num)
        count += mult

print(count)


