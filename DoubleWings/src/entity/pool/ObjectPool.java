/*****************************************************************
 * File: ObjectPool.java
 * Purpose: Object Poll class implementation
 *****************************************************************/

package entity.pool;

import java.util.ArrayList;

/**
 * Abstract class to create quantity lots of objects,
 * like enemies and bullets
 */
public abstract class ObjectPool<Type> {
	
	/**
	 * Stores the objects, like bullets and enemies
	 */
	private ArrayList<Type> objects = new ArrayList<Type>();
	
	/**
	 * Release the objects in the screen when it's empty
	 * @return
	 */
	public Type release(){
		Type obj = null;
		
		/*
		 * Creation of objects when the screen is empty
		 */
		if (objects.isEmpty()){
			obj = create();
		}else{
			int last = objects.size() -1;
			obj = objects.remove(last);
		}
		return obj;
	}
	
	/*
	 * Create's objects to keep in the ArrayLists objects 
	 */
	protected abstract Type create();
	
	/**
	 * Recovers the objects
	 * @param obj when object's container is empty, it adds an object to it
	 */
	public void acquire(Type obj){
		
		if (objects.contains(obj) == false){
			objects.add(obj);
		}
	}
}
