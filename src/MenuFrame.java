import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MenuFrame {	
	
   public static void main(String[] args) { 
	   
	  FilesDealer fd= new FilesDealer();
      JFrame f = new JFrame("A JFrame");
      f.setSize(523, 432);
      f.setLocation(300,200);
      f.getContentPane().setLayout(null);
      
      JLabel lblCompressionAndDecompression = new JLabel("Compression and Decompression");
      lblCompressionAndDecompression.setHorizontalAlignment(SwingConstants.CENTER);
      lblCompressionAndDecompression.setFont(new Font("Tahoma", Font.PLAIN, 20));
      lblCompressionAndDecompression.setBounds(104, 63, 303, 35);
      f.getContentPane().add(lblCompressionAndDecompression);
      
      JButton btnCompressAFile = new JButton("Compress a File");
      btnCompressAFile.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		 try {
				fd.getFileName(false,false);
				// 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
      	}
      });
      btnCompressAFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
      btnCompressAFile.setBounds(69, 195, 174, 46);
      f.getContentPane().add(btnCompressAFile);
      
      JButton btnCompressAFolder = new JButton("Compress a Folder");
      btnCompressAFolder.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		try {
				fd.getFilesNames();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      	}
      });
      btnCompressAFolder.setFont(new Font("Tahoma", Font.PLAIN, 15));
      btnCompressAFolder.setForeground(new Color(0, 0, 0));
      btnCompressAFolder.setBounds(69, 277, 174, 46);
      f.getContentPane().add(btnCompressAFolder);
      
      JButton btnDecompressAFile = new JButton("Decompress a File");
      btnDecompressAFile.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		try {
				fd.getFileName(true, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      	}
      });
      btnDecompressAFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
      btnDecompressAFile.setBounds(286, 195, 163, 46);
      f.getContentPane().add(btnDecompressAFile);
      
      JButton btnDecompressAFolder = new JButton("Decompress a Folder\r\n");
      btnDecompressAFolder.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		try {
				fd.getFileName(true, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      	}
      });
      btnDecompressAFolder.setFont(new Font("Tahoma", Font.PLAIN, 14));
      btnDecompressAFolder.setForeground(new Color(0, 0, 0));
      btnDecompressAFolder.setBounds(286, 277, 163, 46);
      f.getContentPane().add(btnDecompressAFolder);
      f.setVisible(true);
      
    }
}