package base;

public class TextNote extends Note{
	private String contents;
	
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
}