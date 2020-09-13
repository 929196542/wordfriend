<%@ page language="java" import="java.sql.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search a word and get its frequency</title>
    </head>
    
    <body>


<jsp:include page="header.html" />

<% Class.forName("org.sqlite.JDBC"); %>

<form method="get"> 
    Please type a word:
    <input type="text" name="q" />
    <input type="submit" name="submit-button" value="Search"/> 
</form>

<% String q = request.getParameter("q");  
   if (q != null) { %>
   <p>Search results for <b><%= q %></b></p>
   <%= runQuery(q) %>
<% } else { %> 
	<p>Everything in the database:</p>
	<%= runQuery("") %>
<% } %> 


<%
	if (q == null)   
		out.println("<p>You entered nothing.</p>");
	else if (q.strip().equals("")) 
      	out.println("<p>You entered an empty string.</p>"); 
	else
		out.println(String.format("<p>You entered <b>%s</b>.</p>", q));
%>



<%-- Declare and define the runQuery() method. --%>
<%-- https://docs.oracle.com/cd/A87860_01/doc/java.817/a83726/basics7.htm#1014578 --%>
<%! private String runQuery(String cond) throws SQLException {
     Connection conn = null; 
     Statement stmt = null; 
     ResultSet rset = null; 
     try {
        conn = DriverManager.getConnection("jdbc:sqlite:wordfriend.db");
        stmt = conn.createStatement();
        // dynamic query
        rset = stmt.executeQuery (String.format("SELECT * FROM wordfriend WHERE content LIKE '%%%s%%' ORDER BY frequency desc", cond));
       return (formatResult(rset));
     } catch (SQLException e) { 
         return ("<P> SQL error: <PRE> " + e + " </PRE> </P>\n ");
     } finally {
         if (rset!= null) rset.close(); 
         if (stmt!= null) stmt.close();
         if (conn!= null) conn.close();
     }
  }

  private String formatResult(ResultSet rset) throws SQLException {
    StringBuffer sb = new StringBuffer();
    if (!rset.next())
      sb.append("<P> No matching rows.<P>\n");
    else {  sb.append("<style>td{text-align:center}</style><table border=1><tr><td>Content</td><td>Frequency</td><td>Level</td><td>Level Edit</td></tr>"); 
            do {  sb.append("<tr><td>" + rset.getString("content") + 
                            "</td><td>" + rset.getInt("frequency") + "</td><td>" + rset.getInt("level") + "</td><td>" + String.format("<a href=\"./delete.jsp?deleteID=%d\">DELETE</a>", rset.getInt("id")) +   
                           String.format("<form method=\"post\" action=\"./update-level\"> Difficulty level: <input type=\"hidden\" name=\"updateID\" value=\"%d\"><input type=\"radio\" name=\"rating\" value=\"1\">1 <input type=\"radio\" name=\"rating\" value=\"2\">2 <input type=\"radio\" name=\"rating\" value=\"3\">3 <input type=\"submit\" value=\"Update\"/> </form>" + "</BR></tr>\n", rset.getInt("id")));
            } while (rset.next());
           sb.append("</table>"); 
    }
    return sb.toString();
  }
%>

</body>
</html>