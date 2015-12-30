import java.util.List;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RefreshMessageHandling extends JSONMessageTypeHandler {
	String boardID; 

	public String parseMessage(Iterator iter) {
		 while(iter.hasNext()){
             Map.Entry entry = (Map.Entry)iter.next();
             String  key = (String) entry.getKey();
             String  value = (String) entry.getValue();

             if (key.equals("boardID")){
            	 boardID = value;
             }
         	}
		 return createMessage();
	}

	public String createMessage() {
		JSONObject obj = new JSONObject();
		String[] users = new String [100];
		String[] messages = new String [100];
		JSONArray usersArr = new JSONArray();
		JSONArray messagesArr = new JSONArray();

		//gets usernames and messages on a message board by board id
   		 users = (handleHome.serv.clist.getBoard(boardID).getUserNames()).clone();
   		 messages = (handleHome.serv.clist.getBoard(boardID).getMessages()).clone();

		//puts messages into array for the app to read
		for(int i = 0; i < messages.length; i++){
			 if (!(messages[i].equals("blank"))){
				 usersArr.add(users[i]);
				 messagesArr.add(messages[i]);
			 }
		 }
		//sends back users and messages array
        obj.put("messageType",new String("refreshBoard"));
        obj.put("usernamesArray", usersArr);
        obj.put("messagesArray", messagesArr); 
           
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
