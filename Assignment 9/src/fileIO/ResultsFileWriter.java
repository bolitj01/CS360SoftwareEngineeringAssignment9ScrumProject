/**
 * 
 */
package fileIO;


import java.io.*;

import model.ScreenResults;

/**
 * @author Donovan Laptop-4k
 *
 */
public class ResultsFileWriter {
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private File dir;
	
	/**
	 * Constructor of class
	 */
	public ResultsFileWriter(String filename)
	{
		try{
			dir = new File("ScreenResultsHistory");
			boolean success = dir.mkdir();
			if (success){
				System.out.println("Made directory");
				System.out.println(dir.getAbsolutePath());
			}
			fos = new FileOutputStream(dir.getAbsolutePath() + File.separator + filename);
			oos = new ObjectOutputStream(fos);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveScreenResults(ScreenResults screenResults){
		try{
			oos.writeObject(screenResults);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void closeStreams(){
		try{
			if (fos != null){
				fos.close();
			}
			if (oos != null){
				oos.close();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
