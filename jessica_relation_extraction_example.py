
from jessica_relation_extraction import relation_extraction

for r in relation_extraction("Smith's wife is Jessica. Jessica is working for Apple. Jessica is 23 years old."):
	print(r)
