<%@ page language="java" contentType="text/html; charset=UTF-8" import="edu.hui.Frequency"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test JavaBean</title>
</head>
<body>

	<h1>
	We can use a user-defined class in JSP ...  
	</h1>


<%	

	String content = request.getParameter("content");
	if (content != null) {
		out.println("<h2>Word frequency ...</h2>");
		Frequency freq = new Frequency(content);
		out.println(freq.sortHashMapByValues(freq.getFreq()));	
	
	
		
	}
%>

	<form method="post" action="./testUserDefinedClass.jsp">
		<textarea name="content" rows="20" cols="120"></textarea>
		<br /> <input type="submit" name="submit-button" value="Count words" />
		<input type="reset" name="clear-button" value="Clear content" />
	</form>

</body>
</html>