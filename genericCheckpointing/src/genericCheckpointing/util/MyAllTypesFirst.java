package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
	private int 	myInt;
	//private long 	myLong;
	//private String 	myString;	
	//private Bool	myBool;	
	//private int 	myOtherInt;

	
	public MyAllTypesFirst(int myint){myInt = myint;}	

	//getters
	public int get_myInt(){return myInt;}

	//setters
	public void set_myInt(int i){myInt = i;}
	
	@Override 
	public String toString(){
		return "MyAllTypesFirst " + myInt;
	}


}
