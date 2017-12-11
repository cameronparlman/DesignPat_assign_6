Assuming you are in the same directory as this README 

#CLEAN
run:
ant -buildfile genericCheckpointing/src/build.xml clean


_____________________________________________________________________________
#COMPILE
run:
ant -buildfile genericCheckpointing/src/build.xml all


_____________________________________________________________________________
#RUN with command line arguments  LOCAL INPUT	
	Note: replace "iofile.txt" with your input file or use the provided file, 
run: ant -buildfile genericCheckpointing/src/build.xml run -Dargs='serdeser 10 iofile.txt'

run: ant -buildfile genericCheckpointing/src/build.xml run -Dargs='deser 10 iofile.txt'


_____________________________________________________________________________
#Data Structures 

ArrayList of Nodes.
	Running Time:insert: O(N)
	Running Time:sort:   O(Nlog(N))

_____________________________________________________________________________
Notes: 
	values less than ten are not delt with... all fields are serialized. 
	input file must for serdeser or file exception is thrown, does not create its own file. 

!!! 
	indentation in the io file is important 
 two spaces before <complexType> & </complexType> 
 four spaces before fields 
 zero before <DPSerialization> and </DPSerialization>

 	i tried to fix it to run with the same indentation as the provided file but 
	had some problems doing so. 

a provided makefile supports the following commands and runs them through ant
	all: 	(compile)
	runser: serdeser(mode)
	rundes: des (mode)
	clean:  



_____________________________________________________________________________




"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.â€

[Date: 11/22/2017] -- Please add the date here
Cameron Parlman.


