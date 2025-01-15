import re

text = "helloklo123"

i = re.search(r'\d+', text)
print(i.span())