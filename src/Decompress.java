import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decompress {
	public Decompress(String inputPath,String outputPath) throws IOException {
		DataInputStream br= new DataInputStream(new BufferedInputStream(new FileInputStream(inputPath)));
		Node root=readTree(br);
	//	inorder(root,"");
		output(br,root,outputPath);
	}
	
	public static Node buildTree(DataInputStream br) throws IOException {
		int n=br.read();
		if(n==1) {
			return new Node(0, br.read());
		}
		else {
		Node temp=new Node(0, null);
		temp.left=buildTree(br);
		temp.right=buildTree(br);
		return temp;
		}
		
	}
	
	public static Node readTree(DataInputStream br) throws IOException
	{
	Node root=buildTree(br);
	root.freq=br.read();
	return root;
	}
	
	public static void inorder(Node root,String str) {
		if(root==null) return;
		inorder(root.left,str.concat("0"));
		if(root.value!=null)
		System.out.println(root.value+"  "+str);
		inorder(root.right,str.concat("1"));
	}
	public static String output(DataInputStream br,Node root,String outputPath) throws IOException {
		String str="";
		int n;
		n=br.read();
		DataOutputStream bw= new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputPath)));
		while(n!=-1) {
			int n2=br.read();
			String temp=String.format("%8s", Integer.toBinaryString(n)).replace(' ', '0');
			str=str.concat(temp);
			 if(n2==-1) {
				 // n final char , shil el padding
				 str=str.substring(0,str.length()-root.freq);
				 decode(bw,root,root,str,0,0);
				 break;
			 }
			int index=decode (bw,root,root, str,0,-1);
			if(index!=-1) {
			if(index<str.length())
				str=str.substring(index+1);
			else
				str="";
			}
			n=n2;
		}
		bw.close();
		return str;
	}
	public static int decode(DataOutputStream bw,Node root,Node rootTemp,String str,int i,int lastindex) throws IOException {	
		if(rootTemp.left==null) {
				
				if(rootTemp==root) { 
					if(i==str.length()) return lastindex;
					bw.write(rootTemp.value);
					return decode(bw,root,root,str,i+1,i);
					}
				bw.write(rootTemp.value);
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
