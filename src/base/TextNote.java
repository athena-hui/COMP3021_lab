package base;
//LAB 4-Task 3
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
//LAB 4-Task 4
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note{
	private static final long serialVersionUID = 1L;//LAB 4-Task 3
	private String contents;
	
	//LAB 4-Task 3
	public TextNote(File f) throws FileNotFoundException, IOException {
		super(f.getName());
		this.contents = getTextFromFile(f.getAbsolutePath());
	}
	
	//LAB 4-Task 3
	private String getTextFromFile(String absolutePath){
		String result = "";
		String each_line = "";
		try {
			File filename = new File(absolutePath);
			InputStreamReader input_reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader buffer_reader = new BufferedReader(input_reader);
			while ((each_line = buffer_reader.readLine()) != null) {
				result += each_line;
			}
			buffer_reader.close();
		} catch (Exception except) {
			except.printStackTrace();
		}
		return result;
	}
	
	//LAB4 4-Task 4
	public void exportTextToFile(String pathFolder){ 
		try {
			if (pathFolder == "") {
				pathFolder = ".";
			}
			File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ",  "_") +".txt");
			FileWriter file_writer = new FileWriter(file);
			BufferedWriter buffer_writer = new BufferedWriter(file_writer);      
	        buffer_writer.write(this.getContent());
	        buffer_writer.close();
	        file_writer.close();
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	public TextNote(String title){
		super(title);
	}
	
	public TextNote(String title,String content) {
		super(title);
		contents = content;
	}
	
	//add by me
	public String getContent() {
		return contents;
	}
	
	public void setContents(String content) {
		contents = content;
	}
}