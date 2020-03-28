import java.util.Arrays;
import java.lang.reflect.Array;
class EmptyStackException extends Exception
	{
		public EmptyStackException(String s)
		{
			super(s);
		}
	}
public class MyStack<T>{
	public T[] obj;

	public int i=0;
	
	MyStack(){
		//@SuppressWarnings("unchecked")
		this.obj=(T[]) new Object[(int) Math.pow(10,6)];
	}

	void push(T value){	
		obj[i]=value;
		i++;
	}

	T pop() throws EmptyStackException{
		if(i==0)
			throw new EmptyStackException("EmptyStackException");
		else{
			//@SuppressWarnings("unchecked")
			T a=(T) obj[i-1];
			obj[i-1]=null;
			i--;
			return a;
		}
	}
	
	T top() throws EmptyStackException{
		try{if(i==0)
			throw new EmptyStackException("EmptyStackException");
		else{
			//@SuppressWarnings("unchecked")
			T a=(T) this.obj[i-1];
			return a;
		}
		}catch(Exception e){
			throw new EmptyStackException("EmptyStackException");
		}
	}

	boolean isEmpty(){
		if(this.obj[0]==null)
			return true;
		else
			return false;
	}

	/*public static void main(String[] args){
		MyStack<Integer> iobj= new MyStack<Integer>();
		try{
		int a=1;
		iobj.push(a);
		System.out.println(iobj.pop()+" pop");
		System.out.println(iobj.isEmpty()+" bool");
		System.out.println(iobj.top()+" top");
		}catch(EmptyStackException e){
			System.out.println(e);
		}
	
	}*/
}