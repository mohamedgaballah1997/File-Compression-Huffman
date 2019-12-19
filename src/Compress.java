import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Compress {
	
	public Compress(String inputPath,String outputPath) throws IOException {
		HashMap<Character,Integer> hm=readFrequencies(inputPath);
		Node root=buildTree(hm);
		HashMap<Character, String> codes=getCodes(root);
		BufferedWriter writer=new BufferedWriter(new FileWriter(outputPath));
		writeCodes(writer, root, codes);
		writefile(writer,inputPath, codes);
		writer.close();
	}
	public HashMap<Character, Integer> readFrequencies(String path) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader(path));
		HashMap<Character, Integer> hm=new HashMap<>();
		int n;
		n=br.read();
		while(n!=-1) {
			if(n==13) n=br.read(); //new line
			if(!hm.containsKey((char) n))
				hm.put((char) n, 1);
			else
				hm.put((char) n,hm.get((char) n)+1);
			n=br.read();
		}
	return hm;
	}
	
	public Node buildTree(HashMap<Character,Integer> hm) {
		PriorityQueue<Node> heap=new PriorityQueue<>();
		for (Map.Entry<Character,Integer> entry : hm.entrySet())  {
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
	public HashMap<Character, String> getCodes(Node root){
		HashMap<Character, String> codes=new HashMap<>();
		assign(codes, root, "");
		for (Map.Entry<Character,String> entry : codes.entrySet())  {
			System.out.println("Key = " + entry.getKey() + 
                    ", Value = " + entry.getValue()); 
		}
		return codes;
	}

	public static void writefile(BufferedWriter writer,String path,HashMap<Character, String> codes) throws IOException {
		String str="";
		BufferedReader br=new BufferedReader(new FileReader(path));
		
		int n;
		n=br.read();
		while(n!=-1) {
			if(n==13) n=br.read();
			str=str.concat(codes.get((char) n));
			n=br.read();
		}
		Integer padding=(7-str.length()%7)%7;
		for(int i=0;i<padding;i++) {
			str=str.concat("0");
		}
		writer.write(padding.toString());
		writer.write(System.getProperty( "line.separator" ));
		System.out.println(str);
		for(int i=0;i<str.length()/7;i++) {
			int x=Integer.parseInt(str.substring(i*7,i*7+7),2);
			writer.write((char) x);
		//	System.out.print(c);
		}
		
		
	}
	public static void writeCodes( BufferedWriter writer,Node root,HashMap<Character, String> codes) throws IOException {
		if(root.left==null) {
				writer.write(root.value + " "+codes.get(root.value));
				writer.write(System.getProperty( "line.separator" ));
			}
		else {
			writeCodes(writer, root.left,codes);
			writeCodes(writer, root.right,codes); 
		}
	}
	public static void writeTree( BufferedWriter writer,Node root) throws IOException {

		if(root==null) {
			writer.write("-2 ");
			
		}
		if(root.left==null) {
				writer.write(root.value + " ");
				return;
			}
		else {
			writer.write("-1 ");
		}
		writeTree(writer, root.left);
		writeTree(writer, root.right); 
	}
	
	public static void assign(HashMap<Character, String> hm,Node root,String code) {
		if(root.left==null) {
			if(code.equals("")) code="0"; // handle only one char in all file
			hm.put(root.value, code);
		}
		else {
		assign(hm, root.left, code.concat("0"));
		assign(hm, root.right, code.concat("1"));
		}
	}
	
}
