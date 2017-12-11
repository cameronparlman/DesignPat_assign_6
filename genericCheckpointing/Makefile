all: compile 

compile:
	ant -buildfile src/build.xml all 

runser:
	ant -buildfile src/build.xml run -Dargs='serdeser 10 iofile.txt'

rundes:
	ant -buildfile src/build.xml run -Dargs='deser 10 iofile.txt'



clean: 
	ant -buildfile src/build.xml clean

