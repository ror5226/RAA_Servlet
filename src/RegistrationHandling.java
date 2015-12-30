import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

public class RegistrationHandling extends JSONMessageTypeHandler {

	private String password;
	
	//gets username and pw to register 
	public String parseMessage(Iterator iter) {
		while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            String  key = (String) entry.getKey();
            String  value = (String) entry.getValue();

            //gets a new username and password
            if (key.equals("username")){
            	username = value;
            }
            else if (key.equals("password")){
            	password = value;
            }
          }		
		//checks to see if username is already taken 
		status = handleHome.serv.ulist.isUserName(username);
		if (!status){
			handleHome.serv.ulist.registerUser(username, password);
		}
		return createMessage();
	}

	//creates a json message to send back
	//sends status of true if user is registered successfully, false if not
	public String createMessage() {

		JSONObject obj = new JSONObject();
        obj.put("messageType",new String("registrationStatus"));
        
        if (!status){
        	obj.put("status",new Boolean(true));
        	//gets users courses and sends them to device 
        	String [] courses = handleHome.serv.ulist.getUser(username).getCourses().clone();
        //all courses will contain the message black course since user is new
    	obj.put("course1", courses[0]);
        obj.put("course2", courses[1]);
        obj.put("course3", courses[2]);
        obj.put("course4", courses[3]);
        obj.put("course5", courses[4]);
        
        }
        else{
            obj.put("status",new Boolean(false));
        }
        StringWriter out = new StringWriter();
        try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return out.toString();	
	}

}
