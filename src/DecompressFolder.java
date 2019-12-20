import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DecompressFolder {
	public DecompressFolder(String inputPath,String FolderName) throws IOException {
		DataInputStream br= new DataInputStream(new BufferedInputStream(new FileInputStream(inputPath)));
		Node root=readTree(br);
		List<String> files=new ArrayList<String>();
		List<Integer> lengths=new ArrayList<Integer>();
		root.freq=readfileName(br, files, lengths);
		System.out.println(files);
		System.out.println(lengths);
	}
	public static int readfileName(DataInputStream br,List<String> files,List<Integer> lengths) throws IOException {
		int n=br.readInt();
		for(int i=0;i<n;i++) {
			int l=br.readInt();
			byte[] b=new byte[l];
			br.read(b);
			files.add(new String(b));
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
}
