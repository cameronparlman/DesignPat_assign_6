package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
	private int 	myInt;
	private long 	myLong;
	private String 	myString;	
	private boolean	myBool;	
	private int 	myOtherInt;

	
	public MyAllTypesFirst(int myInt, long myLong, String myString, Boolean myBool, int myOtherInt){
		this.myInt = myInt;
		this.myLong = myLong;
		this.myString = myString;
		this.myBool = myBool;
		this.myOtherInt = myOtherInt;
	}	
	public MyAllTypesFirst(){
		myInt = 0;	
		myLong = 0;
	}


	//getters
	public int get_myInt(){return myInt;}
	public long get_myLong(){return myLong;}
	public String get_myString(){return myString;}
	public Boolean get_myBool(){return myBool;}
	public int get_myOtherInt(){return myOtherInt;}


	//setters
	public void set_myInt(int i){myInt = i;}
	public void set_myLong(long i){myLong = i;}
	public void set_myString(String i){myString = i;}
	public void set_myBool(Boolean i){myBool = i;}
	public void set_myOtherInt(int i){myOtherInt = i;}
	



	@Override 
	public String toString(){
		return "MyAllTypesFirst \n\tmyInt:"+myInt+"\n\tmyLong:"+myLong+"\n\tmyString:\""+myString+"\"\n\t"
			+"myBool:"+myBool+"\n\tmyOtherInt:"+myOtherInt;
	}


}
