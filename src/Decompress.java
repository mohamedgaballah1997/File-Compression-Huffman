import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Decompress {
	
	public static void buildNode(Node root,String code,int index,Character c) {
		if(index==code.length()-1) {
			if(code.charAt(index)=='1') root.right=new Node(0, c);
			else root.left=new Node(0, c);
			return;
		}
		if(code.charAt(index)=='1') {
			if(root.right==null) root.right=new Node(0, null);
			buildNode(root.right, code, index+1, c);
		}
		else {
			if(root.left==null) root.left=new Node(0, null);
			buildNode(root.left, code, index+1, c);
		}
		
	}
	
	public static Node readCodes(BufferedReader br) throws IOException
	{
	Node root=new Node(0, null);
	int padding=0;
	String str=br.readLine();
	while(br!=null) {
		System.out.println(str);
		if(str.length()==1 && !str.equals("")) {
			padding=Integer.parseInt(str);
			break;
		}
		if(str.isEmpty())
			buildNode(root,br.readLine().split(" ")[1], 0,'\n');
			
		else 
			buildNode(root,str.substring(2), 0,str.charAt(0));
	str=br.readLine();
	}
	root.freq=padding;
	System.out.println("++++++++"+padding);
	return root;
	}
	
	public static void inorder(Node root,String str) {
		if(root==null) return;
		inorder(root.left,str.concat("0"));
		if(root.value!=null)
		System.out.println((int)root.value+"  "+str);
		inorder(root.right,str.concat("1"));
	}
	public static String output(String l,Node root) {
		String str="";
		String out="";
	//	l=l.substring(0,l.length()-root.freq);
		for(int i=0;i<l.length();i++) {
			int x=(int) l.charAt(i);
			str=str.concat(Integer.toBinaryString(x));
		}
		str=str.substring(0,str.length()-root.freq);
		System.out.println(str);
		return out;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("output.txt"));
		Node root=readCodes(br);
		inorder(root,"");
		output(br.readLine(),root);

	}

}
