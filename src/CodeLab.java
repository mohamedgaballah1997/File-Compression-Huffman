import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CodeLab {

	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		//char b='\n';
		//b=sc.next().charAt(0);
		//System.out.println(b);
		String b = "0001101";
		char c='\r';
		int x=(int) c;
		 x=9;
		//System.out.println((char)x);
		//System.out.println(Integer.toBinaryString(x));
		//System.out.println((char)Integer.parseInt(b,2));
	//	byte[] bval = new BigInteger(b, 2).toByteArray();
	//	System.out.println(Arrays.toString(bval));
	//	System.out.println(b.substring(0,b.length()-3));
	//	new Compress("input.txt");
		 String str="jskfksg";
//		 System.out.println(str.substring(2));
new Compress("input.txt");
		BufferedReader br=null; 
		
		br = new BufferedReader(new FileReader("output.txt"));
		HashMap<Character, Integer> hm=new HashMap<>();
		int n;
		n=br.read();
		while(n!=-1) {
			
			System.out.println(n);
			n=br.read();
		}
		
		
		}
	}

