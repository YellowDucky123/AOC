with open('image.txt') as file:
    lines = [l.strip() for l in file]

for l in lines:
    s = []
    for c in l:
        if c == '#':
            s.append('██')
        elif c == '.':
            s.append('░░')
    print("".join(s))
