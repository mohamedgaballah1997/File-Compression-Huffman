import java.io.File;
import java.io.FileNotFoundException;
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
	
	public static Node readCodes(Scanner sc) throws FileNotFoundException
	{
	Node root=new Node(0, null);
	int padding=0;
	while(sc.hasNext()) {
		String str=sc.nextLine();
		System.out.println(str);
		String[] arr=str.split(" ");
		if(arr.length==1 && !arr[0].equals("")) {
			padding=Integer.parseInt(arr[0]);
			break;
		}
		if(arr.length==1)
			buildNode(root,sc.nextLine().split(" ")[1], 0,'\n');
			
		else
			buildNode(root,arr[1], 0,arr[0].charAt(0));
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
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc=new Scanner(new File("output.txt"));
		Node root=readCodes(sc);
		inorder(root,"");
		output(sc.nextLine(),root);

	}

}
