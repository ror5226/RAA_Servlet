import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GetContactsHandling extends JSONMessageTypeHandler{
	public String parseMessage(Iterator iter) {
		 while(iter.hasNext()){
           Map.Entry entry = (Map.Entry)iter.next();
           String  key = (String) entry.getKey();
           String  value = (String) entry.getValue();

           if (key.equals("username")){
          	 	this.username = value.toString();
           }
           else {
           	//other message
           }
       	}
		 	 	
		 return createMessage().toString();
	}

	public String createMessage() {
		JSONObject obj = new JSONObject();      
		
		obj.put("messageType",new String("getContactsStatus"));
		//sends true if it successfully recieved contacts
      	obj.put("status",new Boolean(true));
		JSONArray inboxArr = new JSONArray();

		//gets an array list of the names that this user has classes with
      	ArrayList <String> names = handleHome.serv.ulist.getContactNames(username);
      	System.out.println(names.size());
       for (int i = 0; i < names.size(); i++)
      	{
       	//adds the names to the array
      		inboxArr.add(names.get(i));
      	}
      	
       //adds JSON array to JSON
      	obj.put("contacts", inboxArr);
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
