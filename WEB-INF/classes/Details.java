import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;

public class Details extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		
		PrintWriter output = response.getWriter();
		
		output.println("Search Started");
		output.println("       ");

		try{

			//Get the values from the form
			String fname = request.getParameter("fname");
			String mname = request.getParameter("mname");
			String lname = request.getParameter("lname");
			String mail = request.getParameter("mail");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			String Occ = request.getParameter("Occ");
			String Comp = request.getParameter("Comp");
			String Hob = request.getParameter("Hob");
			String Boo = request.getParameter("Boo");
			String Musc = request.getParameter("Musc");	
			String OtherFav = request.getParameter("OtherFav");
			String Pla = request.getParameter("Pla");
			String Coun = request.getParameter("Coun");
			String Thin = request.getParameter("Thin");	

			
			MongoClient mongo = new MongoClient("localhost", 27017);


			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("Friends");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myOrders = db.getCollection("FriendList");
			System.out.println("Collection myOrders selected successfully");
				
			BasicDBObject doc = new BasicDBObject("title", "Friends").
				append("fname", fname).
				append("mname", mname).
				append("lname", lname).
				append("mail", mail).
				append("city", city).
				append("state", state).
				append("country", country).
				append("Occ", Occ).
				append("Comp", Comp).
				append("Hob", Hob).
				append("Boo", Boo).
				append("Musc", Musc).
				append("OtherFav", OtherFav ).
				append("Pla", Pla).
				append("Coun", Coun).
				append("Thin",Thin);


			myOrders.insert(doc);
	
			System.out.println("Document inserted successfully");
			
			//Send the response back to the JSP
			PrintWriter out = response.getWriter();

			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("title", "Friends");

			DBCursor cursor = myOrders.find(searchQuery);
			output.println("Printing Values");
			output.println(cursor);

			out.println("<h1> Hello "+fname+" Nice to Meet You </h1>");
			out.println("");
			

			out.println("<html>");
			out.println("\n");
			out.println("<head> <h1>All Friends of Sarath !! </h1> </head>");
			out.println("<body>");

			out.println("<head> You Have Been Added to Friend List of Sarath Kumar !!  </head>");


			while (cursor.hasNext()) {

					out.println("<table border='1'>");

					BasicDBObject obj = (BasicDBObject) cursor.next();	

					out.println("<tr>");
					out.println("<td> First Name: </td>");
					String fname1 = obj.getString("fname");
					out.println("<td>" +fname1+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Last Name: </td>");
					String lname1 = obj.getString("lname");
					out.println("<td>" +lname1+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Mail Address: </td>");
					String mail1 = obj.getString("mail");
					out.println("<td>" +mail1+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Occupation: </td>");
					String Occ1 = obj.getString("Occ");
					out.println("<td>" +Occ1+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Residing City : </td>");
					String city1 = obj.getString("city");
					out.println("<td>" +city1+ "</td>");
					out.println("</tr>");

					out.println("</table>");
					out.println("</body>");
					out.println("</html>"); 

			}

	    } catch (MongoException e) {
		e.printStackTrace();
	    }

	}
	
}