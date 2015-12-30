import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserList {
	//private map of users 
	private static HashMap <String, UserInfo> users = new HashMap<String, UserInfo>();
	
	public UserList(){
	}
	
	//checks if username/password is valid
	//return true if valid
	//return false if invalid
	boolean isUser(String username, String password){
		if (((users.get(username)).getPW()).equals(password)){
			return true;
		}
		 return false;
	}
	
	//checks if username is already used
	//return true if user name is taken
	//return false if user name is free
	boolean isUserName(String username){
		//parse thru map
		return users.containsKey(username);
	}
	
	//registers new user
	//return true if registered
	//return false if user name already exists
	boolean registerUser(String username, String password){
		
		if (!isUserName(username)){
			//add to map
			users.put(username, (new UserInfo(username, password)));
			return true;
		}
		else return false;
	}
	
	//returns user obj
	public UserInfo getUser(String username){
		return users.get(username);
	}

	//returns a listing of all other users that are in similar classes to the user requesting them 
	public ArrayList<String> getContactNames(String username) {
		ArrayList<String> names = new ArrayList<String>();
		Iterator it = users.entrySet().iterator();
        String[] userCourses = users.get(username).getCourses();

        //loops through all users
	    while (it.hasNext()) {
	        Map.Entry user = (Map.Entry)it.next();
	        String[] otherCourses = ((UserInfo)(user.getValue())).getCourses();
	        String tempUser = null;
	        
	        //loops through all usernames to check their couses to see if they have any in common
	        for(int i = 0; i < userCourses.length; i++){
	        	for(int j = 0; j < otherCourses.length; j++){
	        		if (otherCourses[j].equals(userCourses[i])){
	        			String addUser = (((UserInfo)(user.getValue())).getName());
	        			if(!((otherCourses[i].equals("Empty Course Board"))||(addUser.equals(tempUser))||(addUser.equals(getUser(username).getName())))){
	        			//adds username to arraylist of contacts
	        			names.add((String) (user.getKey()));
		        		tempUser = (((UserInfo)(user.getValue())).getName());
	        			}
	        		}
	        	}
	        }
	    }
		
		return names;
	}
}
