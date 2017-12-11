package genericCheckpointing.xmlStoreRestore;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.*;



public class StoreRestoreHandler implements InvocationHandler{
	private final Object target;

	public StoreRestoreHandler(Object target){
		this.target = target;
	}

	public StoreRestoreHandler(){
	target = null;	
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		return method.invoke(target, args);
	}



}
