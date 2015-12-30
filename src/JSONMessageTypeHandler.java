import java.util.Iterator;

//abstract class that all message handlers inherit
public abstract class JSONMessageTypeHandler {

	//contain username, status, and a JSON Handler obj
	protected String username;
	JSONHandler handleHome;
	protected Boolean status = false;

	abstract String parseMessage(Iterator iter);
	abstract String createMessage();
}
