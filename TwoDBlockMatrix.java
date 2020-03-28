import java.util.*;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileNotFoundException;
class InverseDoesNotExistException extends Exception
	{
		public InverseDoesNotExistException(String s)
		{
			super(s);
		}
	}
class SubBlockNotFoundException extends Exception
	{
		public SubBlockNotFoundException(String s)
		{
			super(s);
		}
	}
class IncompatibleDimensionException extends Exception
	{
		public IncompatibleDimensionException(String s)
		{
			super(s);
		}
	}
public class TwoDBlockMatrix{

	public float[][] mainmatrix;

	public TwoDBlockMatrix(float[][] array){
		this.mainmatrix=array;
	}

	public static TwoDBlockMatrix buildTwoDBlockMatrix(java.io.InputStream in) throws FileNotFoundException{
		Scanner s=new Scanner(in);
		//str is taking complete as input string vector
		Vector<String> str=new Vector<String>();
		
		while(s.hasNextLine()){
			str.add(s.nextLine());
		}
	 	
		//index(int) is taking the indices which contain location of row,col
		Vector<Integer> index=new Vector<Integer>();
		index.add(0);
		
		for(int i=0;i<str.size();i++){
			if(str.get(i).equals("#")){
				if((i+1)<str.size())
					index.add(i+1);
			}
		}

		int[] row= new int[index.size()];
		int[] col= new int[index.size()];
		// in row and col array are stores the rowth and columnth value.

		for(int i=0;i<index.size();i++){
			String split[]= (str.get(index.get(i))).split("\\s+");
			row[i]=Integer.parseInt(split[0]);
			col[i]=Integer.parseInt(split[1]);
		}

		int[] roww= new int[index.size()];
		int[] coll= new int[index.size()];
		int max=roww[0];
		int max1=coll[0];

		for(int i=0;i<row.length;i++){
			int count=0;
			int k=1;
			String split1[]= (str.get(index.get(i)+1)).split("\\s+");
			coll[i]=split1.length+col[i];
			while(!(str.get(index.get(i)+k).equals("#"))){
				count++;
				k++;
			}
			roww[i]=row[i]+count;
		}
		
		for(int i=0;i<row.length;i++){
			if(roww[i]>max)
				max=roww[i]; 
			
			if(coll[i]>max1)
				max1=coll[i];
		}

		int numofcol=max1-1;
		int numofrow=max-1;

		float[][] matrix= new float[numofrow][numofcol];
		for(int i=0;i<numofrow;i++){
			for(int j=0;j<numofcol;j++){
				matrix[i][j]=0;
			}
		}

		for(int i=0;i<index.size();i++){
			int k=1;
			while(!(str.get(index.get(i)+k).equals("#"))){
				String split2[]= (str.get(index.get(i)+k)).split("\\s+");
				split2[split2.length-1]=split2[split2.length-1].substring(0,(split2[split2.length-1].length()-1));
				for(int l=0;l<split2.length;l++){
					matrix[row[i]+k-2][col[i]+l-1]=Float.parseFloat(split2[l]);
				}
				k++;
			}
		}
 
		TwoDBlockMatrix input=new TwoDBlockMatrix(matrix);
		return input;
	}


	public TwoDBlockMatrix transpose(){
		float[][] transpose=new float[this.mainmatrix[0].length][this.mainmatrix.length];
		for(int i=0;i<this.mainmatrix[0].length;i++){
			for(int j=0;j<this.mainmatrix.length;j++){
				transpose[i][j]=this.mainmatrix[j][i];
			}
		}
		TwoDBlockMatrix trans=new TwoDBlockMatrix(transpose);
		return trans;
	}

	public TwoDBlockMatrix multiply(TwoDBlockMatrix other) throws IncompatibleDimensionException{

			float[][] product=new float[this.mainmatrix.length][other.mainmatrix[0].length];
			if(this.mainmatrix[0].length==other.mainmatrix.length){
				for(int i=0;i<this.mainmatrix.length;i++){
					for(int j=0;j<other.mainmatrix[0].length;j++){
						product[i][j]=0;
						for(int k=0;k<this.mainmatrix[0].length;k++){
							product[i][j]=product[i][j]+this.mainmatrix[i][k]*other.mainmatrix[k][j];
						}
					}
				}
				TwoDBlockMatrix pro=new TwoDBlockMatrix(product);
				return pro;
			}
			else
				throw new IncompatibleDimensionException("IncompatibleDimension");
		

	}

	public TwoDBlockMatrix getSubBlock(int row_start, int col_start, int row_end, int col_end) throws SubBlockNotFoundException{
		int n=this.mainmatrix.length;
		int m=this.mainmatrix[0].length;
		float[][] subblock=new float[row_end-row_start][col_end-col_start];
		
			if((row_start<row_end)&&(col_start<col_end)&&(0<row_start) && (row_start<=n) && (0<col_start) && (col_start<=m) && (0<row_end) && (row_end<=n) && (0<col_end) && (col_end<=m)){
				for(int i=row_start;i<row_end;i++){
					for(int j=col_start;j<col_end;j++)
						subblock[i-row_start][j-col_start]=this.mainmatrix[i-1][j-1];
				}
				TwoDBlockMatrix sub=new TwoDBlockMatrix(subblock);
				return sub;
			}
			else
				throw new SubBlockNotFoundException("SubBlockNotFound");
				
		
	}

public TwoDBlockMatrix inverse() throws InverseDoesNotExistException{
				float[][] inverse=new float[this.mainmatrix.length][this.mainmatrix.length];
				 if(this.mainmatrix.length==this.mainmatrix[0].length){

						int nn=this.mainmatrix.length;
						float[][] matrix=new float[nn][nn];
						for(int i=0;i<nn;i++){
							for(int j=0;j<nn;j++){
								matrix[i][j]=this.mainmatrix[i][j];
							}
						}
						float det=determinant(this.mainmatrix,nn);
						
						if(Math.abs(det)!=0){
							float[][] matrix2=new float[nn][nn];
							for(int dum=0;dum<nn;dum++){
								for(int dumm=0;dumm<nn;dumm++)
									matrix2[dum][dumm]=0;
							}
							for(int dum=0;dum<nn;dum++)
								matrix2[dum][dum]=1;
							int lw=0;
							int qw=0;
							 
							for (int i = 0; i < nn; i++){
								for (int t = i + 1; t < nn; t++){
									if (matrix[i][i] < matrix[t][i]){ 
										for (int ff = 0; ff < nn; ff++){
											float ss = matrix[i][ff];
											matrix[i][ff] = matrix[t][ff]; 
											matrix[t][ff] = ss;
											float st = matrix2[i][ff]; 
											matrix2[i][ff] = matrix2[t][ff];
											matrix2[t][ff] = st;
										}
									}
								}
								float pivot = matrix[i][i];     
								for (int j = i + 1; j < nn; j++){
									float q = matrix[j][i];
									for (int k = 0; k < nn; k++){
									matrix[j][k] =(-1 * (q / pivot) * matrix[i][k]) + matrix[j][k];
									matrix2[j][k] =(-1 * (q / pivot) * matrix2[i][k]) + matrix2[j][k];
									} 
								}
							}
							for (int g = 0; g < nn; g++){
								float a=matrix[g][g];    
								for (int d = 0; d < nn; d++){
							 		matrix[g][d] = (matrix[g][d] / a);
									matrix2[g][d] = (matrix2[g][d] /a);
								}
							}

							for (int w = nn - 1; w >= 0; w--){
								float pivot1 = matrix[w][w];     
								for (int z = w - 1; z >= 0; z--){
								float q1 = matrix[z][w];
									for (int v = nn - 1; v >= 0; v--){   
										matrix[z][v] =(-1 * (q1 / pivot1) * matrix[w][v]) + matrix[z][v];
										matrix2[z][v]=(-1 * (q1 / pivot1) * matrix2[w][v]) + matrix2[z][v];
							   		}
								}
							}
							TwoDBlockMatrix inv=new TwoDBlockMatrix(matrix2);
							return inv;
						}
						else
							throw new InverseDoesNotExistException("Inverse dont exist");
					}
					else
						throw new InverseDoesNotExistException("Inverse dont exist");
				

	}
					public float determinant( float matrix [][], int n) {   
					   float det = 0;
					   float[][] A = new float[n][n];
					   if (n == 2)
					      return ((matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]));
					   else {
					      for (int p = 0; p < n; p++) {
					            int cnt = 0; 
					            for (int i = 1; i < n; i++) {
					               int cntt = 0;
					               for (int j = 0; j < n; j++) {
					                  if (j == p)
					                  continue;
					                  A[cnt][cntt] = matrix[i][j];
					                  cntt++;
					               }
					               cnt++;
					            }
					            int a = 1;
					            if(p%2==0)
					            	det = det + (a * matrix[0][p] * determinant( A, n - 1 ));
					            else
					            	det = det + (-a * matrix[0][p] * determinant( A, n - 1 ));
					      }
					   }
					   return det;
					}

	public String toString(){
		int len=this.mainmatrix.length;
		int bre=this.mainmatrix[0].length;
		float[][] matrix=new float[len][bre];
			for(int i=0;i<len;i++){
				for(int j=0;j<bre;j++)
					matrix[i][j]=this.mainmatrix[i][j];
			}
		DecimalFormat format2dec = new DecimalFormat("0.00");
		
		Vector<Integer> cnt=new Vector<Integer>();
		int r=0;
		int c=0;
		int cc=0;
		String strg=new String();
		for(int i=0;i<len;i++){
			//System.out.println(i+"ii");
			for(int j=0;j<bre;j++){
				if(matrix[i][j]!=0){


					
					r=i;
					c=j;
					
					

					if(j+1<bre){
					for(int k=j+1;k<bre;k++){
						if(matrix[i][k]==0){
							cc=k;
							break;
						}
						else
							cc=bre;
					}
					
					
					int cot=0;

					for(int ll=c;ll<cc;ll++){
						
						for(int k=i;k<len;k++){
							if(matrix[k][ll]!=0)
								cot++;	
							else
								break;
						}
						
						cnt.add(cot);
						cot=0;
					}
					
					int min=cnt.get(0);
					
					for(int k=0;k<cnt.size();k++){
						if(cnt.get(k)<min)
							min=cnt.get(k);

					}
					int sie=cnt.size();
					cnt.clear();
					

					
					String stttr=Integer.toString(r+1);
					String stttc=Integer.toString(c+1);

					strg=strg+stttr+" "+stttc+"\n";
	
					for(int l=i;l<i+min;l++){
						for(int k=c;k<cc;k++){
							String stt=format2dec.format(matrix[l][k]);
							strg=strg+stt+" ";
							
						}
						
						strg=strg.substring(0,strg.length()-1);
						strg=strg+";\n";
					}
					strg=strg+"#\n";
				

					for(int l=i;l<min+i;l++){
						for(int k=c;k<cc;k++){
							matrix[l][k]=0;
							
						}	
					}	
					}
					else{
						int cop=0;
						int ll=i;
						int mm=j;

						for(int k=i;k<len;k++){

							
							if(matrix[k][j]!=0){
								
								cop++;
							}
							else
								break;
						}
								String stttr=Integer.toString(ll+1);
								String stttc=Integer.toString(mm+1);
								strg=strg+stttr+" "+stttc+"\n";

						for(int k=i;k<i+cop;k++){
								
								
								String stt=format2dec.format(matrix[k][j]);

								strg=strg+ stt+";\n";
								matrix[k][j]=0;

						}
							
						strg=strg+"#\n";
					}
				
			}
			else
				continue;
				
			}
			
		}

		return(strg);
	

}

}
