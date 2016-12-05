/**
 * 
 */
package HealthScreener;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Donovan Laptop-4k
 *
 */
public class ResultsFileWriter {
	private File file;
	private PrintWriter writer;
	
	private static ResultsFileWriter instance = null;
	
	/**
	 * Constructor of class
	 */
	public ResultsFileWriter()
	{
		
		
	}
	
	
	/**
	 * Opens the files to be written to
	 * @param fileName - string of the file name/ file address if not in root
	 * @return - true or false if the file was opened
	 */
	
	public boolean openFile(String fileName)
	{

		try {
			file = new File(fileName);
			if(!file.exists())
			{
				file.createNewFile();
			}
			writer = new PrintWriter(file);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * writes the passed in string to the file
	 * @param message - message to be written to the file
	 * @return - if the text was successfully written to the file
	 */
	public boolean printToFile(String message)
	{
		if(writer == null)
		{
			System.out.println("Please call open before calling print");
			return false;
		}
		
		writer.println(message);
		return true;
	}
	
	/**
	 * Closes the file
	 * @return - returns true or false if the file was closed
	 */
	public boolean closeFile()
	{
		if(writer == null)
		{
			System.out.println("Please call open before calling close");
			return false;
		}
		
		writer.close();
		return true;
	}
}
