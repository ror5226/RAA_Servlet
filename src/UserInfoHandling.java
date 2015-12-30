import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

public class UserInfoHandling extends JSONMessageTypeHandler{
	private String name, year, major;

	//gets user's name, year and major and sets them in the user obj
	public String parseMessage(Iterator iter) {
		while(iter.hasNext()){

            Map.Entry entry = (Map.Entry)iter.next();
            String  key = (String) entry.getKey();
            String  value = (String) entry.getValue();
            
            //gets profile info from a user
            if (key.equals("username")){
                username = value;
             }
            if (key.equals("name")){
               name = value;
            }
            else if (key.equals("year")){
           	   year = value;
            }
            else if (key.equals("major")){
               major = value;
            }
        	}
		
		//gets user obj and sets data
		handleHome.serv.ulist.getUser(username).setName(name);
		handleHome.serv.ulist.getUser(username).setYear(year);
		handleHome.serv.ulist.getUser(username).setMajor(major);
		return createMessage();
	}
	
	//creates a json to return true if data entry is sucessfull
	public String createMessage() {
		JSONObject obj = new JSONObject();
        obj.put("messageType",new String("userInfoStatus"));
        //sends back true when user info is saved
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
