package b100.utils;

import java.lang.reflect.Array;
import java.util.List;

public abstract class Utils {
	
	public static <E> E[] toArray(Class<E> clazz, List<E> list) {
		E[] array = createArray(clazz, list.size());
		
		for(int i=0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] createArray(Class<E> clazz, int length) {
		try{
			return (E[]) Array.newInstance(clazz, length);
		}catch (Exception e) {
			throw new RuntimeException("Creating Array of class "+clazz+" and length "+length);
		}
	}
	
}
