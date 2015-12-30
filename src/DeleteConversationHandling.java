import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DeleteConversationHandling extends JSONMessageTypeHandler {
private String otherUser;
	
	public String parseMessage(Iterator iter) {
		 while(iter.hasNext()){
           Map.Entry entry = (Map.Entry)iter.next();
           String  key = (String) entry.getKey();
           String  value = (String) entry.getValue();

           //gets two users involved with conversation
           if (key.equals("username")){
          	 	this.username = value.toString();
           }
           else if (key.equals("otherUser")){
         	 	this.otherUser = value.toString();
           }
           else {
           	//other message
           }
       	}
		 	 	
		 return createMessage().toString();
	}
	public String createMessage() {
		JSONObject obj = new JSONObject();
		
		//creates a conversation obj for both users
		handleHome.serv.ulist.getUser(username).deleteConversation(otherUser);
		handleHome.serv.ulist.getUser(otherUser).deleteConversation(username);

        obj.put("messageType",new String("deleteConversationStatus"));
      	obj.put("status",new Boolean(true));
		JSONArray inboxArr = new JSONArray();

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
