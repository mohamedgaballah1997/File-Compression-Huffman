import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Compress {
	
	public Compress(String inputPath,String outputPath) throws IOException {
		HashMap<Integer,Integer> hm=readFrequencies(inputPath);

		Node root=buildTree(hm);
		if(root==null) {
			System.out.println("Empty File");
			return;
		}
		HashMap<Integer, String> codes=getCodes(root);
		
		DataOutputStream writer= new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputPath)));
		
		writeTree(writer, root);
	
		writefile(writer,inputPath, codes);
		writer.close();
	}
	public HashMap<Integer, Integer> readFrequencies(String path) throws IOException{
		DataInputStream br = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
		HashMap<Integer, Integer> hm=new HashMap<>();
		int n;
		n=br.read();
		while(n!=-1) {
			if(!hm.containsKey( n))
				hm.put( n, 1);
			else
				hm.put(n,hm.get(n)+1);
			n=br.read();
		}
		br.close();
	return hm;
	}
	
	public Node buildTree(HashMap<Integer,Integer> hm) {
		PriorityQueue<Node> heap=new PriorityQueue<>();
		for (Map.Entry<Integer,Integer> entry : hm.entrySet())  {
			Node node=new Node(entry.getValue(), entry.getKey());
			heap.add(node);
		}
		while(heap.size()>1) {
			Node n1=heap.poll();
			Node n2=heap.poll();
			Node n3=new Node(n1.freq+n2.freq, null);
			n3.left=n1;
			n3.right=n2;
			heap.add(n3);
		}
		return heap.poll();
	}
	
	public HashMap<Integer, String> getCodes(Node root){
		HashMap<Integer, String> codes=new HashMap<>();
		assign(codes, root, "");
		return codes;
	}
	public static void assign(HashMap<Integer, String> hm,Node root,String code) {
		if(root.left==null) {
			if(code.equals("")) code="0"; // handle only one char in all file
			hm.put(root.value, code);
		}
		else {
		assign(hm, root.left, code.concat("0"));
		assign(hm, root.right, code.concat("1"));
		}
	}
	public static void writefile( DataOutputStream writer,String path,HashMap<Integer, String> codes) throws IOException {
		
		DataInputStream br = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
		StringBuilder str=new StringBuilder("");
		int n;
		n=br.read();
		while(n!=-1) {
			
			str=str.append(codes.get(n));
			n=br.read();
		}
		Integer padding=(8-str.length()%8)%8;
		for(int i=0;i<padding;i++) {
			str=str.append("0");
		}
		writer.write(padding);
		for(int i=0;i<str.length()/8;i++) {
			int x=Integer.parseInt(str.substring(i*8,i*8+8),2);
			writer.write(x);
		}
		br.close();
		
	}
	public static void writeTree( DataOutputStream writer,Node root) throws IOException {

		if(root.left==null) {
				writer.write(1) ;
				writer.write(root.value);
				
				return;
			}
		else {
			writer.write(0);
		}
		writeTree(writer, root.left);
		writeTree(writer, root.right); 
	}
	

	
}
