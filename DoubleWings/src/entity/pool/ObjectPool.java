/*****************************************************************
 * File: ObjectPool.java
 * Purpose: Object Poll class implementation
 *****************************************************************/

package entity.pool;

import java.util.ArrayList;
/*
 * Abstract class to create quantity lots of objects,
 * like enemies and bullets
 */
public abstract class ObjectPool<Type> {
	
	private ArrayList<Type> objects = new ArrayList<Type>();
	
	public Type release(){
		Type obj = null;
		
		if (objects.isEmpty()){
			//placeholder
			obj = create();
		}else{
			int last = objects.size() -1;
			obj = objects.remove(last);
		}
		return obj;
	}
	
	protected abstract Type create();
	
	public void acquire(Type obj){
		
		if (objects.contains(obj) == false){
			objects.add(obj);
		}
	}
}
