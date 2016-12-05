package HealthScreener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResultsFileReader {
	private File file;
	private Scanner reader;
		
	public ResultsFileReader()
	{
		
	}
	
	/**
	 * Opens the file that is to be read from
	 * @param fileName - string name of the file/ location if the file is address is not the root
	 * @return - returns true or false if the read file was opened
	 */
	public boolean openFile(String fileName)
	{
		try {
			file = new File(fileName);
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Reads data from the file
	 * @return - returns the string data that was read from the file
	 */
	public String readFile()
	{
		String message = "";
		if(reader == null)
		{
			System.out.println("Please call open before calling read");
			return "";
		}
		
		while(reader.hasNextLine())
		{
			message += reader.nextLine();
			message += "\n";
		}
		
		return message;
	}
	
	/**
	 * returns all of the files names that contain the extension passed
	 * @param extension - the type of file that is looked for
	 * @return - an ArrayList of type String with all of the file names with the extension
	 */
	public ArrayList<String> getFileNames(String extension)
	{
		
		ArrayList<String> fileNames = new ArrayList<String>();
		String workingDir = System.getProperty("user.dir");
		File folder = new File(workingDir);
		File [] files = folder.listFiles();
		
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].getName().contains(extension))
			{
				fileNames.add(files[i].getName().replace(extension, ""));
			}
			
		}
		
		return fileNames;
	}
	
	/**
	 * Closes teh file
	 * @return - returns true or false if the file was closed
	 */
	public boolean closeFile()
	{
		if(reader == null)
		{
			System.out.println("Please call open before calling close");
			return false;
		}
		reader.close();
		
		return true;
	}
}
