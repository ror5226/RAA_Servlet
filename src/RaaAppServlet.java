

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Servlet implementation class RaaAppServlet
 */
@WebServlet("/RaaAppServlet")
public class RaaAppServlet extends HttpServlet {
	CourseBoardList clist;
	UserList ulist;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RaaAppServlet() {
        super();
        // TODO Auto-generated constructor stub
        clist = new CourseBoardList();
        ulist = new UserList();
        //add courses at behrend
        clist.addClass("CMPEN 351");
        clist.addClass("SWENG 411");
        clist.addClass("CMPSC 122");
        clist.addClass("SWENG 311");
        clist.addClass("CMPEN 441");
        clist.addClass("ENG 202C");
        clist.addClass("CAS 100A");
        clist.addClass("CAS 100B");
        clist.addClass("PSYCH 100");
        clist.addClass("CMPSC 360");
        clist.addClass("CMPEN 270");

        //create an existing user
        ulist.registerUser("Bob2015", "bobby");
        ulist.getUser("Bob2015").setName("Robert");
        ulist.getUser("Bob2015").setMajor("Poetry");
        ulist.getUser("Bob2015").setYear("2020");
        String courses [] = new String[] {"SWENG311", "SWENG 411", "CMPSC 122", "CAS 100A", "CMPEN270"};
        ulist.getUser("Bob2015").setCourses(courses);
        


        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	       response.getOutputStream().println("Raa");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONHandler handle = new JSONHandler();
		handle.serv = this;
		//recieves a message from a connected device
        int length = request.getContentLength();
        byte[] input = new byte[length];
        ServletInputStream sin = request.getInputStream();
        int c, count = 0 ;
        while ((c = sin.read(input, count, input.length-count)) != -1) {
            count +=c;
        }
        sin.close();

        //converts devices message to string and prints out in console
        String recievedString = new String(input);
        response.setStatus(HttpServletResponse.SC_OK);
        OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
        System.out.println("in msg: " + recievedString);
        
        //converts input into a JSON and sends it to the JSON message handler
        String jsonText = handle.readMessage(recievedString);
        System.out.println("out msg " + jsonText);
        writer.write(jsonText);
        writer.flush();
        writer.close();


	}

}
