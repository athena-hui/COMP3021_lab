package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Folder implements Comparable<Folder>, java.io.Serializable{
//LAB 4-Task 1
//public class Folder implements Comparable<Folder>{
	//public ArrayList<Note> notes;
	//public String name;
	private static final long serialVersionUID = 1L;//LAB 4-Task 1
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note note : notes) {
			if (note instanceof TextNote) {
				nText += 1;
			}
			if (note instanceof ImageNote) {
				nImage += 1;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Folder o) {
		// TODO Auto-generated method stub
		if (name.compareTo(o.name) > 0) {
			return 1;
		}else if (name.compareTo(o.name) < 0) {
			return -1;
		}else {
			return 0;
		}
	}

	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> result = new ArrayList<Note>();
		String[] keys = keywords.split(" ", 0);
		if (keywords.toLowerCase().contains("or")) {
			for(Note n : notes) {
				int add = 0;
				if ((n.getTitle().matches("(?i).*"+keys[0]+ ".*") || n.getTitle().matches("(?i).*"+keys[2]+ ".*"))
					&& (n.getTitle().matches("(?i).*"+keys[3]+ ".*") || n.getTitle().matches("(?i).*"+keys[5]+ ".*")))
				{
					result.add(n);
					add = 1;
				}
				if ((n instanceof TextNote) && (add == 0)){
					String content = ((TextNote)n).getContent();
					if ((content.matches("(?i).*"+keys[0]+ ".*") || content.matches("(?i).*"+keys[2]+ ".*"))
						&& (content.matches("(?i).*"+keys[3]+ ".*") || content.matches("(?i).*"+keys[5]+ ".*"))) {
						result.add(n);
					}
				}
				
			}
		}else { //Lab7- Task 5
			for(Note n : notes) {
				for (String key: keys) {
					if (n.getTitle().contains(key.toLowerCase())) {
						result.add(n);
						break;
					}
					if ((n instanceof TextNote)) {
						String content = ((TextNote)n).getContent().toLowerCase();
						if (content.contains(key.toLowerCase())) {
							result.add(n);
							break;
						}
					}
				}
			}
			
		}
		return result;
	}
	
	public boolean removeNotes(String title) {
		// TODO
		// Given the title of the note, delete it from the folder.
		// Return true if it is deleted successfully, otherwise return false.
		int index = 0;
		int deleted = 0;
		for(Note n: notes) {
			if (n.getTitle() == title) {
				notes.remove(index);
				deleted = 1;
				break;
			}
			index++;
		}
		if (deleted == 1) {
			return true;
		}
		return false;
	}
}