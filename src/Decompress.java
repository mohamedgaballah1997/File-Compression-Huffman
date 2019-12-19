import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Decompress {
	public Decompress(String inputPath,String outputPath) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(inputPath));
		Node root=readCodes(br);
		inorder(root,"");
		output(br,root,outputPath);
	}
	
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
	while(str!=null) {
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
	return root;
	}
	
	public static void inorder(Node root,String str) {
		if(root==null) return;
		inorder(root.left,str.concat("0"));
		if(root.value!=null)
		System.out.println(root.value+"  "+str);
		inorder(root.right,str.concat("1"));
	}
	public static String output(BufferedReader br,Node root,String outputPath) throws IOException {
		String str="";
		int n;
		n=br.read();
		BufferedWriter bw=new BufferedWriter(new FileWriter(outputPath));
		while(n!=-1) {
			int n2=br.read();
			String temp=String.format("%7s", Integer.toBinaryString(n)).replace(' ', '0');
			str=str.concat(temp);
		//	System.out.print(temp);
			 if(n2==-1) {
				 // n final char , shil el padding
				 str=str.substring(0,str.length()-root.freq);
				 decode(bw,root,root,str,0,0);
				 break;
			 }
			int index=decode (bw,root,root, str,0,0);
		//	System.out.println("++"+index);
			if(index<str.length())
				str=str.substring(index+1);
			else
				str="";
			
			n=n2;
		}
		bw.close();
		return str;
	}
	public static int decode(BufferedWriter bw,Node root,Node rootTemp,String str,int i,int lastindex) throws IOException {	
		if(rootTemp.left==null) {
			if(rootTemp.value=='\n')
				bw.write(System.getProperty( "line.separator" ));
			else
				bw.write(rootTemp.value);
			System.out.print(rootTemp.value);
			return decode(bw,root,root,str,i,i-1);
		}
		
		if(i==str.length()) return lastindex;
		else if(str.charAt(i)=='0') {
			return decode(bw,root,rootTemp.left,str,i+1,lastindex);
		}
		else
			return decode(bw,root,rootTemp.right,str,i+1,lastindex);

	}


}
