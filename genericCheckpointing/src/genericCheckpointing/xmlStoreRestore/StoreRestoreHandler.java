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
				//System.out.println("write an object\n");
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
		//System.out.println("WriteToFile\n");
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
		results.directWrite("</DPSerialization>");
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
		Class<?> genclass = null;; 


		try{
		while( (line = fileprocessor.readLine()) != null){
			if(line.equals("</DPSerialization>")){
			//	System.out.println("BREAK:");
				break;
			}
			else if(line.equals("  </complexType>")){
			}
			else if(line.startsWith("    ", 0)){
		//		System.out.println("Field");
			 	String field[] = parseField(line);
				String fieldname = field[0];
				String fieldtype = field[1];
				String fieldval = field[2];
				//System.out.println("fieldname:" + fieldname + "\tfieldtype:" + fieldtype + "  val:"+ fieldval);
		
				setObjectFields(retObj, "set_"+ fieldname, fieldtype, fieldval);	
			}
			else if(line.startsWith("  ", 0)){
		//		System.out.println("Complex Type");
				String [] data = line.split("\"");
			//	for(String str : data){System.out.println("(" + str+ ")");}
			//	System.out.println("OBJECT TYPE "+ data[1]);
				genclass = Class.forName(data[1]);	
				retObj = genclass.newInstance();
			}

		}

		}catch(ClassNotFoundException e){
			System.out.println("class not found");
			System.exit(1);
		}
		catch(InstantiationException e){
			System.out.println("Instantiationexception");
			System.exit(1);
		}
		catch(IllegalAccessException e){
			System.out.println("IllegalAccessException");
			System.exit(1);
		}
			
	//	System.out.println("RETURN AN OBJECT");
		return retObj;
	}


	//open a file for write
	public void openfileWrite(){
		//System.out.println("openfile to write " + filename + "\n");
		results.openFileToWrite(filename);
	}

	//close the file 
	public void closefileWrite(){
		results.closefileWrite();
	}
	public void closefileRead(){
		fileprocessor.close();
	}
	
   	 //<myInt xsi:type="xsd:int">314</myInt>
	public String[] parseField(String line){
		String data[] = line.split(":");
		//get field name 
		data = data[0].split("<");
		data = data[1].split(" ");
		String fieldname = data[0];
		//get field type 
		data = line.split(":");
		data = data[2].split("\"");
		String fieldtype = data[0];
		//get field value 
		data = line.split(">");
		data = data[1].split("<");
		String fieldval = data[0];
		data = new String[]{fieldname, fieldtype, fieldval};
		
		//for(String str: data){
		//	System.out.println("\t&"+str);
		//}

		return data;

	}	
	
	//setObjectFields(retObj, "set_"+ fieldname, fieldtype, fieldval);	
	public void setObjectFields(Object obj, String method, String fieldtype, String fieldval){
		Method meth = null;	
		Method m[] = obj.getClass().getDeclaredMethods(); 
		//find the correct Method for objects declared methods by comparing methodname decalred names  
		for(int i= 0 ; i < m.length; i++){
			if(m[i].getName().equals(method)){
				meth=m[i];
				//System.out.println("FOUND MATCHING METHOD");
				break;
			}
		}

		Object parameter = new Object();
		if(fieldtype.equals("int")){
			parameter = new Integer(Integer.parseInt(fieldval));
		}
		else if(fieldtype.equals("long")){
			parameter = new Long(Long.parseLong(fieldval));
		}
		else if(fieldtype.equals("string")){
			parameter = new String(fieldval);
		}
		else if(fieldtype.equals("float")){
			parameter = new Float(Float.parseFloat(fieldval));
		}
		else if(fieldtype.equals("double")){
			parameter = new Double(Double.parseDouble(fieldval));
		}
		else if(fieldtype.equals("boolean")){
			parameter = new Boolean(Boolean.parseBoolean(fieldval));
		}
		else if(fieldtype.equals("short")){
			parameter = new Short(Short.parseShort(fieldval));
		}
		else if(fieldtype.equals("char")){
			parameter = new Character(fieldval.charAt(0));
		}
		else{
		}
		try{
			meth.invoke(obj, parameter);
		}
		catch(IllegalAccessException e){
			System.out.println("IllegalAccessException");
			System.exit(1);	
		}catch(IllegalArgumentException e){
			System.out.println(e+" "+ parameter.toString());
			System.exit(1);
		}catch(InvocationTargetException e){
			System.out.println("InvocationTargetException");
			System.exit(1);
		}
		


	}

}

