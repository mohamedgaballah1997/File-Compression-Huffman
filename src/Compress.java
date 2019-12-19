import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Compress {
	public Compress(String path) {
		Scanner sc=null;
		try {
			sc = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		HashMap<Character, Integer> hm=new HashMap<>();
		int counter=0;
		while(sc.hasNext()) {
			counter++;
			String str=sc.nextLine();
			for(int i=0;i<str.length();i++) {
				if(hm.containsKey(str.charAt(i))) {
					hm.put(str.charAt(i),hm.get(str.charAt(i))+1);
				}
				else {
					
					hm.put(str.charAt(i), 1);
				} 
			}
		}
		if(counter>1)
			hm.put('\n',counter-1);
		
		PriorityQueue<Node> heap=new PriorityQueue<>();
		for (Map.Entry<Character,Integer> entry : hm.entrySet())  {
			Node n=new Node(entry.getValue(), entry.getKey());
			heap.add(n);
		}
		while(heap.size()>1) {
			Node n1=heap.poll();
			Node n2=heap.poll();
			Node n3=new Node(n1.freq+n2.freq, null);
			n3.left=n1;
			n3.right=n2;
			heap.add(n3);
		}
		Node root=heap.poll();
		HashMap<Character, String> codes=new HashMap<>();
		assign(codes, root, "");
		for (Map.Entry<Character,String> entry : codes.entrySet())  {
			System.out.println("Key = " + entry.getKey() + 
                    ", Value = " + entry.getValue()); 
		}
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("output.txt"));
		//	writeTree(writer, root);
			writeCodes(writer, root, codes);
			writefile(writer,new File("input.txt"), codes);
			 writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
        
	}

	public static void writefile(BufferedWriter writer,File file,HashMap<Character, String> codes) throws IOException {
		String str="";
		Scanner sc=new Scanner(file);
		while(sc.hasNext()) {
			String line=sc.nextLine();
			for(int i=0;i<line.length();i++) {
				str=str.concat(codes.get(line.charAt(i)));
			}
			if(codes.containsKey('\n'))
			str=str.concat(codes.get('\n'));
		} 
		Integer padding=(4-str.length()%4)%4;
		for(int i=0;i<padding;i++) {
			str=str.concat("0");
		}
		writer.write(padding.toString());
		writer.write(System.getProperty( "line.separator" ));
		System.out.println(str.length()/4);
		for(int i=0;i<str.length()/4;i++) {
			int x=Integer.parseInt(str.substring(i*4,i*4+4),16);
			writer.write(Integer.toHexString(x));
		//	System.out.print(c);
		}
		
	}
	public static void writeCodes( BufferedWriter writer,Node root,HashMap<Character, String> codes) {
	try {
		if(root.left==null) {
				writer.write(root.value + " "+codes.get(root.value));
				writer.write(System.getProperty( "line.separator" ));
			}
		else {
			writeCodes(writer, root.left,codes);
			writeCodes(writer, root.right,codes); 
		}
	}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeTree( BufferedWriter writer,Node root) {
	try {
		if(root==null) {
			writer.write("-2 ");
			return;
		}
		if(root.left==null) {
				writer.write(root.value + " ");
			} 
		else {
			writer.write("-1 ");
		}
		writeTree(writer, root.left);
		writeTree(writer, root.right); 
	}catch (IOException e) {
			e.printStackTrace();
		}
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
