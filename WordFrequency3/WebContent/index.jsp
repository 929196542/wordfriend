<%@page import="edu.hui.FunUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Word Frequency -- old and new</title>
</head>
<body>

	<%-- <p>JSP-Servlet interaction?  Interesting.  How does it compare to Flask?</p> --%>

	<h1>
	Welcome to the Home Page for <%= FunUtils.toLowCase("word friend") %>
	</h1>

	<% out.println("Paste an English article below or <a href=search.jsp>search articles for a word</a>."); %>


	<form method="post" action="./display">
		<textarea name="content" rows="20" cols="120"></textarea>
		<br /> <input type="submit" name="submit-button" value="Count words" />
		<input type="reset" name="clear-button" value="Clear content" />
	</form>

	<p>
		<font color="grey">Current date: <%=new java.util.Date()%></font>
	</p>

</body>
</html>