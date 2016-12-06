package HealthScreener;

import java.io.*;
import java.util.ArrayList;

public class ResultsFileReader {
	private FileInputStream fis;
	private ObjectInputStream ois;
	private File dir;
		
	public ResultsFileReader() {
		dir = new File("ScreenResultsHistory");
		if (dir == null){
			System.out.println("No past screenings have been saved.");
		}
		System.out.println(dir.getAbsolutePath());
	}
	
	/**
	 * Reads the SreenResults object from the file with the given fileName
	 */
	public ScreenResults readScreenResults(String fileName)
	{
		ScreenResults screenResults = null;
		try {
			System.out.println(dir.getAbsolutePath() + "/" + fileName);
			fis = new FileInputStream(dir.getAbsolutePath() + "/" + fileName);
			ois = new ObjectInputStream(fis);
			screenResults = (ScreenResults)ois.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return screenResults;
	}
	
	/**
	 * returns all of the files names that contain the extension passed
	 * @param extension - the type of file that is looked for
	 * @return - an array of type String with all of the file names with the extension
	 */
	public String[] getFileNames(String extension)
	{
		
		String[] list = dir.list();
		
		ArrayList<String> legitimateFileNames = new ArrayList<>();
		
		for(String fileName: list)
		{	
			if(fileName.contains(extension))
			{
				legitimateFileNames.add(fileName.replace(extension, ""));
			}
			
		}
		
		String [] fileNames = new String[legitimateFileNames.size()];
		for (int i = 0; i < fileNames.length; i++){
			fileNames[i] = legitimateFileNames.get(i);
		}
		
		return fileNames;
	}
	
	/**
	 * Closes the file input stream and object input stream
	 */
	public void closeStreams()
	{
		try{
			if (fis != null){
				fis.close();
			}
			if (ois != null){
				ois.close();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
