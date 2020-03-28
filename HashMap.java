package col106.assignment4.HashMap;
import java.util.Vector;

public class HashMap<V> implements HashMapInterface<V> {

	class node<V>{
		String key;
		V value;
	}
	int count=1;
	node[] arr;
	public HashMap(int size) {
		count=size;
		arr=new node[count];
	}
	
	public V put(String keyy, V val){
		int l=keyy.length();
		int k=actualkey(keyy);
		int flag=0;
		V a=null;
		int cnt=0;
		while(arr[k]!=null&&cnt<count){
			if(arr[k].key.equals(keyy)){
				a=(V) arr[k].value;
				arr[k].value=val;
				flag=1;
				break;
			}
			k=(k+1)%(count);
			cnt++;
		}
		if(flag==1){
			return a;
		}
		else{
			arr[k]=new node<V>();
			arr[k].key=keyy;
			arr[k].value=(V) val;
			return null;
		}

	}

	public V get(String keyy){
		int l=keyy.length();
		int k=0;
			int aa=1;
		for(int i=0;i<l;i++){
			k=(k+(aa*(keyy.charAt(i))%count)%count)%count;
			aa=(aa*41)%(this.count);		
		}
		k=k%(count);
		int flag=0;
		V a=null;
		int cnt=0;
		while(arr[k]!=null&&cnt<count){
			if(arr[k].key.equals(keyy)){
				a=(V) arr[k].value;
				flag=1;
				break;
			}
			k=(k+1)%(count);
			cnt++;
		}
		if(flag==1)
			return a;
		else
			return null;
	}
	public int actualkey(String key1){
		int l=key1.length();
		int k1=0;
		int aa=1;
		for(int i=0;i<l;i++){
			k1=(k1+(aa*(key1.charAt(i))%(this.count))%(this.count))%(this.count);
			aa=(aa*41)%(this.count);		
		}
		return k1;
	}

	public boolean remove(String keyy){
		int l=keyy.length();
		int k=0;
		int aa=1;
		for(int i=0;i<l;i++){
			k=(k+(aa*(keyy.charAt(i))%count)%count)%count;
			aa=(aa*41)%(this.count);		
		}
		k=k%(count);
		int flag=0;
		int p=k;
		int cnt=0;
		//cnt1=0;
		while(arr[k]!=null&&cnt<count){

			if(arr[k].key.equals(keyy)){
				int i=k;
				int cnt1=1;
				while(arr[i]!=null&&cnt1<count){
					if(arr[(i+1)%(count)]==null){
						arr[i]=null;
						break;
					}
					int j=(i+1)%count;
					int kk;
					int cnt2=1;
					while(arr[j]!=null&&cnt2<count){
						kk=actualkey(arr[j].key);
						if(kk<=i){
							arr[i].key=arr[j].key;
							arr[i].value=arr[j].value;
							i=j;
							j=(j+1)%count;

						}else
							j=(j+1)%count;
						cnt2++;
					}
					cnt1++;
					if(cnt1==count)
						arr[i]=null;						
				}
				flag=1;
				break;
			}
			k=(k+1)%(count);
			cnt++;
		}
		if(flag==1)
			return true;
		else
			return false;
	}

	public boolean contains(String keyy){
		int l=keyy.length();
		int k=0;
			int aa=1;
		for(int i=0;i<l;i++){
			k=(k+(aa*(keyy.charAt(i))%count)%count)%count;
			aa=(aa*41)%(this.count);		
		}
		k=k%(count);
		int flag=0;
		int cnt=0;
		while(arr[k]!=null&&cnt<count){
			if(arr[k].key.equals(keyy)){
				flag=1;
				break;
			}
			k=(k+1)%(count);
			cnt++;
		}
		if(flag==1)
			return true;
		else
			return false;
	}

	public Vector<String> getKeysInOrder(){
		Vector<String> v=new Vector<String>();
		for(int i=0;i<count;i++){
			if(arr[i]!=null)
				v.add(arr[i].key);
		}
		return v;
	}
}
