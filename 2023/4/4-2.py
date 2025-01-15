d = []

def solution(cards, cLen, cache, id):   # cLen is the amount of cards that exist
    if id >= cLen:
        return 0
    
    cached = cache.get(id)
    if cached:
        return cached

    card = cards[id]
    split = card.split('|')
    Onum = {int(num) for num in split[1].split()}
    Wnum = {int(num) for num in split[0].split(':')[1].split()}

    matchCount = 0
    extraCount = 0
    for num in Onum:
        if num in Wnum:
            matchCount += 1
            extraCount += solution(cards, cLen, cache, id + matchCount)

    res = matchCount + extraCount
    cache[id] = res
    return res

cards = None
with open("input.txt", "r") as file:
    cards = [line.strip() for line in file]

cache = {}

count = 0
cLen  = len(cards)
for id in range(cLen):
    count += solution(cards, cLen, cache, id) + 1

print(count)

