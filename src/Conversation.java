
public class Conversation {
	
	private String otherUser;
	private String[] convo = new String[100];
	
	//initialize conversation
	public Conversation(String otherUser){
		this.otherUser = otherUser;
		//this.otherName = name;
	}
	//insert a username, message into the array
	public void insert(String username, String message){
		
		if(message != null){
		for(int i = 0; i < convo.length; i++){
			if(convo[i]== null){
				convo[i] = (username + ": " + message);
				break;
			}
			//if convo is full of messages
			if(convo[convo.length-1] != null){
				for (int j = 0; j < (convo.length-1); j++){
					convo[j] = convo[j+1];
				}
				convo[convo.length-1] = (username + ": " + message);
			}
		}	
		}else{
			convo[0] = (" ");
		}
	}
	//return string of messages in conversation
	public String[] getMessages(){
		return convo;
	}
	

	
}
