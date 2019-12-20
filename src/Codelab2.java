import java.io.IOException;

public class Codelab2 {
	public static void main(String[] args) throws IOException {
		new Compress("input.txt", "inputc.txt");
		new Decompress("inputc.txt", "inputd.txt");
	}
}
