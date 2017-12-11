package genericCheckpointing.util;

import java.lang.Object;
import java.util.Objects;
public class MyAllTypesSecond extends SerializableObject{
	private float 	myFloatT;
	private double 	myDoubleT;
	private short	myShortT;
	private char 	myCharT;
	private double 	myOtherDoubleT;	

	public MyAllTypesSecond(float myFloatT, double myDoubleT, short myShortT, char myCharT, double myOtherDoubleT){
		this.myFloatT 	= myFloatT;
		this.myDoubleT 	= myDoubleT;
		this.myShortT 	= myShortT;
		this.myCharT 	= myCharT;
		this.myOtherDoubleT = myOtherDoubleT;
	}
	public MyAllTypesSecond(){
		this.myFloatT = 0.0f;
		this.myDoubleT = 0;
		this.myShortT = 0;
		this.myCharT = '0';
		this.myOtherDoubleT =0;
	}

	//getters
	public float 	get_myFloatT(){return myFloatT;}
	public double 	get_myDoubleT(){return myDoubleT;}
	public short 	get_myShortT(){return myShortT;}
	public char 	get_myCharT(){return myCharT;}
	public double 	get_myOtherDoubleT(){return myOtherDoubleT;}

	public void set_myFloatT(float i){myFloatT = i;}
	public void set_myDoubleT(double i){myDoubleT = i;}
	public void set_myShortT(short i){myShortT = i;}
	public void set_myCharT(char i){myCharT = i;}
	public void set_myOtherDoubleT(double i ){myOtherDoubleT = i;}


	@Override 

	public String toString(){
		return "MyAllTypesSecond \n\tmyFloatT:"+myFloatT+"\n\tmyDoubleT:"+myDoubleT
			+"\n\tmyShortT:\""+myShortT+"\"\n\t"
			+"myCharT:"+myCharT+"\n\tmyOtherDoubleT:"+myOtherDoubleT;
	}
	
	@Override 
	public boolean equals(Object other){
		if(other instanceof MyAllTypesSecond){
	/*
			if(this.myFloatT ==  ((MyAllTypesSecond)other).get_myFloatT() &&
				this.myDoubleT == ((MyAllTypesSecond)other).get_myDoubleT() &&
				this.myShortT == ((MyAllTypesSecond)other).get_myShortT() &&
				this.myCharT == ((MyAllTypesSecond)other).get_myCharT() &&
				this.myOtherDoubleT == ((MyAllTypesSecond)other).get_myOtherDoubleT())
			{
				return true;
		}
			
		*/
		if(this.hashCode() == ((MyAllTypesSecond)other).hashCode()){return true;}
		}else{ return false;}
		return false;

	}


	@Override
	public int hashCode(){
		return Objects.hash(myFloatT, myDoubleT, myShortT, myCharT, myOtherDoubleT);
		//return 179 * myFloatT + 199 * myDoubleT + 163*myShortT + ((int)myCharT * 157) + (myOtherDoubleT *59);
	}



}
