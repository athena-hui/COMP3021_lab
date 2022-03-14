package base;

import java.util.Date;
import java.util.Objects;

//LAB 4-Task 1
public class Note implements Comparable<Note>, java.io.Serializable{
//public class Note implements Comparable<Note>{
	private static final long serialVersionUID = 1L;//LAB 4-Task 1
	private Date date;
	private String title;
	
	public Note(String title){
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	@Override
	public int compareTo(Note o) {
		// TODO Auto-generated method stub
		if (date.compareTo(o.date) > 0) {
			return 1;
		}else if (date.compareTo(o.date) < 0) {
			return -1;
		}else {
			return 0;
		}
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
}
