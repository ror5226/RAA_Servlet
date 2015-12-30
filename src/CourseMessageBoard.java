
public class CourseMessageBoard {

    private MessagePackage[]  messages = new MessagePackage [50];
	private String boardID;    
	
	//creates a new course message board
	public CourseMessageBoard(String boardID) {
		this.boardID = boardID;
	}

	//insert a username, message into the array
	public boolean insert(String username, String message){
		for(int i = 0; i < messages.length; i++){
			if(messages[i]== null){
				//creates a new Message Package object and inserts it into the message board array
				messages[i] = new MessagePackage(username, message);
				break;
			}
		}
		if(messages[messages.length-1] != null){
			for (int j = 0; j < (messages.length-1); j++){
				messages[j] = messages[j+1];
			}
			messages[messages.length-1] = new MessagePackage(username, message);
		}
		return false;
	}
	
	//returns array of usernames
	public String[] getUserNames(){
		String[] usernames = new String[50];
		int j = 0;
		for(int i = 0; i < messages.length; i++){
			if(messages[i] != null){
				usernames[j] = (String)messages[i].username;
			}
			else{
				usernames[j] = "blank";			}
			j++;

		}
		return usernames;
	}
	
	//returns array of messages
	public String[] getMessages(){
		String[] messageList = new String[50];
		int j = 0;
		for(int i = 0; i < messages.length; i++){
			if(messages[i] != null){
				messageList[j] = (String)messages[i].message;
			}
			else{
				messageList[j] = "blank";
			}
			j++;
		}
		return messageList;
	}
}
