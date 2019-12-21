import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DecompressFolder {
	public DecompressFolder(String inputPath,String FolderName) throws IOException {
		DataInputStream br= new DataInputStream(new BufferedInputStream(new FileInputStream(inputPath)));
		Node root=readTree(br);
		List<String> files=new ArrayList<String>();
		List<Integer> lengths=new ArrayList<Integer>();
		root.freq=readfileName(br, files, lengths,FolderName);
		output(br,root,files,lengths);
	}
	public static int readfileName(DataInputStream br,List<String> files,List<Integer> lengths,String FolderName) throws IOException {
		File f=new File(FolderName);
		f.mkdir();
		FolderName=FolderName.concat("/");
		int n=br.readInt();
		for(int i=0;i<n;i++) {
			int l=br.readInt();
			byte[] b=new byte[l];
			br.read(b);
			files.add(FolderName.concat(new String(b)));
			lengths.add(br.readInt());
		}
		return br.read();
		
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
	return root;
	}

	public static String output(DataInputStream br,Node root,List<String> files,List<Integer> lengths) throws IOException {
		String str="";
		int n;
		n=br.read();
		int fileIndex=0;
		int counter=0;
		DataOutputStream bw= new DataOutputStream(new BufferedOutputStream(new FileOutputStream(files.get(fileIndex))));
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
			while(index!=-1) {
			 counter++;
			 if(counter==lengths.get(fileIndex)) {
				 bw.close();
				bw= new DataOutputStream(new BufferedOutputStream(new FileOutputStream(files.get(++fileIndex))));
				counter=0;
					
			 }
			if(index<str.length())
				str=str.substring(index+1);
			else
				str="";
		
			index=decode (bw,root,root, str,0,-1);
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
					return i;
					}
				bw.write(rootTemp.value);
			return i-1;
		}
		
		if(i==str.length()) return lastindex;
		else if(str.charAt(i)=='0') {
			return decode(bw,root,rootTemp.left,str,i+1,lastindex);
		}
		else
			return decode(bw,root,rootTemp.right,str,i+1,lastindex);

	}

}
