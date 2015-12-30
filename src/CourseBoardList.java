import java.util.HashMap;

public class CourseBoardList {

	//hashmap of course message boards
	private HashMap<String, CourseMessageBoard> courseBoards = new HashMap<String, CourseMessageBoard>();
	
	//add a new class messages
	public void addClass(String boardID){
		 courseBoards.put(boardID, new CourseMessageBoard(boardID));
	}
	
	//returns course board object based off ID
	public CourseMessageBoard getBoard(String boardID){
		return courseBoards.get(boardID);
	}
}
