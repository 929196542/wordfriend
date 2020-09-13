import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.hui.Frequency;

/**
 * Servlet implementation class ShowArticleContent
 */
@WebServlet("/ShowArticleContent")
public class ShowArticleContent extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public static Boolean isObjectNotEmpty(Object obj) 
	{
        String str = ObjectUtils.toString(obj, "");
        return StringUtils.isNotBlank(str);
    }
    public ShowArticleContent() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out= response.getWriter(); 
		out.println("Working in doGet");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		// doGet(request, response);
		String content = request.getParameter("content").strip();
		PrintWriter out = response.getWriter(); 
		
		if (content == "") 
		{
			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}

		
		//FIXME
		

		
		

		String [] words = content.split(" "); // split string into words. Does not work well if there is more than one space between words.
		//PrintWriter out = response.getWriter(); 
		out.println("<html>");
		out.println("<p><a href=\"index.jsp\">Home</a></p>");
		//out.println("<h2>The current article</h2>");
		//out.println("<p>Total words:" + words.length + "</p>");
		if (content != null) 
		{
			out.println("<h2>Word frequncy ...</h2>");
		//	out.println("<p><font size=+2>" + content + "</font></p>");
			Frequency freq = new Frequency(content);
//			HashMap<String, Integer> m = new HashMap<String, Integer>();
//			m = freq.getFreq();
//		    int x = m.size();
//			for (int num = 0; num < x; num++)
			//out.println(freq.sortHashMapByValues(freq.getFreq()));
			Iterator<Map.Entry<String,Integer>> iterator= freq.sortHashMapByValues(freq.getFreq()).entrySet().iterator();
			out.println("<style>td{text-align:center}</style>");
			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<td>Word</td>");
			out.println("<td>Frequency</td>");
			out.println("<td>Unfamiliarity</td>");
			out.println("</tr>");
			
			// TODO
			while(iterator.hasNext())
			{
				
				Map.Entry<String,Integer> entry = iterator.next(); 
				out.println("<tr>");
			    out.println("<td>"+entry.getKey()+"</td><td>"+entry.getValue()+"</td><td></td>"+"</tr>");
			}
			out.println("</table>");
		}
		
		// Save article and word frequency into SQLite database

		Connection  con = null;
		try 
		{
			try 
			{
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			
			if (content == "") 
			{
				ServletContext sc = this.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}

			
			//FIXME
			
			
			con = DriverManager.getConnection("jdbc:sqlite:wordfriend.db");
			Statement statement = con.createStatement();
			// TODO
			statement.executeUpdate("create table if not exists wordfriend (id integer PRIMARY KEY autoincrement, content string, frequency integer, level integer DEFAULT 0)");
			PreparedStatement statement1 = con.prepareStatement("insert into wordfriend(content, frequency) values(?, ?)"); 
			// TODO 
			Frequency freq = new Frequency(content);
			Iterator<Map.Entry<String,Integer>> iterator= freq.sortHashMapByValues(freq.getFreq()).entrySet().iterator();
			while(iterator.hasNext())
			{
				Map.Entry<String,Integer> entry = iterator.next(); 
				statement1.setString(1, entry.getKey());
				statement1.setInt(2, entry.getValue());
				statement1.executeUpdate();
			}
			/*	
			String sql2 = "select content from wordfriend";
			String sql3 = "select frequency from wordfriend";
			ResultSet wordlist = statement.executeQuery(sql2);
			ResultSet frequencylist = statement.executeQuery(sql3);
			
			out.println("1");
			
				
				out.println("2");
				Map.Entry<String,Integer> entry = iterator.next(); 
				while (wordlist.next())
				{
					out.println("3");
					if (wordlist.getString("content").equals(entry.getKey()))
					{
						out.println("4");
						int x = frequencylist.getInt("frequency");
						String sql1 = "update wordfriend set frequency = '"+(entry.getValue()+x)+"' where content = '"+entry.getKey()+"'";
						statement.executeUpdate(sql1);
					}
					else
					{	
						out.println("5");
						statement1.setString(1, entry.getKey());
						statement1.setInt(2, entry.getValue());
						statement1.executeUpdate();
					}
				}
			}
				
				
		*/		
				
				
				
				
				
			/*	while(fre.next())
				{
					out.println(fre.getString("content"));
					out.println(entry.getKey());
					if(fre.getString("content")!=entry.getKey()) {
						out.println("20");
						statement1.setString(1, entry.getKey());
						statement1.setInt(2, entry.getValue());
						statement1.executeUpdate();
						out.println("2");
					}
					else {
						out.println("30");
						int x = fre.getInt("frequency");
						String sql1 = "update wordfriend set frequency = '"+(entry.getValue()+x)+"' where content = '"+entry.getKey()+"'";
						int a = statement.executeUpdate(sql1);
						out.println("3");
					}
				}
			*/	
				
				
				//statement1.setString(1, entry.getKey());
				//statement1.setInt(2, entry.getValue());
				//statement1.executeUpdate();
			////o
			
			out.println("<h2>Historical articles</h2>");
			ResultSet rs = statement.executeQuery("select * from wordfriend order by frequency desc");
			out.println("<table border=\"1\">");
			out.println("<tr>");
			out.println("<td>Word</td>");
			out.println("<td>Frequency</td>");
			out.println("<td>Unfamiliarity</td>");
			out.println("</tr>");
			while (rs.next()) 
			{
				out.println("<p><tr><td>" + rs.getString("content") + "</td><td>" + rs.getInt("frequency") + "</td><td>" + rs.getInt("level") + "</td></tr></p>");
			}
			out.println("</table>");
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		out.println("</html>");
	}

}
