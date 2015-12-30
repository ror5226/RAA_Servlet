import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

public class EditCourseHandling extends JSONMessageTypeHandler{

	private String[] courses = new String[5];

	public String parseMessage(Iterator iter) {
		 while(iter.hasNext()){
             Map.Entry entry = (Map.Entry)iter.next();
             String  key = (String) entry.getKey();
             String  value = (String) entry.getValue();

             
             if (key.equals("username")){
            	 	this.username = value.toString();
             }
             //grabs the 5 classes that the user is enrolled in
             else if (key.equals("class1")){
            	 	courses[0] = value;
             }
             else if (key.equals("class2")){
             	courses[1] = value;
             }
             else if (key.equals("class3")){
            	 	courses[2] = value;
             }
             else if (key.equals("class4")){
          	 	courses[3] = value; 
               }
             else if (key.equals("class5")){
            	 	courses[4] = value;
             }
             else {
                	//other message
                }
         	}
		 //updates the users courses with the ones sent to the server
 	 	handleHome.serv.ulist.getUser(username).setCourses(courses);
 	 	
		 return createMessage().toString();
	}

	public String createMessage() {
		JSONObject obj = new JSONObject();
        obj.put("messageType",new String("classEditStatus"));
        //sends back true to tell app that the courses were added successfully
        obj.put("status",new Boolean(true));
        
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
