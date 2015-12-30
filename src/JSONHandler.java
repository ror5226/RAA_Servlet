import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONHandler {
	JSONParser parser = new JSONParser();
    RaaAppServlet serv;
    ContainerFactory containerFactory = new ContainerFactory(){
        public List creatArrayContainer() {
          return new LinkedList();
        }

        public Map createObjectContainer() {
          return new LinkedHashMap();
        }
                            
      };
       
     //read in message and determine its type
     String readMessage(String recievedString){
    	 try{
             Map json = (Map)parser.parse(recievedString, containerFactory);
             Iterator iter = (json).entrySet().iterator();
             Iterator temp = (json).entrySet().iterator();

             while(temp.hasNext()){
               Map.Entry entry = (Map.Entry)temp.next();
               String  key = (String) entry.getKey();
               String  value = (String) entry.getValue();
               if (key.equals("messageType")){

            	   //gets the message type of the JSOn sent and sends the remainder of the message to the correct message handler class
            	  //login
            	   if(value.equals("loginMessage")){
            		   LoginHandling lh = new LoginHandling();
            		   lh.handleHome = this;
            		   return lh.parseMessage(iter);
            	   }
            	   //register new user
            	   else if(value.equals("registrationMessage")){
            		   RegistrationHandling rh = new RegistrationHandling();
                	   rh.handleHome = this;
                	   return rh.parseMessage(iter);
            	   }
            	   //profile info update
            	   else if(value.equals("userInfoMessage")){
            		   UserInfoHandling uh = new UserInfoHandling();
            		   uh.handleHome = this;
            		   return uh.parseMessage(iter);
               	   }
            	   //class enrolled update
            	   else if(value.equals("editClassesMessage")){
            		   EditCourseHandling eh = new EditCourseHandling();
                	   eh.handleHome = this;
            		   return eh.parseMessage(iter);
                   }
            	   //refresh a board
            	   else if(value.equals("refreshBoardMessage")){
            		   RefreshMessageHandling reh = new RefreshMessageHandling();
                	   reh.handleHome = this;
            		   return reh.parseMessage(iter);
                   }
            	   //post to a board
            	   else if(value.equals("postBoardMessage")){
            		   PostMessageHandling poh = new PostMessageHandling();
                	   poh.handleHome = this;
            		   return poh.parseMessage(iter);
                   }
            	   //get inbox 
            	   else if(value.equals("getInboxMessage")){
            		   InboxHandling ih = new InboxHandling();
                	   ih.handleHome = this;
            		   return ih.parseMessage(iter);
                   }
            	   //get profile of a user
            	   else if(value.equals("getProfileMessage")){
            		   ProfileHandling ph = new ProfileHandling();
                	   ph.handleHome = this;
            		   return ph.parseMessage(iter);
                   }
            	   //get a user's contacts
            	   else if(value.equals("getContactsMessage")){
            		   GetContactsHandling gh = new GetContactsHandling();
                	   gh.handleHome = this;
            		   return gh.parseMessage(iter);
                   }
            	   //create a new conversation
            	   else if(value.equals("newConversationMessage")){
            		   NewConversationHandling nh = new NewConversationHandling();
                	   nh.handleHome = this;
            		   return nh.parseMessage(iter);
                   }
            	   //get a conversation's messages
            	   else if(value.equals("getConversationMessage")){
            		   ConversationRefreshHandling ch = new ConversationRefreshHandling();
                	   ch.handleHome = this;
            		   return ch.parseMessage(iter);
                   }
            	   //send a message to a user in a conversation
            	   else if(value.equals("sendConversationMessage")){
            		   SendConversationHandling sch = new SendConversationHandling();
                	   sch.handleHome = this;
            		   return sch.parseMessage(iter);
                   }
            	   else if(value.equals("deleteConversationMessage")){
            		   DeleteConversationHandling dh = new DeleteConversationHandling();
                	   dh.handleHome = this;
            		   return dh.parseMessage(iter);
                   }
            	   else{
            		   //not a valid message
            		   return null;
            	   }
            	   
               }
             }
             
           }
           catch(ParseException pe){
             System.out.println(pe);
           }
		return recievedString;
      }
   
   
}
