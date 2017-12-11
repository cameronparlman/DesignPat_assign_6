package genericCheckpointing.util;

import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;

import java.io.FileNotFoundException;



/**
	@see studentCoursesBackup.util
*/
public class Results implements FileDisplayInterface, StdoutDisplayInterface{
	private ArrayList<String> results;	
	PrintWriter printwriter;

	/**
		default constructor initializes empty ArrayList results
	*/
	public Results(){ 
		results = new ArrayList();
	}


	/**
	@param string_in is a result string that will be stored for future printing
	*/
	public void insert(String string_in){
		results.add(string_in);
	}
	

	/**
		write the contents of results(ArrayList) to the file specified by output_file
		@param output_file is the file to be written to
	*/
	public void writeToFile(String output_file){		
		try{
			PrintWriter out = new PrintWriter(output_file);
			for(String str : results){
				out.println(str);
			}
	
			out.close();
		}catch(FileNotFoundException e){
			System.out.println("File ("+output_file+") Not Found");
		}
	}

	//write to stdout
	public void writeToStdout(String s){
		for(String str:results){
		System.out.println(str);
		}
	}

	//open a printwriter to later write into
	public void openFileToWrite(String output_file){
		try{
			printwriter = new PrintWriter(output_file);
		}catch(FileNotFoundException e){
			System.out.println("File (" + output_file+") Not Found");
		}

	}

	//close the printwriter 
	public void closefileWrite(){
		printwriter.close();
	}


	//write all of results stored to file at once. 
	public void write(){
		for(String str: results){
			printwriter.println(str);
		}
	}


	//write directly to file via printwriter 
	public void directWrite(String str){
		printwriter.println(str);
	}

}
