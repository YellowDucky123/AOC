def solution(Wnum, Onum):
    point = 0
    idx = 0
    for num in Onum:
        if num in Wnum:
            point = pow(2, idx)
            idx += 1
    return point

cards = None
with open("input.txt", "r") as file:
    cards = [line.strip() for line in file]

count = 0
for card in cards:
    split = card.split('|')
    Onum = {int(num) for num in split[1].split()}
    Wnum = {int(num) for num in split[0].split(':')[1].split()}
    count += solution(Wnum, Onum)

print(count)

