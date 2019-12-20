import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class CodeLab {

	public static void main(String[] args) throws IOException  {
	/*	DataOutputStream bw= new DataOutputStream(new BufferedOutputStream(new FileOutputStream("test.txt")));
		String str="hids";
		bw.writeInt(str.length());
		bw.writeBytes(str);
		bw.close();
		DataInputStream br = new DataInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
		//int n=br.read();
	
		
		int n=br.readInt();
		System.out.println(n);
		byte[] b=new byte[n];
		br.read(b);
		String s=new String(b);
		System.out.println(s);  */
		new CompressFolder("test","test.txt");
		new DecompressFolder("test.txt","h");
			
		}
	}

