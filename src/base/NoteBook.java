package base;
import java.io.FileInputStream;//LAB 4-Task 2
import java.io.FileOutputStream;//LAB 4-Task 1
import java.io.ObjectInputStream;//LAB 4-Task 2
import java.io.ObjectOutputStream;//LAB 4-Task 1
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//LAB 4-Task 1
class NoteBook implements java.io.Serializable {
//public classNoteBook {
	private static final long serialVersionUID = 1L;//LAB 4-Task 1
	private ArrayList<Folder> folders;
	//public ArrayList<Folder> folders;

	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for (Folder f1 : folders) {
			if (f1.getName() == folderName) {
				f = f1;
			}
		}
		
		if (f == null) {
			f = new Folder(folderName);
			folders.add(f);
		}
		
		for (Note n : f.getNotes()) {
			if (n.equals(note) == true) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		
		f.addNote(note);
		return true;
	}
	
	public void sortFolders() {
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> result = new ArrayList<Note>();
		for(Folder f: folders) {
			result.addAll(f.searchNotes(keywords));
		}
		return result;
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	//LAB 4-task 1
	public boolean save(String file){
		try {
			FileOutputStream file_output = new FileOutputStream(file);
			ObjectOutputStream object_output = new ObjectOutputStream(file_output);
			object_output.writeObject(this);
			
			object_output.close();
			file_output.close();
		} catch (Exception except) {
			except.printStackTrace();
			return false;
		}
		return true;
	}
	
	//LAB 4-task 2
	public NoteBook(String file) {
		FileInputStream file_input = null;
		ObjectInputStream object_input = null;
		try {
			file_input = new FileInputStream(file);
			object_input = new ObjectInputStream(file_input);
			NoteBook notebook_read = (NoteBook) object_input.readObject();
			this.folders = notebook_read.folders;
			
			object_input.close();
			file_input.close();
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	
}