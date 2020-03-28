package col106.assignment3.BST;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Vector;
public class BST<T extends Comparable, E extends Comparable> implements BSTInterface<T, E>  {
	public static void main() {
		BSTDriverCode BDC = new BSTDriverCode();
		System.setOut(BDC.fileout());
	}

	class node{
		T key;
		E value;
		node left;
		node right;
        String name;
        String state;
        String district;
        String constituency;
        String party;
		public node(T k, E v, String name1,String state1, String district1,String constituency1, String party1 ){
			key=k; 
			value=v;
			left=null;
			right=null;
            name=name1;
            state=state1;
            district=district1;
            constituency=constituency1;
            party=party1;
        }
	}
	node root;
    node node1;
    Vector<T> vkey=new Vector<T>();
    Vector<node> v=new Vector<node>();
    Vector<E> vvalue=new Vector<E>();
	BST(){
		root=null;
	}

	public int cnt=0;

    public void insert(T key, E value) {
    	node a=this.root;
        if(a==null)
            root=new node(key,value,null,null,null,null,null);
        else{
        	while(true){
	        	if(a.left==null&&a.value.compareTo(value)>0){
	        		a.left=new node(key,value,null,null,null,null,null);
	            	break;
	            }
	            if(a.right==null&&a.value.compareTo(value)<0){
	            	a.right=new node(key,value,null,null,null,null,null);
	            	break;
	            }
	            if(a.right!=null&&a.value.compareTo(value)<0){
	                a=a.right;
	                continue;
	            }
	            if(a.left!=null&&a.value.compareTo(value)>0){
	                a=a.left;
	            }
	        }                  
    	}
    }
   
    public void insert2(T key, E value,String name, String state, String district, String constituency, String party) {
    	node a=this.root;
        if(a==null)
            root=new node(key,value,name,state,district,constituency,party);
        else{
        	while(true){
	        	if(a.left==null&&a.value.compareTo(value)>0){
	        		a.left=new node(key,value,name,state,district,constituency,party);
	            	break;
	            }
	            if(a.right==null&&a.value.compareTo(value)<0){
	            	a.right=new node(key,value,name,state,district,constituency,party);
	            	break;
	            }
	            if(a.right!=null&&a.value.compareTo(value)<0){
	                a=a.right;
	                continue;
	            }
	            if(a.left!=null&&a.value.compareTo(value)>0){
	                a=a.left;
	            }
	        }                  
    	}
    } 
    public void update(T key, E value) {
    	delete(key);
    	insert(key, value);
		//write your code here
    }
    public node parent(node r){
        node rot=root;
        while(true){
            if(rot.left!=null&&r.value.equals(rot.left.value)){
                return rot;
            }
            if(rot.right!=null&&r.value.equals(rot.right.value)){
                return rot;
            }
            if(r.value.compareTo(rot.value)>0){
                rot=rot.right;
                continue;
            }
            if(r.value.compareTo(rot.value)<0){
                rot=rot.left;
            }
        }
    }
    public Vector<node> givenode(String k){
        Vector<node> nn1=new Vector<node>();
        v.clear();
        inorder(root);
        for(int i=0;i<v.size();i++){
            if(v.get(i).constituency.equals(k)){
                nn1.add(v.get(i));
            }
        }
        return nn1;
    }
    public Vector<node> givenodestate(String k){
        Vector<node> nn1=new Vector<node>();
        v.clear();
        inorder(root);
        for(int i=0;i<v.size();i++){
            if(v.get(i).state.equals(k)){
                nn1.add(v.get(i));
            }
        }
        return nn1;
    }
    public node delete1(T key) {
        //write your code here
        node nn=new node(key,null,null,null,null,null,null);
        v.clear();
        inorder(root);
        E value=v.get(0).value;
        for(int i=0;i<v.size();i++){
            if(v.get(i).key.equals(key)){
                value=v.get(i).value;
                nn=v.get(i);
                break;
            }
        }
        node n2=new node(key,null,null,null,null,null,null);
        n2.value=nn.value;
        n2.key=nn.key;
        n2.district=nn.district;
        n2.state=nn.state;
        n2.constituency=nn.constituency;
        n2.party=nn.party;
        n2.name=nn.name;
        node root2=this.root;
        node root1;
        while(true){
            if(value==root2.value){
                root1=root2;
                break;
            }
            if(root2.left!=null&&value.equals(root2.left.value)){
                root1=root2.left;
                break;
            }
            if(root2.right!=null&&value.equals(root2.right.value)){
                root1=root2.right;
                break;
            }
            if(value.compareTo(root2.value)>0){
                root2=root2.right;
                continue;
            }
            if(value.compareTo(root2.value)<0){
                root2=root2.left;
            }

        }

        if(root2.left==root1){
            if(root1.left==null&&root1.right==null){
                root2.left=null;

            }
            else if(root1.left==null||root1.right==null){
                if(root1.left==null)
                    root2.left=root1.right;
                else if(root1.right==null)
                    root2.left=root1.left;
            }
            else{
                v.clear();
                inorder(root);

                int i=0;
                for(i=0;i<v.size();i++){
                    if(v.get(i).value==root1.value){
                        break;
                    }
                }
                node par=parent(v.get(i+1));
                if(root1.right==v.get(i+1)){
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    root2.left=v.get(i+1);
                   // root1=v.get(i+1);
                }
                else{
                    if(v.get(i+1).right!=null)
                        par.left=v.get(i+1).right;
                    else
                        par.left=null;
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    if(root1.right!=null)
                        v.get(i+1).right=root1.right;
                    else
                        v.get(i+1).right=null;
                    root2.left=v.get(i+1);
                    //root1=v.get(i+1);
                }
            }
        }

        else if(root2.right==root1){
            if(root1.left==null&&root1.right==null){
                root2.right=null;
            }
            else if(root1.left==null||root1.right==null){
                if(root1.left==null)
                    root2.right=root1.right;
                else if(root1.right==null)
                    root2.right=root1.left;
            }
            else{
                v.clear();
                inorder(root);
             
                int i=0;
                for(i=0;i<v.size();i++){
                    if(v.get(i).value==root1.value){
                        break;
                    }
                }
                  node par=parent(v.get(i+1));
                 if(root1.right==v.get(i+1)){
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    root2.right=v.get(i+1);

                 }
                 else{
    
                    if(v.get(i+1).right!=null)
                        par.left=v.get(i+1).right;
                    else
                        par.left=null;
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    if(root1.right!=null)
                        v.get(i+1).right=root1.right;
                    else
                        v.get(i+1).right=null;
                    root2.right=v.get(i+1);
                 }
            }
        }
        else{
                v.clear();
                inorder(root);
                int i=0;
                for(i=0;i<v.size();i++){
                    if(v.get(i).value==root1.value){
                        break;
                    }
                }
                node par=parent(v.get(i+1));
                 if(root1.right==v.get(i+1)){
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    root=v.get(i+1);
                 }
                 else{
                    if(v.get(i+1).right!=null)
                        par.left=v.get(i+1).right;
                    else
                        par.left=null;
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    if(root1.right!=null)
                        v.get(i+1).right=root1.right;
                    else
                        v.get(i+1).right=null;
                    root=v.get(i+1);                
                 }
        }
        return n2;
    }
    public void delete(T key) {
        v.clear();
        inorder(root);
        E value=v.get(0).value;
        for(int i=0;i<v.size();i++){
            if(v.get(i).key.equals(key)){
                value=v.get(i).value;
                break;
            }
        }
        node root2=this.root;
        node root1=new node(null,null,null,null,null,null,null);
        while(true){
            if(value==root2.value){
                root1=root2;
                break;
            }
            if(root2.left!=null&&value.equals(root2.left.value)){
                root1=root2.left;
                break;
            }
            if(root2.right!=null&&value.equals(root2.right.value)){
                root1=root2.right;
                break;
            }
            if(value.compareTo(root2.value)>0){
                root2=root2.right;
                continue;
            }
            if(value.compareTo(root2.value)<0){
                root2=root2.left;
            }

        }
                if(root2.left==root1){
            if(root1.left==null&&root1.right==null){
                root2.left=null;

            }
            else if(root1.left==null||root1.right==null){
                if(root1.left==null)
                    root2.left=root1.right;
                else if(root1.right==null)
                    root2.left=root1.left;
            }
            else{
                v.clear();
                inorder(root);

                int i=0;
                for(i=0;i<v.size();i++){
                    if(v.get(i).value==root1.value){
                        break;
                    }
                }
                node par=parent(v.get(i+1));
                if(root1.right==v.get(i+1)){
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    root2.left=v.get(i+1);
                   // root1=v.get(i+1);
                }
                else{
                    if(v.get(i+1).right!=null)
                        par.left=v.get(i+1).right;
                    else
                        par.left=null;
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    if(root1.right!=null)
                        v.get(i+1).right=root1.right;
                    else
                        v.get(i+1).right=null;
                    root2.left=v.get(i+1);
                    //root1=v.get(i+1);
                }
            }
        }

        else if(root2.right==root1){
            if(root1.left==null&&root1.right==null){
                root2.right=null;
            }
            else if(root1.left==null||root1.right==null){
                if(root1.left==null)
                    root2.right=root1.right;
                else if(root1.right==null)
                    root2.right=root1.left;
            }
            else{
                v.clear();
                inorder(root);
             
                int i=0;
                for(i=0;i<v.size();i++){
                    if(v.get(i).value==root1.value){
                        break;
                    }
                }
                  node par=parent(v.get(i+1));
                 if(root1.right==v.get(i+1)){
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    root2.right=v.get(i+1);

                 }
                 else{
    
                    if(v.get(i+1).right!=null)
                        par.left=v.get(i+1).right;
                    else
                        par.left=null;
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    if(root1.right!=null)
                        v.get(i+1).right=root1.right;
                    else
                        v.get(i+1).right=null;
                    root2.right=v.get(i+1);
                 }
            }
        }
        else{
                v.clear();
                inorder(root);
                int i=0;
                for(i=0;i<v.size();i++){
                    if(v.get(i).value==root1.value){
                        break;
                    }
                }
                node par=parent(v.get(i+1));
                 if(root1.right==v.get(i+1)){
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    root=v.get(i+1);
                 }
                 else{
                    if(v.get(i+1).right!=null)
                        par.left=v.get(i+1).right;
                    else
                        par.left=null;
                    if(root1.left!=null)
                        v.get(i+1).left=root1.left;
                    else
                        v.get(i+1).left=null;
                    if(root1.right!=null)
                        v.get(i+1).right=root1.right;
                    else
                        v.get(i+1).right=null;
                    root=v.get(i+1);                
                 }
        }
    }
    public Vector<node> vec(){
        Vector<node> nn1=new Vector<node>();
        v.clear();
        inorder(root);
        nn1=v;
       
        return nn1;
    } 
    public void inorder(node root) {
        if(root.left!=null)
           inorder(root.left);
        v.add(root);
        if(root.right!=null)
            inorder(root.right);
        else
            return;            
    }
    public int height(node n){
        if(n!=null){
            int r;
            if(n.right==null)
                r=0;
            else
                r=height(n.right);
            int l;
            if(n.left==null)
                l=0;
            else
                l=height(n.left);
            int k=r;
            if(l>r)
            	k=l;
            return k+1;
        }
        else
        	return 0;
    }

    public void printBST () {
        int ht=height(root);
    	for(int i=1;i<=ht;i++)
    		level(root,i);
    	//write your code here
    }
    public void printBST1 () {
        int ht=height(root);
        for(int i=1;i<=ht;i++)
            level2(root,i);
        //write your code here
    }

 	public void level(node root,int level){
 		if(level>1){
            if(root.left!=null)
 			    level(root.left,level-1);
 				//System.out.println
 			if(root.right!=null)
                level(root.right,level-1);
           // System.out.println
            else
                return;
                
 		}
 		else if(level==1)
 			System.out.println(root.key+", "+root.value);
 	}
    public void level2(node root,int level){
        if(level>1){
        	//System.out.printllevel
            if(root.left!=null)
                level2(root.left,level-1);

            if(root.right!=null)
                level2(root.right,level-1);
            //System.out.println(level);
            else
                return;
                
        }
        else if(level==1)
            System.out.println(root.name+", "+root.key+", "+root.state+", "+root.district+", "+root.constituency+", "+root.party+", "+root.value);
    }
    
}