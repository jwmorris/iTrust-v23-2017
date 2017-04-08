import csv

with open('icdCodes.csv', 'r') as file:
	with open('icdCodes.sql', 'w') as writer:
		parser = csv.reader(file)
		header = next(parser)
		for row in parser:
			temp = '('
			temp = temp + "'" + row[0] + "', "
			temp = temp + "'" + row[1].replace("'", "") + "', "
			temp = temp + row[2]
			temp = temp + '),'
			writer.write(temp + '\n')