package col106.assignment4.HashMap;

public class WordCounter{

	public WordCounter(){
		// write your code here
	}

	public int count(String str, String word){
		String a;
		char[] st=new char[str.length()];
		for(int i=0;i<str.length();i++){
			st[i]=str.charAt(i);
		}
		HashMap<Integer> hash = new HashMap<Integer>((int)1000);
		int cnt=0;
		 for (int i=1;i<=str.length();i++){  
            for (int j=0;j<=str.length()-i;j++){    
                int k=j+i-1;
                String b=new String();
                for (int l=j;l<=k;l++){
                    b=b+st[l];
                }
                if(b.equals(word)){
                	cnt++;
                	hash.put(b,cnt); 
                }
                
            } 
        }
		return cnt;
	}
	
}
