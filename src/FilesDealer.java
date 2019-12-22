import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;


public class FilesDealer {
	
	public FilesDealer() {}
	
	
public void getFileName(boolean isCompressed, boolean isFolder) throws IOException {
	JFileChooser f = new JFileChooser();
	StringBuilder sb = new StringBuilder();
	if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	    String file = f.getSelectedFile().toString();
	    String ext1 = getFileExtension(file);
	    System.out.println("extenstion is "+ext1);
	    String compressed = f.getSelectedFile().getParent().toString();
	    if(isCompressed && !isFolder) 
	    {
	    	compressed = compressed + "\\decompressedfile"+ ext1;
	    	System.out.println("Selected File path is "+file);
		    System.out.println("Decompressed File path is: "+compressed);
		    long startTime = System.nanoTime();
		    new Decompress(file, compressed);
		    long endTime = System.nanoTime();
		    System.out.println("Execution Time: "+ (endTime-startTime)/1000000 + "  ms \n");
	    }  
	    else if(isFolder && isCompressed) 
	    {
		    	compressed = compressed + "\\decompressedfolder";
		    	System.out.println("Selected Compressed Folder path is "+file);
			    System.out.println("Decompressed Folder path is: "+compressed);
			    long startTime = System.nanoTime();
			    new DecompressFolder(file, compressed);
			    long endTime = System.nanoTime();
			    System.out.println("Execution Time: "+ (endTime-startTime)/1000000 + "  ms \n"); 
	    }
	    else {
	    compressed = compressed + "\\compressedfile"+ ext1;
	    System.out.println("Selected File path is "+file);
	    System.out.println("Compressed File path is: "+compressed);
	    //start time from here:    
	    long startTime = System.nanoTime();
	    new Compress(file, compressed);
	    long endTime = System.nanoTime();
	    if(new File(file).length()!=0) {
		compressRatio(file, compressed,false);
		System.out.println("Execution Time: "+ (endTime-startTime)/1000000 + "  ms \n");
	    }
	    }
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
	    System.out.println("Selected Folder path is "+folder);
	    System.out.println("Compressed Folder path is: "+compressed);
	    //start time from here:
	    long startTime = System.nanoTime();
	    new CompressFolder(folder, compressed);
	    long endTime = System.nanoTime();
	    if(new File(folder).listFiles().length!=0) {
	    compressRatio(folder, compressed,true);
	    System.out.println("Execution Time: "+ (endTime-startTime)/1000000 + "  ms\n");
	    }
	    }
	else{
	   sb.append("No folder was selected");
    }
   
}
public static void compressRatio(String path2, String path1, boolean isFolder) throws IOException 
{
	System.out.println();
	File file1 = new File(path1);
	File file2 = new File(path2);
	double ratio;
	if(isFolder) 
	{
    double foldersize = folderSize(file2);
	System.out.println("Input file: "+file2.getName()+" Size: "+foldersize);
	System.out.println("Compressed file: "+file1.getName()+" Size: "+file1.length());
	ratio =  (file1.length())*1.0/foldersize;
	}
	else 
	{
		
		System.out.println("Input file: "+file2.getName()+" Size: "+file2.length());
		System.out.println("Compressed file: "+file1.getName()+" Size: "+file1.length());
		ratio =  (file1.length())*1.0/file2.length();
	}
	
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

public String getFileExtension(String name) {
    int lastIndexOf = name.lastIndexOf(".");
    if (lastIndexOf == -1) {
        return ""; // empty extension
    }
    return name.substring(lastIndexOf);
}


}