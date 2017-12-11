package genericCheckpointing.xmlStoreRestore;
import java.lang.reflect.*;


public class SerializeTypes{

	public String serializeClass(Object obj){
		String retval = "  <complexType xsi:type=\"" + obj.getClass().getName() + "\">";
		return retval;
	}

	public String field(Object obj, Field field) throws IllegalAccessException{
		String retval = "";	
		if(String.class.isAssignableFrom(field.getType())){
			retval = "    <"+ field.getName() +" xsi:type=\"xsd:string\">"+field.get(obj)+"</"+field.getName()+">";
		}
		else if(field.getType().equals(boolean.class)){
			retval = "    <"+ field.getName() +" xsi:type=\"xsd:boolean\">"+field.get(obj)+"</"+field.getName()+">";
		}
		else{
			retval = "    <"+ field.getName() +" xsi:type=\"xsd:"+field.getType()+"\">"+field.get(obj)+"</"+field.getName()+">";
		}
			
		return retval;
	}


}
