//Assignment - 1
/*Create a java program to search through the home directory
 and look for files that match a regular expression*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
public class SearchForFile
{
	static int matchFiles = 0;
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
		String pat;
		String folderPath;
		boolean goFurther = true;
		String searchMore;
		while(goFurther)
		{
			System.out.println("Enter the path to the home directory");
			folderPath = sc.next();
			sc.nextLine(); // to skip previous \n character.
			System.out.println("Enter the pattern to be matched ");
			pat = sc.next();
			sc.nextLine();
			//System.out.println(pat);
			File fp = new File(folderPath);
			fileMatchRegExp(fp, pat);
			System.out.println("Do you want to search more files?(yes/no)");
			searchMore = sc.nextLine();
			if(searchMore.equalsIgnoreCase("yes"))
			{
				goFurther = true;
			}
			else
			{
				goFurther = false;
			}
		}
    }
	public static void fileMatchRegExp(File fp, String pat) throws IOException
	{
		if (fp.isDirectory())
        {
			//if it is a directory
            File[] listOfFiles = fp.listFiles();
            if (listOfFiles.length >= 1)
			{
                for (File file : listOfFiles)
                {
                    if (file.isDirectory())
					{
						//call same method.
						String loc = file.getCanonicalPath().toString();
						//System.out.println(loc);
						File dir = new File(loc);
						fileMatchRegExp(dir,pat);
					}
					else 
					{
						String fileName = file.getName();
						//System.out.println(fileName);
						if(Pattern.matches(pat, fileName))
						{
							System.out.println(file.getCanonicalPath().toString());
						}
					}
                        
                }
            }
        }
		else
		{
			// if it is not directory if it is only a file
			String fileName = fp.getName();
			if(Pattern.matches(pat, fileName))
			{
				System.out.println(fp.getCanonicalPath().toString());
			}
		}
	}
}