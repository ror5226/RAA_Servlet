import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;


public class LoginHandling extends JSONMessageTypeHandler{

	private String password;

	public String parseMessage(Iterator iter) {
		while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            String  key = (String) entry.getKey();
            String  value = (String) entry.getValue();

            //gets username and password
            if (key.equals("username")){
            	username = value;
            }
            else if (key.equals("password")){
            	password = value;
            }
          }		
		
		//checks to see if login is valid
		status = handleHome.serv.ulist.isUser(username, password);
		return createMessage();
	}

	@Override
	public String createMessage() {
		
		JSONObject obj = new JSONObject();
        obj.put("messageType",new String("loginStatus"));
        
        //returns true or false depending on if the login was valid or not
        if (status){
        	obj.put("status",new Boolean(true));
        		String [] courses = handleHome.serv.ulist.getUser(username).getCourses();
        		obj.put("course1", courses[0]);
        		
        	//sends courses to the user so that their message boards will be available
            obj.put("course2", courses[1]);
            obj.put("course3", courses[2]);
            obj.put("course4", courses[3]);
            obj.put("course5", courses[4]);
            //sends profile info
            obj.put("name", handleHome.serv.ulist.getUser(username).getName());
            obj.put("year", handleHome.serv.ulist.getUser(username).getYear());
            obj.put("major", handleHome.serv.ulist.getUser(username).getMajor());

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
