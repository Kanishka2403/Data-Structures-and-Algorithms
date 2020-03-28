package col106.assignment3.Heap;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Vector;

public class Heap<T extends Comparable, E extends Comparable> implements HeapInterface <T, E> {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		HeapDriverCode HDC = new HeapDriverCode();
		System.setOut(HDC.fileout());
	}
	/*
	 * end code
	 */
	
	Vector<T> k=new Vector<T>();
	Vector<E> v=new Vector<E>();
	int cnt=0;
	public void insert(T key, E value) {
		v.add(value);
		k.add(key);
		int i=v.size()-1;
		int j=i;
		cnt=0;
		while(cnt<1000 && j!=0&&i!=0){
			j=(i-1)/2;
			if(j>=0 && v.get(i).compareTo(v.get(j))>0){
				E var=v.get(j);
				v.set(j,v.get(i));
				v.set(i,var);
				T var1=k.get(j);
				k.set(j,k.get(i));
				k.set(i,var1);
			}
			i=j;
			cnt++;
		}
		//write your code here		
	}

	public E extractMax() {
		E max= v.get(0);
		int p=v.size()-1;
		v.set(0,v.get(p));
		k.set(0,k.get(p));
		v.remove(p);
		k.remove(p);
		int i=0;
		int j=0;
		int l=p-1;
		int flag=0;
		cnt=0;
		while(cnt<1000 &&j<=l&&i<=l){
			j=2*i+1;
			if(j<=l&&i<=l){
				flag=0;
				if(j+1<=l && v.get(j+1).compareTo(v.get(j))>0)
					flag=1;	
				if(flag==0){
					if(v.get(i).compareTo(v.get(j)) < 0){
						E var=v.get(j);
						v.set(j,v.get(i));
						v.set(i,var);
						T var1=k.get(j);
						k.set(j,k.get(i));
						k.set(i,var1);
					}
				}
				if(flag==1){
					if(v.get(i).compareTo(v.get(j+1)) < 0){
						E var=v.get(j+1);
						v.set(j+1,v.get(i));
						v.set(i,var);
						T var1=k.get(j+1);
						k.set(j+1,k.get(i));
						k.set(i,var1);
					}
				}
			}
			if(flag==0)
				i=j;
			else
				i=j+1;
			if(i>l)
					break;
			cnt++;
		}
		return max;
		//write your code here
		//return null;
	}

	public void delete(T key) {
		int p=v.size();
		int i=0;
		int flagg=0;
		for(i=0;i<p;i++){
			if(key==k.get(i)){
				flagg=1;
				break;
			}
		}
		
		if(flagg==1){
		//System.out.println(i);
			if(i==0){
				E vv=extractMax();
			}else{
			v.set(i,v.get(p-1));
			k.set(i,k.get(p-1));
			v.remove(p-1);
			k.remove(p-1);
			//if(i!=p-1)
			
				if(i!=p-1){
					int j=0;
					int l=p-2;
					int flag=0;
					int ii=i;
					if(i<=l && ((2*i+1<=l &&  v.get(i).compareTo(v.get(2*i+1))<0)|| (2*i+2<=l && v.get(i).compareTo(v.get(2*i+2))<0))){
						cnt=0;
						while(cnt<1000 && j<=l&&i<=l){
							j=2*i+1;
							if(j<=l){
								flag=0;

								if(j+1<=l && v.get(j+1).compareTo(v.get(j))>0)
									flag=1;	
								if(flag==0){
									if(v.get(i).compareTo(v.get(j)) < 0){
										E var=v.get(j);
										v.set(j,v.get(i));
										v.set(i,var);
										T var1=k.get(j);
										k.set(j,k.get(i));
										k.set(i,var1);
									}
								}
								if(flag==1){
									if(v.get(i).compareTo(v.get(j+1)) < 0){
										E var=v.get(j+1);
										v.set(j+1,v.get(i));
										v.set(i,var);
										T var1=k.get(j+1);
										k.set(j+1,k.get(i));
										k.set(i,var1);
									}
								}
								
							}
							if(flag==0)
								i=j;
							else
								i=j+1;
							if(i>l)
								break;
							cnt++;
						}
					}
				
					
					i=ii;
					j=i;
					if(i<=l && (((i-1)/2)>=0 && v.get(i).compareTo(v.get(((i-1)/2)))>0)){
						cnt=0;
						while(cnt<1000 &&j!=0&&i!=0){
							j=(i-1)/2;
							if(j>=0 && v.get(i).compareTo(v.get(j))>0){
								E var=v.get(j);
								v.set(j,v.get(i));
								v.set(i,var);
								T var1=k.get(j);
								k.set(j,k.get(i));
								k.set(i,var1);
							}
							i=j;
							cnt++;
						}
					}
				}
			}
			//}
			//write your code here
		}
	}

 	public void increaseKey(T key, E value) {
		//write your code here
		int p=v.size();
		int i=0;
		for(i=0;i<p;i++){
			if(key==k.get(i))
				break;
		}
		v.set(i,value);
		int j=i;
		cnt=0;
		while(cnt<1000 &&j!=0&&i!=0){
			j=(i-1)/2;
			if(j>=0 && v.get(i).compareTo(v.get(j))>0){
				E var=v.get(j);
				v.set(j,v.get(i));
				v.set(i,var);
				T var1=k.get(j);
				k.set(j,k.get(i));
				k.set(i,var1);
			}
			i=j;
			cnt++;
		}		
	}

	public void printHeap() {
		int p=v.size();
		int i=0;
		for(i=0;i<p;i++){
			System.out.println(k.get(i)+", "+v.get(i));
		}
		//write your code here
	}	
}