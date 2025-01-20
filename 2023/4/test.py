mylist = [1, 2, 3, 4]

def sol(mylist, l):
    print(l)
    sol(mylist, next(mylist))

sol(mylist, 1)