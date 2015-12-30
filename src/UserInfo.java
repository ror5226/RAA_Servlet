import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserInfo {
	private String username, password;
	private String[] courses = new String[5];
	private String major, year, name;
	private HashMap <String, Conversation> inbox = new HashMap <String, Conversation>();
	
	//initialize user info obj (1 per user)
	public UserInfo(String username, String password){
		this.username = username;
		this.password = password;
		for (int i = 0; i < 5; i++){
			courses[i]= "Empty Course Board";
		}
	}
	//gets a new conversation
	public void newConversation(String username){
		inbox.put(username, new Conversation(username));
	}
	
	//gets the usernames of all people that a users is talking to 
	public ArrayList<String> getConversationNames(){
		ArrayList<String> names = new ArrayList<String>();
		Iterator it = inbox.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry convo = (Map.Entry)it.next();
	        names.add((String)convo.getKey());
	    }
		return names;
		
	}
	
	//retruns the user's inbox object
	public Conversation getConversation(String username){
		return inbox.get(username);
	}
	//return password
	String getPW(){
		return password;
	}
	//return courses
	public String[] getCourses(){
		return courses;
	}
	
	//setters to set user data name major year
	public void setName(String name) {
		this.name = name;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	//getters to get user data name major year
	public String getName() {
		return name;
	}
	public String getYear() {
		return year;
	}
	public String getMajor() {
		return major;
	}
	
	//set course array
	public void setCourses(String[] courses) {
		this.courses = courses.clone();
	}
	public void deleteConversation(String otherUser) {
		// TODO Auto-generated method stub
		inbox.remove(otherUser);		
	}
	
	
}
	
