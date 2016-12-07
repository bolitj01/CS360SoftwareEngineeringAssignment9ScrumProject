package fileIO;


import java.io.*;

import model.ScreenResults;

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
	 * Creates directory if doesn't exist, and sets up file and object streams
	 */
	public ResultsFileWriter(String filename) {
		try{
			dir = new File("ScreenResultsHistory");
			dir.mkdir();
			
			fos = new FileOutputStream(dir.getAbsolutePath() + File.separator + filename);
			oos = new ObjectOutputStream(fos);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Save a ScreenResults object to the file fileName
	 */
	public void saveScreenResults(ScreenResults screenResults){
		try{
			oos.writeObject(screenResults);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the file and object streams
	 */
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
