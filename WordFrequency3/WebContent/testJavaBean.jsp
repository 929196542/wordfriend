<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaBean</title>
</head>
<body>

<jsp:useBean id="word" class="edu.hui.Word" scope="page">
	<jsp:setProperty name="word" property="count" value="10" />
</jsp:useBean> 

<% word.setWord("APPLE"); %>

<p>Word: <%= word.getWord() %></p>

<p>
	Count: <jsp:getProperty name="word" property="count" />
</p>


</body>
</html>