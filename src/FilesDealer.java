import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;


public class FilesDealer {
	
	public FilesDealer() {}
	
	
public void getFileName() throws IOException {
	JFileChooser f = new JFileChooser();
	StringBuilder sb = new StringBuilder();
	if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	    String file = f.getSelectedFile().toString();
	    String compressed = f.getSelectedFile().getParent().toString();
	    compressed = compressed + "\\compressedfile.txt";
	    System.out.println("Selected File path is "+file);
	    System.out.println("Compressed File path is: "+compressed);
	    new Compress(file, compressed);
	    //System.out.println("Selected Folder is "+folder);
		compressRatio(file, compressed,false);
		// print the table needed
		// print the execution time
	    
		  }
		  else{
		   sb.append("No file was selected");
		  }
   
}


public void getFilesNames() throws IOException {
	JFileChooser f = new JFileChooser();
	StringBuilder sb = new StringBuilder();
	f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
	if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	    String folder = f.getSelectedFile().toString();
	    String compressed = f.getSelectedFile().getParent().toString();
	    compressed = compressed + "\\compressedfolder.txt";
	    new CompressFolder(folder, compressed);
	    System.out.println("Selected Folder is "+folder);
		compressRatio(folder, compressed,true);
		// print the table needed
		// print the execution time
		  }
		  else{
		   sb.append("No folder was selected");
		  }
   
}
public static void compressRatio(String path2, String path1, boolean isFolder) throws IOException 
{
	//double size1 = (double)Files.size(new File(path1).toPath());
	//double size2 = (double)Files.size(new File(path2).toPath());
	System.out.println();
	File file1 = new File(path1);
	File file2 = new File(path2);
	double ratio;
	if(isFolder) 
	{
    double foldersize = folderSize(file2);
    System.out.println("Compressed file: "+file1.getName()+" Size: "+file1.length());
	System.out.println("Input file: "+file2.getName()+" Size: "+foldersize);
	ratio =  (file1.length())*1.0/foldersize;
	}
	else 
	{
		System.out.println("Compressed file: "+file1.getName()+" Size: "+file1.length());
		System.out.println("Input file: "+file2.getName()+" Size: "+file2.length());
		ratio =  (file1.length())*1.0/file2.length();
	}
	
	//System.out.println("Input file: "+file2.getName()+" Size: "+size2);
	 
	ratio = ratio*100;
	ratio = Math.floor(ratio);
	ratio = ratio/100;
	System.out.println("Compression ratio is "+ratio);
}

public static long folderSize(File directory) {
    long length = 0;
    for (File file : directory.listFiles()) {
        if (file.isFile())
            length += file.length();
        else
            length += folderSize(file);
    }
    return length;
}

}