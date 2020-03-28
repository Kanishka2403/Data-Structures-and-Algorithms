import java.util.Arrays;
import java.lang.reflect.Array;
class MyCalculator extends MyStack{
	MyCalculator(){
	}
	String[] input(String expressio){
		String expression=expressio.replace(" ","");
		int n=expression.length();
		String[] str=new String[n];
		int i=0;
		int f =0;
		int flag=0;
		int f1=0;
		while(i<=n-1){
			flag=0;
			String a=new String();
			while((!(Character.toString(expression.charAt(i)).equals("+")))&&(!(Character.toString(expression.charAt(i)).equals("-")))&&(!(Character.toString(expression.charAt(i)).equals("*")))&&(!(Character.toString(expression.charAt(i)).equals(")")))&&(!(Character.toString(expression.charAt(i)).equals("(")))){
				a=a+expression.charAt(i);	
				flag=1;	
				if(i==n-1){
					i++;
					f1=1;
					break;
				}
				else
					i++;
			}
			if(flag==1){
				str[f]=a;
				f++;
			}
			if(i==n)
				break;
			if(f==n)
			break;
			while((Character.toString(expression.charAt(i)).equals("+"))||(Character.toString(expression.charAt(i)).equals("-"))||(Character.toString(expression.charAt(i)).equals("*"))||(Character.toString(expression.charAt(i)).equals(")"))||(Character.toString(expression.charAt(i)).equals("("))){
				String p=Character.toString(expression.charAt(i));
				str[f]=p;
				f++;
				if(i==n-1){
					i++;
					break;
				}
				else
					i++;
			}
			if(i==n)
				break;
			if(f1==1)
				break;
			if(f==n)
				break;	
		}
		int k=0;
		for(int l=0;l<n;l++){
			if(str[l]==null)
			{
				k=l;
				break;
			}
			else
				k=n;	
		}
		String[] strr=new String[k];
		for(int ml=0;ml<k;ml++){
			strr[ml]=str[ml];
		}
		return strr;
	}
	int multiply(String a, String b){
		int c=Integer.parseInt(a);
		int d=Integer.parseInt(b);
		return c*d;
	}
	int add(String a, String b){
		int c=Integer.parseInt(a);
		int d=Integer.parseInt(b);
		return c+d;
	}
	int subtract(String a, String b){
		int c=Integer.parseInt(a);
		int d=Integer.parseInt(b);
		return c-d;
	}
	int solve(MyStack<String> obj)throws EmptyStackException{
		int result =0;
		MyStack<String> ss= new MyStack<String>();
		try{
		while(obj.isEmpty()==false){
			ss.push(obj.top());
			obj.pop();
			if(!obj.isEmpty()){
				if(obj.top().equals("*")){
					obj.pop();
					if(!obj.isEmpty()){
						int a=multiply(ss.top(),obj.top());
						if(!ss.isEmpty())
							ss.pop();
						obj.pop();
						obj.push(Integer.toString(a));
					}
				}else{
					ss.push(obj.top());
					obj.pop();
				}
			}
		}
		String b=new String();
		while(!ss.isEmpty()){
			String a=ss.top();
			ss.pop();
			if(!ss.isEmpty()){
				if(ss.top().equals("+")){
					ss.pop();
					if(!ss.isEmpty()){
						int p=add(a,ss.top());
						ss.pop();
						ss.push(Integer.toString(p));
					}
				}
				if(ss.top().equals("-")){
					ss.pop();
					if(!ss.isEmpty()){
						int p=subtract(a,ss.top());
						ss.pop();
						ss.push(Integer.toString(p));
					}
				}
			}
			b = a;
		}
		result = Integer.parseInt(b);
	}catch(Exception e){
	}
		return result;	
	}
	int cal(MyStack<String> obj, int count) throws EmptyStackException{
		MyStack<String> ss= new MyStack<String>();
		MyStack<String> ss1= new MyStack<String>();
		int sol=0;
		try{
		while((count>0) && (obj.isEmpty()==false)){
			while(!(obj.top().equals("("))){
				ss.push(obj.top());
				if(obj.isEmpty()==false)
					obj.pop();
				else
					break;
			}
			if(obj.top().equals("(")){
				obj.pop();
				if(ss.isEmpty()==false){
					while(!(ss.top().equals(")"))){
						ss1.push(ss.top());
						if(ss.isEmpty()==false)
							ss.pop();
						else
							break;
					}
				}
				int solv=solve(ss1);
				while(ss1.isEmpty()==false){
					ss1.pop();
				}
				count--;
				if(ss.isEmpty()==false)
					ss.pop();
				ss.push(Integer.toString(solv));	
			}
			while(ss.isEmpty()==false){
				obj.push(ss.top());
				ss.pop();
			}
		}
		sol=solve(obj);
	}catch(Exception e){
	}
		return sol;
	}
	int calculate(String expressio) throws EmptyStackException{
		int ffinal=0;
		try{
		String[] str=input(expressio);
		int k=str.length;
		MyStack<String> o= new MyStack<String>();
		int ctr=0;
		for(int ii=0;ii<k;ii++){
			o.push(str[ii]);
			if(str[ii].equals("(")){
				ctr++;
			}
		}
		ffinal=cal(o,ctr);
	}catch(Exception e){
		}
		return ffinal;
	}
	/*public static void main(String args[]) throws EmptyStackException{
		MyCalculator a= new MyCalculator();
		System.out.println(a.calculate("(3+(4*(7+9*(6-3))+6)+9)"));
	}*/
}