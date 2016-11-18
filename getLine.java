import java.io.*;
import java.util.*;

public class getLine {

	public static String getWord(int difficulty) {

		String[] dict = {"dictfile.txt",
						"easydict.txt",
						"meddict.txt",
						"interdict.txt",
						"harddict.txt",
						"expertdict.txt"};

		try{

				// Current file contains 4308 words, if you get a new file, uncomment the commented code and run it to get new length quickly.
			int max = 4308;
				// Min value because duh.
			int min = 1;

			Random random = new Random();
				// Generate random number.
			int rand = random.nextInt(max - min) + min;

			//System.out.println(rand);
				// Get file
			FileInputStream fs = new FileInputStream(dict[difficulty]);
			DataInputStream in = new DataInputStream(fs);
			InputStreamReader reader = new InputStreamReader(in);
				// This one reads the rows.
			BufferedReader br = new BufferedReader(reader);
			
/*
				// Uncomment if length of the file is needed. If uncommented, str will not be printed. Dunno why.
				// Create a "reader" that checks the lines.
			LineNumberReader ln = new LineNumberReader(reader);
				// Skip lines until last row and get number.
			ln.skip(Long.MAX_VALUE);
			
			int getNum = ln.getLineNumber();
				// Print number as the length of the file.
			System.out.println(getNum);
			ln.close();
*/

			String str;
			int i = 0;
			
			while((str = br.readLine()) != null) {
				if(i == rand) {
			//	System.out.println(str);
					return str;
				}
				i++;
			}
			in.close();
		} 
		catch(IOException e) {
			// No exception
		}
		return null;
	}
}