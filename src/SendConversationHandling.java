import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

public class SendConversationHandling extends JSONMessageTypeHandler{
	private String otherUser, message;

	public String parseMessage(Iterator iter) {
		while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            String  key = (String) entry.getKey();
            String  value = (String) entry.getValue();

            //gets usernames and their message with where they wanna post it
            if (key.equals("username")){
            	username = value;
            }
            else if (key.equals("boardID")){
            	otherUser = value;
            }
            else if (key.equals("message")){
            	message = value;
            }
          }		
		
		
		return createMessage();
	}

	@Override
	public String createMessage() {
		//inserts a message into both users inboxs
		handleHome.serv.ulist.getUser(username).getConversation(otherUser).insert(username, message);
		handleHome.serv.ulist.getUser(otherUser).getConversation(username).insert(username, message);


		JSONObject obj = new JSONObject();
        obj.put("messageType",new String("sendConversationStatus"));
        //sends back true when messages are sent
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
