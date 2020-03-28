package col106.assignment3.Election;
//import col106.assignment3.BST;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Vector;
import java.util.Iterator;
import java.text.DecimalFormat;
public class Election implements ElectionInterface{
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		ElectionDriverCode EDC = new ElectionDriverCode();
		System.setOut(EDC.fileout());
	}
	public BST<String, Integer> bst = new BST();

    	
    public void insert(String name, String candID, String state, String district, String constituency, String party, String votes){
    	int v=Integer.parseInt(votes);
    	//BST.node n=new BST.node(candID,v,name,state,district,constituency,party );
    	
    	bst.insert2(candID,v,name,state,district,constituency,party); 
	}
	public void updateVote(String name, String candID, String votes){
		BST.node n=bst.delete1(candID);
		int v=Integer.parseInt(votes);

		bst.insert2(candID,v,n.name,n.state,n.district,n.constituency,n.party); 
	}

		//write your code here
	
	public void topkInConstituency(String constituency, String k){
		int v=Integer.parseInt(k);
		Vector<BST<String, Integer>.node> nn=bst.givenode(constituency);
		for(int i=0;i<nn.size();i++){
			for(int j=i;j<nn.size();j++){
				if(nn.get(i).value<nn.get(j).value){
					//BST.node tmp=new BST.node(null,null,null,null,null,null,null);
					BST.node tmp=nn.get(i);
					nn.set(i,nn.get(j));
					nn.set(j,tmp);

				}
			}
		}
		int pp=Integer.parseInt(k);
		int total=pp;
		if(pp>nn.size()){
			total=nn.size();

		}
		for (int i=0;i<total;i++){
			System.out.println(nn.get(i).name+", "+nn.get(i).key+", "+nn.get(i).party);
			
		}

		//write your code here
	}
	public void leadingPartyInState(String state){
		Vector<BST<String, Integer>.node> nnn=bst.givenodestate(state);
		Vector<Integer> vot=new Vector<Integer>();
		Vector<String> vpar=new Vector<String>();
		int vote;
		for(int i=0;i<nnn.size();i++){
			vote=0;
			vote=vote+nnn.get(i).value;
			for(int j=i+1;j<nnn.size();j++){	
					if(nnn.get(i).party.equals(nnn.get(j).party)){
						vote=vote+nnn.get(j).value;
						nnn.remove(j);
						j--;
						
					}
			}
			vpar.add(nnn.get(i).party);
			vot.add(vote);
		}
		
		int max=vot.get(0);
		String partylead=new String();
		partylead=vpar.get(0);
		int p=0;
		for(int i=0;i<vot.size();i++){
			if(vot.get(i)>max){
				max=vot.get(i);
				partylead=vpar.get(i);
				p=i;
			//	System.out.println(max+" .. "+i);

			}
		}
		//System.out.println(p+"p");

		Vector<String> winpar=new Vector<String>();
		winpar.add(partylead);
		for(int i=0;i<vot.size();i++){
			if(p!=i){
				if(max==vot.get(i)){
					winpar.add(vpar.get(i));

				}

			}
		}
	
	
		for(int i=0;i<winpar.size();i++){
			for(int j=i;j<winpar.size();j++){
				if(winpar.get(i).compareTo(winpar.get(j))>0){
				String tmpu=new String();
				tmpu=winpar.get(i);
				winpar.set(i,winpar.get(j));
				winpar.set(j,tmpu);

				}
			}
		}
		for(int i=0;i<winpar.size();i++)
				System.out.println(winpar.get(i));
	}
		
	public void cancelVoteConstituency(String constituency){
		Vector<BST<String, Integer>.node> nn=bst.givenode(constituency);
		for(int i=0;i<nn.size();i++)
			bst.delete(nn.get(i).key);
		
		//write your code here
	}
	public void leadingPartyOverall(){
		Vector<BST<String, Integer>.node> nnn=bst.vec();
		//for(int i=0;i<nnn.size();i++)
		//	System.out.println(nnn.get(i).party+" printing"+nnn.get(i).value);
		Vector<Integer> vot=new Vector<Integer>();
		Vector<String> vpar=new Vector<String>();
		int vote;
		for(int i=0;i<nnn.size();i++){
			vote=0;
			vote=vote+nnn.get(i).value;
			for(int j=i+1;j<nnn.size();j++){	
					if(nnn.get(i).party.equals(nnn.get(j).party)){
						vote=vote+nnn.get(j).value;
						nnn.remove(j);
						j--;
						
					}
			}
			vpar.add(nnn.get(i).party);
			vot.add(vote);
		}
		//for(int i=0;i<vot.size();i++)
		//	System.out.println(vot.get(i)+" printing"+vpar.get(i));
		int max=vot.get(0);
		String partylead=new String();
		partylead=vpar.get(0);
		int p=0;
		for(int i=0;i<vot.size();i++){
			if(vot.get(i)>max){
				max=vot.get(i);
				partylead=vpar.get(i);
				p=i;
			//	System.out.println(max+" .. "+i);

			}
		}
		//System.out.println(p+"p");

		Vector<String> winpar=new Vector<String>();
		winpar.add(partylead);
		for(int i=0;i<vot.size();i++){
			if(p!=i){
				if(max==vot.get(i)){
					winpar.add(vpar.get(i));

				}

			}
		}
		for(int i=0;i<winpar.size();i++){
			for(int j=i;j<winpar.size();j++){
				if(winpar.get(i).compareTo(winpar.get(j))>0){
				String tmpu=new String();
				tmpu=winpar.get(i);
				winpar.set(i,winpar.get(j));
				winpar.set(j,tmpu);

				}
			}
		}
		for(int i=0;i<winpar.size();i++)
				System.out.println(winpar.get(i));
	}
		
	
	public void voteShareInState(String party,String state){
		Vector<BST<String, Integer>.node> nnn=bst.givenodestate(state);
		Vector<Integer> vot=new Vector<Integer>();
		Vector<String> vpar=new Vector<String>();
		int vote;
		for(int i=0;i<nnn.size();i++){
			vote=0;
			vote=vote+nnn.get(i).value;
			for(int j=i+1;j<nnn.size();j++){	
					if(nnn.get(i).party.equals(nnn.get(j).party)){
						vote=vote+nnn.get(j).value;
						nnn.remove(j);
						//count--;
					}
			}
			vpar.add(nnn.get(i).party);
			vot.add(vote);
		}
		int max=0;
		String partylead=new String();
		for(int i=0;i<vot.size();i++){
			if(vpar.get(i).equals(party)){
				max=max+vot.get(i);
				
			}
		}
		int votes=0;
		for(int i=0;i<vot.size();i++){
			votes=votes+vot.get(i);
		}
		max=100*max;		
		int answer=(int) (max/votes);
		System.out.println(answer);
	}
	
	public void printElectionLevelOrder() {
		bst.printBST1();
		//write your code here
		
	}
}











