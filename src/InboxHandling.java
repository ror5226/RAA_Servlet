import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class InboxHandling extends JSONMessageTypeHandler{
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
        obj.put("messageType",new String("getInboxStatus"));
       
       	obj.put("status",new Boolean(true));
		JSONArray inboxArr = new JSONArray();
		JSONArray messageArr = new JSONArray();

		//gets an array list of the names that this user is conversing with
       	ArrayList <String> names = handleHome.serv.ulist.getUser(username).getConversationNames();
        for (int i = 0; i < names.size(); i++)
       	{
        	//adds the names to the array
       		inboxArr.add(names.get(i));
            String[] conversation = handleHome.serv.ulist.getUser(username).getConversation(names.get(i)).getMessages().clone();
            for (int j = 0; j < conversation.length; j++){
            	if(conversation[j] == null){
            		if(j != 0){
                    messageArr.add(conversation[j-1]);}
            		break;
            	}
            }
       	}
        //adds JSON array to JSON
       	obj.put("conversations", inboxArr);
       	obj.put("lastMessage", messageArr);
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
