package genericCheckpointing.xmlStoreRestore;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.*;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.Results;
import genericCheckpointing.xmlStoreRestore.SerializeTypes;



public class StoreRestoreHandler implements InvocationHandler{
	String filename="";
	FileProcessor fileprocessor;
	Results results;
	//private final Object target;

	//public StoreRestoreHandler(Object target){
	//	this.target = target;
//	}

	public StoreRestoreHandler(){
	//target = null;	
		results = new Results();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		Object retObject = null;	
	
		String[] data;

		String meth = method.getName();

		if(meth.equals("writeObj")){
				System.out.println("write an object\n");
				writeToFile(args[0]);		
		}
		if(meth.equals("readObj")){
			if(args[0].equals("XML")){
				//readBehavior = xmlreadBehavior
				retObject = readNextObject();				
			}
			else if(args[0].equals("JSON")){
				//readBehavior = jsonreadBehavior 
			}

		}




	//	return method.invoke(target, args);
		return retObject;
	}

	//write an object to the file 
	public void writeToFile(Object obj){
		SerializeTypes typer = new SerializeTypes();
		System.out.println("WriteToFile\n");
		results.directWrite("<DPSerialization>");
		String classstring = typer.serializeClass(obj);
		results.directWrite(classstring);
		//System.out.println(classstring + "\n");
		Field[] fields = obj.getClass().getDeclaredFields();	
		for(int i = 0 ; i < fields.length; i++){
			try{
				fields[i].setAccessible(true);
				String fieldstring = typer.field(obj, fields[i]);
				results.directWrite(fieldstring);
			} catch(IllegalArgumentException e){
				System.out.println("Illegal Argument passed\n");
				System.exit(1);
			}catch (IllegalAccessException e){
				System.out.println("Illegal Access\n");
				System.exit(1);
			}
		}
		results.directWrite("  </complexType>");
		results.directWrite("</DpSerialization>");
	}



	//set the file name 
	public void setfile(String filename){
		this.filename = filename;
	//	System.out.println("Set filename to "+ filename+"\n");
	} 


	//open a file for read with fileprocessor 
	public void openfileRead(){fileprocessor= new FileProcessor(filename);}

/*
	<DPSerialization>
	  <complexType xsi:type="genericCheckpointing.util.MyAllTypesFirst">
   	    <myInt xsi:type="xsd:int">314</myInt>
 	    <myLong xsi:type="xsd:long">314159</myLong>
   	    <myString xsi:type="xsd:string">Design Patterns</myString>
	    <myBool xsi:type="xsd:boolean">false</myBool>
	    <myOtherInt xsi:type="xsd:int">314</myOtherInt>
	    </complexType>
	</DPSerialization>
*/


	public Object readNextObject(){
		Object retObj = null; 	
		String line = null;	

		while( (line = fileprocessor.readLine()) != null){
			if(line.equals("</DPSerialization>")){
				break;
			}
			else if(line.equals("  </complexType>")){
			}
			else if(line.startsWith("    ", 0)){
				System.out.println("Field\n");
			}
			else if(line.startsWith("  ", 0)){
				System.out.println("Complex Type\n");
			}
			

		


		}


		return retObj;
	}


	//open a file for write
	public void openfileWrite(){
		System.out.println("openfile to write " + filename + "\n");
		results.openFileToWrite(filename);
	}

	//close the file 
	public void closefileWrite(){
		results.closefileWrite();
	}
		

}
