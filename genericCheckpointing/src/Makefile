all: compile 

compile:
	ant -buildfile build.xml all 

runser:
	ant -buildfile build.xml run -Dargs='serdeser 10 iofile.txt'

rundes:
	ant -buildfile build.xml run -Dargs='deser 10 iofile.txt'



clean: 
	ant -buildfile build.xml clean

