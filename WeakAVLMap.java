package col106.assignment4.WeakAVLMap;
import java.util.Vector;

public class WeakAVLMap<K extends Comparable,V> implements WeakAVLMapInterface<K,V>{
class node{
		K key;
		V value;
		int rank;
		node left;
		node right;
		public node(K k, V v){
			key=k; 
			value=v;
			rank=1;
			left=null;
			right=null;
        }
	}
	node root;
	public int rotate;
	public WeakAVLMap(){
		root=null;
		// write your code here
	}
	public node parent(node r){
		if(r==null||r==root)
			return null;
        node rot=root;
        while(true){
        	if(rot.key.equals(r.key)){
        		return rot;
        	}
        	/*if(rot.key.equals(rot.left.key)||rot.key.equals(rot.rightt.key))
        		return rot;*/
            if(rot.left!=null&&r.key.equals(rot.left.key)){
            	/*if(rot.left.left!=null&&rot.left.left.key.equals(rot.left.key))
            		return rot.left;
            	if(rot.left.right!=null&&rot.left.right.key.equals(rot.left.key))
            		return rot.left;*/
            	if(r.rank==0)
            		return rot.left;
            	else
                	return rot;
            }
            if(rot.right!=null&&r.key.equals(rot.right.key)){
            	/*if(rot.right.left!=null&&rot.right.left.key.equals(rot.right.key))
            		return rot.right;
            	if(rot.right.right!=null&&rot.right.right.key.equals(rot.right.key))
            		return rot.right;*/
            	if(r.rank==0)
            		return rot.right;
            	else
                	return rot;
            }
            if(r.key.compareTo(rot.key)>0){
                rot=rot.right;
                continue;
            }
            if(r.key.compareTo(rot.key)<0){
                rot=rot.left;
            }
        }
    }
	int flag1=0;
	V val=null;
	int pr=0;
	int pa=0;
	int pq=0;
	int ps=0;
	public node sibling(node a){
		node p=parent(a);
		if(a==p.right)
			return p.left;
		else
			return p.right;
	}

	public node insert(K key, V value){
		flag1=0;
		node a=this.root;
        if(a==null){
            root=new node(key,value);
            a=root;
        }
        else{
        	while(true){
        		if(a.key.equals(key)){
        			val=a.value;
        			a.value=value;
        			flag1=1;
        			break;
        		}
	        	if(a.left==null&&a.key.compareTo(key)>0){
	        		a.left=new node(key,value);
	        		a=a.left;
	            	break;
	            }
	            if(a.right==null&&a.key.compareTo(key)<0){
	            	a.right=new node(key,value);
	            	a=a.right;
	            	break;
	            }
	            if(a.right!=null&&a.key.compareTo(key)<0){
	                a=a.right;
	                continue;
	            }
	            if(a.left!=null&&a.key.compareTo(key)>0){
	                a=a.left;
	            }
	        }                  
    	}
		// write your code her 
		return a;
	}
	int flag=0;
	int flagg=0;
	public int totalcount=0;
	public V put(K key, V value){
		node a=insert(key,value);
		if(flag1==1)
			return val;
		else{
			a.rank=1;
			while(a!=root){
				flag=0;
				flagg=0;
				node p=parent(a);
				if(p==null)
					pr=0;
				else
					pr=p.rank;
				pa=a.rank;
				node s=sibling(a);
				if(s==null)
					ps=0;
				else
					ps=s.rank;
				if(pr-pa==1)
					break;
				else{
					if(pr-ps==1){
						p.rank++;
						if(p==root)
							break;
					}
					else{
						if((a==p.left)&&(a.left!=null)&&(a.rank-a.left.rank==1)){
							flag=0;
							this.totalcount++;
							p.rank--;
							rightrotate(p);
							
							break;
							
						}else if((a==p.left)&&(a.right!=null)&&(a.rank-a.right.rank==1)){
							flagg=1;
							this.totalcount=this.totalcount+2;
							p.rank--;
							a.rank--;
							(a.right).rank++;
							leftrotate(a);
							rightrotate(p);
							break;
						}else if((a==p.right)&&(a.right!=null)&&(a.rank-a.right.rank==1)){
							flagg=0;
							this.totalcount++;
							p.rank--;
							leftrotate(p);
							if(p.left==null&&p.right==null)
								p.rank=1;
							break;
							
						}else if((a==p.right)&&(a.left!=null)&&(a.rank-a.left.rank==1)){
							flag=1;
							this.totalcount=this.totalcount+2;
							p.rank--;
							a.rank--;
							(a.left).rank++;
							rightrotate(a);	
							leftrotate(p);
							break;
							
						} break;
					}
				}	
				if(a!=root)
					a=parent(a);
				else
					break;
			}
		}
		return null;
	}
	public void rightrotate(node p){
		this.rotate++;
		node c=parent(p);
		node a=p.left;
		node b=a.right;
		a.right=p;
		p.left=b;
		if(c!=null&&c.key.compareTo(a.key)>0)
			c.left=a;
		else if(c!=null&&c.key.compareTo(a.key)<0)
			c.right=a;
		else
			this.root=a;
	}
	public void leftrotate(node p){
		this.rotate++;
		node c=parent(p);
		node a=p.right;
		node b=a.left;
		a.left=p;
		p.right=b;
		if(c!=null&&c.key.compareTo(a.key)>0)
			c.left=a;
		else if(c!=null&&c.key.compareTo(a.key)<0)
			c.right=a;
		else
			this.root=a;
	}
	int fg=0;
	V valr=null;

	/*public V remove(K keyy) {
		v.clear();
		if(root==null)
			return null;
        inorder(root);
        fg=0;
        for(int i=0;i<v.size();i++){
        	if(v.get(i).key.equals(keyy)){
        		fg=1;
        		break;
        	}
        }
        if(fg==0)
        	return null;
        else if(v.size()==1){
        	V valxx=v.get(0).value;
        	root=null;
        	return valxx;
        }
        else{
	        node root2=this.root;
	        node root1=new node(null,null);
	        while(true){
	            if(keyy.equals(root2.key)){
	                root1=root2;
	                valr=root1.value;
	                break;
	            }
	            if(root2.left!=null&&keyy.equals(root2.left.key)){
	                root1=root2.left;
	                valr=root1.value;
	                break;
	            }
	            if(root2.right!=null&&keyy.equals(root2.right.key)){
	                root1=root2.right;
	                valr=root1.value;
	                break;
	            }
	            if(keyy.compareTo(root2.key)>0){
	                root2=root2.right;
	                continue;
	            }
	            if(keyy.compareTo(root2.key)<0)
	                root2=root2.left;
	        }
	        if(root2.left==root1){
	            if(root1.left==null&&root1.right==null){
	                root2.left=new node(root2.key,null);
	                (root2.left).rank=0;
	                balance(root2.left);
	            }
	            else if(root1.left==null||root1.right==null){
	                if(root1.left==null){
	                    root2.left=root1.right;
	                    balance(root2.left);
	                }
	                else if(root1.right==null){
	                    root2.left=root1.left;
	                    balance(root2.left);
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
	                int aa=v.get(i).rank;
	                node par=parent(v.get(i+1));
	                if(root1.right==v.get(i+1)){
	                        v.get(i+1).left=root1.left;
	                        v.get(i+1).rank=aa;
	                        root2.left=v.get(i+1);
	                        if((v.get(i+1)).right==null){
	                        	v.get(i+1).right=new node(v.get(i+1).key,null);
	                        	v.get(i+1).right.rank=0;
	                        }
	                        balance(v.get(i+1).right);
	                }
	                else{
	                	v.get(i+1).rank=aa;
						v.get(i+1).left=root1.left;
	                    if(v.get(i+1).right!=null)
	                      	par.left=v.get(i+1).right;
	                    else{
	                       	par.left=new node(par.key,null);
	                       	par.left.rank=0;
	                   	}
	                    v.get(i+1).right=root1.right;
	                    root2.left=v.get(i+1);
	                    balance(par.left);
	                }
	            }
	        }
	        else if(root2.right==root1){
	            if(root1.left==null&&root1.right==null){
	                root2.right=new node(root2.key,null);
	                (root2.right).rank=0;
	                balance(root2.right);
	            }
	            else if(root1.left==null||root1.right==null){
	                if(root1.left==null){
	                    root2.right=root1.right;
	                    balance(root2.right);
	                }
	                else if(root1.right==null){
	                    root2.right=root1.left;
	                    balance(root2.right);
	                }
	            }
	            else{
	                v.clear();
	                inorder(root);
	                int i=0;
	                for(i=0;i<v.size();i++){
	                    if(v.get(i).key==root1.key){
	                        break;
	                    }
	                }
	                int bb=v.get(i).rank;
	                node par=parent(v.get(i+1));
	                if(root1.right==v.get(i+1)){
                        v.get(i+1).left=root1.left;
                        v.get(i+1).rank=bb;
     	                root2.right=v.get(i+1);
     	                if((v.get(i+1)).right==null){
                        	v.get(i+1).right=new node(v.get(i+1).key,null);
                        	v.get(i+1).right.rank=0;
                        }
                        balance(v.get(i+1).right);
	                }
	                 else{
	                 	v.get(i+1).rank=bb;
	                 	v.get(i+1).left=root1.left;
	                    if(v.get(i+1).right!=null)
	                        par.left=v.get(i+1).right;
	                    else{
	                        par.left=new node(par.key,null);
	                        par.left.rank=0;
	                    }
	                    v.get(i+1).right=root1.right;
	                    root2.right=v.get(i+1);
	                    balance(par.left);
	                 }
	            }
	        }
	        else{
                v.clear();
                inorder(root);
                int i=0;
                for(i=0;i<v.size();i++){
                    if(v.get(i).key==root1.key){
                        break;
                    }
                }
                int dd=v.get(i).rank;
                if(i+1>=v.size()){
                	if(v.size()==1){
                		root=null;
                		V vvv=valr;
                		return vvv;
                	}
                	else{
                		root=v.get(v.size()-2);
                		V vvv=valr;
                		return vvv;
                	}

                }
                else{
	                node par=parent(v.get(i+1));
	                if(root1.right==v.get(i+1)){
	                	v.get(i+1).left=root1.left;
	                	v.get(i+1).rank=dd;
	                	root=v.get(i+1);
	                    if(v.get(i+1).right==null){
	                        v.get(i+1).right=new node(v.get(i+1).key,null);
	                        v.get(i+1).right.rank=0;
	                    }
	                    balance(v.get(i+1).right);
	                }
	                else{
	                	v.get(i+1).left=root1.left;
	                	v.get(i+1).rank=dd;
	                    if(v.get(i+1).right!=null)
	                        par.left=v.get(i+1).right;
	                    else{
	                        par.left=new node(par.key,null);
	                        par.left.rank=0;
	                    }
	                    v.get(i+1).right=root1.right;
	                    root=v.get(i+1);
	                    balance(par.left);                   
	                }
	            }
	                 
	        }
	        V vv=valr;
			return vv;
	    }
    }
    int psl=0;
    int psr=0;
	public void balance(node q){
		//node q=p.left;
		while(q!=root){
			if((q.rank!=0)&&(q.left==null||q.left.rank==0)&&(q.right==null||q.right.rank==0))
				q.rank=1;
			flag=0;
			flagg=0;
			//System.out.println(q.key+" key "+q.rank+" qrank ");
			node p=parent(q);
			//System.out.println(p.rank+" parent ");
			if(p==null)
				pr=0;
			else
				pr=p.rank;
			pq=q.rank;
			node s=sibling(q);
			if(s==null)
				ps=0;
			else
				ps=s.rank;
			//System.out.println(p.key+" parent "+q.key+" key "+pr+" pr "+pq+" pq "+ps+" ps ");
			if(pr-pq==1||pr-pq==2){
				//System.out.println(q.key+"inthelop");
				if(q.rank==0){
					p=parent(q);
					//System.out.println(q.key+" q "+q.rank);
					if(p.left!=null&&p.left.equals(q))
						p.left=null;
					else
						p.right=null;
					q=p;
					//System.out.println(q.key+" q "+q.rank);
				}else
					q=parent(q);
				continue;
			}

			else{
				if(pr-ps==2)
					p.rank--;
				else{
					if(s!=null&&s.left!=null)
						psl=s.left.rank;
					else
						psl=0;
					if(s!=null&&s.right!=null)
						psr=s.right.rank;
					else
						psr=0;
					if(ps-psl==2&&ps-psr==2){
						p.rank--;
						s.rank--;
					}
					else if(ps-psl==1||ps-psr==1){
						if(s==p.left&&ps-psl==1){
							flag=0;
							s.rank++;
							p.rank--;
							this.totalcount++;
							rightrotate(p);
						}else if(s==p.left&&ps-psr==1&&ps-psl!=1){
							flagg=1;
							p.rank=p.rank-2;
							s.rank--;
							(s.right).rank=(s.right).rank+2;
							this.totalcount=this.totalcount+2;
							leftrotate(s);
							rightrotate(p);
						}else if(s==p.right&&ps-psr==1){
							flagg=0;
							s.rank++;
							p.rank--;
							this.totalcount++;
							leftrotate(p);
						}else if(s==p.right&&ps-psl==1&&ps-psr!=1){
							flag=1;
							p.rank=p.rank-2;
							s.rank--;
							(s.left).rank=(s.left).rank+2;
							this.totalcount=this.totalcount+2;
							rightrotate(s);	
							leftrotate(p);
						}	
					}
					else
					p.rank--;
				}
			}
			if(q.rank==0){
				p=parent(q);
				//System.out.println(q+" q "+q.rank);
				if(p.left!=null&&p.left.equals(q))
					p.left=null;
				else
					p.right=null;
				q=p;
				//System.out.println(q+" q "+q.rank);
			}
			else if(q!=root){		
				q=parent(q);
			}
			else
				break;
		}

	}
*/
	public V remove(K keyy) {
		v.clear();
		if(root==null)
			return null;
        inorder(root);
        fg=0;
        for(int i=0;i<v.size();i++){
        	if(v.get(i).key.equals(keyy)){
        		fg=1;
        		break;
        	}
        }
        if(fg==0)
        	return null;
        else if(v.size()==1){
        	V valxx=v.get(0).value;
        	root=null;
        	return valxx;
        }
        else{
	        node root2=this.root;
	        node root1=new node(null,null);
	        while(true){
	            if(keyy.equals(root2.key)){
	                root1=root2;
	                valr=root1.value;
	                break;
	            }
	            if(root2.left!=null&&keyy.equals(root2.left.key)){
	                root1=root2.left;
	                valr=root1.value;
	                break;
	            }
	            if(root2.right!=null&&keyy.equals(root2.right.key)){
	                root1=root2.right;
	                valr=root1.value;
	                break;
	            }
	            if(keyy.compareTo(root2.key)>0){
	                root2=root2.right;
	                continue;
	            }
	            if(keyy.compareTo(root2.key)<0)
	                root2=root2.left;
	        }
	        if(root2.left==root1){
	            if(root1.left==null&&root1.right==null){
	                root2.left=new node(root2.key,null);
	                (root2.left).rank=0;
	                balance(root2.left);
	            }
	            else if(root1.left==null||root1.right==null){
	                if(root1.left==null){
	                    root2.left=root1.right;
	                    balance(root2.left);
	                }
	                else if(root1.right==null){
	                    root2.left=root1.left;
	                    balance(root2.left);
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
	                int aa=v.get(i).rank;
	                node par=parent(v.get(i+1));
	                if(root1.right==v.get(i+1)){
	                        v.get(i+1).left=root1.left;
	                        v.get(i+1).rank=aa;
	                        root2.left=v.get(i+1);
	                        if((v.get(i+1)).right==null){
	                        	v.get(i+1).right=new node(v.get(i+1).key,null);
	                        	v.get(i+1).right.rank=0;
	                        }
	                        balance(v.get(i+1).right);
	                }
	                else{
	                	v.get(i+1).rank=aa;
						v.get(i+1).left=root1.left;
	                    if(v.get(i+1).right!=null)
	                      	par.left=v.get(i+1).right;
	                    else{
	                       	par.left=new node(par.key,null);
	                       	par.left.rank=0;
	                   	}
	                    v.get(i+1).right=root1.right;
	                    root2.left=v.get(i+1);
	                    balance(par.left);
	                }
	            }
	        }
	        else if(root2.right==root1){
	            if(root1.left==null&&root1.right==null){
	                root2.right=new node(root2.key,null);
	                (root2.right).rank=0;
	                balance(root2.right);
	            }
	            else if(root1.left==null||root1.right==null){
	                if(root1.left==null){
	                    root2.right=root1.right;
	                    balance(root2.right);
	                }
	                else if(root1.right==null){
	                    root2.right=root1.left;
	                    balance(root2.right);
	                }
	            }
	            else{
	                v.clear();
	                inorder(root);
	                int i=0;
	                for(i=0;i<v.size();i++){
	                    if(v.get(i).key==root1.key){
	                        break;
	                    }
	                }
	                int bb=v.get(i).rank;
	                node par=parent(v.get(i+1));
	                if(root1.right==v.get(i+1)){
                        v.get(i+1).left=root1.left;
                        v.get(i+1).rank=bb;
     	                root2.right=v.get(i+1);
     	                if((v.get(i+1)).right==null){
                        	v.get(i+1).right=new node(v.get(i+1).key,null);
                        	v.get(i+1).right.rank=0;
                        }
                        balance(v.get(i+1).right);
	                }
	                 else{
	                 	v.get(i+1).rank=bb;
	                 	v.get(i+1).left=root1.left;
	                    if(v.get(i+1).right!=null)
	                        par.left=v.get(i+1).right;
	                    else{
	                        par.left=new node(par.key,null);
	                        par.left.rank=0;
	                    }
	                    v.get(i+1).right=root1.right;
	                    root2.right=v.get(i+1);
	                    balance(par.left);
	                 }
	            }
	        }
	        else{
                v.clear();
                inorder(root);
                int i=0;
                for(i=0;i<v.size();i++){
                    if(v.get(i).key==root1.key){
                        break;
                    }
                }
                int dd=v.get(i).rank;
                if(i+1>=v.size()){
                	if(v.size()==1){
                		root=null;
                		V vvv=valr;
                		return vvv;
                	}
                	else{
                		root=v.get(v.size()-2);
                		V vvv=valr;
                		return vvv;
                	}

                }
                else{
	                node par=parent(v.get(i+1));
	                if(root1.right==v.get(i+1)){
	                	v.get(i+1).left=root1.left;
	                	v.get(i+1).rank=dd;
	                	root=v.get(i+1);
	                    if(v.get(i+1).right==null){
	                        v.get(i+1).right=new node(v.get(i+1).key,null);
	                        v.get(i+1).right.rank=0;
	                    }
	                    balance(v.get(i+1).right);
	                }
	                else{
	                	v.get(i+1).left=root1.left;
	                	v.get(i+1).rank=dd;
	                    if(v.get(i+1).right!=null)
	                        par.left=v.get(i+1).right;
	                    else{
	                        par.left=new node(par.key,null);
	                        par.left.rank=0;
	                    }
	                    v.get(i+1).right=root1.right;
	                    root=v.get(i+1);
	                    balance(par.left);                   
	                }
	            }
	                 
	        }
	        V vv=valr;
			return vv;
	    }
    }
    int psl=0;
    int psr=0;
	public void balance(node q){

	while(q!=root){
			if((q.rank!=0)&&(q.left==null||q.left.rank==0)&&(q.right==null||q.right.rank==0))
				q.rank=1;
			flag=0;
			flagg=0;
			//System.out.println(q.key+" key "+q.rank+" qrank ");
			node p=parent(q);
			//System.out.println(p.rank+" parent ");
			if(p==null)
				pr=0;
			else
				pr=p.rank;
			pq=q.rank;
			node s=sibling(q);
			if(s==null)
				ps=0;
			else
				ps=s.rank;
			//System.out.println(p.key+" parent "+q.key+" key "+pr+" pr "+pq+" pq "+ps+" ps ");
			if(pr-pq==1||pr-pq==2){
				//System.out.println(q.key+"inthelop");
				if(q.rank==0){
					p=parent(q);
					//System.out.println(q.key+" q "+q.rank);
					if(p.left!=null&&p.left.equals(q))
						p.left=null;
					else
						p.right=null;
					q=p;
					//System.out.println(q.key+" q "+q.rank);
				}else
					q=parent(q);
				continue;
			}

			else{
				if(pr-ps==2){					p.rank--;
				
				if(p.equals(root)){
						if(q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
						break;
					}
				}
				else{
					if(s!=null&&s.left!=null)
						psl=s.left.rank;
					else
						psl=0;
					if(s!=null&&s.right!=null)
						psr=s.right.rank;
					else
						psr=0;
					if(ps-psl==2&&ps-psr==2){
						p.rank--;
						s.rank--;
					}
					else if(ps-psl==1||ps-psr==1){
						if(s==p.left&&ps-psl==1){
							flag=0;
							s.rank++;
							p.rank--;
							this.totalcount++;
							rightrotate(p);
							if(p.left==null&&p.right==null)
								p.rank=1;
							if(q!=root&&q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
							break;
						}else if(s==p.left&&ps-psr==1&&ps-psl!=1){
							flagg=1;
							p.rank=p.rank-2;
							s.rank--;
							(s.right).rank=(s.right).rank+2;
							this.totalcount=this.totalcount+2;
							leftrotate(s);
							rightrotate(p);
								if(q!=root&&q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
							break;
						}else if(s==p.right&&ps-psr==1){
							flagg=0;
							s.rank++;
							p.rank--;
							this.totalcount++;
							leftrotate(p);
								if(p.left==null&&p.right==null)
								p.rank=1;
							if(q!=root&&q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
							break;

						}else if(s==p.right&&ps-psl==1&&ps-psr!=1){
							flag=1;
							p.rank=p.rank-2;
							s.rank--;
							(s.left).rank=(s.left).rank+2;
							this.totalcount=this.totalcount+2;
							rightrotate(s);	
							leftrotate(p);
								if(q!=root&&q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
							break;
						}	
					}
				}
			}
			if(q.rank==0){
				p=parent(q);
				//System.out.println(q+" q "+q.rank);
				if(p.left!=null&&p.left.equals(q))
					p.left=null;
				else
					p.right=null;
				q=p;
				//System.out.println(q+" q "+q.rank);
			}
			else if(q!=root){		
				q=parent(q);
			}
			else
				break;
		}

	}

		//node q=p.left;
	/*	int kani=0;
		while(q!=root&&kani<25){
			kani++;
			if((q.rank!=0)&&(q.left==null||q.left.rank==0)&&(q.right==null||q.right.rank==0))
				q.rank=1;
			flag=0;
			flagg=0;
			//System.out.println(q.key+" key "+q.rank+" qrank ");
			node p=parent(q);
			//System.out.println(p.rank+" parent ");
			if(p==null)
				pr=0;
			else{
				
				pr=p.rank;
			}
			pq=q.rank;
			node s=sibling(q);
			if(s==null)
				ps=0;
			else
				ps=s.rank;
			//System.out.println(p.key+" parent "+q.key+" key "+pr+" pr "+pq+" pq "+ps+" ps ");
			if(pr-pq==1||pr-pq==2){
				if(q.rank==0){
					p=parent(q);
					//System.out.println(q+" q "+q.rank);
					if(p.left!=null&&p.left.equals(q))
						p.left=null;
					else
						p.right=null;
				}
				if(p.left==null&&p.right==null)
					p.rank=1;
				break;
			}

			else{
				if(pr-ps==2){
					p.rank--;
					if(p.equals(root)){
						if(q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
						break;
					}
				}
				else{
					if(s!=null&&s.left!=null)
						psl=s.left.rank;
					else
						psl=0;
					if(s!=null&&s.right!=null)
						psr=s.right.rank;
					else
						psr=0;
					if(ps-psl==2&&ps-psr==2){
						p.rank--;
						s.rank--;
						if(p.equals(root)){
							if(q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}

							break;
						}
					}
					else if(ps-psl==1||ps-psr==1){
						if(s==p.left&&ps-psl==1){
							flag=0;
							s.rank++;
							p.rank--;
							this.totalcount++;
							rightrotate(p);
							if(p.left==null&&p.right==null)
								p.rank=1;
							if(q!=root&&q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
							break;
						}else if(s==p.left&&ps-psr==1&&ps-psl!=1){
							flagg=1;
							p.rank=p.rank-2;
							s.rank--;
							(s.right).rank=(s.right).rank+2;
							this.totalcount=this.totalcount+2;
							leftrotate(s);
							rightrotate(p);
							if(q!=root&&q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
							break;
						}else if(s==p.right&&ps-psr==1){
							flagg=0;
							s.rank++;
							p.rank--;
							this.totalcount++;
							leftrotate(p);
							if(p.left==null&&p.right==null)
								p.rank=1;
							if(q!=root&&q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
							break;
						}else if(s==p.right&&ps-psl==1&&ps-psr!=1){
							flag=1;
							p.rank=p.rank-2;
							s.rank--;
							(s.left).rank=(s.left).rank+2;
							this.totalcount=this.totalcount+2;
							rightrotate(s);	
							leftrotate(p);
							if(q!=root&&q.rank==0){
							p=parent(q);
							//System.out.println(q+" q "+q.rank);
							if(p.left!=null&&p.left.equals(q))
								p.left=null;
							else
								p.right=null;
						}
							break;
						}
						//break;	
					}
				}
			}
			if(q.rank==0){
				p=parent(q);
				//System.out.println(q+" q "+q.rank);
				if(p.left!=null&&p.left.equals(q))
					p.left=null;
				else
					p.right=null;
				q=p;
				//System.out.println(q+" q "+q.rank);
			}
			else if(q!=root){		
				p=parent(q);
				q=p;
			}
			else
				break;
		}

	}*/

	public V get(K key){
		v.clear();
		inorder(root);
		//b.clear();
    	for(int i=0;i<v.size();i++){
    		if(v.get(i).key.compareTo(key)==0)
    			return v.get(i).value;
    	}
		// write your code her 
		return null;
	}
	Vector<node> v=new Vector<node>();

	public void inorder(node root) {
        if(root.left!=null)
           inorder(root.left);
        v.add(root);
        if(root.right!=null)
            inorder(root.right);
        else
            return;            
    }
    Vector<node> vv=new Vector<node>();
	Vector<V> valv=new Vector<V>();
	public Vector<V> searchRange(K key1, K key2){
		v.clear();
		inorder(root);
		vv.clear();
		valv.clear();
    	for(int i=0;i<v.size();i++){
    		if(v.get(i).key.compareTo(key1)>=0 && v.get(i).key.compareTo(key2)<=0)
    			vv.add(v.get(i));
    	}
    	for(int i=0;i<vv.size();i++)
    		valv.add(vv.get(i).value);
    	if(valv.size()>0)
    		return valv;
    	else
    		return null;
	}

	public int rotateCount(){
		// write your code her 
		int t=this.rotate;
		return t;
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

	public int getHeight(){
		int ht=height(root);
		// write your code her 
		return ht;
	}

	Vector<K> bf=new Vector<K>();
	
	public void level(node root,int level){
 		if(level>1){
            if(root.left!=null)
 			    level(root.left,level-1);
 			if(root.right!=null)
                level(root.right,level-1);
            else
                return;
                
 		}
 		else if(level==1){
 			//if(root.rank>0){
 				bf.add(root.key);
 				//System.out.println(root.key+" key "+root.rank+" rank ");
 		//}
 	}
 	}
	public Vector<K> BFS(){
		bf.clear();
		int ht=height(root);
		for(int i=1;i<=ht;i++){
    		level(root,i);


		}
		// write your code her 
		return bf;
	}
}
