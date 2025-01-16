def fileToData(f, data):
    tmp = []
    for line in f:
        if line == '':
            data.append(tmp)
            tmp = []
            continue
        tmp.append(line)
    data.append(tmp)

def dataToDict(data, id):
    # print(data)
    for i in data[1:]:
        token = i.split()
        amount = int(token[2])
        start = int(token[1])
        dest = int(token[0])

        # print(start, start + amount + 1)
        if id in range(start, start + amount + 1):
            placement = id - start
            return dest + placement


f = None
with open("input.txt", "r") as file:
    f = [line.strip() for line in file]

###############
data = []
# 0: seeds
# 1: seed to soil
# 2: soil to fertilizer
# 3: fertilizer to water
# 4: water to light
# 5: light to temp
# 6: temp to humidity
# 7: humidity to location
###############
fileToData(f, data)

locations = []
seeds = data[0][0].split(':')[1].split()
for seed in seeds:
    id = int(seed)

    for i in range(1, 8):
        newId = dataToDict(data[i], id)
        if newId:
            id = newId

    locations.append(id)
print(min(locations))



