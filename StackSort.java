import java.util.Arrays;
import java.lang.reflect.Array;
class StackSort extends MyStack{
	StackSort(){
	}
	String[] sort(int[] nums) throws EmptyStackException{
		MyStack<Integer> A= new MyStack<Integer>();
		int n=nums.length;
		int[] numss=new int[n];
		String[] B=new String[2*n];
		int p=0;
		int q=0;
		String a=new String();
		int j=0;
		if(n>0){
			A.push(nums[0]);
			B[j]="PUSH";
			j++;
		}
		p=1;
		while(q<n){
			while(p<n){
				if(A.isEmpty()==false&&nums[p]>A.top()){
						numss[q]=A.top();
						A.pop();
						B[j]="POP";
						j++;
						q++;
				}
				if(A.isEmpty()==true||nums[p]<=A.top()){
					A.push(nums[p]);
					B[j]="PUSH";
					j++;
					p++;
				}
			}
			numss[q]=A.top();
			A.pop();
			B[j]="POP";
			j++;
			q++;		
		}
		String[] not=new String[]{"NOTPOSSIBLE"};
		for(int i=0;i<n-1;i++){
			if((numss[i]>numss[i+1])||n==0){
				return(not);
			}
		}
		return(B);		
	}
	String[][] kSort(int[] nums) throws EmptyStackException{
		int n=nums.length;
		String[][] B=new String[n][2*n];
		int p=0;
		int q=0;
		String a=new String();
		int j=0;
		int cnt=0;
		while(cnt<=n){
			j=0;
			int xoxo=0;
			q=0;
			MyStack<Integer> A= new MyStack<Integer>();
			if(n>0){
				A.push(nums[0]);
				B[cnt][j]="PUSH";
				j++;
			}
			p=1;
			while(q<n){
				while(p<n){
					if(A.isEmpty()==false&&nums[p]>A.top()){
							nums[q]=A.top();
							A.pop();
							B[cnt][j]="POP";
							j++;
							q++;
					}
					if(A.isEmpty()==true||nums[p]<=A.top()){
						A.push(nums[p]);
						B[cnt][j]="PUSH";
						j++;
						p++;
					}
				}
				nums[q]=A.top();
				A.pop();
				B[cnt][j]="POP";
				j++;
				q++;		
			}
			for(int i=0;i<n-1;i++){
				if((nums[i]>nums[i+1])||n==0){
					xoxo=1;
					break;					
				}
			}
			if(xoxo==0)
				break;
			if(xoxo==1)
				cnt++;
		}
		String[][] Bing=new String[cnt+1][2*n];
		for(int x=0;x<cnt+1;x++){
			for(int y=0;y<2*n;y++)
				Bing[x][y]=B[x][y];
		}
		return(Bing);		
	}
}	
