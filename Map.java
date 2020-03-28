package col106.assignment4.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
//import col106.assignment4.HashMap;
//import col106.assignment4.WeakAVLMap;

public class Map<V> {
	static PrintStream out;
	public PrintStream fileout(){
		return out;
	}
	
	public Map() {
		// write your code here	
	}
	public void eval(String inputFileName, String outputFileName)throws IOException, FileNotFoundException{
		HashMap<String> hash = new HashMap<String>((int)1000001);
		WeakAVLMap<String,String> avl = new WeakAVLMap<String,String>();
		File file;
		file = new File(inputFileName);
		out = new PrintStream(new FileOutputStream(outputFileName, false), true);
		System.setOut(out);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String st = br.readLine().trim();
			int n= Integer.parseInt(st);
			long thashr=0;
			long tavlr=0;
			long thashp=0;
			long tavlp=0;
			long difference;
			long endTime;
			long beginTime;
			String str;
			Vector<String> v=new Vector<String>();

			for(int tc=0;tc<n;tc++){
				st=br.readLine().trim();
				v.add(st);
			}
			for(int i=0;i<v.size();i++){
				char pp=v.get(i).charAt(0);
				if(pp=='I'){
					str=v.get(i).substring(2,v.get(i).length());
					int j=0;
					String kk=new String();
					char ppp=str.charAt(j);
					while(ppp!=','){
						kk=kk+str.charAt(j);
					
						j++;
						ppp=str.charAt(j);
					}
					int cnt=0;
					while(cnt<=j+1){
						str=str.substring(1,str.length());
						cnt++;
					}
					String val=str;
					beginTime = System.currentTimeMillis();
					hash.put(kk,val);
					endTime = System.currentTimeMillis();
					difference = endTime - beginTime;
					thashp=thashp+difference;
					beginTime = System.currentTimeMillis();
					avl.put(kk,val);
					endTime = System.currentTimeMillis();
					difference = endTime - beginTime;
					tavlp=tavlp+difference;

				}
				else{
					str=v.get(i).substring(2,v.get(i).length());
					String a=v.get(i);
					beginTime = System.currentTimeMillis();
					hash.remove(a);
					endTime = System.currentTimeMillis();
					difference = endTime - beginTime;
					thashr=thashr+difference;
					beginTime = System.currentTimeMillis();
					avl.remove(a);
					endTime = System.currentTimeMillis();
					difference = endTime - beginTime;
					tavlr=tavlr+difference;
				}
			}
			System.out.println("Operations" + "\t" + "WAVL" + "\t" + "HashMap");
			System.out.println("Insertions" + "\t" + tavlp + "\t" + "\t" + thashp);
			System.out.println("Deletions" + "\t" + tavlr + "\t" + "\t" + thashr);
		}catch (FileNotFoundException e) {
				System.err.println("Input file Not found. ");
		}catch (Exception e) {
					e.printStackTrace();
		} 
	}
	/*public static void main(String[] args) throws IOException{
		Map<String> map = new Map<String>();
		map.eval("map_input","map_output");
	}*/
}

