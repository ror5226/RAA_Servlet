import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ProfileHandling extends JSONMessageTypeHandler{
	String selected; 
	public String parseMessage(Iterator iter) {
		 while(iter.hasNext()){
           Map.Entry entry = (Map.Entry)iter.next();
           String  key = (String) entry.getKey();
           String  value = (String) entry.getValue();
           //gets current user and user they wish to see a profile of
           if (key.equals("username")){
        	   this.username = value;
           }
           if (key.equals("selectedUsername")){
          	 	this.selected = value.toString();
           }
           else {
           	//other message
           }
       	}
		 return createMessage().toString();
	}

	public String createMessage() {
		JSONObject obj = new JSONObject();
       obj.put("messageType",new String("getProfileStatus"));
      
      	obj.put("status",new Boolean(true));
      	//sends back desired users profile info 
      	obj.put("username", selected);
		obj.put("name", handleHome.serv.ulist.getUser(selected).getName());
		obj.put("major", handleHome.serv.ulist.getUser(selected).getMajor());
		obj.put("year", handleHome.serv.ulist.getUser(selected).getYear());
		
		String [] courses = handleHome.serv.ulist.getUser(selected).getCourses();
		//sends courses that user is enrolled in
		obj.put("course1", courses[0]);
		obj.put("course2", courses[1]);
		obj.put("course3", courses[2]);
		obj.put("course4", courses[3]);
		obj.put("course5", courses[4]);
		//sends profile info
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
