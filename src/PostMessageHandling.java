import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

public class PostMessageHandling extends JSONMessageTypeHandler{
	private String boardID, message;

	public String parseMessage(Iterator iter) {
		 while(iter.hasNext()){
             Map.Entry entry = (Map.Entry)iter.next();
             String  key = (String) entry.getKey();
             String  value = (String) entry.getValue();

             //gets username, message, and board id to post on
             if (key.equals("username")){
            	   username = value;
              }
              else if (key.equals("boardID")){
              	   boardID = value;
              }
              else if (key.equals("message")){
             	   message = value;
              }
         	}

		    //inserts a message onto the message board
		 	handleHome.serv.clist.getBoard(boardID).insert(username, message);
		 	return createMessage();
	}

	public String createMessage() {
		JSONObject obj = new JSONObject();
		
        obj.put("messageType",new String("postBoardStatus"));
        //sends back true to tell device that message was posted successfully
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
