import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConversationRefreshHandling extends JSONMessageTypeHandler{

	private String otherUser;

	public String parseMessage(Iterator iter) {
		while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            String  key = (String) entry.getKey();
            String  value = (String) entry.getValue();

            //gets the two users involved in the conversation
            if (key.equals("username")){
            	username = value;
            }
            else if (key.equals("otherUser")){
            	otherUser = value;
            }
            else {
               	//other message
               }
          }		
		
		return createMessage();
	}

	@Override
	public String createMessage() {
		
		JSONObject obj = new JSONObject();
        obj.put("messageType",new String("getConversationStatus"));
        
        obj.put("status",new Boolean(true));
        JSONArray convoArr = new JSONArray();
        //gets all of the messages in a conversation
        String[] conversation = handleHome.serv.ulist.getUser(username).getConversation(otherUser).getMessages().clone();
        
        
        
		//puts messaaes into array for the app to read
		for(int i = 0; i < conversation.length; i++){
			 if (!(conversation[i] == null)){
				 convoArr.add(conversation[i]);
			 }
		 }
		
		obj.put("conversation", convoArr);
		
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
