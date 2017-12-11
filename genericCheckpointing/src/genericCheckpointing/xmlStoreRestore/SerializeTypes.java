package genericCheckpointing.xmlStoreRestore;
import java.lang.reflect.*;


public class SerializeTypes{

	public String serializeClass(Object obj){
		String retval = "  <complexType xsi:type=\"" + obj.getClass().getName() + "\">";
		return retval;
	}

	public String field(Object obj, Field field) throws IllegalAccessException{
		String retval = "";	
		//try{
			retval = "    <"+ field.getName() +" xsi:type=\"xsd:"+field.getType()+"\">"+field.get(obj)+"</"+field.getName()+">";
		//}
		//catch (IllegalAccessException e){

		//}
		return retval;
	}


}
