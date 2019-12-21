import java.io.IOException;

public class CodeLab {

	public static void main(String[] args) throws IOException {

		 new Compress("input.txt","compressed.txt");
		 new Decompress("compressed.txt", "decompressed.txt");

		}
	}

